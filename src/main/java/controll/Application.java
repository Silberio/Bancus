package controll;

import java.io.IOException;

import view.ApplicationGUI;

public class Application {

    public static void main(String[] args) throws IOException {

    	Logic logic = Logic.getInstance();

    	logic.establishConnectionWithDB();
    	logic.receiveUserLogin();
    	// Run login interface in Logic
    	ApplicationGUI.setAccount(logic.getAccount());
    	ApplicationGUI.main(args);
    	logic.updateAndCloseConnectionWithDB();
    	
    }
    
    
}
