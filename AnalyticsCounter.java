package com.hemebiotech.analytics;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.TreeMap;

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
				
				//put symptome and value in map 
				bf.write( entry.getKey() + " : " + entry.getValue() );
				
				bf.newLine();
			}
			
			bf.flush();
		} catch (Exception e) {			
		}
	}

	@Override
	public Map<String, Integer> GetSymptomsRecurrence() throws Exception {
		// first get input file
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

			line = reader.readLine();	// get next symptom
		}

		reader.close();

		return symptomeCounter;
	}

}
