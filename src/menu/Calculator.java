package menu;
import java.util.Scanner;
import java.util.InputMismatchException;

/*
 * This is a simple calculator, created for fun and learning.
 */

public class Calculator{
	
	//Initialise input and variables counter
	static int varAmount = 0; 			//Total amount of variables in the formula
	static double variable1;			//First variable for usage in function
	static double variable2;			//Second variable for usage in function
	static double result;				//Result of function
	static int intput;					//Result of integer input scanner
	static double douput;				//Result of double input scanner
	
	//Different input scanners, both return input.
	public static int intScan() {
		Scanner intScan = new Scanner(System.in);
		intput = intScan.nextInt();
		return intput;
	}
	public static double douScan() {
		Scanner douScan = new Scanner(System.in);
		douput = douScan.nextDouble();
		return douput;
	}
	
	//Calculator functions, all take 2 variables except root and square.
	public static double plus(double var1, double var2) {
		result = var1 + var2;
		System.out.println(var1 + " + " + var2 + " = " + result);
		return result;
		}
	public static double min(double var1 ,double var2) {
		result = var1 - var2;
		System.out.println(var1 + " - " + var2 + " = " + result);
		return result;
	}
	public static double div(double var1, double var2) {
		result = var1 / var2;
		System.out.println(var1 + " : " + var2 + " = " + result);
		return result;
	}
	public static double tim(double var1, double var2) {
		result = var1 * var2;
		System.out.println(var1 + " x " + var2 + " = " + result);
		return result;
	}
	public static double root(double var1) {
		result = Math.sqrt(var1);
		System.out.println("The square root of " + var1 + " is: " + result);
		return result;
		}
	public static double sqr(double var1) {
		result = var1 * var1;
		System.out.println(var1 + " squared is: " + result);
		return result;
	}
	
	public static void main (String[] args) {
		System.out.println("Welcome to the calculator!\nPlease follow the onscreen instructions.\n");
		do{
			/*
			 * Takes user input for the amount of variables.
			 * It should only take integers as input or ask again.
			 */
			System.out.println("How many variables are there in your calculation?");
			
			try {
				int input = Calculator.intScan();
				if(input > 1) {
					varAmount = input;
				} else {
					System.out.println("Too few variables, try again.\n");
			}} catch (InputMismatchException e){
				System.out.println("Please enter a valid number.\n");
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
		Calculator.min(6,8);
		Calculator.tim(12,3.33);
		Calculator.div(1,33);
		Calculator.sqr(35);
		Calculator.root(666);

	}
}