package menu;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
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
	static private Instant start;
	static private Instant stop;
	static private boolean cheating = false;
	
	private static void setRange () {
		do{ // A loop asking for first and last integer of the range, only exits if 'first' is lower than 'last'
			System.out.println("\nPlease give me the first number of the range: (Cannot be 0)");
			NumberGuesser.setFirst(Calculator.intScan());
			System.out.println("\nPlease give me the last number of the range:");
			NumberGuesser.setLast(Calculator.intScan());
			if (first >= last) {
				System.out.println("\nPlease make the beginning of the range lower than the end of the range.\n");
				}
			} while (first >= last);
	}
	
	// Function to set the beginning of the range
	private static void setFirst (int newFirst) {
		first = newFirst;
	}
	
	// Function to set the end of the range
	private static void setLast (int newLast) {
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
			timeElapsed = duration.toHoursPart() + " hour(s) " + duration.toMinutesPart() + " minutes " + duration.toSecondsPart() + " seconds";
		} else if(duration.toMinutesPart() > 0) {
			timeElapsed = duration.toMinutesPart() + " minutes " + duration.toSecondsPart() + " seconds";
		} else {
			timeElapsed = duration.toSecondsPart() + " seconds";
		}
		return timeElapsed;
	}
	
	// Beginning of the program, initialises variables and starts loop after user has read the instructions.
	public static void main(String[] args) {
		boolean exit = false;
		Random rand = new Random();
		
		System.out.println("Welcome to the number guesser!\nThis game is fairly simple, you set a range from which one of us randomly chooses a number.\nOnce a number is chosen, the other gets to guess!");
		boolean exit1 = false;
		NumberGuesser.setFirst(0); // reset the first int from the range
		do { 	//Beginning of the main loop
			do {
					if (first == 0) { // If the first int of the range equals 0, the user gets to set a new range.
						NumberGuesser.setRange();
					}
						
					// Calculate the total range and ask user if the range is correct
					int range = last - first + 1;
					System.out.println("\nIs the range from " + first + " to " + last + " correct?");
					String answer = RandomSelector.stringput().toString().toLowerCase();
					switch (answer) {
					case "y": case "yes":
						exit1 = true;
						System.out.println("\nDo you want to guess or should I guess?");
						String answer2 = RandomSelector.stringput().toString().toLowerCase();
				
						switch (answer2) {
						case "me": case "i": // User gets to guess, program chooses an random integer in range and asks for a guess. Case ends if user guesses correctly. 
							tries = 0; // Resets the 'tries' counter
							int generatedNumber = (rand.nextInt(range) + first);
							System.out.println("\nGenerating random number between " + (first -1) + " and " + (last + 1) + "..\nDone! :)");
							NumberGuesser.startTime();
							boolean exit2 = false;
							if (cheating = false) {
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
								System.out.println("\nIt took you " + tries + " tries, you guessed it in: " + NumberGuesser.timeElapsed() + ".");
								Calculator.prompt();
								exit1 = true;
								break;
							} else {
								String nmbrs = "1234567890";
								int guess = 0;
								do {
									System.out.println("\nWhat is your guess? Answer 'quitter' to quit.");
									StringBuilder ans = RandomSelector.stringput();
									if(ans.toString().compareToIgnoreCase("quitter") == 0) {
										System.out.println("\nSo you give up eh? I expected nothing less..");
										exit2 = true;
										NumberGuesser.stopTime();
										break;
									} else if(ans.toString().contains(nmbrs)) {
										try {
											guess = Integer.parseInt(ans.toString());
										} catch (NumberFormatException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									if(guess < first || guess > last) {
										System.out.println("\nThat's outside of the range stupid! So I'm not gonna count this as a real guess.");
									} else if(guess == generatedNumber) {
										System.out.println("\nOooooh, very close but no cigar. Try again!");
										tries++;
									} else {
										System.out.println("\nNope, try again!");
										tries++;
									}
								} while(exit2 != true);
								System.out.println("\nIt took you " + tries + " tries, but never guessed it. Hopefully that'll teach you to not cheat again ;)\nYou've wasted: " + NumberGuesser.timeElapsed() + ".");
								Calculator.prompt();
								exit1 = true;
								break;
							}
				
						case "you": // Program has to guess, user memorizes a number from the range
							tries = 0; // Resets the 'tries' counter
							ArrayList<Integer> guesses = new ArrayList<Integer>();
							guesses.clear(); 
							System.out.println("\nAllright, but I'm not gonna stop until I guessed it! :D\nDo you have a number in mind?");
							Calculator.prompt();
							NumberGuesser.startTime();
							boolean cheat = false;
							boolean exit3 = false;
							int genGuess;
							do {
								boolean isValid = false;
								do { // Loop that generates a random integer in the range, if it has been generated before; it generates an new integer.
									genGuess = (rand.nextInt(range) + first);
									if(guesses.size() == range) { // Checks if the amount of guesses equal the total range, if true then it breaks both loops and
										System.out.println("\nYou haven't been paying attention, I guessed every number in the range!\nSo I automatically win and I will close this match.");
										cheat = true;
										cheating = true;
										isValid = true;
										exit3 = true;
										NumberGuesser.stopTime();
									}
									if (!(guesses.contains(genGuess))) { // If the generated guess isn't in the guesses array, then break the loop
										isValid = true;
									}
								} while(isValid != true);
								if (exit3 != true) {
									System.out.println("\nIs " + genGuess + " the number you chose?");
									String answer3 = RandomSelector.stringput().toString().toLowerCase();
									switch (answer3) {
									case "y":
									case "yes":
										NumberGuesser.stopTime();
										System.out.println("\nYay, I did it \'mother\'board! :D");
										exit3 = true;
										break;
									case "n":
									case "no":
										System.out.println("\nAwww, are you sure? :c\nOh well, I'll just keep on guessing.");
										guesses.add(genGuess);	// Adds the generated guess to the array
										isValid = false;		// Flips the guess generating loop condition back
										tries++;				// Increments the total amount of tries
										break;
									default:
										System.out.println("\nPlease enter 'yes' or 'no");
										break;
									}
								}
							} while(exit3 != true);
							if (cheat) { 	// If the user 'cheated', this gets run.
								System.out.println("\nYou've wasted " + NumberGuesser.timeElapsed() + " of our time, thanks for that!");
								Calculator.prompt();
							} else {		// Otherwise this gets run
								System.out.println("\nIt took me " + tries + " tries and I did it in: " + NumberGuesser.timeElapsed() + ".");
								Calculator.prompt();
							}
							break;
						default:
							System.out.println("\nPlease input 'me' or 'you'.");
							break;
						}
						exit1 = true;
						break;
					case "n": case "no": // User gets to change beginning and the end of the range
						NumberGuesser.setRange();
						break;
					default: // If the user gives an invalid input
						System.out.println("\nPlease enter 'yes' or 'no'.");
						break;
					}
				} while(exit1 != true); // Condition of the guess game loop
				System.out.println("\nDo you want to play again?");
				String replay = RandomSelector.stringput().toString().toLowerCase();
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
		}while(exit != true); // Condition of the main menu loop

	}
}
