package com.hemebiotech.analytics;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class AnalyticsCounter implements ISymptomReader{
	String urlFile;

	AnalyticsCounter(String myUrl){
		this.urlFile = myUrl;
	}

	/**
	 * This function create the result symptome file
	 * listResult is a Map with symptomes and their number of recurrence
	 * 
	 * The file created is like "Symptome : Number" per line
	 * 
	 */
	public void createFileSymptomeCounter(Map<String, Integer> listResult){
		//Create result output file
		File file = new File("result.out");
		BufferedWriter bf = null;


		try {		
			//create new BufferedWriter for the output file
			bf = new BufferedWriter( new FileWriter(file) );

			//iterate map entries
			for(Map.Entry<String, Integer> entry : listResult.entrySet()){
				
				//put symptome and value 
				bf.write( entry.getKey() + " : " + entry.getValue() );
				
				//new line
				bf.newLine();
			}
			
			bf.flush();
		} catch (Exception e) {			
		}
	}

	@Override
	public Map<String, Integer> GetSymptoms() throws Exception {
		// first get input
		BufferedReader reader = new BufferedReader (new FileReader(urlFile));
		String line = reader.readLine();

		// Map which contains symptome, recurrence
		Map<String, Integer> symptomeCounter = new TreeMap<>();


		while (line != null) {

			// if first time of symptome, add it in the map
			if (!symptomeCounter.containsKey(line)){
				symptomeCounter.put(line, 1);
			} else {
				// else, increment recurrence of symptome
				symptomeCounter.put(line, symptomeCounter.get(line)+1);
			}

			line = reader.readLine();	// get another symptom
		}

		reader.close();

		return symptomeCounter;
	}

}
