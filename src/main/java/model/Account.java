package model;

public interface Account {
	
	public double checkBalance();
	public void insertToAccount(double amount);
	public void withdrawFromAccount(double amount);
	public void setLoginInfo(UserModel user);
	public UserModel getLoginInfo();
}
