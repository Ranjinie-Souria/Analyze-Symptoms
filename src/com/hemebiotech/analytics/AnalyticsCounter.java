package com.hemebiotech.analytics;

import java.io.FileWriter;

public class AnalyticsCounter {
	private static int symptomCount = 0;	// initialize to 0
	
	public static void main(String args[]) throws Exception {

		ReadSymptomDataFromFile symptomList = new ReadSymptomDataFromFile("symptoms.txt");
		
		
		System.out.print(symptomList.numberOfSymptoms("headache"));
		
		
		/*int headCount = 0;	// counts headaches
		while (line != null) {
			System.out.println("symptom from file: " + line);
			if (line.equals("headache")) {
				headCount++;
				headacheCount++;
				System.out.println("number of headaches: " + headCount);
			}
			else if (line.equals("rash")) {
				rashCount++;
			}
			else if (line.contains("pupils")) {
				pupilCount++;
			}

			line = reader.readLine();	// get another symptom
		}*/

		
		// next generate output
		FileWriter writer = new FileWriter ("result.out");
		writer.write("headache: " + symptomCount + "\n");
		writer.close();
	}
}
