package cma;

import listingFile.ListingFile;

public class cma {

	public static void main(String[] args) {
		
		
		if(args.length == 0) {
			System.out.println("dne");
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
						
						ListingFile.writeListingFile();
						break;
			
			case "-verbose":
			case "-v":	System.out.println("verbose option is being implemented");break;
			
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
