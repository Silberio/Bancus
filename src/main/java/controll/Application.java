package controll;

import java.io.IOException;

import view.ApplicationGUI;

public class Application {

    public static void main(String[] args) throws IOException {

    	Logic logic = Logic.getInstance();

    	logic.establishConnectionWithDB();
    	// Run login interface in Logic
    	ApplicationGUI.setAccount(logic.getAccount()); // THIS HAS TO CHANGE so that all accounts go in here
    	ApplicationGUI.main(args);
    	logic.updateAndCloseConnectionWithDB();
    	
    }
    
    
}
