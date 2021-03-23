package errorReporting;

import java.util.ArrayList;

public class ErrorReporter implements IErrorReporter {

	private int errorcount = 0;
	private ArrayList<ErrorMessage> errorList = new ArrayList<ErrorMessage>();
	
	public ErrorReporter()
	{
		
	}
	
	public void record(ErrorMessage e)
	{
		errorList.add(e);
		errorcount++;
	}
	
	private void report()
	{
		for(ErrorMessage err : errorList)
		{
			System.out.println(err);
		}
	}
}
