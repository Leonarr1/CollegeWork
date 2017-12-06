
public class Harness 
{
	private String make;
	private int model;
	private int timesUsed;
	private String lastInstructorCheck;
	private boolean isOnLoan;
	private String borrowingMember;
	private static int MAX_USES = 25;
	
	public Harness (String make, int model, int timesUsed, String lastInstructorCheck, boolean isOnLoan, String borrowingMember)
	{
		this.make = new String (make);
		this.model = model;
		this.timesUsed = timesUsed;
		this.lastInstructorCheck = lastInstructorCheck;
		this.isOnLoan = isOnLoan;
		this.borrowingMember = borrowingMember;
	}
	public Harness (String make, int model, String lastInstructorCheck)
	{
		this.make = make;
		this.model = model;
		this.timesUsed = 0;
		this.lastInstructorCheck = lastInstructorCheck;
		this.isOnLoan = false;
		this.borrowingMember = null;
	}
	
	public void checkHarness (String instructorName)
	{
		if( !isOnLoan )
		{
			lastInstructorCheck = instructorName;
			timesUsed = 0;
		}
		else
		{
			System.out.println("This Harness is currently on loan to " + borrowingMember + ".");
		}
	}
	
	public boolean isHarnessOnLoan()
	{
		return isOnLoan;
	}
	
	public boolean canHarnessBeLoaned()
	{
		boolean canBeLoaned = true;
		if(isOnLoan || timesUsed >= MAX_USES )
		{
			canBeLoaned = false;
		}
		return canBeLoaned;
	}
	
	public void loanHarness(String borrowingMember)
	{
		if(!isOnLoan || timesUsed >= MAX_USES)
		{
			this.borrowingMember = borrowingMember;
			isOnLoan = true;
		}
		else
		{
			System.out.println("Harness cannot be loaned.");
		}
	}
	
	public void returnHarness()
	{
		if(isOnLoan)
		{
			timesUsed++;
			isOnLoan = false;
			borrowingMember = null;
		}
		else
		{
			System.out.println("Harness is currently not on Loan.");
		}
	}
	
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public int getTimesUsed() {
		return timesUsed;
	}
	public void setTimesUsed(int timesUsed) {
		this.timesUsed = timesUsed;
	}
	public String getLastInstructorCheck() {
		return lastInstructorCheck;
	}
	public void setLastInstructorCheck(String lastInstructorCheck) {
		this.lastInstructorCheck = lastInstructorCheck;
	}
	public boolean isOnLoan() {
		return isOnLoan;
	}
	public void setOnLoan(boolean isOnLoan) {
		this.isOnLoan = isOnLoan;
	}
	public String getBorrowingMember() {
		return borrowingMember;
	}
	public void setBorrowingMember(String borrowingMember) {
		this.borrowingMember = borrowingMember;
	}
	public String toString()
	{
		return ("Make: " + make + " Model: " + model + " Times used: " + timesUsed 
				+ " On loan: " + (isOnLoan?"Yes":"No") + (isOnLoan?"":" Borrowing Member: " + borrowingMember));
		
	}
	
}
