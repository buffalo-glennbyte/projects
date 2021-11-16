/**
 * This is the main menu from which you can run different projects.
 * Current status:
 * Calculator; 			Mainly done, only custom formula left
 * Number guesser;		Not even started
 * Age calc;			Not even started
 * Random selecter;		Not even started
 * Student enroller;	Not even started
 * Sports planner;		Not even started
 */

package menu;
import java.util.Scanner;
import java.util.InputMismatchException;


/**
 * @author Buffalo
 */
public class Menu {

	static //List of menu items
	String[] menu = {"Main Menu","Calculator","Student Registry","Numbers game","Random Selecter","Age Calculator","Sports Planner","Exit Program"};
	
	public static void main(String[] args) {
		// Start of main menu + options
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		System.out.println("Welcome to the main menu.\nPlease choose an option from below:");
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");
		for(int x = 1; x < menu.length; x++) {
			System.out.println(x + ". " + menu[x]);
		};
		System.out.println();
		
		//Accept user input and test it for validity:
		int choice = 0;
		do {
			try {
				Scanner inputScanner = new Scanner(System.in);
				int input = inputScanner.nextInt();
				if(input < menu.length && input > 0) {
					System.out.println("You've chosen: " + menu[input]);
					choice = input;
				} else {
					System.out.println("Wrong input, please try again.");	
			}} catch (InputMismatchException e){
				System.out.println("Please enter a number.");
			} 
		} while (choice == 0);
		System.out.println(choice);
		

		}
	}