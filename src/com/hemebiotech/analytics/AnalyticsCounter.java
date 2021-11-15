package com.hemebiotech.analytics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AnalyticsCounter {
	
	
	public static void main(String args[]) throws Exception {

		ReadSymptomDataFromFile symptomList = new ReadSymptomDataFromFile("symptoms.txt");
		Scanner scanner = new Scanner(System.in);
		ResultsWriter results = new ResultsWriter();
	    int nbOfDifferentSymptoms = getNbSymptoms(scanner); 
	    List<String> symptomsToCheck = new ArrayList<String>();
	    Map<String, Integer> symptomsChecked = new HashMap<String, Integer>();	    
	    
	    fillSymptoms(nbOfDifferentSymptoms,symptomsToCheck,symptomsChecked,scanner,symptomList);
	    	
	    scanner.close();
	    results.writeResults(symptomsChecked);
		
	}
	
	/**
	 * Returns the list of all the symptoms in the symptoms.txt file
	 * 
	 * @param answer : The input entered, needs to be equal to /help
	 * @param symptomList : The text file with the symptoms in duplicates
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
	
	/**
	 * 
	 * Get the number of symptoms the user wants to check, sends an error message when the number isn't an integer
	 * 
	 * @param scanner : The scanner
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
	 * The main code, it fills the data in a Map List to prepare it for the results.out file
	 * Also informs the user about the number of symptoms in the list each time they type a symptom
	 * 
	 * @param nbOfDifferentSymptoms : The number of different symptoms to check
	 * @param symptomsToCheck : The symptoms to check
	 * @param symptomsChecked : A Map List with the name of the symptom and the number of time it appears
	 * @param scanner : The Scanner
	 * @param symptomList : The symptoms.txt file
	 * 
	 */
	public static void fillSymptoms(int nbOfDifferentSymptoms, List<String> symptomsToCheck, Map<String, Integer> symptomsChecked, Scanner scanner,ReadSymptomDataFromFile symptomList) {
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
	        
	        if(!answer.equals("/help")&&!symptomList.getListSymptoms().contains(answer)) {
	        	symptomsToCheck.add(answer);
		    	symptomsChecked.put(answer,symptomList.numberOfSymptoms(symptomsToCheck.get(i)));
				System.out.println("The symptom '"+answer+"' was not found in the list of symptoms.");
			} else {
	        	symptomsToCheck.add(answer);
		    	symptomsChecked.put(answer,symptomList.numberOfSymptoms(symptomsToCheck.get(i)));
		    	System.out.println("There's "+symptomsChecked.get(answer)+" symptoms of '"+answer+"'.");
	        }
	    }
	}
	
	
}