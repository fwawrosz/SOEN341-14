package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

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
		assertEquals(new SrcReader().doesFileOpen("TestInherentMnemonics.asm"),"File opens succesfully.");
  
	}
	
	@Test
	void scannerMnemonic() {
		//Test scanner mnemonic list
		assertEquals(new Scanner().getMnemonicList().toString(), "[halt, pop, dup, exit, ret, not, and, or, xor, neg, inc, dec, add, sub, mul, div, rem, shl, shr, teq, tne, tlt, tgt, tle, tge, halt]");
	}
	
	@Test
	void scannerToken() {
		//Test Scanners Token List
		assertEquals(new Scanner().getTokenAt(0).toString(),"[halt(1,0)=Mnemonic]");
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
	
	@Test
	void parser() {
		//Test Parser
	    
        Parser parser1 = new Parser();
	    List<String> result;
		result = parser1.ParseThis(new Scanner().getTokenList());
		
		assertEquals(result.toString(), "[Mnemonic, halt, EOL, EOL, Mnemonic, pop, EOL, EOL, Mnemonic, dup, EOL, EOL, Mnemonic, exit, EOL, EOL, Mnemonic, ret, EOL, EOL, Mnemonic, not, EOL, EOL, Mnemonic, and, EOL, EOL, Mnemonic, or, EOL, EOL, Mnemonic, xor, EOL, EOL, Mnemonic, neg, EOL, EOL, Mnemonic, inc, EOL, EOL, Mnemonic, dec, EOL, EOL, Mnemonic, add, EOL, EOL, Mnemonic, sub, EOL, EOL, Mnemonic, mul, EOL, EOL, Mnemonic, div, EOL, EOL, Mnemonic, rem, EOL, EOL, Mnemonic, shl, EOL, EOL, Mnemonic, shr, EOL, EOL, Mnemonic, teq, EOL, EOL, Mnemonic, tne, EOL, EOL, Mnemonic, tlt, EOL, EOL, Mnemonic, tgt, EOL, EOL, Mnemonic, tle, EOL, EOL, Mnemonic, tge, EOL, EOL, Mnemonic, halt, EOL, EOL, EOF, EOF]");
	}

}


