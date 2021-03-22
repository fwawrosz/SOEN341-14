package token;
// TestPosition.java

public class TestPosition {
    public static void main(String[] args) throws Exception {
        IPosition p1 = new Position(1,4);   // (line, col)

        System.out.println("Test Position");
        System.out.println("p1[1,4]");
        System.out.print("p1["+p1.getLineNumber()+","+p1.getColumnNumber()+"]");

        System.out.println();
    }
}
