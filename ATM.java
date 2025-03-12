package atm;

import java.util.Scanner;


public class ATM {
	
	private Scanner sc;
	
	public ExcHandler e = new ExcHandler();
	
	private Bank b = new Bank();
	
	public void log()
	{
		while(true)
		{
			System.out.println("Please select your transaction:");
			System.out.println("1.Creat a new account");
			System.out.println("2.Log in your account");
			System.out.println("3.Exit ATM");
			sc=new Scanner(System.in);
			int select=sc.nextInt();
			sc.nextLine();
			try {
				if(select>3)
					throw e;
			} catch(ExcHandler e) {
				e.handler();
			}
			
			if(select==1)
			{
				System.out.println("Please enter your user name.");
				String userName = sc.nextLine();
				System.out.println("Please enter your password.");
				String password = sc.nextLine();
				b.creatUser(userName, password);
				
			}
			if(select==2)
			{
				System.out.println("Please enter your user name.");
				String userName = sc.nextLine();
				System.out.println("Please enter your password.");
				String password = sc.nextLine();
				
				boolean log = b.login(userName, password);
				
				if(log)
				{
					System.out.println("Success to log.");
					b.createAndStartThread();
					menu();
					
				}
				else
					System.out.println("Fail to log.");
				
				
			}
			if(select==3)
			{
				System.out.println("Exit."); 
				break;
			}
			
		}
	}
	
	
	public void menu()
	{
		while(true)
		{
			System.out.println("Please select your transaction:");
			System.out.println("1.Deposit");
			System.out.println("2.Withdrawal");
			System.out.println("3.Query Balance");
			System.out.println("4.Change password");
			System.out.println("5.Log out");
			
			sc=new Scanner(System.in);
			int select=sc.nextInt();
			sc.nextLine();
			try {
				if(select>5)
					throw e;
			} catch(ExcHandler e) {
				e.handler();
			}
			
			if(select==1)
			{
				System.out.println("Please enter your input money.");
				double inMoney=sc.nextDouble();
				
				try {
					if(inMoney<=0)
						throw e;
				} catch(ExcHandler e)
				{
					e.handler_deposit();
				}
				
				b.deposit(inMoney);
				
			}
			if(select==2)
			{
				System.out.println("Please enter your output money.");
				double outMoney=sc.nextDouble();
				
				try {
					if(outMoney<=0)
						throw e;
					if(outMoney>b.queryBalance())
						throw e;
				} catch(ExcHandler e)
				{
					e.handler_withdrawal();
				}
				
				b.withdrawal(outMoney);
				
			}
			if(select==3)
			{
				System.out.println("Now your money is: "+b.queryBalance()+" ."); 
			}
			if(select==4)
			{
				System.out.println("Please enter your new password.");
				String password = sc.nextLine();
				b.changePassword(password);
				
			}
			if(select==5)
			{
				b.logout();
				log();
				
			}
			
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ATM atm = new ATM();
		atm.log();

	}

}
