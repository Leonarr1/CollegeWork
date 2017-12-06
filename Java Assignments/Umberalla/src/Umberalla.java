import javax.swing.JOptionPane;

public class Umberalla {

	public static void main(String[] args) 
	{
		int answer = JOptionPane.showConfirmDialog(null, "Is it raining out?");
		boolean raining = (answer==JOptionPane.YES_OPTION);
		if (raining) 
		{
		JOptionPane.showMessageDialog(null, "Bring your umbrella and put it up.");
		}
		else
		{
		answer = JOptionPane.showConfirmDialog(null, "Does it look like it might rain out though?");
		JOptionPane.showMessageDialog(null, (answer==JOptionPane.YES_OPTION)?
				"Best bring the umbrella just incase then.":"Probably safe to leave the umbrella at home then.");
		}

	}

}
