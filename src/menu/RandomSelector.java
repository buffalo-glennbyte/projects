package menu;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * This little program currently has 2 functions:
 * - User inputs options he cannot decide between and the program chooses one.
 * - Coin tosser, user guesses which side it will land on and the program flips a coin and tells you if you've won.
 * 
 * In the future I might add another function, same as the first except user can add "weight" to the options.
 * That way some options have a higher or lower chance, for example:
 * User can't decide what to vegetable to add the meal, inputs different vegetables in the program but likes carrot more than brussel sprouts.
 * User is then able to give "give weight" to carrots so it has a higher chance than sprouts.
 */

public class RandomSelector {

	static boolean exit = false;	// Initial exit value, only modified if user selects exit program.
	static boolean stop = false;	// Initial stop value, used in the do while loop for user input in the random selector
	
	// Input scanner for text
	public static String stringput() {
		Scanner stringput = new Scanner(System.in);
		String input = stringput.next();
		return input;
	}
	// Coin tosser, returns heads or tails.
	public static String cointoss() {
		String result = null;
		int temp = (int)(Math.random() * 10) + 1;
		if(temp < 6) {
			result = "heads";
		} else if(temp >= 6) {
			result = "tails";
		}
		return result;
	}
	
	// Menu
	static String[] menu = {"empty", "Input stuff and choose for me!", "Flip a coin", "Exit program"};
	
	public static void main(String[] args) {
		// Beginning of the menu loop, only stops when user selects exit program.
		do {
			System.out.println("Welcome to the random selector!\nPlease choose what you want to do:\n");
			for (int t = 1; t < menu.length; t++) {
				System.out.println(t + ". " + menu[t]);
			}
			System.out.println();
			int choice = Calculator.intScan();
			System.out.println();
			switch (choice) {
			case 1: // Random selector, user inputs choices and program randomly chooses.
				System.out.println("Allright, so you can't decide between a couple of options you say? Let me help you out ;)\nWhat are the first 2 entries for the list? (seperated with enter)");
				ArrayList<String> list = new ArrayList<String>();
				list.add(RandomSelector.stringput().toLowerCase());
				list.add(RandomSelector.stringput().toLowerCase());
				do { // Array input loop, only stops when user enters "n".
					// NEED TO REWRITE LOOP, IF USER INPUTS ANYTHING ELSE RELOOP WITHOUT INPUT.
					System.out.println("\nDo you want to add another entry?");
					switch (RandomSelector.stringput().toLowerCase()) {
					case "yes":
					case "y":
						list.add(RandomSelector.stringput().toLowerCase());
						break;
					case "no":
					case "n":
						stop = true;
						break;
					default:
					System.out.println("\nPlease enter Yes or No.");
					}
				} while(stop != true);
				// Prints out every entry in the array, gives user the option to remove an entry and then waits until user presses enter.
				boolean remove = false;
				do {
					System.out.println("\nCurrently, these are all the entries:\n");
					for (int t=0; t < list.size(); t++) {
						System.out.println((t+1) + ". " + list.get(t));
					}
					System.out.println("\nDo you want to Add/Remove an entry or Continue?");
					switch (RandomSelector.stringput().toLowerCase()) {
					case "c":
					case "continue":
						remove = true;
						break;
					case "r":
					case "remove":
						System.out.println();
						for (int t=0; t < list.size(); t++) {
							System.out.println((t+1) + ". " + list.get(t));
						}
						System.out.println("\nWhich entry do you want to remove?");
						int entry = Calculator.intScan() -1;
						System.out.println("\nRemoving entry: " + list.get(entry));
						list.remove(entry);
						break;
					case "a":
					case "add":
						System.out.println("\nWhat do you want to add?");
						list.add(RandomSelector.stringput().toLowerCase());
						break;
					default:
						System.out.println("\nPlease input Continue, Remove or Add.");
					}
				} while(remove != true);
				
				
				Calculator.prompt();
				
				
				break;
			
			case 2:	// Coin tosser, user inputs heads/tails and program tells them if they won.
				System.out.println("So you want to flip a coin eh?\nType either heads or tails:");
				String side = RandomSelector.stringput().toLowerCase();
				System.out.println("");
				if(side.equals("heads") || side.equals("tails")) { //Check if user input is valid
					String temp = RandomSelector.cointoss();
					System.out.println("I'll flip this beautiful digital coin, and it's: " + temp);
					if(temp.equals(side)) { //Checks if user input matches the coin toss
						System.out.println("You won! :D");
					} else {
						System.out.println("Aww, you lost :'(");
					}
					Calculator.prompt();
				} else {
					System.out.println("That's not heads or tails! >:C");
				}
				System.out.println("");
				break;
			
			case 3: // Exits the program
				System.out.println("Goodbye!");
				exit = true;
				Calculator.prompt();
				break;
			
			default: // If user tries to input anything else than available options
				System.out.println("Wrong choice, please try again.\n");
			}
		} while (exit != true);
		
	}

}
