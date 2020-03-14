package com.hemebiotech.analytics;

import java.util.Map;

public class main {
	public static void main(String args[]) {
        
        try {
            // Initialize AnalyticsCounter Object, argument : path to symptome file 
            AnalyticsCounter ac = new AnalyticsCounter("symptoms.txt");
            

            Map<String, Integer> mySymptoms = ac.GetSymptomsRecurrence();
            
            ac.createFileSymptomeCounter(mySymptoms);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}