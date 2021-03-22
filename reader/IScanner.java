package reader;

import java.io.FileInputStream;
import java.util.ArrayList;

import token.Token;

public interface IScanner {
	int getEOF();
	Token getTokenAt(int index);
	int getNumberOfToken();
}
