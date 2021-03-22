package reader;

public class TestSrcReader {

	public static void main(String[] args) {
		
		SrcReader reader = new SrcReader();
		
		System.out.println("Test Reader");
		System.out.println("File opens succesfully.");
		reader.doesFileOpen("TestInherentMnemonics.asm");
		
		
		
	}

}
