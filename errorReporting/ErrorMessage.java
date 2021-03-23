package errorReporting;

import token.Position;

public class ErrorMessage {
	
	String filename = "";
	String msg = "";
	Position pos;
	
	//default constructor
	public ErrorMessage()
	{
	}
	
	public ErrorMessage(String m, Position p, String f)
	{
		msg = m;
		pos = p;
		filename = f;
	}
	
	public String toString()
	{
		return (filename + ": Error: Line: " + pos.getLineNumber() + " - " + msg);
	}
}
