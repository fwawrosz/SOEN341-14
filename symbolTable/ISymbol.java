package symbolTable;

import java.util.Hashtable;

import token.Mnemonic;

public interface ISymbol {
	
	public Hashtable<String, Mnemonic> getSymbolTable();
	
	public Mnemonic getMnemonic(String name);
	
}
