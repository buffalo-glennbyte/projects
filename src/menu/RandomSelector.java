package menu;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/*
 * This little program currently has 2 functions:
 * - User inputs options he cannot decide between and the program chooses one.
 * - Coin tosser, user guesses which side it will land on and the program flips a coin and tells you if you've won.
 * 
 * In the future I might add another function, similar to the first function except user can add "weight" to the options.
 * That way some options have a higher or lower chance, for example:
 * User can't decide what to vegetable to add the meal, inputs different vegetables in the program but likes carrot more than brussel sprouts.
 * User is then able to give "give weight" to carrots so it has a higher chance than sprouts.
 */

public class RandomSelector {

	static boolean exit = false;	// Initial exit value, only modified if user selects exit program.
	
	// Input scanner for text
	public static StringBuilder stringput() {
		Scanner stringput = new Scanner(System.in);
		StringBuilder input = new StringBuilder(stringput.next());
		return input;
	}
	// Coin tosser, returns heads or tails.
	public static String cointoss() {
		boolean coin = ThreadLocalRandom.current().nextBoolean();
		String result = "failure";
		if(coin == true) {
			result = "heads";
		} else if(coin == false) {
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
			int menuChoice = Calculator.intScan();
			System.out.println();
			
			switch (menuChoice) {
			case 1: // Random selector; creates an dynamic array list, takes user choices and program randomly chooses.
				ArrayList<String> list = new ArrayList<String>();
				System.out.println("Allright, so you can't decide between a couple of options you say? Let me help you out ;)\nPlease enter all the options, seperated with an enter.\nType 'Continue' if you want to continue to the next phase.\n");
				boolean Break = false;
				do {
					String input = RandomSelector.stringput().toString();
					switch (input) {
					case "Continue":
						Break = true;
						break;
					default:
						list.add(input.toLowerCase());
						break;
					}
				} while (Break != true);
				
				// Prints out every entry in the array
				boolean cont = false;
				do {
					System.out.println("\nCurrently, these are all the entries:\n");
					for (int t=0; t < list.size(); t++) {
						System.out.println((t+1) + ". " + list.get(t));
					}
					// Starts a loop in which the user can edit entries or continue
					System.out.println("\nDo you want to Add/Remove an entry or Continue?");
					switch (RandomSelector.stringput().toString().toLowerCase()) {
					case "c": case "continue":
						cont = true;
						break;
						
					case "r": case "remove":
						System.out.println();
						for (int t=0; t < list.size(); t++) {
							System.out.println((t+1) + ". " + list.get(t));
						}
						System.out.println("\nWhich entry do you want to remove?");
						int entry = Calculator.intScan() -1;
						System.out.println("\nRemoving entry: " + list.get(entry));
						list.remove(entry);
						break;
						
					case "a": case "add":
						System.out.println("\nWhat do you want to add?");
						list.add(RandomSelector.stringput().toString().toLowerCase());
						break;
						
					default:
						System.out.println("\nPlease input 'Continue', 'Remove' or 'Add'.");
						break;
					}
				} while(cont != true);
				// Beginning of selection loop, asks if user wants to "reroll"
				System.out.println("\nSo now I'll choose one of the options from the list.");
				boolean again = false;
				do {
					int RSchoice = (int)(Math.random() * list.size());
					System.out.println("And I've randomly chosen: " + list.get(RSchoice));
					System.out.println("Are you satisfied or do you want me to choose randomly again?");
					String choice = RandomSelector.stringput().toString();
					switch (choice) {
					case "a" : case "again":
						break;
						
					case "y" : case "yes":
						again = true;
						break;
						
					default:
						System.out.println("\nPlease input 'yes' or 'again'.");
						break;
					}
				} while (again != true);
				System.out.println("\nThanks for letting me choose for you for a change, somedays I get really tired of always letting you choose for me :'c");
				Calculator.prompt();
				
				break;
			
			case 2:	// Coin tosser, user inputs heads/tails and program tells them if they won.
				System.out.println("So you want to flip a coin eh?\nType either 'heads' or 'tails':");
				boolean stop = false;
				int wins = 0, loss = 0 , coinheads = 0, cointails = 0, userheads = 0, usertails = 0;
				// The tracking variables get initialised above me, game loop starts underneath me.
				do {
					String side = RandomSelector.stringput().toString().toLowerCase();
					System.out.println("");
					switch(side) {
					case "t": case "tails": case "h": case "heads":
						// Logic to convert single letter to full length word and track users choice
						if(side.equals("t")) {
							side = "tails";
						} else if(side.equals("h")) {
							side = "heads";
						} 
						if(side.equals("heads")) {
							userheads++;
						} else if(side.equals("tails")) {
							usertails++;
						}
						
						String coin = RandomSelector.cointoss();
						// Logic to add coin toss result to tracker variable
						if(coin.equals("heads")) {
							coinheads++;
						} else if(coin.equals("tails")) {
							cointails++;
						}
						
						System.out.println("I'll flip this beautiful digital coin, and it's: " + coin);
						// Checks if user input matches the coin toss
						if(coin.equals(side)) { 
							System.out.println("You won! :D");
							wins++;
						} else {
							System.out.println("Aww, you lost :'(");
							loss++;
						}
						// Replay question, if user answers no then the loop stops
						System.out.println("\nWant to play again?");
						String replay = RandomSelector.stringput().toString().toLowerCase();
						switch(replay) {
						case "yes": case "y":
							System.out.println("\nPick a side:");
							break;
						case "no": case "n":
							stop = true;
							break;
						default: // If the user inputs an invalid answer
							System.out.println("Please answer 'yes' or 'no'.");
							break;
						}
						break;
					default: // If the user inputs an invalid answer
						System.out.println("That's not heads or tails! >:C\nPlease input 'heads' or 'tails':");
						break;
					}
				} while(stop != true);
				// This prints out the tracking stats and winrate, afterwards jumps back to menu
				System.out.println("\nIt was fun playing with you! These are the stats:");
				System.out.println("I flipped the coin " + (wins+loss) + " times, " + coinheads + " heads and " + cointails + " tails.");
				System.out.println("You choose heads " + userheads + " times and tails " + usertails + " times.");
				System.out.println("Wins: " + wins);
				System.out.println("Losses: " + loss);
				System.out.println("Winrate: " + ((wins * 100.0f) / (wins+loss)) + "%");
				Calculator.prompt();
				break;
				
			case 3: // Exits the program
				System.out.println("Goodbye!");
				exit = true;
				Calculator.prompt();
				break;
			
			default: // If user tries to input anything else than available options
				System.out.println("Wrong choice, please try again.\n");
				break;
			}
		} while (exit != true);
		
	}

}
