package menu;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

	/*
	 * This little program asks the user for a first and last number of an range.
	 * It then it asks if the user want to guess or make the computer guess.
	 * If the user decides to guess, the program generates a random integer in the earlier defined range and keeps asking for input until the user guesses correctly.
	 * If the user decides the program has to guess, the program generates a random integer in the range and asks if it guessed right.
	 * Once the user or program guessed correctly, there will be an output of the amount of tries and total duration.
	 */

public class NumberGuesser {

	static private int first, last, tries;
	static Instant start;
	static Instant stop;
	
	// Function to set the beginning of the range
	public static void setFirst (int newFirst) {
		first = newFirst;
	}
	// Function to set the end of the range
	public static void setLast (int newLast) {
		last = newLast;
	}
	// Function to place a time stamp for when either begins guessing
	public static void startTime() {
		Instant tempstart = Instant.now();
		start = tempstart;
	}
	// Function to place a time stamp for when either guess it right
	public static void stopTime() {
		Instant tempstop = Instant.now();
		stop = tempstop;
	}
	// Function to measure the difference between the time stamps and outputs it in the correct format
	public static String timeElapsed() {
		String timeElapsed = "";
		Duration duration = Duration.between(start, stop);
		if(duration.toHoursPart() > 0) {
			timeElapsed = duration.toHoursPart() + " hour(s) " + duration.toMinutesPart() + " minutes " + duration.toSecondsPart() + " seconds.";
		} else if(duration.toMinutesPart() > 0) {
			timeElapsed = duration.toMinutesPart() + " minutes " + duration.toSecondsPart() + " seconds.";
		} else {
			timeElapsed = duration.toSecondsPart() + " seconds.";
		}
		return timeElapsed;
	}
	
	// Beginning of the program, initialises variables and starts loop after user has read the instructions.
	public static void main(String[] args) {
		boolean exit = false;
		Random rand = new Random();
		
		System.out.println("Welcome to the number guesser!\nThis game is fairly simple, you set a range from which one of us randomly chooses a number.\nOnce a number is chosen, the other gets to guess!");
		// Beginning of the main loop
		do {
			if (first == 0) {
				do{ // A loop asking for first and last integer of the range, only exits if 'first' is lower than 'last'
					System.out.println("\nPlease give me the first number of the range:");
					NumberGuesser.setFirst(Calculator.intScan());
					System.out.println("\nPlease give me the last number of the range:");
					NumberGuesser.setLast(Calculator.intScan());
					if (first >= last) {
						System.out.println("\nPlease make the beginning of the range lower than the end of the range.\n");
						}
					} while (first >= last);
				}
						
			// Calculate the total range and ask user if the range is correct
			int range = last - first + 1;
			boolean exit1 = false;
			System.out.println("\nIs the range from " + first + " to " + last + " correct?");
			String answer = RandomSelector.stringput().toLowerCase();
			switch (answer) {
			case "y": case "yes":
				System.out.println("\nDo you want to guess or should I guess?");
				String answer2 = RandomSelector.stringput().toLowerCase();
				
				switch (answer2) {
				case "me": case "i": // User gets to guess, program chooses an random integer in range and asks for a guess. Case ends if user guesses correctly. 
					int generatedNumber = (rand.nextInt(range) + first);
					System.out.println("\nGenerating random number between " + (first -1) + " and " + (last + 1) + "..\nDone! :)");
					NumberGuesser.startTime();
					boolean exit2 = false;
					do {
						System.out.println("\nWhat is your guess?");
						int guess = Calculator.intScan();
						if(guess < first || guess > last) {
							System.out.println("\nThat's outside of the range stupid! So I'm not gonna count this as a real guess.");
						} else if(guess == generatedNumber) {
							NumberGuesser.stopTime();
							System.out.println("\nAmazing, you guessed correctly!");
							exit2 = true;
						} else {
							System.out.println("\nNope, try again!");
							tries++;
						}
					} while(exit2 != true);
					System.out.println("\nIt took you " + tries + " tries, you guessed it in: " + NumberGuesser.timeElapsed());
					Calculator.prompt();
					exit1 = true;
					break;
				
				case "you": // User chooses an number in the range and the program guesses.
					System.out.println("\nAllright, but I'm not gonna stop until I guesses it! :D");
					NumberGuesser.startTime();
					boolean exit3 = false;
					do {
						int generatedGuess = (rand.nextInt(range) + first);
						System.out.println("\nIs " + generatedGuess + " the number you chose?");
						String answer3 = RandomSelector.stringput().toLowerCase();
						switch (answer3) {
						case "y": case "yes":
							NumberGuesser.stopTime();
							System.out.println("\nYay, I did it \"mother\"board! :D");
							exit3 = true;
							break;
						case "n": case "no":
							System.out.println("\nAwww, are you sure? :c\nOh well, I'll just keep on guessing.");
							tries++;
							break;
						default:
							System.out.println("\nPlease enter 'yes' or 'no");
							break;
						}
					} while(exit3 != true);
					System.out.println("\nIt took me " + tries + " tries and I did it in: " + NumberGuesser.timeElapsed());
					Calculator.prompt();
					break;
				default:
					System.out.println("\nPlease input 'me' or 'you'.");
					break;
				}
				exit1 = true;
				break;
			case "n": case "no": // User gets to change beginning and the end of the range
				do{ // A loop asking for first and last integer of the range, only exits if 'first' is lower than 'last'
					System.out.println("\nPlease give me the first number of the range:");
					NumberGuesser.setFirst(Calculator.intScan());
					System.out.println("\nPlease give me the last number of the range:");
					NumberGuesser.setLast(Calculator.intScan());
					if (first >= last) {
						System.out.println("\nPlease make the beginning of the range lower than the end of the range.\n");
						}
					} while (first >= last);
				break;
			default: // If the user gives an invalid input
				System.out.println("\nPlease enter 'yes' or 'no'.");
				break;
			} while(exit1 != true);
			System.out.println("\nDo you want to play again?");
			String replay = RandomSelector.stringput();
			switch (replay) {
			case "y": case "yes":
				NumberGuesser.setFirst(0);
				NumberGuesser.setLast(0);
				break;
			case "n": case "no":
				System.out.println("\nGoodbye!");
				Calculator.prompt();
				exit = true;
				break;
			default:
				System.out.println("\nPlease enter 'yes' or 'no'.");
				break;
			}
		} while(exit != true);
	}
}
