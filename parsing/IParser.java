package parsing;


import token.Position;
import token.Token;
import token.TokenType;

import java.util.ArrayList;
import java.util.List;

import SymbolTable.Symbol;
import reader.Scanner;

public interface IParser{

	private void ParseThis(Token currenntToken) {
	}

	public List<LineStatements> ParseFile();
}