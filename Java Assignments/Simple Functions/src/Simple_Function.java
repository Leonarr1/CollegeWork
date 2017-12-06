public class Simple_Function {

	public static void main(String[] args) {
		
		boolean fiveIsPrime = isPrime( 81 );
		System.out.println( "81 is " + (fiveIsPrime ? "" : "not ") + "prime." );
		
	    int testNumber = 20;
	    int result = toPowerOf( testNumber, 2 );
	    System.out.println( "" + testNumber + " to the power of 2 is " + result );

	}

	public static boolean isPrime( int number )
	{
	  int divisor=number/2;
	  boolean isPrimeNumber = true;
	  while ((divisor > 1) && (isPrimeNumber))
	  {
	    if (number % divisor-- == 0)
	      isPrimeNumber = false;
	  }
	  return isPrimeNumber;
	}

	public static int toPowerOf( int base, int exponent )
	{
	  int answer = 1;
	  for(int exponentCount = 0; (exponentCount < exponent); exponentCount++)
	  {
	    answer *= base;
	  }
	  return answer;
	}

}