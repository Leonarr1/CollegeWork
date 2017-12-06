
import java.util.Scanner;

public class Incremental_Statistics 
{

	public static void main(String[] args) 
	{
		boolean runLoop = true;
		double average = 0;
		double lastAverage = 0;
		double variance = 0;
		double newValue = 0;
		int totalValues = 0;
		
		Scanner inputScanner = new Scanner(System.in) ;

		while (runLoop  == true )
		{
			System.out.println("Enter a number (or type 'exit'):");
			
			if (inputScanner.hasNext("exit"))
			{
				runLoop = false;
				System.out.println("Goodbye");
				inputScanner.close();
			}
			else if (inputScanner.hasNextDouble())
				 {
					 lastAverage = average;
					 totalValues = totalValues+1;
						
					 newValue = inputScanner.nextDouble();
					 average = lastAverage + (newValue-lastAverage)/totalValues;
			
					 variance = ((variance*(totalValues-1))+(newValue-lastAverage)*(newValue-average))/totalValues;
						
					 System.out.println("So far the average is " + average + " and the variance is " + variance);
				 }
				 else 
				 {
					inputScanner.nextLine();					
					System.out.println("Invalid chatacter.");
					inputScanner.nextLine();					//clears the scanner of the invalid character to avoid infinite loop
				 }
		}
	}
}
