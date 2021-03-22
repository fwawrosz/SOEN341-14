package reader;

public class TestScanner {

	public static void main(String[] args) {
		
		Scanner scanMnemonics = new Scanner();

		for(int i =0 ; i<scanMnemonics.getNumberOfToken();i++) {
			System.out.println(scanMnemonics.getTokenAt(i));
		}
		
		
	}

}
