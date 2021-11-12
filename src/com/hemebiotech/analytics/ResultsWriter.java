package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Writes in the result.out file
 *
 */
public class ResultsWriter {
	
	public void writeResults(Map<String, Integer> symptomsChecked) {
		try {
			FileWriter writer = new FileWriter ("result.out");
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
}
