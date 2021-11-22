package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Write the result.out file
 *
 */
public class ResultsWriter {
	
	public void writeResults(Map<String, Integer> symptomsChecked) {
		try {
			FileWriter writer = new FileWriter ("result.out");
			symptomsChecked = order(symptomsChecked);
			for (Map.Entry<String, Integer> entry : symptomsChecked.entrySet()) {
				String symptomName = entry.getKey();
				Integer symptomCount = entry.getValue();
				writer.write(symptomName+": " + symptomCount + "\n");
			}
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Orders the results alphabetically
	 * 
	 * @param unsortMap The non alphabetically ordered results map
	 * @return The alphabetically ordered results map
	 * 
	 */
    public static Map<String, Integer> order(Map<String, Integer> unsortMap) {
        Map<String, Integer> treeMap = new TreeMap<String, Integer>(unsortMap);
        return treeMap;
    }

    
}
