
public abstract class Test 
{

	public static void main(String[] args) 
	{
		int year =2015;
		int month = 06;
		int date = 06;
		double monthShifted;
		int dow;
		monthShifted = (month - 2 + 12)%12;
		if (month <= 2)
		{
			year--;
		}
		
		int firstDigitsOfYear = year / 100;
		int lastDigitsOfYear = year % 100;
		
		int y = 5*(year%4) + 4 * (year%100) + 6 * (year % 400);
		
		dow = (date + (int)(2.6*monthShifted-0.2)+y)%7;
		
		
		
		double dayValue = (date + Math.floor(2.6*(((month+9)%12)+1)-0.2)+lastDigitsOfYear+Math.floor(lastDigitsOfYear/4)+Math.floor((firstDigitsOfYear/4)-(2*firstDigitsOfYear)))%7;
		dayValue = Math.abs(dayValue);
		String days[] = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		System.out.println(days[dow] + "\n" + firstDigitsOfYear + "\n" + lastDigitsOfYear );
	}

}
