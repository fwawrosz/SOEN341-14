package symbolTable;

public class TestSymbol {

	public static void main(String[] args) {
		
		Symbol table = new Symbol();
		
		System.out.println("Test Symbol Table");
		System.out.println("Mnemonic(not, 12)");
		System.out.println(table.getMnemonic("not"));

	}

}
