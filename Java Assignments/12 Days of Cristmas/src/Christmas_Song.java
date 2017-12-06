
public class Christmas_Song 
{
	public static final int DAYS_OF_CHRISTMAS = 12;
	
	public static void main(String[] args) 
	{
		for(int currentDay = 0; currentDay < DAYS_OF_CHRISTMAS; currentDay++)
		{
			String dayOfChristmas[]  = {"first","second","third","fourth","fifth","sixth","seventh","eighth","nineth","tenth","eleventh","tweleveth"};
			System.out.println("On the " + dayOfChristmas[currentDay] + " day of Christmas my true love sent to me:");
			switch(currentDay)
			{
			case 11:
				System.out.println("12 Drummers Drumming");
			case 10:
				System.out.println("Eleven Pipers Piping");
			case 9:
				System.out.println("Ten Lords a Leaping");
			case 8:
				System.out.println("Nine Ladies Dancing");
			case 7:
				System.out.println("Eight Maids a Milking");
			case 6:
				System.out.println("Seven Swans a Swimming");
			case 5:
				System.out.println("Six Geese a Laying");
			case 4:
				System.out.println("Five Golden Rings");
			case 3:
				System.out.println("Four Calling Birds");
			case 2:
				System.out.println("Three French Hens");
			case 1:
				System.out.println("Two Turtle Doves");
			case 0:
				System.out.println("and a Partridge in a Pear Tree.\n");
			default :
				break;
			}
		}
	}

}
