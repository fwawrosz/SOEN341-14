package parsing;


import token.Position;
import token.Token;
import token.TokenType;

import java.util.ArrayList;
import java.util.List;

import symbolTable.Symbol;
import reader.Scanner;

public interface IParser{

	List<String> ParseThis(ArrayList<Token> currenntTokens);


}