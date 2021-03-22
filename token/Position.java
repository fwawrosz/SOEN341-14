package token;
// Position.java - (c) 2001-2021 by Michel de Champlain

public class Position implements IPosition {
    public Position(int lineNumber, int columnNumber) {
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }
    
    //default constructor, initialize everything to zero
    public Position()
    {
    	lineNumber = 0;
    	columnNumber = 0;
    }
    
    public  int    getLineNumber()   	{ return lineNumber;   }
    public  int    getColumnNumber() 	{ return columnNumber; }

    //functions to incremement positions cleanly
    public  void   incLineNumber()    	{ lineNumber++; }
    public	void   incColumnNumber()  	{ columnNumber++; }
    
    //functions to reset position to 0 cleanly
    public  void   resetLineNumber()  	{ lineNumber = 0; }
    public  void   resetColumnNumber()	{ columnNumber = 0; }
    
    public  String toString() {
        return "("+getLineNumber()+","+this.getColumnNumber()+")";
    }
    
    private int lineNumber;
    private int columnNumber;
}
