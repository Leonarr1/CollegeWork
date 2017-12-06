
public class Triangular_Star 
{

	public static double ERROR_MARGIN = 1e-7;
	
	public static void main(String[] args) 
	{
		int triangularNumber = 0;

		for(int index = 0; triangularNumber >= 0; index++ )
		{
			triangularNumber = determinTriangularNumber(index, triangularNumber);
			if (isStarNumber(triangularNumber))
			{
				System.out.println(triangularNumber);
			}
		}
	}
	
	public static boolean isStarNumber(int triangularNumber) 
	{
		double testValue = (triangularNumber - 1) /6.00;
		int deduction = (int) (testValue + ERROR_MARGIN);
		if (Math.abs(testValue - deduction) > ERROR_MARGIN)	// checks to make sure the outcome will be an integer because of the "lossy" nature of doubles
			return false;
		int root = (int) (Math.sqrt(deduction) + ERROR_MARGIN); // ensures the square root will return the correct integer
		return deduction == root * (root + 1);
			
	}
		
	public static int determinTriangularNumber(int index, int previousTriangularNumber )
	{
		return previousTriangularNumber + index ;
	}
}
