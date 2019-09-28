package view;

import java.text.DecimalFormat;

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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Account;

public class ApplicationGUI extends Application {
	
	static Account account;
	
	public static void setAccount(Account account) {
		ApplicationGUI.account = account;
	}
	    
	public static double selectCheckBalance() {
	    	return account.checkBalance();
	}
	    
	
	@Override
	public void start(Stage primaryStage) {
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
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

