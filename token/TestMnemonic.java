package token;

public class TestMnemonic {

	public static void main(String[] args) {
		
		Mnemonic haltMnemonic = new Mnemonic("halt", 0x00);		
		System.out.println("Test Mnemonic");
		System.out.println("Mnemonic(halt, 0)");
		System.out.println(haltMnemonic);

	}

}
