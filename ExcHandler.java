package atm;

public class ExcHandler extends Exception{
	
	public void handler()
	{
		System.out.println("Error! This is not involved!");
	}

	public void handler_deposit()
	{
		System.out.println("Error! You cannot deposit!");
	}
	
	public void handler_withdrawal()
	{
		System.out.println("Error! You cannot withdrawal!");
	}
}
