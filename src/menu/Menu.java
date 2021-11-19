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


/**
 * @author Buffalo
 */
public class Menu {

	static //List of menu items
	String[] menu = {"null","Calculator","Student Registry","Number guesser","Random Selector","Age Calculator","Sports Planner","Exit Program"};
	
	public static void main(String[] args) {
		boolean exit = false;
		do {
			// Start of main menu + options
			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
			System.out.println("Welcome to the main menu.\nPlease choose an option from below:");
			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");
			for(int x = 1; x < menu.length; x++) {
				System.out.println(x + ". " + menu[x]);
			};
			System.out.println();
			
			// Start of menu loop, stops only if user activates exit program.
			int choice = Calculator.intScan();
			switch (choice) {
			case 1: // Calculator
				Calculator.main(args);
				break;
			case 2: //Student registry
				break;
			case 3: //Number guesser
				NumberGuesser.main(args);
				break;
			case 4: //Random selector
				RandomSelector.main(args);
				break;
			case 5: //Age calculator
				break;
			case 6: //Sports planner
				break;
			case 7: //Exit program
				System.out.println("Goodbye!");
				exit = true;
				Calculator.prompt();
				break;
			default:
				System.out.println("Wrong input, try again.\n");
			}
		} while (exit != true);
		}
	}