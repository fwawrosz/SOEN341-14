package parsing;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import token.Mnemonic;
import token.Token;
import token.TokenType;
import SymbolTable.Symbol;
import errorReporting.ErrorMessage;
import errorReporting.ErrorReporter;
import java.util.Hashtable;
import reader.Scanner;

public class Parser implements IParser {
public static ErrorReporter errrep = new ErrorReporter();
private List<LineStatements> InterR = new ArrayList<LineStatements>();
private LineStatements linestatement = new LineStatements();
private Symbol symbolTable = new Symbol();
private Hashtable<String, Mnemonic> ht = symbolTable.getSymbolTable();
Scanner scr = new Scanner();
private int counter = 0;


public List<LineStatements> ParseFile(){
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
					if(scr.getTokenAt(counter2++).getCode() == TokenType.LabelOperand) {						
						linestatement.setOperand(scr.getTokenAt(counter2));
						if(scr.getTokenAt(counter2++).getCode() == TokenType.Number) {
							linestatement.setNumber(scr.getTokenAt(counter2));
						}else {
							errrep.record(new ErrorMessage("This immediate instruction must have a valid number operand"));
						}
					}else {
						errrep.record(new ErrorMessage("This immediate instruction must have an operand field."));
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
			case EOL:
				InterR.add(linestatement);			
		}		

}

}