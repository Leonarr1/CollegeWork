import javax.swing.JOptionPane;


public class Square_Ages 
{
	public static final int LIFE_EXPECTANCY = 123;
	public static final int THIS_YEAR = 2016;

	public static void main(String[] args) 
	{
		int ageCount = 0;
		int ageSquared = 0;
		
		for (ageCount = 0; ageCount<LIFE_EXPECTANCY; ageCount++)
		{
			ageSquared = ageCount*ageCount;
			
			if (ageSquared >= THIS_YEAR-LIFE_EXPECTANCY && ageSquared <= THIS_YEAR+LIFE_EXPECTANCY && ageSquared-ageCount < THIS_YEAR && ageSquared-ageCount > THIS_YEAR - LIFE_EXPECTANCY)
			{
				JOptionPane.showMessageDialog(null, "Age: " + ageCount + " Year: " + ageSquared + " \nThey are currently: " + (ageCount-(ageSquared-THIS_YEAR)) + " years old.");
			}
		}
	}

}
