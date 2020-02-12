package controll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.UserModel;

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
		System.out.println("FUNCTION: DatabaseConnection connectToDB called...");
		   conn = DriverManager.
		            getConnection("jdbc:mysql://localhost:3306/bancus", "root", "");
		   System.out.println("connection established...");
	}
	
	// We could use a USER object to put user from DB into logic
	
	
	/**
	 * Get first user from database
	 * @return UserModel object with all the info from database
	 */
	public UserModel getUserModel() {
		System.out.println("FUNCTION: DatabaseConnection getUserModel called...");
		UserModel result = new UserModel();
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM accounts");
			rs.next();
			result = new UserModel(rs.getInt("amt"), rs.getString("user"), rs.getInt("user_ID"), rs.getString("password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * return all users from the database in a single ArrayList 
	 * filled with UserModel objects
	 */
	public ArrayList<UserModel> retreiveAllUsersFromDB() {
		ArrayList<UserModel> users = new ArrayList<UserModel>();

		System.out.println("FUNCTION: DatabaseConnection retreiveAllUsersFromDB called...");
		try {
			UserModel result = new UserModel();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM accounts");

			while(rs.next()) {
				result = new UserModel(rs.getInt("amt"), rs.getString("user"), rs.getInt("user_ID"), rs.getString("password"));
				users.add(result);
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	
	/**
	 * Updates the database account with the balance in the in-memory account
	 * @param amt
	 */
	public void updateAccount(int amt) {
		System.out.println("FUNCTION: DatabaseConnection  updateAccount called...");
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
		System.out.println("FUNCTION: DatabaseConnection closeConnection called...");
        try {
			conn.close();
			System.out.println("Successful. Closing...");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
