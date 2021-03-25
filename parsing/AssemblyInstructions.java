package parsing;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import token.Mnemonic;
import token.Position;
import token.Token;
import token.TokenType;
import SymbolTable.Symbol;

import reader.Scanner;

//to handle assembly instructions
public class AssemblyInstructions  {

	//For the next sprint, change to mnemonic
	private Token mnToken;
	//we are missing the operand which will be added.
	
	//constructor
	public AssemblyInstructions(Token a){
		this.mnToken = a;
		
	}
	
	public AssemblyInstructions() {
		
	}
	


}