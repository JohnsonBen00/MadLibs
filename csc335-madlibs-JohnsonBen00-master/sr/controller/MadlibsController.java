package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.MadlibsModel;

/**
 * This class interacts with the user's inputs and requested word types
 * @author Benhur J. Tadiparti
 *
 */
public class MadlibsController {
	
	private MadlibsModel model;
	private String[] tags;
	
	/**
	 * Makes a list of requested word types
	 * @param model the model object
	 */
	public MadlibsController(MadlibsModel model) {
		this.model = model;
		this.tags = new String[this.model.getTempMap().size()];
	}
	
	/**
	 * Returns t/f if the tag and val are valid inputs based on the word
	 * choices provided to the program
	 * @param tag the word type
	 * @param val the users' input
	 * @return true if it is a valid word, else false
	 */
	public boolean check(String tag, String val) {
		HashMap<String, ArrayList<String>> words = this.model.getWordMap();
		for (String key: words.keySet()) {
			if (key.equals(tag) && words.get(key).contains(val)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns a list of requested word types
	 * @return a list of requested word types
	 */
	public String[] makeTags() {
		int i = 0;
		for (String key: this.model.getTempMap()) {
			this.tags[i] = key;
			i++;
		}
		return this.tags;
	}
}
