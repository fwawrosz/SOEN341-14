package reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import token.Position;
import token.Token;
import token.TokenType;

import errorReporting.ErrorReporter;
import errorReporting.ErrorMessage;

public class Scanner implements IScanner {
	
	public static ErrorReporter errrep = new ErrorReporter();
	private static final int EOF = -1;
	private StringBuilder tokenName;
	private ArrayList<Token> tokenList;
	private boolean inside_comment = false;	//flag that determines whether we're in a comment, i.e. whether to ignore everything past a certain point
	private Position pos;	//variable keeping track of current position
	private boolean insidecstring = false; //flag that determines whether we're inside a cstring
	
	public int getEOF() {return Scanner.EOF;}
	
	private void createMnemonicList(FileInputStream reader){
		
		int i;
		this.tokenName = new StringBuilder();
		this.tokenList = new ArrayList<Token>();	//sequence of tokens, which will be used by the parser
		pos = new Position();
		boolean is_mnemonic = false;
		boolean is_number = false;
		boolean is_directive = false;
		boolean is_directiveoperand = false;
		
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
						pos.incColumnNumber();	//we're going to the next column
						continue;	//move on, nothing else to do
					}
					
					//if it's a letter, chances are it's a label
					else if(Character.isLetter((char)i))	{}	//do nothing for now; we're not dealing with labels yet
					
					
					//if it's anything else (number, etc.), it's probably an error
					else { errrep.record(new ErrorMessage("filename", new Position(pos.getLineNumber(), 0), "Invalid character.")); }		//report error
				}
					
				if((char)i == ';' || ((char)i == '\n' && !inside_comment)) {
					
					//the next character will be the first character of a line
					pos.resetColumnNumber();
					
					//this might need to be reformatted/moved later on,
					//as mnemonics will not be at line ends
					if(this.tokenName.length()!=0) {
		
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
					
						if(this.tokenName.length()!=0) {
						
						//add mnemonic to the token list
						if(is_mnemonic)
						{
							this.tokenList.add(new Token(new Position(pos.getLineNumber(),0),this.tokenName.toString(),TokenType.Mnemonic));
						}
						
						if(is_number)
						{
							this.tokenList.add(new Token(new Position(pos.getLineNumber(),0),this.tokenName.toString(),TokenType.Number));
						}
						
						if(is_directive)
						{
							this.tokenList.add(new Token(new Position(pos.getLineNumber(),0),this.tokenName.toString(),TokenType.Directive));
							if (!this.tokenName.equals(".cstring"))
							{
								errrep.record(new ErrorMessage("filename", new Position(pos.getLineNumber(), 0), "Invalid character."));
							}
						}
					}
					
					this.tokenName.setLength(0);	
					
					
					is_mnemonic = false;
					is_number = false;
					is_directive = false;
					
					pos.incColumnNumber();
					continue;	//move on, nothing else to do
				}
								
				//if we have a letter, or if we're inside a mnemonic, but if we are not inside a directive
				else if((Character.isLetter((char) i) || is_mnemonic) && !(is_directiveoperand) && !(is_directive))
				{
					is_mnemonic = true;
					this.tokenName.append((char) i);
				}
				
				
				//for numbers: parse as token
				else if ( (Character.isDigit((char)i) || is_number) && !(is_directiveoperand) && !(is_directive))
				{
					is_number = true;
					this.tokenName.append((char) i);
				} 
				
				//if we have a dot, or we're inside a directive
				else if ((char)i == '.' || is_directive)
				{
					is_directive = true;
					this.tokenName.append((char) i);
				}
				
				else if ((char)i == '"')
				{
					//if we're already in a directive string
					//the second quotation mark means we want to end everything
					if(is_directiveoperand)
					{
						this.tokenName.append((char) i);
						this.tokenList.add(new Token(new Position(pos.getLineNumber(),0),this.tokenName.toString(),TokenType.DirectiveString));
						this.tokenName.setLength(0);
						is_directiveoperand = false;
						continue;
					}
					
					//otherwise, this signifies the start of a string
					is_directiveoperand = true;
					this.tokenName.append((char) i);
					
				}
				
				//check for EOL in the string
				else if (((char)i == '\n' || (char)i == '\r') && is_directiveoperand)
				{
					errrep.record(new ErrorMessage("filename", new Position(pos.getLineNumber(), 0), "End of Line detected inside a string."));
				}
				
				//we can append anything in a string (might need extra check for 8-bit ASCII)
				else if(is_directiveoperand)
				{
					this.tokenName.append((char) i);
				}
				
				//for any other character, it's probably invalid
				else { errrep.record(new ErrorMessage("filename", new Position(pos.getLineNumber(), 0), "Invalid character.")); }
			}
			
			//add an EOF token since we're done with the file
			tokenList.add(new Token(new Position(pos.getLineNumber(), pos.getColumnNumber()), "EOF", TokenType.EOF));
			
			//by this point, the file has ended and we've broken out of the while loop, so if is_directiveoperand is not false, that means the string got interrupted
			if(is_directiveoperand)
			{
				errrep.record(new ErrorMessage("filename", new Position(pos.getLineNumber(), 0), "End of File detected inside a string."));
			}
			
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
