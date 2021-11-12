package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AnalyticsCounter {
	private static int symptomCount = 0;	// initialize to 0
	
	public static void main(String args[]) throws Exception {

		ReadSymptomDataFromFile symptomList = new ReadSymptomDataFromFile("symptoms.txt");
		
		Scanner scanner = new Scanner(System.in);  // Create a Scanner object
		
	    System.out.println("How many symptoms would you like to find ? (Input a number) : ");
	    String nbOfDifferentSymptomsString = scanner.nextLine();  // Read user input
	    
	    int nbOfDifferentSymptoms = Integer.parseInt(nbOfDifferentSymptomsString);
	    List<String> symptomsToCheck = new ArrayList<String>();
	    Map<String, Integer> symptomsChecked = new HashMap<String, Integer>();
	    
	    
	    
	    for (int i = 0; i < nbOfDifferentSymptoms; i++) {
	    	System.out.println("Type the name of the symptom n°"+(i+1)+": ");
	    	String symptom = scanner.nextLine().toLowerCase();
	    	symptomsToCheck.add(symptom);
	    	symptomsChecked.put(symptom,symptomList.numberOfSymptoms(symptomsToCheck.get(i)));
	    	System.out.print("There's "+symptomsChecked.get(symptom)+" symptoms of '"+symptom+"'.");

	    }
	    scanner.close();
	    
	    ResultsWriter results = new ResultsWriter();
	    results.writeResults(symptomsChecked);
		
	}
}
