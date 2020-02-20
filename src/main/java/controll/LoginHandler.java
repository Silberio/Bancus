package controll;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Account;
import view.ApplicationGUI;

public class LoginHandler implements EventHandler<ActionEvent> {

	private TextField username = new TextField();
	private PasswordField password = new PasswordField();
	
	/**
	 * As of currently, this just prints out whatever is input in the username and password fields of the GUI
	 */
	public void handle(ActionEvent event) {
		System.out.println(username.getText());
		System.out.println(password.getText());
	}

	/**
	 * Set here the username and password fields from the GUI // I DON'T LIKE THIS SOLUTION
	 * @param username
	 * @param password
	 */
	public void setLoginFields(TextField username, PasswordField password) {
		this.username =  username;
		this.password = password;
	}
}
