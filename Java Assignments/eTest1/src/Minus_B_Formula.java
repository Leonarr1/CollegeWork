import java.util.Scanner;

public class Minus_B_Formula 
{
	
	
	public static void main(String[] args) 
	{
		double resultA = 0;
		double resultB = 0;
		double squareRootValue = 0;
		boolean runLoop = true;
		
		Scanner inputScanner = new Scanner(System.in);
		
		while (runLoop)
		{
			System.out.println("Input the coefficients of your second order polynomial seperated by spaces (or enter quit):");
			
			if (inputScanner.hasNext("quit"))
			{
				runLoop = false;
			}
			else if(inputScanner.hasNextDouble())
			{
				try
				{
					while(inputScanner.hasNextDouble())
					{
						if(inputScanner.hasNextDouble())
						{
							double a = inputScanner.nextDouble();
							double b = inputScanner.nextDouble();
							double c = inputScanner.nextDouble();
							
						if (a!=0)
						{
								if (b*b<(4*a*c))
										System.out.println("This equation has no real roots.");
								else
								{
									squareRootValue = Math.sqrt(b*b-(4*a*c));
								 	
									resultA = (-b+squareRootValue)/(2*a);
									
									resultB = (-b-squareRootValue)/(2*a);
									
									System.out.println("The roots to the equation are x=" + resultA + " and x=" + resultB  );
								}
						}
							else if (b==0)
								System.out.println("This Equation has no roots");
							else
								System.out.println("This equation has one root x= " + (-c/b));
						}
					}
				}
				catch (Exception input)
				{
						System.out.println("invalid Entry");
				}
			}
			else
			{
				System.out.println("Invalid Entry");
				inputScanner.nextLine();
			}
		}
		inputScanner.close();
	}
}
