import java.util.Scanner;

public class test 
{

	public static void main(String[] args) 
	{
		double average = 0;
		double variance = 0;
		double newValue = 0;
		double lastAverage = 0;
		int numberOfValues = 0;
		
		
		//System.out.println("Enter a number (or type 'exit'):");
		Scanner inputScanner = new Scanner(System.in) ;
		System.out.println("Enter a number (or type 'exit'):");
		
		while (!inputScanner.hasNext("exit"))
		{
			
			
			numberOfValues = numberOfValues + 1;
			
				average = inputScanner.nextDouble();
		
			
				newValue = inputScanner.nextDouble();
				average = lastAverage + (newValue-lastAverage)/numberOfValues;
				lastAverage = average;
				
				variance = ((variance * (numberOfValues-1)) + (numberOfValues-lastAverage) * (numberOfValues-average))/numberOfValues;
		
			
			System.out.println("So far the average is " + average + " and the variance is " + variance);
			System.out.println("\nEnter a number (or type 'exit'):");
		}
	}
}
