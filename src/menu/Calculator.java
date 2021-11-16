package menu;
import java.util.Scanner;

/*
 * This is a simple calculator, created for fun and learning.
 * The custom formula is incomplete, currently takes user input and stores it in a array.
 * Need to implement logic so user can use the array indexes with the calculator functions.
 * Would be nice if user can select which array indexes he wants to use with which function.
 * 
 * Edit math functions to not return value, useful for the custom formula.
 * 
 * Any feedback is more than welcome!
 * Message me @ glennbit.gb@gmail.com
 */

public class Calculator{
	
	//Initialise input and variables counter
	static int varAmount; 				//Total amount of variables in the custom formula
	static double result;				//Result of function
	static int intput;					//Result of integer input scanner
	static double douput;				//Result of double input scanner
	static boolean exit = false;		//Exit flag
	
	//Different input scanners, both return input.
	public static int intScan() {
		boolean temp = true;
		do {
			try {
				Scanner intScan = new Scanner(System.in);
				intput = intScan.nextInt();
				temp = false;
			} catch (Exception e) {
				System.out.println("Not a number, try again.");
			}
		} while (temp);
		return intput;
	}	
	public static double douScan() {
		boolean temp = true;
		do {
			try {
				Scanner douScan = new Scanner(System.in);
				douput = douScan.nextDouble();
				temp = false;
			} catch (Exception e) {
				System.out.println("Not a number, try again.");
			}
		}while(temp);
		return douput;
	}
	public static void prompt() {
		System.out.println("Press enter to continue..");
		Scanner prompt = new Scanner(System.in);
		prompt.nextLine();
	}
	
	//Calculator functions, all take 2 variables except root and square.
	//All the result in the line print get show as a float, otherwise the result would sometimes look odd.
	public static double plus(double var1, double var2) {
		result = var1 + var2;
		System.out.println(var1 + " + " + var2 + " = " + (float)result);
		Calculator.prompt();
		return result;
		}
	public static double min(double var1 ,double var2) {
		result = var1 - var2;
		System.out.println(var1 + " - " + var2 + " = " + (float)result);
		Calculator.prompt();
		return result;
	}
	public static double div(double var1, double var2) {
		result = var1 / var2;
		System.out.println(var1 + " : " + var2 + " = " + (float)result);
		Calculator.prompt();
		return result;
	}
	public static double tim(double var1, double var2) {
		result = var1 * var2;
		System.out.println(var1 + " x " + var2 + " = " + (float)result);
		Calculator.prompt();
		return result;
	}
	public static double root(double var1) {
		result = Math.sqrt(var1);
		System.out.println("The square root of " + var1 + " is: " + (float)result);
		Calculator.prompt();
		return result;
		}
	public static double sqr(double var1) {
		result = var1 * var1;
		System.out.println(var1 + " squared is: " + (float)result);
		Calculator.prompt();
		return result;
	}
	
	//Menu list
	static String[] menu = {"empty","Addition","Subtraction","Division","Multiplication","Squareroot","Squared","Custom formula","Exit calculator"};
	
	//Main program
	public static void main (String[] args) {
		System.out.println("Welcome to the calculator!");
		
		//Beginning of the menu loop, keeps looping until the user modifies the boolean exit with option 7.
		do {
			System.out.println("\nWhat kind of calculation do you want to perform?\nPlease note you can input decimals with a . when asked for input.\n");
			for(int t=1; t < menu.length; t++) {
				System.out.println(t + ". " + menu[t]);
			}
			System.out.println();
			int choice = Calculator.intScan(); 
			// Above takes users choice as integer input, then matches it to a case underneath.
			// If it doesn't match anything it will reload the menu. 
			switch (choice) {
			
			case 1: //Addition choice
				System.out.println("Please input two variables, commit each with enter:");
				Calculator.plus(Calculator.douScan(), Calculator.douScan());
				break;
				
			case 2: //Subtraction choice
				System.out.println("Please input two variables, commit each with enter:");
				Calculator.min(Calculator.douScan(), Calculator.douScan());
				break;
				
			case 3: //Division choice
				System.out.println("Please input two variables, commit each with enter:");
				Calculator.div(Calculator.douScan(), Calculator.douScan());
				break;
				
			case 4: //Multiplication choice
				System.out.println("Please input two variables, commit each with enter:");
				Calculator.tim(Calculator.douScan(), Calculator.douScan());
				break;
				
			case 5: //Square root choice
				System.out.println("Please input the number you want to find the square root of:");
				Calculator.root(Calculator.douScan());
				break;
				
			case 6: //Squared choice
				System.out.println("Please input the number you want squared:");
				Calculator.sqr(Calculator.douScan());
				break;
				
			case 7: //Custom formula, NOT FINISHED YET.
				do{
					/*
					 * Takes user input for the amount of variables.
					 * It should only take integers as input or ask again.
					 */
					System.out.println("UWU! PLEASE NOTICE ME SENPAI, I AM CURRENTLY INCOMPLETE SO PLEASE BE GENTLE.");
					System.out.println("How many variables are there in your calculation?");
					int input = Calculator.intScan();
					if(input > 1) {
						varAmount = input;
					} else {
						System.out.println("Too few variables, try again.\n");
					}
				} while(varAmount < 1);
				
				// Initialise a new array based on previous input and asks for the numbers.
				double[] varArr = new double[varAmount];
				System.out.println("\nGoing from left to right, please input the variables now.");
				for(int i=0; i < varAmount; i++) {
					System.out.println("Please input variable number " + (i+1) + ":");
					double temp = Calculator.douScan();
					varArr[i] = temp;	
				}
				
				// Output of variables array.
				System.out.println("\nThese are all the variables you gave as input:");
				for(int t = 0; t < varArr.length; t++) {
					System.out.println(varArr[t]);
				}
				Calculator.prompt();
				break;
				
			case 8: //Exit program choice
				System.out.println("Goodbye!");
				exit = true;
				Calculator.prompt();
				break;
				
			default: //If the user makes an incorrect or invalid choice
				System.out.println("Invalid choice, please try again.\n");
			}
		} while(exit != true); //End of the menu loop, 
	}
}