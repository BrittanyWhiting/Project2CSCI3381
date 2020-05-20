// Brittany Whiting 
// CSCI 3381 Java Object Oriented Programming
package project1;

import java.util.ArrayList;

public class Patient {

	String id;
	String prediction;
	String result;
	ArrayList<Double> data;
	
	// constructor 
	public Patient(String i, String p, String r, ArrayList<Double> d) {
		id = i;
		prediction = p;
		result = r;
		data = d;
	}
	
	// creates the object patient
	public Patient(String id) {
		this(id, "unknown", "unknown", new ArrayList<Double>());
	}
	
	// getters & setters
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getPrediction() {
		return prediction;
	}
	
	public void setPrediction(String prediction) {
		this.prediction = prediction;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public ArrayList<Double> getData() {
		return data;
	}
	
	//override equals object to make sure that the id is equal to the actual id
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Patient ) {
			Patient p = (Patient)obj;
			return this.getId().equals(p.getId());
		}
		else {
			return false;
		}
	}
	
	// makes the prediction for the patients in the patient collection
	public void makePred() {
		if(this.getPrediction().equals("unknown")) {
			this.setPrediction(Predictor.predict(data.get(3697), data.get(3258)));
		}
	}
	
	
	
}

