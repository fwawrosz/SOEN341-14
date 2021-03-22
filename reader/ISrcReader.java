package reader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public interface ISrcReader {
	
	FileInputStream ReadSourceFile(String name) ;
	String doesFileOpen(String filename);
	public void closeStream();
	
}
