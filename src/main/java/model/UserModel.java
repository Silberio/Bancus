package model;

public class UserModel {
	private int amt;
	private String name;
	private int ID;
	
	/**
	 * Initialize empty UserModel object
	 */
	public UserModel() {}
	
	/**
	 * initialize UserModel with monetary amount, name and ID
	 * @param amt
	 * @param name
	 * @param ID
	 */
	public UserModel(int amt, String name, int ID) {
		this.amt = amt;
		this.name = name;
		this.ID = ID;
	}
	
	public UserModel(int amt, String name) {
		this.amt = amt;
		this.name = name;
	}

	public int getAmt() {
		return amt;
	}

	public void setAmt(int amt) {
		this.amt = amt;
	}

	/**
	 * Returns the name of the user account
	 * @return String with name of user
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public String toString() {
		return "User: " + this.name + " amount: " + this.amt;
	}
	
	
}
