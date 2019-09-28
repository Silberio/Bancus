package controll;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import view.ApplicationGUI;
import view.UserInterface;

public class Application {

    public static void main(String[] args) throws IOException {

    	Logic logic = Logic.getInstance();

    	logic.establishConnectionWithDB();
    	ApplicationGUI.setAccount(logic.getAccount());
    	ApplicationGUI.main(args);
    	logic.updateAndCloseConnectionWithDB();
    	
    }
    
    
}
