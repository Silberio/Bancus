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
	
	public void handle(ActionEvent event) {
		System.out.println(username.getText());
		System.out.println(password.getText());
	}

	public void setLoginFields(TextField username, PasswordField password) {
		this.username =  username;
		this.password = password;
	}
}
