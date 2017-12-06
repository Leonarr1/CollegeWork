import java.util.Scanner;

import javax.swing.JOptionPane;

public class Factorial 
{
	public static void main(String[] args)
	{
		String input = JOptionPane.showInputDialog(null, "Input number: ");
		Scanner inputScanner = new Scanner( input );
		int number = inputScanner.nextInt();
		
		JOptionPane.showMessageDialog(null, "Factorial of " + number + " = " + factorialOf(number));
	}
	
	
	public static int factorialOf( int number)
	{
		
		int result = 0;
		int count = number;
		while (count > 0)
		{
			result = number * factorialOf(number-1);
			count = count-1;
		}
		
		return result;
	}
		
		
		
		
	
}
