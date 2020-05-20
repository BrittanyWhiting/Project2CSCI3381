// Brittany Whiting 
// CSCI 3381 Java Object Oriented Programming
package project1;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PatientCollection implements PatientCollectionADT{

	ArrayList<Patient> patients = new ArrayList<Patient>();
	
	// Return the patient with the given id.  Return void if the id does 
	// not exist in the collection
	@Override
	public Patient getPatient(String id) {
		for(Patient p : patients) {
			if(p.getId().equals(id)) {
				return p;
			}
		}
		return null;
	}

	// Remove and return the Patient with the given id.  Return void if the id does not exist.
	@Override
	public Patient removePatient(String id) {
		// TODO Auto-generated method stub
		Patient p = null;
		for(Patient pat : patients) {
			if(pat.getId().equals(id)) {
				p = pat;
			}
		}
		if(p == null) {
			return p;
		}
		patients.remove(p);
		return p;
	}

	// Set the result field for the patient with given id.
	@Override
	public void setResultForPatient(String id, String result) {
		Patient p = getPatient(id);
		if(p != null) {
			p.setResult(result);
		}
	}

	// Return an ArrayList with all of the collection's patient ids
	@Override
	public ArrayList<String> getIds() {
		ArrayList<String> ids = new ArrayList<String>();
		for(Patient pat : patients) {
			ids.add(pat.getId());
		}
		return ids;
	}

	
	@Override
	public String addPatientsFromFile(String fileName) {
		String err = "";
		try {	
			Scanner scan = new Scanner(new File(fileName));
			while(scan.hasNext()) {
				try {
					String line = scan.nextLine();
					String[] elements = line.split(",");
					
					if(elements.length != 4779) {
						throw new IllegalArgumentException("Incorrect amount of columns");
					}
					
					String result = elements[0];
				
					
					if (!(result.equals("unknown") || result.equals("CR") || result.equals("DP"))) {
						throw new IllegalArgumentException("Invalid value for result");
					}
					
					String pred = elements[1];

					if (!(pred.equals("unknown") || pred.equals("predDP") || pred.equals("predCR"))){
						throw new IllegalArgumentException("Invalid value for prediction");
					}
					
				
					String id = elements[2];
					Patient pats = this.getPatient(id);

					if (pats != null){
						throw new IllegalArgumentException("Duplicate ID found");
					}
					
					ArrayList<Double> data = new ArrayList<Double>();
					for(int i = 3; i < elements.length-1; i++ ) {
						double d = Double.parseDouble(elements[i]);
						data.add(d);
					}
					
					Patient p = new Patient(id, pred, result, data);
					patients.add(p);
					
					
					
				}catch(IllegalArgumentException e) {
					err += "Invalid patient number " +  e.getMessage() + "\n"; 
					
				}
			}
			
		} catch(FileNotFoundException e) {
			return("File not Found");
		}
		return err;
	}
	
	
	public String addNewPatientsFromFile(String fileName) {
		String err = "";
		try {	
			Scanner scan = new Scanner(new File(fileName));
			while(scan.hasNext()) {
				try {
					String line = scan.nextLine();
					String[] elements = line.split(",");
					
					if(elements.length != 4777) {
						throw new IllegalArgumentException("Incorrect amount of columns");
					}
					
					String result = "unknown";
				
					String pred = "unknown";

					
					String id = elements[0];
					Patient pats = this.getPatient(id);

					if (pats != null){
						throw new IllegalArgumentException("Duplicate ID found");
					}
					
					ArrayList<Double> data = new ArrayList<Double>();
					for(int i = 3; i < elements.length-1; i++ ) {
						double d = Double.parseDouble(elements[i]);
						data.add(d);
					}
					
					Patient p = new Patient(id, pred, result, data);
					patients.add(p);
					
					
					
				}catch(IllegalArgumentException e) {
					err += "Invalid patient number " +  e.getMessage() + "\n"; 
					
				}
			}
			
		} catch(FileNotFoundException e) {
			return("File not Found");
		}
		return err;
	}
	
	
	public String toString () {
		String s = "";
		for(Patient p: patients) {	
			s += p.getResult() + "," + p.getPrediction() +"," + p.getId() + "\n";
			
		}
		return s;
		
	}
	
	// calls the makePred function in the patient class 
	public void setPred() {
		for(Patient p : patients) {
			p.makePred();
		}
	}
	
	// file writer
	public void writeFile() {
		try {
		FileWriter myWriter = new FileWriter("data.csv");
		for(Patient p: patients) {
			String s = "";
			if(p.getResult().equals("") || p.getResult().equals(null)) {
				p.setResult("unknown");
			}
			s += p.getResult() + "," + p.getPrediction() +"," + p.getId();
			for(Double d : p.getData()) {
				s += "," + d;	
			}
			s += "\n";
			myWriter.write(s);
		}
		myWriter.close();
		} catch(Exception e) {
			System.out.print("File not Found");
		}
	}
	
	
	
}
