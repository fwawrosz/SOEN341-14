package reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import token.Position;
import token.Token;
import token.TokenType;

public class Scanner implements IScanner {
	
	private static final int EOF = -1;
	private StringBuilder mnemonic;
	private ArrayList<String> mnemonicList;
	private ArrayList<Token> tokenList;
	private boolean ignorecomment = false;	//flag that determines whether we're in a comment, i.e. whether to ignore everything past a certain point
	private Position pos = new Position();	//variable keeping track of current position
	
	public int getEOF() {return Scanner.EOF;}
	
	private void createMnemonicList(FileInputStream reader){
		
		int i;
		this.mnemonic = new StringBuilder();
		this.mnemonicList = new ArrayList<String>();
		this.tokenList = new ArrayList<Token>();	//sequence of tokens, which will be used by the parser
		
		try {
			
			while ((i = reader.read()) != Scanner.EOF)
			{
				
				//check first character to determine whether we have a label
				if (pos.getColumnNumber() == 0)
				{
					//if it's a whitespace, there's no label
					if((char) i == ' ' || (char) i == '\t')
					{
						pos.incLineNumber();	//we're going to the next line
						continue;	//move on, nothing else to do
					}
					
					//if it's a letter, chances are it's a label
					else if(Character.isLetter((char)i))	{}	//do nothing for now; we're not dealing with labels yet
					
					
					//if it's anything else (number, etc.), it's probably an error
					else {}		//report error; not coded yet
				}
				
				//determine what to do based on characters
				//skip whitespaces
				if((char) i == ' ' || (char) i == '\t')
				{
					pos.incColumnNumber();
					continue;	//move on, nothing else to do
				}
				
				
				//check for a semicolon to start the beginning of a comment
				else if ((char)i == ';')
				{
					pos.incColumnNumber();
					ignorecomment = true;
				}
				

				//when a newline is encountered
				else if ((char)i == '\n')
				
				{
					
					//it's the end of the comment, if there was one; don't ignore text anymore
					ignorecomment = false;	
					
					//the next character will be the first character of a line
					pos.resetColumnNumber();
					
					//this might need to be reformatted/moved later on,
					//as mnemonics will not be at line ends
					mnemonicList.add(this.mnemonic.toString());
					
					//add an EOL token since it's the end of a line
					//tokenList.add(new Token(new Position(pos.getLineNumber(), pos.getColumnNumber()), "EOL", TokenType.EOL));
					this.mnemonic.setLength(0);
					
				}
				
				//if we're after a semicolon, but before a newline, ignore the character since it's a comment
				else if (ignorecomment)
				{
					pos.incColumnNumber();
					continue;
				}
				
				
				else if(Character.isLetter((char) i))
				{
					this.mnemonic.append((char) i);
				}
				
				
				//for numbers-- do nothing yet
				else if ( Character.isDigit((char)i) )	{  }
				
				//for dots-- do nothing yet
				else if ((char)i == '.')				{  }
				
				
				
				
				//--------------Beginning of old/obsoleted code--------------
				/*
				//skip whitespaces
				if((char) i == ' ' || (char) i == '\t') { continue; }
				
				//check for a semicolon to start the beginning of a comment
				else if ((char)i == ';')				{ ignorecomment = true; }
				
				//when a newline is encountered
				else if ((char)i == '\n')
				
				{
					//it's the end of comment, don't ignore anymore
					ignorecomment = false;	
					
					//the next character will be the first character of a line
					firstchar = true;
					
					//this might need to be reformatted/moved later on,
					//as mnemonics will not be at line ends
					mnemonicList.add(this.mnemonic.toString());
					this.mnemonic.setLength(0);
					
				}
				
				//if we're after a semicolon, but before a newline, ignore the character since it's a comment
				else if (ignorecomment)					{ continue; }
				
				else if(Character.isLetter((char) i))
				{
					this.mnemonic.append((char) i);
				}

				//for numbers-- do nothing yet
				else if ( Character.isDigit((char)i) )	{  }
				
				//for dots-- do nothing yet
				else if ((char)i == '.')				{  }
				*/
				
				
				//old code
				/*
				if((char) i != ' ' || (char) i != '\t') {

					if((char) i == '\n') {
						mnemonicList.add(this.mnemonic.toString());
						this.mnemonic.setLength(0);
					}
					if(Character.isLetter((char) i))
					{
						this.mnemonic.append((char) i);
					}
				}
				*/
					
				//-----------------End of old/obsoleted code-----------------	
			}
			
			//add an EOF token since we're done with the file
			//tokenList.add(new Token(new Position(pos.getLineNumber(), pos.getColumnNumber()), "EOF", TokenType.EOF));
			
		} catch (IOException e) {
			System.out.println("Could not read the file");
		}
			
	}
	
	
	
	public ArrayList<String> getMnemonicList() { //should set this to private
		
		SrcReader readFile = new SrcReader();
		createMnemonicList(readFile.ReadSourceFile("TestImmediate.asm"));
		readFile.closeStream();
		return this.mnemonicList;
		
	}
	
	private void createTokenList(){
		
		this.tokenList = new ArrayList<Token>();
		this.getMnemonicList();
		
		for(int i = 0; i< this.mnemonicList.size(); i++) {
			this.tokenList.add(new Token(new Position(i+1, 0),this.mnemonicList.get(i),TokenType.Mnemonic));
			//add an EOL token since it's the end of a line
			tokenList.add(new Token(new Position(i+1, pos.getColumnNumber()), "EOL", TokenType.EOL));
		}
		
		//add an EOF token since we're done with the file
		tokenList.add(new Token(new Position(this.mnemonicList.size()+1, pos.getColumnNumber()), "EOF", TokenType.EOF));
		
	}
	
	public ArrayList<Token> getTokenList(){ //should set this to private
		this.createTokenList();
		return this.tokenList;
	}
	
	public Token getTokenAt(int index){
		return this.getTokenList().get(index);
	}
	
	public int getNumberOfToken() {
		return this.getTokenList().size();
	}
	
	/*
	public Token getTokenRecursively(int size) {
		
		if(size == this.getNumberOfToken()) {
			size-=1;
		}
		
		if(size == 0) {
			System.out.println(this.getTokenAt(size));
			return this.getTokenAt(size);
		}
		
		
		this.getTokenRecursively(--size);
		System.out.println(this.getTokenAt(++size));
		return this.getTokenAt(size);
				
	}
	*/
	
	
	
	
	
	
	
	
	

	
	
}
