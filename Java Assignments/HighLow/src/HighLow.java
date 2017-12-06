import java.util.Random;
import javax.swing.JOptionPane;

public class HighLow 
{

	public static final int GUESSES_TO_WIN = 4;
	public static final int MAX_NUMBER = 12;
	public static final int HIGHER_OPTION = 0;
	public static final int LOWER_OPTION = 1;
	public static final int EQUALS_OPTION = 2;
	public static final int EXIT_OPTION = -1;
	

	public static void main(String[] args) 
	{

		Random  generator = new Random();
		int firstCard = 2 + generator.nextInt(MAX_NUMBER+1);
		int correctConsecutiveGuesses = 0;
		String faceCards[]  = {"a Jack","a Queen","a King","an Ace"}; //Face cards stored in an array

		boolean isHigher = false;
		boolean isLower = false;
		boolean isEqual= false;
		boolean runLoop = true;

		while (runLoop)
		{
			JOptionPane.showMessageDialog(null, "Your card is " + (firstCard < 11 ? firstCard : faceCards[firstCard-11]) );
			
			int nextCard = 2 + generator.nextInt(MAX_NUMBER+1); 
			if (firstCard<nextCard)
				isHigher = true;
			if (firstCard>nextCard)
				isLower = true;
			if (firstCard==nextCard)
				isEqual = true;

			int guessCounter = GUESSES_TO_WIN-(correctConsecutiveGuesses+1);	    	

			String[] guess = {"Higher", "Lower", "Equal"};
			int choice = JOptionPane.showOptionDialog(null, "What will the next card be?", null, JOptionPane.DEFAULT_OPTION,
					JOptionPane.QUESTION_MESSAGE,null,guess, guess[0]);
			if (choice == EXIT_OPTION)
				runLoop= false;
			else
			{
				JOptionPane.showMessageDialog(null, "The card was  " + (nextCard < 11 ? nextCard : faceCards[nextCard-11] ) + "\nYou guessed the card would be " + guess[choice]);

				if ( isHigher && choice == HIGHER_OPTION )
				{
					correctConsecutiveGuesses++;
					JOptionPane.showMessageDialog(null, "Correct!" + "\nGuesses left to win: " + guessCounter ); 
				}
				else if ( isLower && choice == LOWER_OPTION )
				{
					correctConsecutiveGuesses++;
					JOptionPane.showMessageDialog(null, "Correct!" + "\nGuesses left to win: " + guessCounter ); 
				}
				else if ( isEqual && choice == EQUALS_OPTION )
				{
					correctConsecutiveGuesses++;
					JOptionPane.showMessageDialog(null, "Correct!" + "\nGuesses left to win: " + guessCounter ); 
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Incorrect!");
					correctConsecutiveGuesses = 0;
				}

				if (correctConsecutiveGuesses==GUESSES_TO_WIN)
				{
					runLoop = false;
					JOptionPane.showMessageDialog(null, "Congrats you win!");
				}

				isHigher = false;
				isLower = false;
				isEqual= false;

				firstCard = nextCard;
			}  
		}	    
	}
}