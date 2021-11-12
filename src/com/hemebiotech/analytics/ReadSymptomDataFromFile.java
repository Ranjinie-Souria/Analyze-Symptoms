package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Simple brute force implementation
 *
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private String filepath;
	
	/**
	 * 
	 * @param filepath a full or partial path to file with symptom strings in it, one per line
	 */
	public ReadSymptomDataFromFile (String filepath) {
		this.filepath = filepath;
	}
	
	@Override
	public List<String> GetSymptoms() {
		ArrayList<String> result = new ArrayList<String>();
		
		if (filepath != null) {
			try {
				BufferedReader reader = new BufferedReader (new FileReader(filepath));
				String line = reader.readLine();
				
				while (line != null) {
					result.add(line);
					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param  Counts the number of times a certain typed symptom appears in a list
	 */
	public Integer numberOfSymptoms(String symptomEntered) {
		int numberSymptom = 0;
		List<String> symptomList = this.GetSymptoms();
		for(String symptom : symptomList){
			if(symptom.equals(symptomEntered)) {
				numberSymptom++;
			}
			
		}
		return numberSymptom;
	}
	
	/**
	 * 
	 * @param  Lists all symptoms without duplicates
	 */
	public List<String> getListSymptoms() {
		List<String> listSymptoms = new ArrayList<String>();
		List<String> symptomList = this.GetSymptoms();
		Set<String> set = new HashSet<>(symptomList);
		listSymptoms.addAll(set);
		java.util.Collections.sort(listSymptoms);
		return listSymptoms;
	}

}
