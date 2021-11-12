package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AnalyticsCounter {
	private static int symptomCount = 0;	// initialize to 0
	
	public static void main(String args[]) throws Exception {

		ReadSymptomDataFromFile symptomList = new ReadSymptomDataFromFile("symptoms.txt");
		
		Scanner scanner = new Scanner(System.in);  // Create a Scanner object
		ResultsWriter results = new ResultsWriter();
		
	    System.out.println("How many symptoms would you like to find ? (Input a number) : ");
	    String nbOfDifferentSymptomsString = scanner.nextLine();  // Read user input
	    
	    int nbOfDifferentSymptoms = Integer.parseInt(nbOfDifferentSymptomsString);
	    List<String> symptomsToCheck = new ArrayList<String>();
	    Map<String, Integer> symptomsChecked = new HashMap<String, Integer>();
	    
	    
	    
	    for (int i = 0; i < nbOfDifferentSymptoms; i++) {
	    	System.out.println("Type the name of the symptom n°"+(i+1)+": ");
	    	System.out.println("(For a list of all the symptoms, write /help)");
	    	String answer = scanner.nextLine();
	    	while(answer.equals("/help")) {
		    		System.out.println("List of all symptoms : ");
		    		for (String element : symptomList.getListSymptoms()) {
		    		    System.out.println(element);
		    		}
		    		System.out.println("Type the name of the symptom n°"+(i+1)+": ");
		    		System.out.println("(For a list of all the symptoms, write /help)");
		    		String newAnswer = scanner.nextLine();
		    		if(!newAnswer.equals("/help")) {
		    			answer = newAnswer.toLowerCase();
		    			break;
		    		}
	    	}	    	
	    	symptomsToCheck.add(answer);
	    	symptomsChecked.put(answer,symptomList.numberOfSymptoms(symptomsToCheck.get(i)));
	    	System.out.println("There's "+symptomsChecked.get(answer)+" symptoms of '"+answer+"'.");

	    }
	    scanner.close();
	    
	    
	    results.writeResults(symptomsChecked);
		
	}
}
