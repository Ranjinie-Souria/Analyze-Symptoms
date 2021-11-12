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
		
	    System.out.println("How many symptoms would you like to find ? (Input a number) : ");
	    int nbOfDifferentSymptoms = 0;
	    
	    try {
	    	String num = scanner.nextLine();
	    	nbOfDifferentSymptoms = Integer.parseInt(num);
	    }
	    catch (NumberFormatException e)
	    {
	    	System.out.println("Input is not a number.");
	    }
	    
	        
	      
	      

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