package view;

import java.util.Scanner;

import model.Account;

public class UserInterface {

	private static Scanner sc = new Scanner(System.in);
	private static int status = 0;
	
    public static void appStartMenu() {
    	System.out.println("SILBCORP SYSTEMS XXIIX");
    	System.out.println("==o==o==");
    	
    }	
    
    /**
     * Selection menu. Mostly system.out's, but line 26 takes an input and an account object.
     * The input goes first thru a check to make sure the input is within the given options
     * and that it's a valid integer. Input then goes into a switch loop and executes what 
     * user asked for
     */
    public static void selectionMenu(Account account) {
    	System.out.println("Would you like to...");
    	System.out.println("1) Withdraw \n2) Deposit\n3) Check account balance\n4) Exit");
    	System.out.println("Input option: ");
    	System.out.println("==o==o==");
    	selectAction(inputSelection(sc.nextLine()),account);
    	System.out.println("==o==o==");
    	
    }
    
    /**
     * Checks for valid input.
     * @param input
     * @return
     */
    public static int inputSelection(String input){
    	int selection = 0;
    	try {
    		selection = Integer.parseInt(input);
    	} catch(NumberFormatException e) {
    		System.out.println("Not a number!");
    	}
    	
    	if(selection > 4) {
    		System.out.println("Must be between 1 and 4!");
    		selection = 0;
    	}
    	
    	return selection;
    	
    }
    
    /**
     * Input amount to be placed in account object. Checks that its a valid number.
     * @param str
     * @return
     */
    private static int inputAmount(String str) {
    	int amt = 0;
    	try {
    		amt = Integer.parseInt(str);
    	} catch(NumberFormatException e) {
    		System.out.println("Not a number!");
    	} 
    	if(amt < 0) {
    		System.out.println("Cannot be negative numbers!");
    		amt = 0;
    	}
    	return amt;
    }
    
    /**
     * Switch loop where each case has function respective to action selected by user
     * @param selection
     * @param account
     */
    public static void selectAction(int selection, Account account) {
    	switch(selection) {
    	case 1:
    		selectWithdraw(account);
    		break;
    	case 2:
    		selectDeposit(account);
    		break;
    	case 3:
    		selectCheckBalance(account);
    		break;
    	case 4:
    		status = 1;
    	}
    		
    }

    public static void selectDeposit(Account account) {
    	System.out.println("Input amount to deposit: ");
    	Double amount = (double) inputAmount(sc.nextLine());
    	
    	account.insertToAccount(amount);
    }
    
    public static void selectWithdraw(Account account) {
    	System.out.println("Input amount to withdraw: ");
    	Double amount = (double) inputAmount(sc.nextLine());
    	
    	account.withdrawFromAccount(amount);
    }
    
    public static void selectCheckBalance(Account account) {
    	System.out.println("Your balance is: " + account.checkBalance());
    }
    
    /**
     * if status is 0, program runs. Checked on loop at main function.
     * @return
     */
    public static boolean appRunning() {
    	while(status==0) {
    		return true;
    	}
    	return false;
    }

}
