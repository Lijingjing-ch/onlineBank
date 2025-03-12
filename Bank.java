package atm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Bank {
	
	private Account a;
	
	

	public void creatUser(String userName,String password)
	{
		try {
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/data?serverTimezone=UTC&useSSL=false","root","123456789abc");
			String insert = "INSERT INTO account VALUE(?,?,0.0)";
            PreparedStatement prepareStatement = conn.prepareStatement(insert);
            prepareStatement.setString(1,userName);
            prepareStatement.setString(2, password);
            int line = prepareStatement.executeUpdate();
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}

	
	public boolean login(String userName,String password)
	{
		try {
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/data?serverTimezone=UTC&useSSL=false","root","123456789abc");
			String log = "SELECT * FROM `account` WHERE userName=? AND password=?";
            PreparedStatement prepareStatement = conn.prepareStatement(log);
            prepareStatement.setString(1,userName);
            prepareStatement.setString(2, password);
            ResultSet result = prepareStatement.executeQuery();
            if(result.next())
            	{
            	a = new Account(userName,password,result.getDouble("money"));
            	return true;
            	}
            else
            	return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
	

	public void logout()
	{
		a = null;
		flag=false;

	}
	
	public void changePassword(String password)
	{
		try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data?serverTimezone=UTC&useSSL=false", "root", "123456789abc");
            String update = "UPDATE account SET password = ? WHERE username = ?";
            PreparedStatement prepareStatement = conn.prepareStatement(update);
            prepareStatement.setString(1, password);
            prepareStatement.setString(2, a.getUserName());
            int rowsAffected = prepareStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		

	}
	
	public void deposit(double inMoney)
	{
		double nowMoney=queryBalance()+inMoney;
		try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data?serverTimezone=UTC&useSSL=false", "root", "123456789abc");
            String update = "UPDATE account SET money = ? WHERE username = ?";
            PreparedStatement prepareStatement = conn.prepareStatement(update);
            prepareStatement.setDouble(1, nowMoney);
            prepareStatement.setString(2, a.getUserName());
            int rowsAffected = prepareStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}
	
	public void withdrawal(double outMoney)
	{
		double nowMoney=queryBalance()-outMoney;
		try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data?serverTimezone=UTC&useSSL=false", "root", "123456789abc");
            String update = "UPDATE account SET money = ? WHERE username = ?";
            PreparedStatement prepareStatement = conn.prepareStatement(update);
            prepareStatement.setDouble(1, nowMoney);
            prepareStatement.setString(2, a.getUserName());
            int rowsAffected = prepareStatement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }

	}

	public double queryBalance()
	{
		double balance = 0.0;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data?serverTimezone=UTC&useSSL=false", "root", "123456789abc");
            String query = "SELECT money FROM account WHERE username = ?";
            PreparedStatement prepareStatement = conn.prepareStatement(query);
            prepareStatement.setString(1, a.getUserName());
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                balance = resultSet.getDouble("money");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
	}
	
    public boolean flag;
	
	public void addInterest()
	{
		try {
            while (flag) {
              
                double money = queryBalance();
                money *= 1.05;
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data?serverTimezone=UTC&useSSL=false", "root", "123456789abc");
                String update = "UPDATE account SET money = ? WHERE username = ?";
                PreparedStatement prepareStatement = conn.prepareStatement(update);
                prepareStatement.setDouble(1, money);
                prepareStatement.setString(2, a.getUserName());
                prepareStatement.executeUpdate();
                Thread.sleep(10000);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
			
			
}

		
	
	public void createAndStartThread()
	{
		Thread thread = new Thread(new Runnable() {
			public void run()
			{
				addInterest();
			}
		});
		thread.start();
	}


}
