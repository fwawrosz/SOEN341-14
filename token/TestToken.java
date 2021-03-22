package token;
// TestToken.java

public class TestToken {
    public static void main(String[] args) throws Exception {
        Position p1 = new Position(1,0);   // (line)

        Token t1 = new Token(p1, "halt", TokenType.Mnemonic);
 

        System.out.println("Test Token");
        System.out.println("[halt(1,0)=Mnemonic]");

        System.out.print(t1);

        System.out.println();
    }
}
