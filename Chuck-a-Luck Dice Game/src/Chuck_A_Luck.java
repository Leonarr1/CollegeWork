import java.util.Scanner;

public class Chuck_A_Luck 
{
	static Dice   d1 = new Dice();
	static Dice   d2 = new Dice();
	static Dice   d3 = new Dice();
	static double totalBets;

	
	public static void main (String args[])
	{
		Wallet pW = new Wallet();								// Initialize player wallet
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("Enter Starting Cash Amount: ");
		while(!inputScanner.hasNextDouble())
		{
			System.out.println("Enter an amount of cash please.");
			inputScanner.nextLine();
		}
		double initialCash = inputScanner.nextDouble();
		pW.put(initialCash);
		inputScanner.nextLine();
		boolean exit = false;
		while(pW.check()>0.00 && !exit)
		{
			System.out.println("Bet Types: Triple, Field, High, Low \nCall your bet or enter quit to exit.");
			String betType;
			betType = inputScanner.nextLine();
			betType = (betType.toLowerCase()).replaceAll(" ", "");
			if(betType.equals("exit"))
			{
				exit = true;
			}
			else
			{
				resolveBet(betType, pW);
			}
		}
		inputScanner.close();
		summary(initialCash, pW);
		System.out.println("goodbye");
	}
	
	public static void resolveBet(String betType, Wallet pW)
	{
		if(betType == null)
		{
			System.out.println("Error: null string betType in resolveBet");
			System.exit(1);
		}
		if(isValidBetType(betType))
		{
			Scanner betInput = new Scanner(System.in);
			System.out.println("How much would you like to bet: ");
			while(!betInput.hasNextDouble())
			{
				System.out.println("Enter an amount of cash please.");
				betInput.nextLine();
			}
				double betAmount = betInput.nextDouble();
				totalBets += betAmount;
				if(pW.get(betAmount))
				{
					if(betType.equals("triple"))
					{
						triple (betAmount, pW);
					}
					if(betType.equals("field"))
					{
						field (betAmount, pW);
					}
					if(betType.equals("high"))
					{
						high (betAmount, pW);
					}
					if(betType.equals("low"))
					{
						low (betAmount, pW);
					}
				}
				else
				{
					System.out.println("Not enoguh cash");
					return;
				}
		}
		else
		{
			System.out.println("Invalid bet type");
			return;
		}
		System.out.println("You have: $" + pW.check());
	}
	
	public static boolean isValidBetType(String betType)
	{
	return (betType.equals("triple") || betType.equals("field") || betType.equals("high") || betType.equals("low"));
	}
	
	public static void triple (double betAmount, Wallet pW)
	{
		if(d1.roll() == d2.roll() && d2.roll() == d3.roll())
		{
			System.out.println("You win\n Dice 1:" + d1.topFace() + " Dice 2:" 
					+ d2.topFace() + " Dice 3: " + d3.topFace());
			double winnings = betAmount*30;
			pW.put(betAmount+winnings);
		}
		else
		{
			System.out.println(" Dice 1:" + d1.topFace() + " Dice 2:"
					+ d2.topFace() + " Dice 3: " + d3.topFace());
			System.out.println("You lose");
		}
	}
	
	public static void field (double betAmount, Wallet pW)
	{
		if(d1.roll() + d2.roll() + d3.roll() <8 || d1.roll() + d2.roll() + d3.roll() > 12)
		{
			System.out.println("You win\nDice 1:" + d1.topFace() + " Dice 2:" 
					+ d2.topFace() + " Dice 3: " + d3.topFace() + " Total: "
					+ (d1.topFace()+d2.topFace()+d3.topFace()));
			double winnings = betAmount;
			pW.put(betAmount+winnings);
		}
		else
		{
			System.out.println("Dice 1:" + d1.topFace() + " Dice 2:"
					+ d2.topFace() + " Dice 3: " + d3.topFace()+ " Total: "
					+ (d1.topFace()+d2.topFace()+d3.topFace()));
			System.out.println("You lose");
		}
	}
	
	public static void high (double betAmount, Wallet pW)
	{
		boolean isTriple = false;
		if(d1.roll() == d2.roll() && d2.roll() == d3.roll())
			isTriple = true;
		if(d1.roll() + d2.roll() + d3.roll() > 10 && !isTriple )
		{
			System.out.println("You win\nDice 1:" + d1.topFace() + " Dice 2:" 
					+ d2.topFace() + " Dice 3: " + d3.topFace() + " Total: "
					+ (d1.topFace()+d2.topFace()+d3.topFace()));
			double winnings = betAmount;
			pW.put(betAmount+winnings);
		}
		else
		{
			System.out.println("Dice 1:" + d1.topFace() + " Dice 2:"
					+ d2.topFace() + " Dice 3: " + d3.topFace()+ " Total: "
					+ (d1.topFace()+d2.topFace()+d3.topFace()));
			System.out.println("You lose");
		}
	}
	
	public static void low (double betAmount, Wallet pW)
	{
		boolean isTriple = false;
		if(d1.roll() == d2.roll() && d2.roll() == d3.roll())
			isTriple = true;
		if(d1.roll() + d2.roll() + d3.roll() < 11 && !isTriple )
		{
			System.out.println("You win\nDice 1:" + d1.topFace() + " Dice 2:" 
					+ d2.topFace() + " Dice 3: " + d3.topFace() + " Total: "
					+ (d1.topFace()+d2.topFace()+d3.topFace()));
			double winnings = betAmount;
			pW.put(betAmount+winnings);
		}
		else
		{
			System.out.println("Dice 1:" + d1.topFace() + " Dice 2:"
					+ d2.topFace() + " Dice 3: " + d3.topFace()+ " Total: "
					+ (d1.topFace()+d2.topFace()+d3.topFace()));
			System.out.println("You lose");
		}
	}
	
	public static void summary(double initialCash, Wallet pW)
	{
		System.out.println("you started with: $" + initialCash );
		System.out.println("you now have: $" + pW.check() );
		System.out.println("You bet a total of: $" + totalBets);
		System.out.println("you are " + (initialCash<pW.check()?"up cash. Congrats":"down cash. Unlucky") );
	}
}
