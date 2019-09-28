package controll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseConnection {
	private Statement st;
	private Connection conn;
	
	public DatabaseConnection() {
		
	}
	
	/**
	 * Establish connection to mysql database
	 * @throws SQLException
	 */
	public void connectToDB() throws SQLException {
		   conn = DriverManager.
		            getConnection("jdbc:mysql://localhost:3306/bancus", "root", "");
		   

		   System.out.println("connection established...");
		   /*
		   ResultSet rs = st.executeQuery("SELECT * FROM accounts");
		   
		   while(rs.next()) {
			   System.out.println(rs.getString("user") + " " + rs.getString("amt"));
		   } */

	}
	
	// We could use a USER object to put user from DB into logic
	
	/**
	 * Get first user from database
	 * @return int value with the 'amt' column index in database. i.e. returns a string with the amount of money in account
	 */
	public int getUser() {
		int result = -1;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM accounts");
			rs.next();
			result = rs.getInt("amt");
			
			System.out.println(result);
			//here, get a resultset with specific user ID

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * Get all accounts from DB and return an arraylist with them
	 * @return ArrayList populated with all values from database
	 */
	public ArrayList<String> getAllAccounts() {
		ArrayList<String> userAccounts = new ArrayList<String>();
		   
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM accounts");
			
			while(rs.next()) {
					userAccounts.add(rs.getString("user") + " " + rs.getString("amt"));
					System.out.println("user added...");
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userAccounts;
		
	}
	
	/**
	 * Updates the database account with the balance in the in-memory account
	 * @param amt
	 */
	public void updateAccount(int amt) {
		String sql = "UPDATE accounts SET amt = " + amt +
				" WHERE user_id = 1";
		try {
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Database updated...");
		
	}
	
	/**
	 * closes connection with Database
	 */
	public void closeConnection() {
        try {
			conn.close();
			System.out.println("Successful. Closing...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
