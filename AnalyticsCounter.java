package com.hemebiotech.analytics;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.TreeMap;

public class AnalyticsCounter implements ISymptomReader {
	String urlFile;

	AnalyticsCounter(String myUrl) {
		this.urlFile = myUrl;
	}

	/**
         * Créer le fichier de sortie, avec les informations des symptomes.
         * 
         * @param listResult
         *            Contient chaque symptome et son nombre d'occurence
		 * 
		 * @author SOUICI Yanis
		 * @version 1.0
		*/
	public void createFileSymptomeCounter(Map<String, Integer> listResult) {
		// Create result output file
		File file = new File("result.out");
		BufferedWriter bf = null;

		try {
			// create new BufferedWriter for the output file
			bf = new BufferedWriter(new FileWriter(file));

			// iterate map entries
			for (Map.Entry<String, Integer> entry : listResult.entrySet()) {

				// put symptome and value in map
				bf.write(entry.getKey() + " : " + entry.getValue());

				bf.newLine();
			}

			bf.flush();
		} catch (Exception e) {
		}
	}

	/**
         * Analyse le fichier afin de compter le nombre d'occurence de chaque symptome.
		 * 
		 * @return une Map contenant chaque symptome avec son nombre d'occurence
		 * 
		 * @throws Exception si le fichier est mal lu
		 * @author SOUICI Yanis
		 * @version 1.0
		 * @since 1.0
		*/
	@Override
	public Map<String, Integer> GetSymptomsRecurrence() throws Exception {

		BufferedReader reader = openFile(urlFile);
		String line = reader.readLine();

		// Map which contains symptome, recurrence
		Map<String, Integer> symptomeCounter = new TreeMap<>();

		while (line != null) {

			// if first time of symptome, add it in the map
			if (!symptomeCounter.containsKey(line)) {
				symptomeCounter.put(line, 1);
			} else {
				// else, increment recurrence of symptome
				symptomeCounter.put(line, symptomeCounter.get(line) + 1);
			}

			line = reader.readLine(); // get next symptom
		}

		reader.close();

		return symptomeCounter;
	}

	/**
         * Ouvre le fichier qui sera traité par le programme.
         * @see GetSymptomsRecurrence
		 * 
		 * @return le ficher contenant la liste des symptomes
		 * 
		 * @throws Exception si le fichier est mal lu
		 * @author SOUICI Yanis
		 * @version 1.0
		 * @since 1.0
		*/
	public BufferedReader openFile(String fileName) throws FileNotFoundException {
		BufferedReader reader = new BufferedReader (new FileReader(fileName));
		return reader;
	}
}
