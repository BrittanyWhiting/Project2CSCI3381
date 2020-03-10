// Brittany Whiting
// CSCI 3381 JAVA OOP
// Project 2!! 

package project2;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
public class MainFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Patients");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(700, 500);

		MainPanel myPanel = new MainPanel();

		frame.getContentPane().add(myPanel);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				myPanel.doClose();
			}
		});
		
		
		
	}
}
