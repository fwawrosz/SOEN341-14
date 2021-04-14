package cma;

import listingFile.ListingFile;
import reader.SrcReader;

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
						
				}else {
					System.out.println("You must provide a .asm file name with the option -listing.");
				}		
				break;
			case "-verbose":
			case "-v":
				
				if(args.length > 1 && !args[1].isEmpty() && args[1].contains(".asm")) {
					
					filename = args[1];
					new SrcReader().doesFileOpen(filename);
					ListingFile.writeListingFile(filename);
			}else {
				System.out.println("You must provide a .asm file name with the option -verbose.");
			}
			break;
			
			default:
				if(args[0].contains(".asm")) {
					System.out.println(args[0].substring(0,args[0].indexOf(".asm")));
				}else {
					System.out.println("This option does not exist");
				}
			
			}
		
		}
		
		

	}

}
