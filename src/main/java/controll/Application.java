package controll;

import java.io.IOException;

import view.ApplicationGUI;

public class Application {

    public static void main(String[] args) throws IOException {

    	Logic logic = Logic.getInstance();

    	logic.establishConnectionWithDB();
    	// Run login interface in Logic
    	ApplicationGUI.setAccount(logic.getAccount()); // THIS HAS TO CHANGE so that all accounts go in here
    	/*
    	 * pseudo:
    	 * 		appGUI takes in the whole ass array
    	 * 		inside appGUI, depending on the login info user puts
    	 * 		the account inside appGUI is initialized with only that accout
    	 * 		
    	 * 		is this bad practice??? is it bad to have all accounts in the GUI?
    	 * 		is the view holding too much logic? 
    	 * 		how do i optimize this bitch?
    	 * 
    	 * 		ok another possible solution:
    	 * 
    	 * 		array is only contained within LOGIC class
    	 * 		login button in GUI sends a "request" to logic
    	 * 		logic checks if the input in GUI coincides with any account in array
    	 * 
    	 * 		how to make it go round? logic.hasLoginData -> btnListener in GUI checks data from logic
    	 */
    	ApplicationGUI.main(args);
    	logic.updateAndCloseConnectionWithDB();
    	
    }
    
    
}
