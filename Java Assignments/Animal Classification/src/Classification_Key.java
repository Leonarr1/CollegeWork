import javax.swing.JOptionPane;

public class Classification_Key 
{

	
	
	public static void main(String[] args) 
	{
			int answer = JOptionPane.showConfirmDialog(null, "Is the animal warm blooded?");
			boolean warmBlood = (answer ==JOptionPane.YES_OPTION);
			if (warmBlood)
			{
				answer = JOptionPane.showConfirmDialog(null, "Does the animal have feathers?");
				boolean feathers = (answer ==JOptionPane.YES_OPTION);
				if (feathers)
				{
				JOptionPane.showMessageDialog(null, "This animal is a bird.");
				}
				else
				{
				JOptionPane.showMessageDialog(null, "This animal is a mammal.");
				}
			}
			else 
			{
				answer = JOptionPane.showConfirmDialog(null, "Does the animal have scales");
				boolean scales = (answer ==JOptionPane.YES_OPTION);
				if (scales)
				{
				answer = JOptionPane.showConfirmDialog(null, "Does the animal have fins");	
				boolean fins = (answer ==JOptionPane.YES_OPTION);
				if (fins)
				{
				JOptionPane.showMessageDialog(null, "This animal is a fish.");
				}
				else
				{
				JOptionPane.showMessageDialog(null, "This animal is a reptile.");
				}
				
			}	
			else
			{
			JOptionPane.showMessageDialog(null, "This animal is an amphibian.");
			}
		}
	}
}
