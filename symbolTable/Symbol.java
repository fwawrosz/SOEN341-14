package symbolTable;

import java.util.Hashtable;

import token.Mnemonic;

public class Symbol implements ISymbol {
	
	private Hashtable<String, Mnemonic> symbolTable;
	
	public Symbol() {

		this.symbolTable = new Hashtable();
		
		symbolTable.put("halt", new Mnemonic("halt", 0x00));
		symbolTable.put("pop" ,new Mnemonic("pop", 0x01));
		symbolTable.put("dup" ,new Mnemonic("dup", 0x02));
		symbolTable.put("exit" ,new Mnemonic("exit", 0x03));
		symbolTable.put("ret" ,new Mnemonic("ret", 0x04));
		
		symbolTable.put("not" ,new Mnemonic("not", 0x0C));
		symbolTable.put("and" ,new Mnemonic("and", 0x0D));
		symbolTable.put("or" ,new Mnemonic("or", 0x0E));
		symbolTable.put("xor" ,new Mnemonic("xor", 0x0F));
		symbolTable.put("neg" ,new Mnemonic("neg", 0x10));
		
		symbolTable.put("inc" ,new Mnemonic("inc", 0x11));
		symbolTable.put("dec" ,new Mnemonic("dec", 0x12));
		symbolTable.put("add" ,new Mnemonic("add", 0x13));
		symbolTable.put("sub" ,new Mnemonic("sub", 0x14));
		symbolTable.put("mul" ,new Mnemonic("mul", 0x15));
		
		symbolTable.put("div" ,new Mnemonic("div", 0x16));
		symbolTable.put("rem" ,new Mnemonic("rem", 0x17));
		symbolTable.put("shl" ,new Mnemonic("shl", 0x18));
		symbolTable.put("shr" ,new Mnemonic("shr", 0x19));
		symbolTable.put("tne" ,new Mnemonic("teq", 0x1A));
		
		symbolTable.put("tne" ,new Mnemonic("tne", 0x1B));
		symbolTable.put("tlt" ,new Mnemonic("tlt", 0x1C));
		symbolTable.put("tgt" ,new Mnemonic("tgt", 0x1D));
		symbolTable.put("tle" ,new Mnemonic("tle", 0x1E));
		symbolTable.put("tge" ,new Mnemonic("tge", 0x1F));
				
	}
	
	@Override public Hashtable<String, Mnemonic> getSymbolTable(){
		return this.symbolTable;
	}

	@Override
	public Mnemonic getMnemonic(String name) {
		return this.symbolTable.get(name);
	}
	
	public void PrintTable() {
		this.symbolTable.entrySet().forEach(entry -> {
			System.out.println(entry.getKey() +"->"+entry.getValue());});
	}
	
}
