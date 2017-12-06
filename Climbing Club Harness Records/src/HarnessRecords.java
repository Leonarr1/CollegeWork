import java.io.IOException;
import java.util.ArrayList;

public class HarnessRecords 
{
	private ArrayList<Harness> harnessList = new ArrayList<Harness>();
	
	public HarnessRecords(ArrayList<Harness> harnessList)
	{
		this.harnessList = harnessList;
	}

	public HarnessRecords(java.io.InputStream records) throws IOException 
	{
		int content;
		boolean isOnLoan;
		String file = "";
		while((content = records.read()) != -1)
		{
			file += (char) content;
			System.out.print((char) content); 
		}
		String[] lines = file.split("\r\n");
		int noOfLines = Integer.parseInt(lines[0]);
		for(int i = 1; i < noOfLines+1; i++)
		{
			String line = lines[i];
			String[] splitLine = line.split(",");
			String make = splitLine[0].toLowerCase();
			int model = Integer.parseInt(splitLine[1]);
			int timesUsed = Integer.parseInt(splitLine[2]);
			String lastInstructorCheck = splitLine[3].toLowerCase();
			if(splitLine[4].equals("true"))
			{
				isOnLoan = true;
			}
			else
			{
				isOnLoan = false;
			}
			String borrowingMember = splitLine[5].toLowerCase();
			this.harnessList.add(new Harness(make, model, timesUsed, lastInstructorCheck, isOnLoan, borrowingMember));
		}
		
		
	}

	public boolean isEmpty()
	{
		return harnessList.isEmpty();
	}

	public void addHarness(Harness newHarness)
	{
		try
		{
			if(newHarness != null)
				harnessList.add(newHarness);
			else
				System.out.println("Error: null harness entry");
		}
		catch (Exception e)
		{
			System.out.println("Error: invalid entry");
		}
	}

	public Harness findHarness(String makeQuery, int modelQuery)
	{
		Harness foundHarness = null;
		Harness harnessToCheck = null;
		if(makeQuery == null || modelQuery <= 0)
		{
			System.out.println("Error: invalid query.");
		}
		for(int i = 0; i < harnessList.size(); i++)
		{
			harnessToCheck = harnessList.get(i);
			if(harnessToCheck.getMake().equals(makeQuery) && harnessToCheck.getModel() == modelQuery)
				foundHarness = harnessToCheck;
		}

		return foundHarness;

	}

	public Harness checkHarness(String instructorName, String make, int model)
	{
		Harness harnessToUpdate = null;
		Harness harnessToCheck = null;
		try
		{
			harnessToCheck = this.findHarness(make, model);
			if(harnessToCheck != null && !harnessToCheck.isOnLoan())
			{
				harnessToUpdate = harnessToCheck;
				harnessToUpdate.checkHarness(instructorName);
			}		
		}
		catch(NullPointerException e)
		{
			System.out.println("Invalid Entry");
		}
		return harnessToUpdate;
	}

	public Harness loanHarness(String borrowingMemeber)
	{
		Harness harnessToLoan = null;
		Harness harnessToCheck = null;
		try
		{
			for(int i = 0; i < harnessList.size(); i++)
			{
				harnessToCheck = harnessList.get(i);
				if(harnessToCheck.canHarnessBeLoaned())
				{
					harnessToLoan = harnessToCheck;
					harnessToLoan.loanHarness(borrowingMemeber);
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Invalid Entry");
		}
		return harnessToLoan;
		
	}

	public Harness returnHarness(String make, int model)
	{
		Harness harnessToReturn = null;
		try
		{
			harnessToReturn = this.findHarness(make, model);
			if(harnessToReturn != null && harnessToReturn.isOnLoan())
			{
				harnessToReturn.returnHarness();
			}
			else if(harnessToReturn != null && !harnessToReturn.isOnLoan())
			{
				System.out.println("Error: This harness is not currently on loan.");
			}
		}
		catch (Exception e)
		{
			System.out.println("Invalid Entry");
		}
		return harnessToReturn;
	}
	
	public Harness removeHarness(String make, int model)
	{
		Harness harnessToRemove = null;
		Harness harnessToCheck = null;
		try
		{
			for(int i = 0; i < harnessList.size(); i++)
			{
				harnessToCheck = this.findHarness(make, model);
				if(harnessToCheck != null && !harnessToCheck.isOnLoan())
				{
					harnessList.remove(i);
					harnessToRemove = harnessToCheck;
				}
			}		
		}
		catch (Exception e)
		{
			System.out.println("Invalid Entry");
		}
		return harnessToRemove;

	}
	
	public int size()
	{
		return this.harnessList.size();
	}
	
	public Harness getHarness(int index)
	{
		return this.harnessList.get(index);
	}

}
