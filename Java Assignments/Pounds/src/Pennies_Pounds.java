import java.util.Scanner;
import javax.swing.JOptionPane;

public class Pennies_Pounds 
{
	//declaring constants for conversion rates
	
	public static final int OLD_SHILINGS_PER_OLD_POUND = 20;
	public static final int OLD_PENNIES_PER_OLD_SHILINGS = 12;
	public static final int NEW_PENNIES_PER_OLD_PENNY = 67;
	public static final int NEW_PENNIES_PER_NEW_POUND = 100;
	
	
	public static void main(String[] args) {
		
		//initialise scanner with delimiters for the three separate values
		
		String input = JOptionPane.showInputDialog("Enter amount of Old pounds, shilings "
				+ "and pennies in the form (pounds:shilings:pennies) ");
		Scanner inputScanner = new Scanner( input );
		inputScanner.useDelimiter(":");
		int oldPounds = inputScanner.nextInt();
		int oldShilings = inputScanner.nextInt();
		int oldPennies = inputScanner.nextInt();
		inputScanner.close();
		
		int totalShilings = (int) (oldPounds*OLD_SHILINGS_PER_OLD_POUND) + oldShilings;
		int totalOldPennies = (int) (totalShilings*OLD_PENNIES_PER_OLD_SHILINGS) + oldPennies;
		int totalNewPennies = (int) (totalOldPennies*NEW_PENNIES_PER_OLD_PENNY);
		int totalNewPounds = (int) (totalNewPennies/NEW_PENNIES_PER_NEW_POUND);
		int remainderPennies = (int) (totalNewPennies%NEW_PENNIES_PER_NEW_POUND);
		
		//I found using int does run into overflow errors with large values
		
		JOptionPane.showMessageDialog(null, "Value in modern currency : £" + totalNewPounds + "." + remainderPennies);
	}

}	
		
		
		
				



	


