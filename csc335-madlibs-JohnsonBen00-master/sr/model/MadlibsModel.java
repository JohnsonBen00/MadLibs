package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class generates a random template from the file, and generates a list of valid words for the user
 * to input. 
 * @author Benhur J. Tadiparti
 *
 */
public class MadlibsModel {
	
	private HashMap<String, ArrayList<String>> wordMap;
	private ArrayList<String> tempMap;
	private String[] tags = {"(ADJ)", "(N)", "(PLN)", "(GER)", "(VPT)", "(V)", "(PN)", "(PPN)", "(AA)", "(NUM)"};
	private String temp;
	
	/**
	 * Choices a random template from the given templates.txt file.
	 * @throws FileNotFoundException if files don't exist
	 */
	public MadlibsModel() throws FileNotFoundException {
		this.wordMap = new HashMap<>();
		this.tempMap = new ArrayList<String>();
		this.temp = randomLine();
	}
	
	/**
	 * Returns the number of lines in the templates.txt file
	 * @return the number of lines in the templates.txt file
	 * @throws FileNotFoundException if files don't exist
	 */
	public int getLines() throws FileNotFoundException{
		int count = 0;
		Scanner file = new Scanner(new File("templates.txt"));            
	    while(file.hasNextLine()) {
	    	file.nextLine();
	    	count++;
	    }
	    return count;
	}
	
	/**
	 * Returns the random number within file size
	 * @return random number within file size
	 * @throws FileNotFoundException if files don't exist.
	 */
	public int randomLineNum() throws FileNotFoundException {
		int numLine = getLines();
		int num = (int) (Math.random() * ( numLine - 0 ));
		return num;
	}
	
	/**
	 * Returns a random template from templates.txt and stores the tags required from that mad lib template
	 * in a private field
	 * @return random line from templates.txt
	 * @throws FileNotFoundException if files don't exist
	 */
	public String randomLine() throws FileNotFoundException{
		int lineNum = randomLineNum();
		int count = 0;
		String s;
		Scanner file = new Scanner(new File("templates.txt"));      
	    while(count != lineNum) {
	    	s = file.nextLine();
	    	count++;
	    }
	    s = file.nextLine();
	    String[] arr = s.split(" ");
	    for(int i = 0; i < arr.length; i++) {
	    	for(int j = 0; j < this.tags.length; j++) {
	    		if (this.tags[j].equals(arr[i])) {
	    			this.tempMap.add(arr[i]);
	    		}
	    	}
	    }
	    return s;
	}
	
	/**
	 * Reads in a file filled in with valid words and their types and stores that data in
	 * a private field
	 * @throws FileNotFoundException if files don't exist
	 */
	public void mapWords() throws FileNotFoundException {
		Scanner file = new Scanner(new File("parts_of_speech.txt"));
		ArrayList<String> adj = new ArrayList<String>();
		ArrayList<String> n = new ArrayList<String>();
		ArrayList<String> pln = new ArrayList<String>();
		ArrayList<String> ger = new ArrayList<String>();
		ArrayList<String> vpt = new ArrayList<String>();
		ArrayList<String> v = new ArrayList<String>();
		ArrayList<String> pn = new ArrayList<String>();
		ArrayList<String> ppn = new ArrayList<String>();
		ArrayList<String> aa = new ArrayList<String>();
		ArrayList<String> num = new ArrayList<String>();
	    while(file.hasNextLine()) {
	    	String[] arr = file.nextLine().split("\t");
	    	if (arr[1].equals("ADJ")) {
	    		adj.add(arr[0]);
	    	}
	    	if (arr[1].equals("N")) {
	    		n.add(arr[0]);
	    	}
	    	if (arr[1].equals("PLN")) {
	    		pln.add(arr[0]);
	    	}
	    	if (arr[1].equals("GER")) {
	    		ger.add(arr[0]);
	    	}
	    	if (arr[1].equals("VPT")) {
	    		vpt.add(arr[0]);
	    	}
	    	if (arr[1].equals("V")) {
	    		v.add(arr[0]);
	    	}
	    	if (arr[1].equals("PN")) {
	    		pn.add(arr[0]);
	    	}
	    	if (arr[1].equals("PPN")) {
	    		ppn.add(arr[0]);
	    	}
	    	if (arr[1].equals("AA")) {
	    		aa.add(arr[0]);
	    	}
	    	if (arr[1].equals("NUM")) {
	    		num.add(arr[0]);
	    	}
	    }
	    this.wordMap.put("ADJ", adj);
	    this.wordMap.put("N", n);
	    this.wordMap.put("PLN", pln);
	    this.wordMap.put("GER", ger);
	    this.wordMap.put("VPT", vpt);
	    this.wordMap.put("V", v);
	    this.wordMap.put("PN", pn);
	    this.wordMap.put("PPN", ppn);
	    this.wordMap.put("AA", aa);
	    this.wordMap.put("NUM", num);
	}
	
	/**
	 * @return the valid words
	 */
	public HashMap<String, ArrayList<String>> getWordMap() {
		return this.wordMap;
	}
	
	/**
	 * @return the word tags requested
	 */
	public ArrayList<String> getTempMap() {
		return this.tempMap;
	}
	
	/**
	 * @return the template
	 */
	public String getTemp() {
		return this.temp;
	}
	
	/**
	 * Sets the resulted template to the temp field
	 * @param temp the resulted template
	 */
	public void setTemp(String temp) {
		this.temp = temp;
	}
}
