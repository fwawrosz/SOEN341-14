package cma;

import java.util.Scanner;

import listingFile.ListingFile;
import reader.SrcReader;
import symbolTable.Symbol;



public class cma {

	public static void main(String[] args) {
		
		String filename = "";
		
		if(args.length == 0) {
			System.out.println("Add option or file name.");
		}else {
		
		
			switch(args[0]) {
			
			case "-banner":
			case "-b": System.out.println("Cm Cross-Assembler Version Alpha - Developed by Team 14."); break;
			
			case "-help":
			case "-h":	System.out.println("-?\n-listing\n-l\n\tGenerate listing file\n"
										 + "-?\n-verbose\n-v\n\tGenerate listing file and meaningful information during the software execution\n"
										 + "-?\n-banner\n-b\n\tSoftware version");break;
			
			case "-listing":
			case "-l":	
				if(args.length > 1 && !args[1].isEmpty() && args[1].contains(".asm")) {
					
						filename = args[1];
						new SrcReader().doesFileOpen(filename);
						ListingFile.writeListingFile(filename);
						//.generateExe()
						
				}else {
					System.out.println("You must provide a .asm file name with the option -listing.");
				}		
				break;
			case "-verbose":
			case "-v":
				
				if(args.length > 1 && !args[1].isEmpty() && args[1].contains(".asm")) {
					
					filename = args[1];
					new SrcReader().doesFileOpen(filename);
					
					//provide useful information
					System.out.println("Your file "+filename+" is ready for processing");
					
					Scanner key = new Scanner(System.in);
					
					System.out.print("Enter yes, if this is the file you want to process: ");
					String response = key.nextLine();
					
					if(!(response.equalsIgnoreCase("yes"))) {
						System.out.println("Run the cross-assembler using the correct file.");
						System.exit(0);
					}
					
					//beginning of placeholder
					System.out.println("\nThe following Symbol Table is being used:");
					System.out.println("---------------------------------------------------------");
					new Symbol().PrintTable();
					System.out.println("---------------------------------------------------------\n");
					//end of placeholder
					
					System.out.println(filename+" is beind parsed...");
					ListingFile.writeListingFile(filename);
					System.out.println("Generating executable...");
					//.generateExe()
					
			}else {
				System.out.println("You must provide a .asm file name with the option -verbose.");
			}
			break;
			
			default:
				if(args[0].contains(".asm")) {
					System.out.println(args[0].substring(0,args[0].indexOf(".asm")));
					//.generateExe()
				}else {
					System.out.println("This option does not exist");
				}
			
			}
		
		}
		
		

	}

}
