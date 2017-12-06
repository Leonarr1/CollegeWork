import java.util.Scanner;

public class Sample 
{
	

	public static void main(String[] args) 
	{
		System.out.println("Enter the maximum square as a whole number." );
		Scanner inputScanner = new Scanner(System.in);
		if (inputScanner.hasNextInt())
		{
			int maximumSquare = inputScanner.nextInt();
			int maximumNumber = (int) Math.sqrt(maximumSquare);
			if (maximumSquare < 0)
			{
				System.out.println("No squares are less than or equal to " + maximumSquare + ".");
			}
			else if (maximumSquare == 0)
				{
					System.out.println("The only square less than or equal to " + maximumSquare + "is " + maximumSquare + ".");
				}
			else
			{
				System.out.println("The numbers whose squares are less than or equal to " + maximumSquare + " are");
				for (int count=0; count <= maximumNumber; count++)
				{
					System.out.print((count==0?"":", ") + count);
				}
				System.out.println(".");		
			}
		}
		else
		{
			System.out.println("Invalid input. \nEnter a whole positive number.");
		}
		inputScanner.close();
	}
}
