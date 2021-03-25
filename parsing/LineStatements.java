package parsing;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import token.Position;
import token.Token;
import token.TokenType;
import SymbolTable.Symbol;
import reader.Scanner;



public class LineStatements {
//for a given line, we need an instructtion
	private AssemblyInstructions instr;
	public AssemblyInstructions getInstr() {
		return instr;
	}

	public void setInstr(AssemblyInstructions instr) {
		this.instr = instr;
	}

	private Token label;
	public Token getLabel() {
		return label;
	}

	public void setLabel(Token label) {
		this.label = label;
	}

	private Token mnemonic;
	public Token getMnemonic() {
		return mnemonic;
	}

	public void setMnemonic(Token mnemonic) {
		this.mnemonic = mnemonic;
	}

	public Token getComment() {
		return comment;
	}

	public void setComment(Token comment) {
		this.comment = comment;
	}

	public Token getOperand() {
		return operand;
	}

	public void setOperand(Token operand) {
		this.operand = operand;
	}

	public Token getDirective() {
		return directive;
	}

	public void setDirective(Token directive) {
		this.directive = directive;
	}

	public Token getNumber() {
		return number;
	}

	public void setNumber(Token number) {
		this.number = number;
	}

	private Token comment;
	@Override
	public String toString() {
		return "LineStatements [instr=" + instr + ", label=" + label + ", mnemonic=" + mnemonic + ", comment=" + comment
				+ ", operand=" + operand + ", directive=" + directive + ", number=" + number + "]";
	}

	private Token operand;
	private Token directive;
	private Token number;


//constructor
public LineStatements (Token label, Token mnemonic, AssemblyInstructions instruction, Token comment, Token operand, Token directive, Token number){
    this.instr= instruction;
    this.label= label;
    this.mnemonic= mnemonic;
    this.comment= comment;
    this.operand= operand;
    this.directive= directive;
    this.number=number;
        }

public LineStatements() {
	
	
}




}