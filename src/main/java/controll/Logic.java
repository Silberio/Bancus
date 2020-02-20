package controll;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Account;
import model.UserModel;

public class Logic {
	
	public static Logic instance = null;
	private static Account act = new Logic.PersonalAccount();
	private static DatabaseConnection dbconn = new DatabaseConnection();

	/**
	 * Singleton (for some reason...) instantiation of the Logic class
	 * @return
	 */
	public static Logic getInstance() {
		System.out.println("FUNCTION: Logic getInstance created...");
		if(instance==null) {
			instance = new Logic();
		}
		return instance;
	}
	
	/*
	 * DATABASE CONNECTION
	 */
	
	/**
	 * Initialize internal connection with mysql database
	 */
	public void establishConnectionWithDB() {
		System.out.println("FUNCTION: Logic establishConnectionWithDB called...");
    	try {
			dbconn.connectToDB();
			
			//	grab arraylist from DBconn
			// 	somehow put the arraylist so that it's available for the gui
			UserModel user = dbconn.getUserModel();
			act.insertToAccount(user.getAmt());
			act.setLoginInfo(user);
			System.out.println("Connection succesful...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * Takes balance form in-memory account and inserts value into Database,
	 * then closes connection.
	 */
	public void updateAndCloseConnectionWithDB() {
		System.out.println("FUNCTION: Logic updateAndCloseConnection called...");
		int amt = (int) act.checkBalance();
		dbconn.updateAccount(amt);
		dbconn.closeConnection();
	}
	
	


	public Account getAccount() {
		return act;
	}

	protected static class PersonalAccount implements Account {
		
		private double balance = 0;
		private UserModel user;

		public double checkBalance() {
			return balance;
		}

		public void insertToAccount(double amount) {
			this.balance += amount;
		}

		public void withdrawFromAccount(double amount) {
			this.balance -= amount;
		}


		public void setLoginInfo(UserModel user) {
			this.user = user;			
		}

		public UserModel getLoginInfo() {
			return user;
		}
	}
}
