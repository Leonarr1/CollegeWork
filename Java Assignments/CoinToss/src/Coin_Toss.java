import java.util.Random;

public class Coin_Toss
{
	public static final int NUMBER_OF_TOSSES = 10000;
	public static final int HEADS = 1;
	public static final int TAILS = 0;

	public static void main(String[] args)
	{
		int numberOfHeads = 0;
		int coinToss = 0;
		int count = 0;
		Random generator = new Random();
		
		for (count = 0; (count < NUMBER_OF_TOSSES); count++)
		{
			coinToss = generator.nextInt(HEADS + 1);
			
			if (coinToss == HEADS)
			{
				numberOfHeads = numberOfHeads+1;
			}
		}
		System.out.println("From 10,000 coin tosses there were " + numberOfHeads + " heads and "
							+ (NUMBER_OF_TOSSES-numberOfHeads) + " tails. "
							+ "\nThe last coin toss was a " + (coinToss==1?"heads.":"tails."));
	}

}
