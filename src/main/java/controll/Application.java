package controll;

import java.io.IOException;

import view.UserInterface;

public class Application {
	
    public static void main(String[] args) throws IOException {
    	Logic logic = Logic.getInstance();
    	logic.readReceipt();
    	
    	UserInterface.appStartMenu();
        while(UserInterface.appRunning()) {
        	UserInterface.selectionMenu(logic.getAccount());	
        }
        logic.writeReceipt();
        System.out.println("Quitting...");
        
    }
}
