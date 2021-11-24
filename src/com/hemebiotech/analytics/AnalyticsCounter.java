package com.hemebiotech.analytics;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * The main code
 * 
 */
public class AnalyticsCounter {
	
	
	public static void main(String args[]) throws Exception {

		ReadSymptomDataFromFile symptomList = new ReadSymptomDataFromFile("symptoms.txt");
		Scanner scanner = new Scanner(System.in);
		ResultsWriter results = new ResultsWriter();
		Map<String, Integer> symptomsChecked = new HashMap<String, Integer>();
		
		for (int i = 0; i < symptomList.getListSymptoms().size(); i++) {
	    	symptomsChecked.put(symptomList.getListSymptoms().get(i), symptomList.numberOfSymptoms(symptomList.getListSymptoms().get(i)));
	    }
		
		System.out.println("Here is all the symptoms with their occurences in the file "+symptomList.getFilepath()+" : ");
		fillAllSymptoms(symptomsChecked, scanner);
		
		System.out.println("Would you like to know the occurence of some specifics symptoms ? Type 1 for yes, 0 for no : ");
		String answer = scanner.nextLine();
		if(answer.equals("1")) {
			int nbOfDifferentSymptoms = getNbSymptoms(scanner); 
			fillNSymptoms(nbOfDifferentSymptoms,symptomsChecked,scanner,symptomList);
		}
	    
	    scanner.close();
	    results.writeResults(symptomsChecked);
		
	}
	

	
	/**
	 * Informs the user about the number of occurences of all symptoms
	 * 
	 * @param symptomsChecked A Map List with the name of every symptom and its number of occurences
	 * @param scanner The scanner
	 */
	public static void fillAllSymptoms(Map<String, Integer> symptomsChecked, Scanner scanner) {
		symptomsChecked = ResultsWriter.order(symptomsChecked);
		for (Map.Entry<String, Integer> entry : symptomsChecked.entrySet()) {
			String symptomName = entry.getKey();
			Integer symptomCount = entry.getValue();
			System.out.println("There's "+symptomCount+" symptoms of '"+symptomName+"'.");
		}
	}
	
	
	/**
	 * 
	 * Get the number of symptoms the user wants to check, sends an error message when the number isn't an integer
	 * 
	 * @param scanner The scanner
	 * @return returns the number of symptoms the user wants to check
	 * 
	 */
	public static int getNbSymptoms(Scanner scanner) {
		System.out.println("How many symptoms would you like to find ? (Input a number) : ");
		
		int nbOfDifferentSymptoms = 0;
		try {
	    	String num = scanner.nextLine();
	    	nbOfDifferentSymptoms = Integer.parseInt(num);
	    	
	    }
	    catch (NumberFormatException e)
	    {
	    	System.out.println("Error : You need to input a number.");
	    }
		return nbOfDifferentSymptoms;
	}
	
	/**
	 * 
	 * Informs the user about the number of symptoms in the list each time they type a symptom
	 * 
	 * @param nbOfDifferentSymptoms The number of different symptoms to check
	 * @param symptomsChecked A Map List with the name of every symptom and its number of occurences
	 * @param scanner The Scanner
	 * @param symptomList A list of all the symptoms without duplicates
	 * 
	 */
	public static void fillNSymptoms(int nbOfDifferentSymptoms, Map<String, Integer> symptomsChecked, Scanner scanner,ReadSymptomDataFromFile symptomList) {
		for (int i = 0; i < nbOfDifferentSymptoms; i++) {
	    	System.out.println("Type the name of the symptom n°"+(i+1)+": ");
	    	System.out.println("(For a list of all the symptoms, write /help)");
	    	String answer = scanner.nextLine();
	        while(answer.equals("/help")) {
	        	helpCommand(answer, symptomList);
	    		System.out.println("Type the name of the symptom n°"+(i+1)+": ");
	    		System.out.println("(For a list of all the symptoms, write /help)");
	    		String newAnswer = scanner.nextLine();
	    		if(!newAnswer.equals("/help")) {
	    			answer = newAnswer.toLowerCase();
	    			break;
	    		}
	        }
	        if(!answer.equals("/help")) {
		    	System.out.println("There's "+symptomsChecked.get(answer)+" symptoms of '"+answer+"'.");
	        }
	    }
	}
	
	/**
	 * Returns the list of all the symptoms in the symptoms.txt file
	 * 
	 * @param answer The input entered, needs to be equal to /help
	 * @param symptomList A list of all the symptoms without duplicates
	 * 
	 */
	public static void helpCommand(String answer, ReadSymptomDataFromFile symptomList) {		
		if(answer.equals("/help")) {
			System.out.println("List of all symptoms : ");
    		for (String element : symptomList.getListSymptoms()) {
    		    System.out.println(element);
    		}
		}		
	}
	
	
}