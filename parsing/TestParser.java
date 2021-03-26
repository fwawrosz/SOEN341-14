package parsing;

import java.util.ArrayList;
import java.util.List;

import token.Position;
import token.Token;
import token.TokenType;

public class TestParser {

	  public static void main(String[] args) {
		  
	
	 Parser parser1 = new Parser();
	 List<LineStatements> result = parser1.ParseFile();
	
	 System.out.println("Test Parser");
     System.out.println(result.get(0).toString());
}
}
