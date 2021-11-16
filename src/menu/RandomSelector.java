package menu;
import java.util.Scanner;

public class RandomSelector {

	static boolean exit = false;	//Initial exit value, used to terminate program.
	
	//Inputscanner for text
	public static String stringput() {
		Scanner stringput = new Scanner(System.in);
		String input = stringput.next();
		return input;
	}
	//Cointosser, returns heads or tails.
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
	
	//Menu
	static String[] menu = {"empty", "Input stuff and choose for me!", "Flip a coin", "Exit program"};
	
	public static void main(String[] args) {
		
		do {
			System.out.println("Welcome to the random selector!\nPlease choose what you want to do:\n");
			for (int t = 1; t < menu.length; t++) {
				System.out.println(t + ". " + menu[t]);
			}
			System.out.println();
			int choice = Calculator.intScan();
			System.out.println();
			switch (choice) {
			case 1:
				break;
			
			case 2:	//Cointosser, user inputs heads or tails
				System.out.println("So you want to flip a coin eh?\nType either heads or tails:");
				String side = RandomSelector.stringput();
				System.out.println("");
				if(side.equals("heads") || side.equals("tails")) { //Check if user input is valid
					String temp = RandomSelector.cointoss();
					System.out.println("I'll flip this beautiful digital coin, and it's: " + temp);
					if(temp.equals(side)) { //Checks if user input matches the coin toss
						System.out.println("You won! :D\n");
					} else {
						System.out.println("Aww, you lost :'(\n");
					}
					Calculator.prompt();
				} else {
					System.out.println("That's not heads or tails! >:C");
				}
				System.out.println("");
				break;
			
			case 3: //Exits the program
				System.out.println("Goodbye!");
				exit = true;
				Calculator.prompt();
				break;
			
			default: //If user tries to input anything else than available options
				System.out.println("Wrong choice, please try again.\n");
			}
		} while (exit != true);
		
	}

}
