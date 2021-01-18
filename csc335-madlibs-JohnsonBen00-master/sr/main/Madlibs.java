package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.MadlibsController;
import model.MadlibsModel;


/**
 * This class simulates a Madlibs game.
 * 
 * The user is asked to input specific types of words into the console,
 * if the word isn't a specific type, the program asks if they want to play the game properly,
 * which will reset the game or end the end upon their following input.
 * If the words are correct, the game proceeds to take those inputs and fill them into 
 * a mad lib template and the result is printed. Asks the user if they want to play again.
 * 
 * @author Benhur J. Tadiparti
 *
 */
public class Madlibs {
	
	/**
	 * Creates appropriate objects the game needs.
	 * and runs game with those objects/variable
	 * @param args -- not used
	 * @throws FileNotFoundException if the files for the model don't exist.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(System.in); //Gets input from the console
		MadlibsModel model = new MadlibsModel(); //Model object
		model.mapWords(); //Map parts_of_speech to tags
		MadlibsController controller = new MadlibsController(model); //Controller object
		play(input, model, controller);
	}
	
	/**
	 * Generates the template, the words allowed and the environments needed to play a game.
	 * @param input gets the users' inputs
	 * @param model the model object
	 * @param controller the controller object
	 * @throws FileNotFoundException if the files for model don't exist
	 */
	public static void play(Scanner input, MadlibsModel model, MadlibsController controller) throws FileNotFoundException {
		String[] tags = controller.makeTags(); //Gets needed tags from template
		String[] words = getTags(tags, input, model, controller); //Gets the user's words
		printUpdate(words, model); //Prints the result
		System.out.print("Puzzle complete! Would you like to play again? "); //Asks to play again
		String val = input.next();
		System.out.println();
		if (val.toLowerCase().equals("yes")) { //If yes, new objects/environments are created
			model = new MadlibsModel(); // New Model object
			model.mapWords(); //Map parts_of_speech to tags
			controller = new MadlibsController(model); // New Controller object
			play(input, model, controller);
		}
		System.out.println("Bye."); //Else, exits program
		System.exit(0);;
	}
	
	/**
	 * Asks the user the requested type of word, checks to see if the word is valid,
	 * stores the word in an array, if word isn't valid -- the program prompts the user to 
	 * restart the game and play properly or end the game.
	 * @param tags an array that contacts the requested types of words based on the chosen template
	 * @param input console input reader
	 * @param model the model object
	 * @param controller the controller object
	 * @return a String[] that contains the user's ipnuts
	 * @throws FileNotFoundException if files don't exist
	 */
	public static String[] getTags(String[] tags, Scanner input, MadlibsModel model, MadlibsController controller) throws FileNotFoundException {
		String[] words = new String[tags.length];
		for(int i = 0; i < tags.length; i++) { //Iterates through the word types requested
			System.out.print("Enter a(n) ");
			String tag = ""; //Requested word type, without the ()
			if(tags[i].equals("(ADJ)")) {
				System.out.print("Adjective: ");
				tag = "ADJ";
			}
			else if(tags[i].equals("(N)")) {
				System.out.print("Noun: ");
				tag = "N";
			}
			else if(tags[i].equals("(PLN)")) {
				System.out.print("Plural Noun: ");
				tag = "PLN";
			}
			else if(tags[i].equals("(GER)")) {
				System.out.print("Verb Ending In \"-ing\": ");
				tag = "GER";
			}
			else if(tags[i].equals("(VPT)")) {
				System.out.print("Verb Past Tense: ");
				tag = "VPT";
			}
			else if(tags[i].equals("(V)")) {
				System.out.print("Verb: ");
				tag = "V";
			}
			else if(tags[i].equals("(PN)")) {
				System.out.print("Proper Noun: ");
				tag = "PN";
			}
			else if(tags[i].equals("(PPN)")) {
				System.out.print("Plural Proper Noun: ");
				tag = "PPN";
			}
			else if(tags[i].equals("(AA)")) {
				System.out.print("Article Adjective: ");
				tag = "AA";
			}
			else if(tags[i].equals("(NUM)")) {
				System.out.print("Number: ");
				tag = "NUM";
			}
			String val = input.next().toLowerCase(); // Asks for input
			boolean check = controller.check(tag, val); // Checks if word is valid
			if (check == false) { //Asks user to restart/end game
				System.out.println("Invalid word.");
				System.out.print("Would you like to play again? ");
				val = input.next();
				System.out.println();
				if (!val.toLowerCase().equals("yes")) {//End game
					System.out.println("Bye.");
					System.exit(0);
				}
				//Restart game
				model = new MadlibsModel(); // New Model object
				model.mapWords(); //Map parts_of_speech to tags
				controller = new MadlibsController(model); // NewController object
				play(input, model, controller);
			}
			words[i] = "(" + val + ")";
		}
		System.out.println();
		return words;
	}
	
	/**
	 * Prints the mad lib with the users' inputs.
	 * @param words a String[] which contains the users' inputs
	 * @param model the model object
	 */
	public static void printUpdate(String[] words, MadlibsModel model) {
		String str = model.getTemp();
		Pattern p = Pattern.compile("\\([A-Z]+\\)");
		Matcher m = p.matcher(str);
		String out = null;
		int i = 0;
		while (m.find()) {
			out = m.replaceFirst(words[i++]);
			m = p.matcher(out);
		}
		model.setTemp(out);
		System.out.println(out);
		System.out.println();
	}
	
}
