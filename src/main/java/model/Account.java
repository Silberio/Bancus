package model;

public interface Account {
	
	/**
	 * Returns a double representing the monetary amount pressent in the account
	 * @return a double
	 */
	public double checkBalance();
	
	/**
	 * "Inserts" a double representing a monetary amount into the account
	 * @param amount
	 */
	public void insertToAccount(double amount);
	
	/**
	 * Subtracts a given amount from the account
	 * @param amount a double representing a monetary amount to subtract
	 */
	public void withdrawFromAccount(double amount);
	
	/**
	 * Initializes a user model that is to represent the login information (name and password)
	 * @param user
	 */
	public void setLoginInfo(UserModel user);
	
	/**
	 * Returns name and password for a given user model
	 * @return
	 */
	public UserModel getLoginInfo();
}
