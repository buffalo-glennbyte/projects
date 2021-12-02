package menu;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.regex.*;



public class AgeCalculator {

	public static void timeCalculation() { // Calculates the difference between the two time stamps, then prints the difference in months; days; hours; minutes and seconds.
		Duration difference = Duration.between(begin, end);
		System.out.println(Period.between(begin.toLocalDate(), end.toLocalDate()).toTotalMonths() + " months.");
		System.out.println(difference.toDays() + " days.");
		System.out.println(difference.toHours() + " hours.");
		System.out.println(difference.toMinutes() + " minutes.");
		System.out.println(difference.toSeconds() + " seconds.");
	}
	public static void setBegin(LocalDateTime tempBegin) { // Sets birth/begin date
		begin = tempBegin;
	}
	public static void setEnd(LocalDateTime tempEnd) { // Sets death/end date
		end = tempEnd;
	}
	public static void getBirthdate() { // Gets birth date from user and checks it against a custom format, loops until valid answer is given.
		boolean BDloop = false;
		do {
			System.out.println("\nOn what date were you born? (DD-MM-YYYY)");
			birthdate = RandomSelector.stringput().toString();
			Matcher dateMatch = dateCheck.matcher(birthdate);
			if (!(dateMatch.matches())) {
				System.out.print("\nPlease answer in the correct format.");
			} else {
				BDloop = true;
			}
		} while(BDloop != true);
	}
	public static void getBirthtime() { // Gets birth time from user and checks it against a custom format, loops until valid answer is given or user answers no.
		boolean BTloop = false;
		do {
			System.out.println("\nDo you know on what time you were born? (HH:MM)\nAnswer 'no' if you don't know.");
			birthtime = RandomSelector.stringput().toString().toLowerCase();
			Matcher timeMatch = timeCheck.matcher(birthtime);
			if (birthtime.equals("no")) { // If user answers no, the birth time is set to 00:00.
				birthtime = "00:00";
				BTloop = true;
			} else if(!(timeMatch.matches())) {
				System.out.println("\nInvalid answer, did you answer in the correct format?");
			} else {
				BTloop = true;
			}
		} while(BTloop != true);
	}
	public static void getEnddate() {
		boolean EDloop = false;
		do {
			System.out.println("\nUntil what day do you want to calculate? (DD-MM-YYYY)");
			enddate = RandomSelector.stringput().toString();
			Matcher dateMatch = dateCheck.matcher(enddate);
			if (!(dateMatch.matches())) {
				System.out.print("\nPlease answer in the correct format.");
			} else {
				EDloop = true;
			}
		} while(EDloop != true);
	}
	public static void getEndtime() {
		boolean ETloop = false;
		do {
			System.out.println("\nUntil what time do you want to calculate? (HH:MM)\nAnswer 'no' if you don't know.");
			endtime = RandomSelector.stringput().toString().toLowerCase();
			Matcher timeMatch = timeCheck.matcher(endtime);
			if (endtime.equals("no")) { // If user answers no, the end time is set to 00:00.
				endtime = "00:00";
				ETloop = true;
			} else if(!(timeMatch.matches())) {
				System.out.println("\nInvalid answer, did you answer in the correct format?");
			} else {
				ETloop = true;
			}
		} while(ETloop != true);
	}
	public static StringBuilder modString(String input) {
		StringBuilder output = new StringBuilder();
		return output;
	}
	static private final String datePattern = "\\d{2}-\\d{2}-\\d{4}";
	static private final String timePattern = "\\d{2}:\\d{2}";
	static private final Pattern dateCheck = Pattern.compile(datePattern);
	static private final Pattern timeCheck = Pattern.compile(timePattern);
	static private String birthdate;
	static private String birthtime;
	static private String birthTotal;
	static private String enddate;
	static private String endtime;
	static private String endTotal;
	static private LocalDateTime begin;
	static private LocalDateTime end;
	static private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	static String[] menu = {"null", "My lifespan so far", "The lifespan of a deceased person", "The amount of time left", "Exit program"};
	
	public static void main(String[] args) {
		boolean exitProgram = false;
		System.out.println("Welcome to the time calculator!\nIn this application you can calculate how long you or someone else has been alive.");
		do{ // Beginning of the main loop, asks what option the user wants and starts it.
			System.out.println("\nWhat do you want to calculate?\n");
			for (int i = 1; i < menu.length; i++) {
			System.out.println(i + ". " + menu[i]);
			}
			int menuoption = Calculator.intScan();
			switch(menuoption) {
			
			case 1: // This asks for a date and time, then calculates the amount of time that has passed until today.
				AgeCalculator.getBirthdate();
				AgeCalculator.getBirthtime();
				birthTotal = birthdate + " " + birthtime;
				LocalDateTime datetime = LocalDateTime.parse(birthTotal, format);
				end = LocalDateTime.now();
				begin = datetime;
				System.out.println("\nYou've been alive for:\n");
				AgeCalculator.timeCalculation();
				break;
			
			case 2: // This asks for a date of birth and time, then asks for a date of death and time. Afterwards calculates the total amount of time that person was alive.
				AgeCalculator.getBirthdate();
				AgeCalculator.getBirthtime();
				birthTotal = birthdate + " " + birthtime;
				LocalDateTime BDdatetime= LocalDateTime.parse(birthTotal, format);
				begin = BDdatetime;
				AgeCalculator.getEnddate();
				AgeCalculator.getEndtime();
				
				break;
			
			case 3: // Asks for a future date, then calculates the amount of time still left from present until inputed date.
				System.out.println("\nA bit morbid if you ask me but you want to find out.\nOn what day are you planning to die?");
				
				break;
			
			case 4: // Exits the program
				System.out.println("\nGoodbye!");
				exitProgram = true;
				Calculator.prompt();
				break;
			
			default: // If the user makes an invalid main menu choice.
				System.out.println("\nPlease make a valid choice.");
				break;
			}
		} while(exitProgram != true);
		
	}

}
