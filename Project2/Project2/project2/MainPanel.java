package project2;

import javax.swing.JPanel;

import project1.Patient;
import project1.PatientCollection;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextArea;
import javax.swing.JRadioButton;


public class MainPanel extends JPanel {
	
	final JFileChooser fc = new JFileChooser();
	private PatientCollection myPats;
	//private JLabel patientData;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private ImageIcon image;
	private TextArea patientData;
	
	
	
	
	public MainPanel() {
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(800,600));
		setLayout(null);
		
		// new patient collection from data.csv
		myPats = new PatientCollection("./data.csv");
		
	
		// selects patient and shows patient data
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Patient myPat = myPats.getPatient((String) comboBox.getSelectedItem());
				patientData.setText(myPat.toString());
			}
		
		});
		
		JLabel lblPatientData = new JLabel("Patient Data: ");
		lblPatientData.setFont(new Font("Rockwell", Font.PLAIN, 15));
		lblPatientData.setBounds(383, 211, 104, 21);
		add(lblPatientData);
		
	
		
		patientData = new TextArea();
		patientData.setEditable(false);
		patientData.setBackground(Color.WHITE);
		patientData.setBounds(383, 234, 400, 121);
		add(patientData);
		
		
		
		// patient id label and id's 
		JLabel lblPatient = new JLabel("Patient ID:");
		lblPatient.setFont(new Font("Rockwell", Font.PLAIN, 15));
		lblPatient.setBounds(39,210,154,22);
		add(lblPatient);
		
		ArrayList<String> ids = myPats.getIds();
		comboBox.setModel(new DefaultComboBoxModel(ids.toArray()));
		comboBox.setBounds(28, 233, 190, 22);
		add(comboBox);
		
	
		JButton btnAddPatient = new JButton("Add Patients");
		btnAddPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnAddPatient) {
			        int returnVal = fc.showOpenDialog(MainPanel.this);

			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            String fn = file.getName();
			            myPats.addPatientsFromFile(fn);
			            comboBox.setModel(new DefaultComboBoxModel(ids.toArray()));
			            comboBox.setBounds(28, 233, 190, 22);
			    		add(comboBox);
			        }
			   }
			}
		});
		btnAddPatient.setBounds(39, 91, 117, 29);
		add(btnAddPatient);
		
		
		JButton btnRemovePatients = new JButton("Remove Patient Selected");
		btnRemovePatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "You are about to delete a Patient", "WARNING", JOptionPane.WARNING_MESSAGE);
				Patient tempPat = myPats.removePatient((String) comboBox.getSelectedItem());
				comboBox.setModel(new DefaultComboBoxModel(ids.toArray()));
				Patient myPat = myPats.getPatient((String) comboBox.getSelectedItem());
				patientData.setText(myPat.toString());		
			}
		});
		btnRemovePatients.setBounds(305, 91, 182, 29);
		add(btnRemovePatients);
		
		JLabel lblPatientDataBase = new JLabel("Patient Information\n");
		lblPatientDataBase.setFont(new Font("Rockwell", Font.BOLD, 15));
		lblPatientDataBase.setBounds(305, 29, 190, 21);
		add(lblPatientDataBase);
		
		JButton btnSaveFile = new JButton("Save File");
		btnSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doClose();
			}
		});
		btnSaveFile.setBounds(617, 91, 117, 29);
		add(btnSaveFile);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(MainPanel.class.getResource("/project2/clipart-doctor-pocket-8.png")));
		lblNewLabel.setBounds(593, 403, 190, 178);
		add(lblNewLabel);
		
		JRadioButton rdbtnLightMode = new JRadioButton("Light Mode");
		buttonGroup.add(rdbtnLightMode);
		rdbtnLightMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnLightMode.isSelected() == true) {
					
					setBackground(Color.LIGHT_GRAY);
					lblPatientDataBase.setForeground(Color.black);
					lblPatient.setForeground(Color.black);
					lblPatientData.setForeground(Color.black);
				}
			}
			});
		rdbtnLightMode.setBounds(0, 6, 141, 23);
		add(rdbtnLightMode);
		
		JRadioButton rdbtnDarkMode = new JRadioButton("Dark Mode");
		buttonGroup.add(rdbtnDarkMode);
		rdbtnDarkMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnDarkMode.isSelected() == true) {
					setBackground(Color.DARK_GRAY);
					lblPatientDataBase.setForeground(Color.white);
					lblPatient.setForeground(Color.white);
					lblPatientData.setForeground(Color.white);
				}
			}
		});
		rdbtnDarkMode.setBounds(653, 6, 141, 23);
		add(rdbtnDarkMode);
		
		
		
		
		
		
		
		

	}
	
	
	
	
	// panel. do write
	
	public void doClose() {
		myPats.doWrite("./textwrite.csv");
	}
}






