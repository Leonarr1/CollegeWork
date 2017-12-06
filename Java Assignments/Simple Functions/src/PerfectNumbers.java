public class PerfectNumbers {

	public static final int MAX_NUMBER = 10000;
	
	public static void main (String args[])
	{
		for (int number = 1; (number <= MAX_NUMBER); number++)
		{
			if (isPerfectNumber(number))
			{
		    	System.out.println( "The number " + number + " is a perfect number." );
			}
		}
	}
	
	// Check if the passed number is a perfect number (i.e. if the sum of
	// its divisors equals the number itself).
	private static boolean isPerfectNumber( int number )
	{
		int sumOfDivisors = 0;
		int currentDivisor = 1;
		while ((currentDivisor != number) && (currentDivisor != -1))
		{
			sumOfDivisors += currentDivisor;
			currentDivisor = getNextDivisor( currentDivisor, number );
		}
		return (number == sumOfDivisors);
	}
	
	// Given a number and a divisor, find and return the next highest
	// divisor of the number.  If there is no such divisor return -1.
	private static int getNextDivisor ( int lastDivisor, int number )
	{
		if ((lastDivisor > 0) && (lastDivisor < number) &&
				(number % lastDivisor == 0))
		{
			int divisor = lastDivisor;
			do {
				divisor++;
			} while (number % divisor != 0);
			return divisor;
		}
		return -1;
	}
}