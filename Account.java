package atm;

public class Account {
	
	private String userName;
	private String password;
	private double money;
	
	public Account(String userName,String password,double money)
	{
		this.userName = userName;
		this.password = password;
		this.money = money;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public double getMoney()
	{
		return money;
	}
	
	public void setMoney(double newMoney)
	{
		money=newMoney;
	}
	
	 @Override
	 public String toString() 
	 {
	    return "”√ªß√˚: " + userName + ", √‹¬Î: " + password + ", ”‡∂Ó: " + money;
	 }

}
