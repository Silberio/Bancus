package controll;

import java.io.IOException;

import view.ApplicationGUI;

public class Application {

    public static void main(String[] args) throws IOException {

    	Logic logic = Logic.getInstance();

    	logic.establishConnectionWithDB();
    	// Run login interface in Logic
    	ApplicationGUI.setAccount(logic.getAccount()); // This has to disappear at some point 
    	ApplicationGUI.main(args);
    	logic.updateAndCloseConnectionWithDB();
    	
    	/*
    	 * ok so the new deal is to somehow make the GUI functional without having it to be connected to the
    	 * backend. What I mean is, make the GUI "dumb"
    	 * 
    	 * Next step is to figure out how to get the input from GUI out to the Logic
    	 * 
    	 * possible fix:
    	 * 		have login handler initialized inside the logic
    	 * 		logic retrieves input data from GUI?
    	 * 		login initialized in the main method and delegated to GUI and Logic?
    	 * 		getters and setters?
    	 * 
    	 * 			HOW TO GET DATA FROM GUI INTO LOGIC
    	 * 		
    	 * next from that:
    	 * 		logic processes input data
    	 * 		logic retrieves matching data from database
    	 * 		logic then puts that data into the GUI
    	 * 
    	 */
    }
    
    
}
