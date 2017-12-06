import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.Scanner;



public class SOE_Graphical_Representation 
{
	public static int CANVASX = 1980/2;
	public static int CANVASY = 1080;
	public static int WIDTH = 25;
	public static int HEIGHT = 25;
	public static float SPACING = 5;
	public static final int MARGIN = 50;
	public static final int PRIMESPACE = 200;
	public static final int PRIMEHEADER = 30;
	public static int N;		//upper limit of primes

	public static Font numberFont;
	
	
	public static void main(String[] args)
	{
		while (N < 2)
		{
			System.out.println("Enter int >= 2 : ");
			Scanner inputScanner = new Scanner(System.in);
			if(!inputScanner.hasNextInt())
			{
				System.out.println("Error: please enter an integer");
				inputScanner.next();
			}
			else
			{
				
				N = inputScanner.nextInt();
				if (N >= 2)
				{			
					setup();
					displayNumbers2ToN(N-1);
					sieve(N);
					
					inputScanner.close();
				}
				else
				{
					System.out.println("Error: please enter an integer >= 2");
				}
			}
		}
	}
	
	public static void setup()
	{
		/*
		while((int) Math.round(Math.sqrt(N)) > CANVASX - (MARGIN+PRIMESPACE))
		{
			CANVASX+= 2*WIDTH;
		}
			*/
		StdDraw.setCanvasSize(CANVASX, CANVASY);
		StdDraw.setXscale(0, CANVASX);
		StdDraw.setYscale(CANVASY, 0);	
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text((CANVASX) - (PRIMESPACE/2+MARGIN), PRIMEHEADER/2, "Prime Numbers:");
	}
	
	public static boolean[] createSequence ( int N )
	{
		boolean[] isPrime = new boolean[N-1];	// N-1 because 1 is excluded
		Arrays.fill(isPrime, true);
		return isPrime;
	}
		
	public static void sieve (int N)
	{
		boolean[] isPrime = createSequence(N);
		boolean[] colored = new boolean[isPrime.length];
		Arrays.fill(colored, false);
		int prime_count = 0;
		int n;
		for (n = 2; n<=isPrime.length; n++)
		{
			if(isPrime[n-2])
			{
			prime_count++;	
			displayPrime(n , prime_count);
			crossOutHigherMultiples (isPrime, colored, n, prime_count);	
			}
		}
	}

	public static void crossOutHigherMultiples (boolean[] isPrime, boolean[] colored, int n, int prime_count)
	{
		if(isPrime == null)
		{
			System.out.println("isPrime: null array exception in: crossOutHigherMultiples" );
			System.exit(1);
		}
		else if( n > isPrime.length )
		{
			System.out.println("isPrime: out of bounds exception in: crossOutHigherMultiples ");
			System.exit(1);
		}
		else if(colored == null)
		{
			System.out.println("colored: null array exception in: crossOutHigherMultiples" );
			System.exit(1);
		}
		else
		{
			//check if "n" has been crossed off yet
			if(isPrime[n-2]) // ignores 2 and 3 as they can be assumed to be prime
			{
				//cross off all multiples of "n"
				int multiple = 2*n;		
				for ( multiple = 2*n; multiple<isPrime.length+2; multiple += n)
				{
					if( prime_count-2 > colored.length )
					{
						System.out.println("colored: out of bounds exception in: crossOutHigherMultiples ");
						System.exit(1);
					}
					else
					{
						isPrime[multiple-2] = false;
						if (!colored[multiple-2])
						{
							displayComposite (multiple-2, prime_count, isPrime.length);
							colored[multiple-2] = true;
						}
					}
				}
			}
		}
	}
	
	public static void displayNumber (int n, Color c, int N)
	{
		float fontScale = 16;
		float x = (MARGIN + (2*WIDTH)*(1/N) + SPACING);	// x position
		float y = MARGIN;								// y position
		int rowLength = (int) Math.round(Math.sqrt(N));
		
		while (rowLength*(2*(WIDTH+SPACING)) > CANVASX - (MARGIN + PRIMESPACE) || rowLength*(2*(WIDTH+SPACING)) > CANVASY - MARGIN)
		{
			WIDTH*=0.9f;
			HEIGHT*=0.9f;
			SPACING*=0.8f;
			
		}
		if (N>450)
		{
			fontScale *=0.6;
		}
		if(N>900)
		{
			fontScale *=0.8;
		}
		
		numberFont = new Font("Arial", Font.PLAIN, Math.round(fontScale));
		StdDraw.setFont(numberFont);
		if(n<rowLength-1)
		{
			x = MARGIN + (n+1)*((2*WIDTH) + SPACING);
			StdDraw.setPenColor(c);
			StdDraw.filledRectangle(x, y, WIDTH, HEIGHT);
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.text(x, y, "" + (n+2));	
			StdDraw.show(50);
		}
		else 
		{
			int row = (n+1)/rowLength;
			int col = (n+1)%rowLength;
			x = MARGIN + (col*((2*WIDTH) + SPACING));
			y = MARGIN + (row*((2*HEIGHT) + SPACING));
			StdDraw.setPenColor(c);
			StdDraw.filledRectangle(x, y, WIDTH, HEIGHT);
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.text(x, y, "" + (n+2));	
			StdDraw.show(50);
		}

	}

	public static void displayNumbers2ToN (int N)
	{
		Color initialColor = StdDraw.DARK_GRAY;

		for (int i=0; i<N; i++)
		{
			displayNumber(i,initialColor,N);
		}
		
		
	}

	public static void displayComposite (int m, int prime_count, int N)
	{
		Color[] compositeColors = {StdDraw.BLUE,StdDraw.CYAN,StdDraw.GREEN,StdDraw.MAGENTA,
				StdDraw.ORANGE,StdDraw.PINK,StdDraw.RED,StdDraw.YELLOW,StdDraw.BLACK};
		if (prime_count-1 < 0)
		{
			System.out.println("compositeColors: out of bounds exception in: displayComposite" );
			System.exit(1);
		}
		else if(prime_count > compositeColors.length )
		{
			int r = (int) (Math.random()*255);
			int g = (int) (Math.random()*255);
			int b = (int) (Math.random()*255);
			Color c = new Color(r,g,b);
			displayNumber(m, c, N);
		}
		else
		{
		displayNumber(m, compositeColors[prime_count-1], N);
		}
		
		
		
	}

	public static int displayPrime (int p, int prime_count)
	{
		prime_count--;						// subtracted to make the list of primes 0 indexed
		StdDraw.setPenColor(StdDraw.BLACK);
		int row = prime_count/4;
		int col = prime_count%4;
		float y = MARGIN + PRIMEHEADER + (row*((2*HEIGHT)));
		float x = (CANVASX + MARGIN) - ((MARGIN+PRIMESPACE) - (col*(2*(WIDTH+SPACING))));
		StdDraw.text(x, y, "" + (p));
		System.out.println(prime_count+1);
		StdDraw.show(50);
		return p;
	}
}
