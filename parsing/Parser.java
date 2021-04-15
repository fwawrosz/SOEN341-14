package parsing;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import token.Mnemonic;
import token.Token;
import token.TokenType;
import symbolTable.Symbol;
import errorReporting.ErrorMessage;
import errorReporting.ErrorReporter;
import java.util.Hashtable;
import reader.Scanner;

public class Parser implements IParser {
public static ErrorReporter errrep = new ErrorReporter();
private IntermatidateRepresentation InterR = new IntermatidateRepresentation();
private LineStatements linestatement = new LineStatements();
private Symbol symbolTable = new Symbol();
private Hashtable<String, Mnemonic> ht = symbolTable.getSymbolTable();
private Scanner scr;
private int counter = 0;


public IntermatidateRepresentation ParseFile(String name){

	scr = new Scanner(name);
	int size = scr.getNumberOfToken();
	
	for(;counter<size; counter++) {
		Token token = scr.getTokenAt(counter);
		if(token.getCode() == TokenType.EOF)
			return InterR;
		else
			ParseThis(token);
	}
	return InterR;
}

private void ParseThis(Token currentToken){
	int counter2 = counter;
		
		switch (currentToken.getCode()){
			case Mnemonic:
				linestatement.setMnemonic(currentToken);
				if(ht.get(currentToken.getName()) == null) {
					if(scr.getTokenAt(++counter2).getCode() == TokenType.Number) {
						linestatement.setNumber(scr.getTokenAt(counter2));
					}else 
                    {
                        ErrorMessage errMessage = new ErrorMessage("This immediate instruction must have an operand field.", currentToken.getPosition(), "TestImmediate.asm");
						errrep.record(errMessage);
                        System.out.println(errMessage);
					}
				}	
				counter = counter2;
				break;
			case Label:
				linestatement.setLabel(currentToken);
				break;
			case Comment: 
				linestatement.setComment(currentToken);
				break;				
			case Directive:
				linestatement.setDirective(currentToken);
				break;
			case EOL:
				InterR.addNewLineStatement(linestatement);
				linestatement = new LineStatements();
				break;
		}		

}

}