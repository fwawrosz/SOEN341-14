package listingFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import reader.Scanner;
import symbolTable.Symbol;

public class ListingFile {
	public static void buildListingFile() {
		try {
			File listingFile = new File("TestInherentMnemonics.lst");
			if (listingFile.createNewFile()) {
				System.out.println("File created: " + listingFile.getName());
			} else {
				System.out.println("Did not create file. Listing file already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred while creating listing file.");
			e.printStackTrace();
		}
	}
	public static void writeListingFile() {
		int line = 1; // line number
		int addr = 0; // address in decimal
		Scanner scanMnemonics = new Scanner();
		Iterator<String> mnemonic = scanMnemonics.getMnemonicList().iterator();
		try {
			FileWriter listingFile = new FileWriter("TestInherentMnemonics.lst");
			listingFile.write("Line\tAddr\tCode\tLabel\tMne\tOperand\tComments\n"); // headers
			while (mnemonic.hasNext()) {
				String nextMnemonic = mnemonic.next(); // next Mnemonic
				listingFile.write(line++ + "\t");
				String hexAddr = Integer.toHexString(addr++).toUpperCase(); // address in Hex
				String hexAddr4 = "";
				for (int i = hexAddr.length(); i < 4; i++) { // address in 0000 format
					hexAddr4 += "0";
				}
				hexAddr4 += hexAddr;
				listingFile.write(hexAddr4 + "\t");
				Symbol table = new Symbol();
				String code = "";
				if(table.getMnemonic(nextMnemonic) != null) {
				 code = Integer.toHexString(table.getMnemonic(nextMnemonic).getOpcode()).toUpperCase(); // code
				}
				String code4 = "";
				for (int i = code.length(); i < 2; i++) { // code in 0000 format
					code4 += "0";
				}
				code4 += code;
				listingFile.write(code4 + "\t");
				listingFile.write("\t"); // label ------ to be added
				listingFile.write(nextMnemonic + "\n");
				// Operand ------ to be added
				// comment ------ to be added
			}
			listingFile.close();
			System.out.println("Successfully wrote to listing file.");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to listing file.");
			e.printStackTrace();
		}
	}
}
