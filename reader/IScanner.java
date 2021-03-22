package reader;

import java.io.FileInputStream;
import java.util.ArrayList;

import token.Token;

public interface IScanner {
	int getEOF();
	ArrayList<String> getMnemonicList();	
	ArrayList<Token> getTokenList();
	Token getTokenAt(int index);
}
