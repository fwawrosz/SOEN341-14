package reader;

public class TestScanner {

	public static void main(String[] args) {
		
		Scanner scanMnemonics = new Scanner();
		
		System.out.println("Test Scanners Mnemonic List");
		System.out.println("[halt, pop, dup, exit, ret, not, and, or, xor, neg, inc, dec, add, sub, mul, div, rem, shl, shr, teq, tne, tlt, tgt, tle, tge, halt]");
		System.out.println(scanMnemonics.getMnemonicList());

		
		System.out.println("Test Scanners Token List");
		System.out.println("[halt(1,0)=Mnemonic][EOL(1,0)=EOL][pop(2,0)=Mnemonic]");
		System.out.print(scanMnemonics.getTokenAt(0));
		System.out.print(scanMnemonics.getTokenAt(1));
		System.out.print(scanMnemonics.getTokenAt(2));
		
		/*
		System.out.println();
		for(int i =0 ; i<scanMnemonics.getNumberOfToken();i++) {
			System.out.println(scanMnemonics.getTokenAt(i));
		}
		*/
		
	}

}
