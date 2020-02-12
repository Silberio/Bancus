package view;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Account;
import model.UserModel;

public class ApplicationGUI extends Application {
	
	static Account account;
	
	public static void setAccount(Account account) {
		System.out.println("FUNCTION: setAccount called...");
		ApplicationGUI.account = account;
	}
	    
	public static double selectCheckBalance() {
	    	return account.checkBalance();
	}
	    
	
	@Override
	public void start(Stage primaryStage) {
		System.out.println("FUNCTION: appGUI start called...");
		  primaryStage.setTitle("BANCUS MAXIMUS XII");
	        
	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER_LEFT);
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(25, 25, 25, 25));
	        
	        // scentitle is the text element that says "Welcome"
	        Text scenetitle = new Text("ATM SIMULATOR X0001");
	        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        
	        //adds scenetitle to grid (the whole container thingy)
	        grid.add(scenetitle, 0, 0, 2, 1);
	        // params are OBJECT then col, row, column span and row span

	        Label title = new Label("Welcome to ATM");
	        grid.add(title, 0, 1);
	        
	        Label input = new Label("Amount: ");
	        grid.add(input, 1, 2);
	        
	        final TextField userTextField = new TextField();
	        grid.add(userTextField, 4, 2);

	        final Text actiontarget = new Text();
	        grid.add(actiontarget, 4, 1);
	        
	        // Gridlines visible for debug
	        grid.setGridLinesVisible(false);

	        Button btn1 = new Button("Withdraw");
	        Button btn2 = new Button("Deposit");
	        Button btn3 = new Button("Check Balance");
	        Button btn4 = new Button("Cancel");

	        VBox vbBtn = new VBox(15);
	        vbBtn.setAlignment(Pos.BOTTOM_LEFT);
	        vbBtn.getChildren().addAll(btn1, btn2, btn3, btn4);
	        grid.add(vbBtn, 1, 1);
	        
	        
	        //RESTRICT INPUT FIELD
	        
	        userTextField.textProperty().addListener(new ChangeListener<String>() {
	            public void changed(ObservableValue<? extends String> observable, String oldValue, 
	                String newValue) {
	                if (!newValue.matches("\\d*")) {
	                    userTextField.setText(newValue.replaceAll("[^\\d]", ""));
	                }
	            }
	        });

	        
/*
 * BUTTON ACTION HANDLERS
 */
	        //Withdraw button
	        btn1.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            public void handle(ActionEvent e) {
	                double value = 0;
	                
	            	actiontarget.setFill(Color.FIREBRICK);
	                if(userTextField.getText().equals("")) {
	                	account.withdrawFromAccount(value);
	                	actiontarget.setText("Nothing withdrawn");
	                	
	                } else {
	                	value = Double.valueOf(userTextField.getText());
	                account.withdrawFromAccount(value);
	                userTextField.setText("");
	                actiontarget.setText("Withdrawn: $" + value);
	                }
	            }
	        });
	        
	        //Deposit button
	        btn2.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            public void handle(ActionEvent e) {
	                double value = 0;
	                
	            	actiontarget.setFill(Color.FIREBRICK);
	                if(userTextField.getText().equals("")) {
	                	account.insertToAccount(0);
	                	actiontarget.setText("Nothing deposited");

	                } else  {
	                	value = Double.valueOf(userTextField.getText());
	                account.insertToAccount(value);
	                actiontarget.setText("Deposited: $" + value);
	                userTextField.setText("");
	                }
	            }
	        });
	        
	        //Check Balance button
	        btn3.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            public void handle(ActionEvent e) {
	                actiontarget.setFill(Color.FIREBRICK);
	                DecimalFormat df = new DecimalFormat("#.###");
	                double value = selectCheckBalance();
	                actiontarget.setText("Balance: $" + df.format(value));
	            }
	        });
	        
	        //Exit button
	        btn4.setOnAction(new EventHandler<ActionEvent>() {
	        	 
	            public void handle(ActionEvent e) {
	                actiontarget.setFill(Color.LIME);
	                actiontarget.setText("");
	                userTextField.setText("");
	            }
	        });
	        
	        Scene scene = new Scene(grid, 500, 300);   
	        primaryStage.setScene(scene);        
	        primaryStage.show();
	        
	        loginPrompt(primaryStage);
	}
	


	/**
	 * This is a modal window that asks for login data. Username and password
	 * as stated in the database. Cancel exits the entire program, while login will
	 * tell the user if the input is wrong, otherwise it'll close the modal window
	 * @param primaryStage
	 */
	public void loginPrompt(Stage primaryStage) {
		System.out.println("FUNCTION: appGUI loginPrompt called...");
        Label secondLabel = new Label("Please Log in: ");
        final Label wrongLogin = new Label("");
        final TextField userName = new TextField();
        final PasswordField password = new PasswordField();
        Button loginBtn = new Button("Login");
        Button cancelBtn = new Button("Cancel");
        
        GridPane secondaryLayout = new GridPane();
        secondaryLayout.setAlignment(Pos.CENTER_LEFT);
        secondaryLayout.setHgap(10);
        secondaryLayout.setVgap(10);
        secondaryLayout.setPadding(new Insets(25, 25, 25, 25));

        secondaryLayout.add(secondLabel, 0, 0);
        secondaryLayout.add(wrongLogin, 1, 0);
        secondaryLayout.add(userName, 1, 1);
        secondaryLayout.add(password, 1, 2);
        secondaryLayout.add(loginBtn, 0, 3);
        secondaryLayout.add(cancelBtn, 1, 3);


        
        Scene secondScene = new Scene(secondaryLayout, 350, 200);

        // New window (Stage)
        final Stage newWindow = new Stage();
        newWindow.initStyle(StageStyle.UNDECORATED);
        newWindow.setTitle("Second Stage");
        newWindow.setScene(secondScene);

        // Specifies the modality for new window.
        newWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        newWindow.initOwner(primaryStage);

        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 130);
        newWindow.setY(primaryStage.getY() + 100);

        newWindow.show();
        
        
        /*
         * BUTTON LISTENERS
         */
        
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
        	// this button just checks whether the input in the textboxes coincides with
        	// the data given at the start of the program
        	public void handle(ActionEvent e) {
        		String nameInput = userName.getText();
        		String passInput = password.getText();
        		
        		if(nameInput.equals(
        				account.getLoginInfo().getName())
        				&
        				passInput.equals(account.getLoginInfo().getPassword())) {        			
        			newWindow.close();
        		} else {
        			System.out.println("wrong username or password");
        			wrongLogin.setText("Incorrect username or password");
        			wrongLogin.setTextFill(Color.RED);
        		}
        	}
        	// instead of that, maybe this function here should go to Logic class and be like
        	// "hey yo, user inserted these values into my textboxes, do u have these values in ur
        	// array?" and then logic is either "ya, true" or "nah bruh false" and given that, 
        	// this function will go thru with the login, and load ONLY the data from that given
        	// account that coincided with the login data given on textboxes
        	// otherwise, it should tell the user "bitch wrong ass password or username"
        	
        	// but how to = make THIS BUTTON LISTENER check if something in LOGIC class exists?
		});
        
        
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		System.exit(0);
        	}
		});
        
	}
	
	/**
	 * this bitch feels like too much logic inside what should be solely the view
	 * view shouldn't be handling login data
	 * @param users
	 */
	public void checkUsersArray(ArrayList<UserModel> users) {
		while(users.iterator().hasNext()) {
			//check if input == next.element.name && next.element.password
			
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println("FUNCTION: appGUI main called...");
		launch(args);
	}
}

