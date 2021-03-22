package parsing;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import token.Mnemonic;
import token.Position;
import token.Token;
import token.TokenType;
import symbolTable.Symbol;

import reader.Scanner;

public class Parser implements IParser {

private static final String Mnemonic = null;
private static final String Label = null;
private static final String LabelOperand = null;
private static final String EOL = null;
//first we must receive the tokens from the reader.
private ArrayList<Token> currentToken;
	private Scanner scr;

//now we create to objects to parse linestatements and assembly instructions
private LineStatements linestatement;
private AssemblyInstructions instr;

public List<String> ParseThis(ArrayList<Token> currentTokens){

	//for the IR, we decided to use a 1-Dimensional String list for this sprint since we are
	//a bit behind on schedule

	ArrayList<Token> currentToken =currentTokens;
	List<String> InterR = new ArrayList<String>();

	//each stored token will have 2 attributes: Type, TokenName. They will be stored back-to-back

//now we request the token list:
	//***I THINK WE NEED A STATIC LIST TO REQUEST IT. WE NEED TO CONTACT THE OTHER GROUP
	//currentToken = scr.getTokenList();


	int counter = 0;
// We use a While statement to check when the token type is EOF, so we stop parsing
	while( currentToken.get(counter).getCode () != TokenType.EOF){
	
		//we will use a Switch Statement to future proof our work for comments, labels, etc.
		switch (currentToken.get(counter).getCode()){

			case Mnemonic:
				//now we parse the Mnemonic by calling to the respecting token class
				Token token1;
				token1 = currentToken.get(counter);

						//now we parse it to the Array List
					//we begin by type, then we add name
					InterR.add(token1.getCode().toString());
					InterR.add(token1.getName());
					instr = new AssemblyInstructions(token1);

					//we increment the counter so the loop would check the new token
					counter++;

			case Label:
				//dont need it now
			case LabelOperand:
				//dont need it now
			case EOL:
				//now we know that we have to go to the next line
				InterR.add(currentToken.get(counter).getCode().toString());
				InterR.add(currentToken.get(counter).getName());
				counter ++;				
				linestatement = new LineStatements(instr);
		}


	}


	if (currentToken.get(counter).getCode() == TokenType.EOF){
		//now lets return the list to the backend
		InterR.add(currentToken.get(counter).getCode().toString());
		InterR.add(currentToken.get(counter).getName());
		return InterR;

	}
	return InterR;

}

}