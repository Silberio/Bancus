package controll;

import java.io.FileNotFoundException;
import java.io.IOException;

import view.UserInterface;

public class Application {
	
    public static void main(String[] args) throws IOException {
    	Logic logic = Logic.getInstance();
    	try {
    		logic.readReceipt();
    	} catch (FileNotFoundException e) {
    		System.out.println("... Could not find previous account record");
    	}
    	
    	UserInterface.appStartMenu();
        while(UserInterface.appRunning()) {
        	UserInterface.selectionMenu(logic.getAccount());	
        }
        logic.writeReceipt();
        System.out.println("Quitting...");
        
    }
}
