package reader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;


public class SrcReader implements ISrcReader {

	private FileInputStream src;
	
	
	@Override
	public FileInputStream ReadSourceFile(String filename)  {
		
		File srcFile = new File(filename); // Read the file
		if (!srcFile.canRead()) {
			System.out.println("Cannot open source file : " + srcFile.getName());
			System.out.println("System will now exit");
			System.exit(0);
		}
		
		try { 
		 src = new FileInputStream(srcFile);
		}
		catch(Exception e) {
			System.out.println("File corrupt");
			System.exit(0);
		}
		
		
		return src;
			
	}
	
	public String doesFileOpen(String filename) {
		
		this.ReadSourceFile(filename);
		//System.out.println("File opens succesfully.");
		this.closeStream();
		return "File opens succesfully.";
		
	}
	
	public void closeStream() {
		try {
			this.src.close();
		} catch (IOException e) {
			System.out.println("Could not close file.");
		}
	}

}


	
