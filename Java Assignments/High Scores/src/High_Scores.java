import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class High_Scores 
{

	public static void main(String[] args)
	{
		try
		{
		String input = JOptionPane.showInputDialog("Enter the amount of scores you want to keep track of: ");
		Scanner inputScanner = new Scanner( input );
		if (inputScanner.hasNextInt())
		{
			 int arrayLength = inputScanner.nextInt();
			 int highScores[] = new int[arrayLength];
			 initialiseHighScores(highScores);
			 inputScanner.close();
			 
			 String nextInput = JOptionPane.showInputDialog("Enter the next score: ");
			 Scanner scoreInput = new Scanner(nextInput);
			 
			 for (int index = 0; index < arrayLength; index++)
			 {
					 		 
				 if (scoreInput.hasNextInt())
				 {
					 int nextScore = scoreInput.nextInt();
				 
					 insertScore(highScores, nextScore);
				 }
				 else
				 {	 
					 scoreInput.next();
					 JOptionPane.showMessageDialog(null, "You must enter an integer as the score.",
								"Error", JOptionPane.ERROR_MESSAGE);
					 index--;
				 }
				 nextInput = JOptionPane.showInputDialog("Enter the next score: ");
				 
			 }
			

			 printHighScores(highScores);
			 
			 
		}
		else
			JOptionPane.showMessageDialog(null, "You must enter an integer as the amount of scores to keep track of.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		
		catch (NullPointerException exception)
		{
		
		}
	}
	
	public static void initialiseHighScores(int[] highScores)
	{
		for(int index = 0; index >= highScores.length; index++ )
		{
			highScores[index] = 0;
		}
	}
	
	public static void printHighScores(int[] highScores) 
	{
		JOptionPane.showMessageDialog(null, "High Scores: " + "\n" + Arrays.toString(highScores));
	}
	
	public static boolean higherThan(int newScore, int scoreToCheck)
	{
		return newScore >= scoreToCheck;
	}
	
	public static void insertScore(int[] highScores, int nextScore)
	{
		int index = 0;
		int lastElement = (highScores.length) - 1;
		boolean finished = false;
		while(!finished)
		{
			if (index > highScores.length)
			{
				finished = true;
			}
			else if (higherThan(nextScore, highScores[index]))
			{
				for(int scorePlace = lastElement; scorePlace > index; scorePlace--)
				{
				 
				 int scoreBefore = scorePlace - 1;
				 highScores[scorePlace] = highScores[scoreBefore];
				 
				}
				highScores[index] = nextScore;
				finished = true;
			}
			index++;
		}
		
	}

}
