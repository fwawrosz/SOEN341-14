package parsing;

import java.util.ArrayList;
import java.util.List;

import reader.Scanner;
import token.Position;
import token.Token;
import token.TokenType;

public class TestParser {

	  public static void main(String[] args) {
		  
		  Parser parser1 = new Parser();
		  List<String> result;
		  
		  result = parser1.ParseThis(new Scanner().getTokenList());
		  
		  
		/*  
		  ArrayList<Token> currentTokens = new ArrayList<Token>();
		Token token1 = new Token( new Position(1,1), "halt",  TokenType.Mnemonic);
		Token token2 = new Token( new Position(2,1), "pop",  TokenType.Mnemonic);
		Token token3 = new Token( new Position(2,3), "EOF",  TokenType.EOF);
		currentTokens.add(token1);
		currentTokens.add(token2);
		currentTokens.add(token3);
		*/

		 System.out.println("Test Parser");
		 System.out.println("[Mnemonic, halt, EOL, EOL, Mnemonic, pop, EOL, EOL, Mnemonic, dup, EOL, EOL, Mnemonic, exit, EOL, EOL, Mnemonic, ret, EOL, EOL, Mnemonic, not, EOL, EOL, Mnemonic, and, EOL, EOL, Mnemonic, or, EOL, EOL, Mnemonic, xor, EOL, EOL, Mnemonic, neg, EOL, EOL, Mnemonic, inc, EOL, EOL, Mnemonic, dec, EOL, EOL, Mnemonic, add, EOL, EOL, Mnemonic, sub, EOL, EOL, Mnemonic, mul, EOL, EOL, Mnemonic, div, EOL, EOL, Mnemonic, rem, EOL, EOL, Mnemonic, shl, EOL, EOL, Mnemonic, shr, EOL, EOL, Mnemonic, teq, EOL, EOL, Mnemonic, tne, EOL, EOL, Mnemonic, tlt, EOL, EOL, Mnemonic, tgt, EOL, EOL, Mnemonic, tle, EOL, EOL, Mnemonic, tge, EOL, EOL, Mnemonic, halt, EOL, EOL, EOF, EOF]");
	     System.out.println(result.toString());
}
}
