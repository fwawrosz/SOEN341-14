package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import parsing.LineStatements;
import parsing.Parser;
import reader.Scanner;
import reader.SrcReader;
import symbolTable.Symbol;
import token.Mnemonic;
import token.Position;
import token.Token;
import token.TokenType;

class TestJunit {

	@Test
	void reader() {
		
		//Test reader class
		assertEquals(new SrcReader().doesFileOpen("TestImmediate.asm"),"File opens succesfully.");
  
	}
	
	@Test
	void scannerToken() {
		//Test Scanners Token List
		assertEquals(new Scanner("TestImmediate.asm").getTokenAt(3).getName(),"enter.u5");
	}
	
	@Test
	void symbolTable() {
		//Test Symbol Table
		assertEquals(new Symbol().getMnemonic("not").toString(),"Mnemonic(not, 12)");
	}
	
	@Test
	void token() {
		//Test Token
		Position p1 = new Position(1,0);   // (line)
		Token t1 = new Token(p1, "halt", TokenType.Mnemonic);
		assertEquals(t1.toString(),"[halt(1,0)=Mnemonic]");
	}
	
	@Test
	void position() {
		//Test position
        assertEquals(new Position(1,4).toString(),"(1,4)");
	}
	
	@Test
	void mnemonic() {
		//Test Mnemonic
        assertEquals(new Mnemonic("halt", 0x00).toString(),"Mnemonic(halt, 0)");
	}
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	@Test
	void parser() {
		//Test Parser
	    
		Parser parser1 = new Parser();
		List<LineStatements> result = parser1.ParseFile();
		
		assertEquals(result.get(0).toString(), "LineStatements [instr=null, label=null, mnemonic=null, comment=[; TestImmediate.asm - Test immediate instructions.\n"
				+ "(0,0)=Comment], operand=null, directive=null, number=null] ");
	}
	*/




