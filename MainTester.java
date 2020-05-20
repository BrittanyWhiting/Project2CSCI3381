// Brittany Whiting 
// CSCI 3381 Java Object Oriented Programming
package project1;

import java.util.ArrayList;

public class MainTester {
	public static void main(String[] args){
	PatientCollection collection = new PatientCollection();
	
	// step 1: collection of patients
	System.out.println("Adding patients from data.csv");
	collection.addPatientsFromFile("data.csv");
	System.out.println(collection);
	
	// step 2: new patient entry
	System.out.println("Adding patients from the new file, newdata.csv");
	collection.addNewPatientsFromFile("newdata.csv");
	System.out.println(collection);
	
	// step 3: predictions
	System.out.println("Creating predictions for all patients");
	collection.setPred();
	System.out.println(collection);
	
	// creating a new file containing all data types, and the predictions
	collection.writeFile();
	
	
	
	
	}

}