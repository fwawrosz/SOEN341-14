package reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import token.Position;
import token.Token;
import token.TokenType;

public class Scanner implements IScanner {
	
	private static final int EOF = -1;
	private StringBuilder tokenName;
	private ArrayList<Token> tokenList;
	private boolean inside_comment = false;	//flag that determines whether we're in a comment, i.e. whether to ignore everything past a certain point
	private Position pos;	//variable keeping track of current position
	
	public int getEOF() {return Scanner.EOF;}
	
	private void createMnemonicList(FileInputStream reader){
		
		int i;
		this.tokenName = new StringBuilder();
		this.tokenList = new ArrayList<Token>();	//sequence of tokens, which will be used by the parser
		pos = new Position();
		boolean is_mnemonic = false;
		
		try {
			
			while ((i = reader.read()) != Scanner.EOF)
			{
				
				//check first character to determine whether we have a label
				if (pos.getColumnNumber() == 0)
				{
					
					if((char) i == ';' || inside_comment) {
						
						//if end of comment
						if((char) i == '\n') {
							inside_comment = false;
							
							pos.resetColumnNumber();
													
							//Add comment from beginning of line to the tokenList
							tokenList.add(new Token(new Position(pos.getLineNumber(),0), this.tokenName.toString(), TokenType.Comment));
							
							this.tokenName.setLength(0);
							
							//add EOL at end of line
							tokenList.add(new Token(new Position(pos.getLineNumber(), pos.getColumnNumber()), "EOL?", TokenType.EOL));
							pos.incLineNumber();
							
						}else {
							//if comment is in first column, save it all
							inside_comment = true;
							if((char) i != '\n') {
								this.tokenName.append((char) i);	
							}
						}
						continue;
					}
					
					if((char) i == '\n' && !inside_comment) {
						
						//add EOL at end of line
						tokenList.add(new Token(new Position(pos.getLineNumber(), pos.getColumnNumber()), "EOL", TokenType.EOL));
						pos.incLineNumber();	//we're going to the next line
						continue;	//move on, nothing else to do
					}
					
					//if it's a whitespace, there's no label
					if((char) i == ' ' || (char) i == '\t')
					{
						pos.incColumnNumber();	//we're going to the next line
						continue;	//move on, nothing else to do
					}
					
					//if it's a letter, chances are it's a label
					else if(Character.isLetter((char)i))	{}	//do nothing for now; we're not dealing with labels yet
					
					
					//if it's anything else (number, etc.), it's probably an error
					else {}		//report error; not coded yet
				}
					
				if((char)i == ';' || ((char)i == '\n' && !inside_comment)) {
					
					//the next character will be the first character of a line
					pos.resetColumnNumber();
					
					//this might need to be reformatted/moved later on,
					//as mnemonics will not be at line ends
					if(!this.tokenName.isEmpty()) {
		
						//add mnemonic to the token list
						this.tokenList.add(new Token(new Position(pos.getLineNumber(),0),this.tokenName.toString(),TokenType.Mnemonic));
						
					}
					
					this.tokenName.setLength(0);	
					
					if((char)i == '\n') {
						//add EOL at end of line
						tokenList.add(new Token(new Position(pos.getLineNumber(), pos.getColumnNumber()), "EOL", TokenType.EOL));
						pos.incLineNumber();
					}
				}
				
				
				//check for a semicolon to start the beginning of a comment
				if ((char)i == ';' || inside_comment)
				{
					if((char)i == ';') {
						pos.incColumnNumber();
						inside_comment = true;
						this.tokenName.append((char) i);
					}
					
					else if((char)i == '\n') {
						inside_comment = false;
						pos.resetColumnNumber();
						
						//add comment from end of line to token list
						this.tokenList.add(new Token(new Position(pos.getLineNumber(),0), this.tokenName.toString(), TokenType.Comment));
						
						this.tokenName.setLength(0);
						
						
						//add EOL at end of line
						tokenList.add(new Token(new Position(pos.getLineNumber(), pos.getColumnNumber()), "EOL", TokenType.EOL));
						pos.incLineNumber();
					}
					
					else {
						if((char)i != '\n') {
							this.tokenName.append((char) i);
						}
					}
				}
				
				//determine what to do based on characters
				//skip whitespaces
				else if(((char) i == ' ' || (char) i == '\t'))
				{
					is_mnemonic = false;
					pos.incColumnNumber();
					continue;	//move on, nothing else to do
				}
								
				else if(Character.isLetter((char) i) || is_mnemonic)
				{
					is_mnemonic = true;
					this.tokenName.append((char) i);
				}
				
				
				//for numbers-- do nothing yet
				else if ( Character.isDigit((char)i) )	{ } 
				
				//for dots-- do nothing yet
				else if ((char)i == '.')				{ } 
				
			}
			
			//add an EOF token since we're done with the file
			tokenList.add(new Token(new Position(pos.getLineNumber(), pos.getColumnNumber()), "EOF", TokenType.EOF));
			
		} catch (IOException e) {
			System.out.println("Could not read the file");
		}
			
	}
	
	
	private void createTokenList(){
		
		SrcReader readFile = new SrcReader();
		createMnemonicList(readFile.ReadSourceFile("TestImmediate.asm"));
		readFile.closeStream();
		
	}
	
	private ArrayList<Token> getTokenList(){ //should set this to private
		this.createTokenList();
		return this.tokenList;
	}
	
	public Token getTokenAt(int index){
		return this.getTokenList().get(index);
	}
	
	public int getNumberOfToken() {
		return this.getTokenList().size();
	}
	

	
	
	
	
	
	
	
	
	

	
	
}
