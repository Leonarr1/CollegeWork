import java.util.Scanner;
import javax.swing.JOptionPane;

public class Day_Of_The_Week 
{
	public static final int DAYS_IN_APRIL_JUNE_SEPT_NOV = 30;
	public static final int DAYS_IN_FEBRUARY_NORMALLY = 28;
	public static final int DAYS_IN_FEBRUARY_IN_LEAP_YEAR = 29;
	public static final int DAYS_IN_MOST_MONTHS = 31;
	public static final int NUMBER_OF_MONTHS = 12;

	public static void main(String[] args) 
{
		
		try {
			String input = JOptionPane.showInputDialog("Enter date (day/month/year):");
			Scanner scanner = new Scanner(input);
			scanner.useDelimiter("/");
			int date = scanner.nextInt();
			int month = scanner.nextInt();
			int year = scanner.nextInt();
			scanner.close();

			if (validDate(date, month, year)) 
			{
				JOptionPane.showMessageDialog(null, "The date was: " + dayOfTheWeek(date, month, year)
						+ ", the " + date + numberEnding(date) + " of " + monthFull(month) + " " + year +".");
			} 
			else 
			{
				JOptionPane.showMessageDialog(null, "" + date + "/" + month + "/" + year + " is not a valid date.",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (NullPointerException exception) {

		} catch (java.util.NoSuchElementException exception) {
			JOptionPane.showMessageDialog(null, "No number entered.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static boolean validDate(int date, int month, int year) // function from tutorial																
	{
		return ((year >= 0) && (month >= 1) && (month <= NUMBER_OF_MONTHS) && (date >= 1)
				&& (date <= daysInMonth(month, year)));
	}

	public static int daysInMonth(int month, int year) // function from tutorial
	{
		int numberOfDaysInMonth = DAYS_IN_MOST_MONTHS;
		switch (month) {
		case 2:
			numberOfDaysInMonth = isLeapYear(year) ? DAYS_IN_FEBRUARY_IN_LEAP_YEAR : DAYS_IN_FEBRUARY_NORMALLY;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			numberOfDaysInMonth = DAYS_IN_APRIL_JUNE_SEPT_NOV;
			break;
		default:
			numberOfDaysInMonth = DAYS_IN_MOST_MONTHS;
			break;
		}
		return numberOfDaysInMonth;
	}

	public static boolean isLeapYear(int year) // function from tutorial
	{
		return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
	}

	public static String dayOfTheWeek(int date, int month, int year)
	{
		
		double monthShifted = (month - 2 + 12)%12;
		monthShifted = (month - 2 + 12)%12;
		int yearInput = year;
		if (month <= 2)
			yearInput--;
		int yearCalculation = 5*(yearInput%4) + 4 * (yearInput%100) + 6 * (yearInput % 400);
		int dayValue = (date + (int)(2.6*monthShifted-0.2)+yearCalculation)%7;

		String days[] = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		String dayOfWeek = days[dayValue];
		return dayOfWeek;
	}

	public static String numberEnding(int date)
	{
		String ordinal[] = { "st", "nd", "rd", "th" };
		int ending = -1;
		switch (date) {
		case 1:
			ending = 0;
			break;
		case 2:
			ending = 1;
			break;
		case 3:
			ending = 2;
			break;
		default:
			ending = 3;
			break;
		}
		String ordinalEnding = ordinal[ending];
		return ordinalEnding;
	}

	public static String monthFull(int month) 
	{
		String monthNames[] = { "January", "Febuary", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		String monthFull = monthNames[month - 1];
		return monthFull;
	}
}