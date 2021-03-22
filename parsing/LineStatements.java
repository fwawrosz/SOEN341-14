package parsing;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import token.Position;
import token.Token;
import token.TokenType;
import symbolTable.Symbol;
import reader.Scanner;



public class LineStatements {
//for a given line, we need an instructtion
	private AssemblyInstructions instr;

//constructor
public LineStatements (AssemblyInstructions instruction){
    this.instr= instruction;

        }

public LineStatements() {
	
}
}