package controll;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.Account;

public class Logic {
	
	public static Logic instance = null;
	private static Account act = new Logic.PersonalAccount();
	
	public static Logic getInstance() {
		if(instance==null) {
			instance = new Logic();
		}
		return instance;
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
	
	public static Account getAccount() {
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
