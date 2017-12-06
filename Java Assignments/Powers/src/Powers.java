import java.util.Scanner;

import javax.swing.JOptionPane;

public class Powers 
{

	
	
	public static void main(String[] args) 
	{
		int base = 1;
		int exponent = 1;
		int result = 1;
		int count = 0;
		
		 
		try
		{
			String input = JOptionPane.showInputDialog("Input the base value");
		    Scanner inputBase = new Scanner( input );
		    base = inputBase.nextInt();
		    
		    String input2 = JOptionPane.showInputDialog("Input the exponent value");
		    Scanner inputExponent = new Scanner( input2 );
	    	exponent = inputExponent.nextInt();
		    
		    for(count=0;count<exponent;count++)
		    {
		    	result = base*result;
		    }
		    
		    JOptionPane.showMessageDialog(null, "The result = " + result);
		    inputBase.close();
		    inputExponent.close();
		}
		catch(NullPointerException exception)
		{
			
		}
		
	   
	}

}
