import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Harness_Manager 
{
	public static final int ADD_OPTION = 0;
	public static final int REMOVE_OPTION = 1;
	public static final int CHECK_HARNESS_OPTION = 2;
	public static final int LOAN_HARNESS_OPTION = 3;
	public static final int RETURN_HARNESS_OPTION = 4;
	public static final int PRINT_HARNESS_LIST_OPTION = 5;
	public static final int EXIT_OPTION = -1;
		

	public static void main (String[] args) throws IOException
	{

		boolean runLoop = true;	
		HarnessRecords records = new HarnessRecords(Harness_Manager.class.getResourceAsStream("records.txt"));	// file must be placed in program directory

		while(runLoop)
		{
			String[] options = {"Add", "Remove", "Check Harness", "Loan Harness", "Return Harness", "Print Records" };
			int choice = JOptionPane.showOptionDialog(null, "Select the operation to preform.", null, JOptionPane.DEFAULT_OPTION,
					JOptionPane.QUESTION_MESSAGE,null,options, options[0]);
			if(choice == EXIT_OPTION)
				runLoop = false;
			if(choice == ADD_OPTION)
				add(records);
			if(choice == REMOVE_OPTION)
				remove(records);
			if(choice == CHECK_HARNESS_OPTION)
				check(records);
			if(choice == LOAN_HARNESS_OPTION)
				loan(records);
			if(choice == RETURN_HARNESS_OPTION)
				returnHarness(records);
			if(choice == PRINT_HARNESS_LIST_OPTION)
				printList(records);
		}
	}
	
	public static void add(HarnessRecords records)
	{
		String input = JOptionPane.showInputDialog("Enter new harness data seperated by spaces:\nMake Model and Instructor who checked it. ");
		Scanner inputS = new Scanner( input );
		String make = inputS.next().toLowerCase();
		int model = inputS.nextInt();
		String lastInstructorCheck = inputS.next();
		Harness newHarness = new Harness(make, model, lastInstructorCheck);
		records.addHarness(newHarness);
		JOptionPane.showMessageDialog(null,"Harness added:\n" + newHarness.toString());
		inputS.close();		
	}
	
	public static void remove(HarnessRecords records)
	{
		String input = JOptionPane.showInputDialog("Enter the make and model of the harness to remove");
		Scanner inputS = new Scanner( input );
		String make = inputS.next().toLowerCase();
		int model = inputS.nextInt();
		if(records.removeHarness(make, model) == null)
		{
			JOptionPane.showMessageDialog(null,"No such harness exists or it's on loan currently.");
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Harness removed");
		}	
		inputS.close();
	}
	
	public static void check(HarnessRecords records)
	{
		String input = JOptionPane.showInputDialog("Enter the name of the instructor, the make and the model.");
		Scanner inputS = new Scanner(input);
		String instructor = inputS.next();
		String make = inputS.next().toLowerCase();
		int model = inputS.nextInt();
		Harness harnessToCheck = null;
		harnessToCheck = records.checkHarness(instructor, make, model);
		if(harnessToCheck == null)
		{
			JOptionPane.showMessageDialog(null,"No such harness exists or it is currently on loan.");
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Harness:\n" + harnessToCheck.toString() + "\nChecked by: " + instructor);
		}
		inputS.close();
	}
	
	public static void loan(HarnessRecords records)
	{
		String input = JOptionPane.showInputDialog("Enter the name of the borrowing member.");
		Scanner inputS = new Scanner( input );
		String borrowingMember = inputS.next().toLowerCase();
		Harness harnessToLoan = null;
		harnessToLoan = records.loanHarness(borrowingMember);
		if(harnessToLoan == null)
		{
			JOptionPane.showMessageDialog(null,"No harness available.");
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Harness:\n" + harnessToLoan.toString() + "\nLoaned to: " + borrowingMember);
		}
		inputS.close();
	}
	
	public static void returnHarness(HarnessRecords records)
	{
		String input = JOptionPane.showInputDialog("Enter the make and model of the harness on loan.");
		Scanner inputS = new Scanner( input );
		String make = inputS.next().toLowerCase();
		int model = inputS.nextInt();
		Harness harnessToReturn = null;
		harnessToReturn = records.returnHarness(make, model);
		if(harnessToReturn == null)
		{
			JOptionPane.showMessageDialog(null,"No such harness exists.");
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Harness:\n" + harnessToReturn.toString() + "\nReturned. ");
		}	
		inputS.close();
		
	}
	
	public static void printList(HarnessRecords records)
	{
		String list = "";
		for(int i = 0; i < records.size(); i++)
		{
			list += ("Harness No. " + (i+1) + " " + records.getHarness(i).toString() + "\n");
		}
		JOptionPane.showMessageDialog(null,"" + list);
	}
	
}
