package controll;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Account;

public class Logic {
	
	public static Logic instance = null;
	private static Account act = new Logic.PersonalAccount();
	private static DatabaseConnection dbconn = new DatabaseConnection();

	public static Logic getInstance() {
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

    	try {
			dbconn.connectToDB();
			int amt = dbconn.getUser();
			act.insertToAccount(amt);
			System.out.println("Connection succesful...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Takes balance form in-memory accuont and inserts value into Database,
	 * then closes connection.
	 */
	public void updateAndCloseConnectionWithDB() {
		int amt = (int) act.checkBalance();
		dbconn.updateAccount(amt);
		dbconn.closeConnection();
	}
	
	/**
	 * writes account balance to .txt file
	 * @throws IOException
	 */
	public static void writeReceipt() throws IOException {
		
		FileWriter fw = new FileWriter("out.txt");
		 
		fw.write(""+ act.checkBalance());
	 
		fw.close();
		
	}
	
	/**
	 * Reads the .txt receipt and loads whatever amount in there into the account object
	 * @throws IOException
	 */
	public static void readReceipt() throws IOException {
		
			FileReader fileReader = new FileReader("out.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);

		String line = null;
	            // Always wrap FileReader in BufferedReader.
	            while((line = bufferedReader.readLine()) != null) {
	            	act.insertToAccount(Double.parseDouble(line));
	            }   
	            // Always close files.
	            bufferedReader.close();         
	}

	
	public static void loadAccount(ArrayList<String> actList) {
		// here, get the ONE user and put it's amount into account
	}
	
	public Account getAccount() {
		return act;
	}

	protected static class PersonalAccount implements Account {
		
		private double balance = 0;

		public double checkBalance() {
			return balance;
		}

		public void insertToAccount(double amount) {
			this.balance += amount;
		}

		public void withdrawFromAccount(double amount) {
			this.balance -= amount;
		}
		
		
	}
}
