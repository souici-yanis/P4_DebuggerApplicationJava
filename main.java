package com.hemebiotech.analytics;

import java.util.Map;

public class main {
	public static void main(String args[]) {
        
        try {
            AnalyticsCounter ac = new AnalyticsCounter("Project02Eclipse/symptoms.txt");
            Map<String, Integer> mySymptoms = ac.GetSymptoms();
            ac.createFileSymptomeCounter(mySymptoms);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}