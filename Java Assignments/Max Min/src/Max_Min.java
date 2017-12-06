import java.util.Scanner;

import javax.swing.JOptionPane;

public class Max_Min 
{

	public static void main(String[] args) 
	{
		
		
	    String input = JOptionPane.showInputDialog("Input your values seperated by a space : ");
	    Scanner inputScanner = new Scanner( input );
	    
	    
		if (inputScanner.hasNextDouble())
		{
			double minValue = inputScanner.nextDouble();
			double maxValue = minValue;
			
		    while (inputScanner.hasNextDouble())
		    {
		    	double testNumber = inputScanner.nextDouble();
		    	
			    	if (testNumber > maxValue)
			    		maxValue = testNumber;
			    	
			    	if (testNumber < minValue)
			    		minValue = testNumber;
			    	
		    }
		    JOptionPane.showMessageDialog(null, "Max Value = " + maxValue + "\nMin Value = " + minValue);
		    inputScanner.close();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Invalid entry","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	    
	}		
}
