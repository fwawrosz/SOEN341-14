package reader;

public class TestScanner {

	public static void main(String[] args) {
		
		Scanner scanMnemonics = new Scanner("TestImmediate.asm");
		
		for(int i =0 ; i<scanMnemonics.getNumberOfToken();i++) {
			System.out.println(scanMnemonics.getTokenAt(i));
		}
		/*
		System.out.println("Test scanner");
		System.out.println("[enter.u5(2,1)=Mnemonic]");
		System.out.println(scanMnemonics.getTokenAt(3));
		*/
		
	}

}
