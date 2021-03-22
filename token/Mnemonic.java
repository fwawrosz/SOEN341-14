package token;

public class Mnemonic implements IMnemonic {
	
	private String name;
	private int opcode;
	
	public Mnemonic(String name, int opcode) {
		this.name = name;
		this.opcode = opcode;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getOpcode() {
		return this.opcode;
	}
	
	public String toString() {
		return "Mnemonic("+this.getName()+", "+this.getOpcode()+")";
	}
	
	
	
}
