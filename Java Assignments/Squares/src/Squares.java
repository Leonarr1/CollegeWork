
public class Squares 
{

	public static void main(String[] args) 
	{
		int ageCount = 0;
		int ageSquared = 0;
		
		for (ageCount = 0; ageCount<124; ageCount++)
		{
			ageSquared = ageCount*ageCount;
			System.out.println("Age: " + ageCount + "^2 = " + ageSquared);
					
		}
	}

}
