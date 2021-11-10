package menu;
import java.util.Scanner;
import java.util.InputMismatchException;

/*
 * This is a simple calculator, created for fun.
 */

public class Calculator{
	
	//Initialise input and variables counter
	static int variables = 0;
	
	public static void main (String[] args) {
		System.out.println("Welcome to the calculator!\nPlease follow the onscreen instructions.\n");
		int input = 0;
		do{
			/*
			 * Takes user input for the amount of variables.
			 * It should only take integers as input or ask again.
			 */
			System.out.println("How many variables are there in your calculation?");
			
			try {
				Scanner inputScanner = new Scanner(System.in);
				input = inputScanner.nextInt();
				if(input > 1) {
					variables = input;
				} else {
					System.out.println("Invalid input, try again.");
					inputScanner.close();
			}} catch (InputMismatchException e){
				System.out.println("Please enter a valid number.");
				input = 0;
			} 
		} while(variables < 1);
		// Initialise a new array based on previous input and asks for the numbers.
		int[] varArr = new int[variables];
		System.out.println("\nGoing from left to right, please input the variables now.");
		for(int i=0; i < variables; i++) {
			System.out.println("Please input variable number " + (i+1) + ":");
			Scanner inputScanner = new Scanner(System.in);
			int temp = inputScanner.nextInt();
			varArr[i] = temp;
			inputScanner.close();
		}
		// Output of variables array.
		System.out.println("\nThese are all the variables you gave as input:");
		for(int temp = 0; temp < varArr.length; temp++) {
			System.out.println(varArr[temp]);
		}
		
	}
}