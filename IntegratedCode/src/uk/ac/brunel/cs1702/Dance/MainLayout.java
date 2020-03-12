package uk.ac.brunel.cs1702.Dance;

import java.awt.BorderLayout;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MainLayout {
	//setting up panels for screen
	StartScreen startPanel = new StartScreen(this);
	SecondScreen secondPanel = new SecondScreen(this);
	ThirdScreen thirdPanel = new ThirdScreen(this);
	private ArrayList<String> hexaList = new ArrayList<String>();
	String outputFile = "Output.txt";
	Finch myFinch = new Finch();

	CardLayout layout = new CardLayout();
	JPanel cards = new JPanel(layout);
	// Declaring the variables
	int decimal;
	String hexadecimal;
	String octal;
	String binary;

	public MainLayout() {
		//Cards method
		cards.add(startPanel, "start panel");
		cards.add(secondPanel, "second panel");
		cards.add(thirdPanel, "third panel");

		JFrame frame = new JFrame("Dancing Finch Game");
		frame.add(cards);
		frame.setPreferredSize(new Dimension(700, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void addHexToList(String hex) {
		this.hexaList.add(hex);
	}
	
	public void printHexaToFile() { // For text file and sorting hexadecimal
		String currentDir = System.getProperty("user.dir");
		System.out.println(currentDir); // prints the location of text file on console
		
		Collections.sort(this.hexaList, new Comparator<String>() {
			public int compare(String hex1, String hex2) {
				return hex1.compareTo(hex2);
			}
		});
		
        FileWriter writer;
		try {
			
			writer = new FileWriter(this.outputFile, false);
			for(int i = 0; i < this.hexaList.size() ; i++) {
				if(i == 0) writer.write(this.hexaList.get(i));
				else writer.write(", " + this.hexaList.get(i));
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
 
	}

	public static void main(String[] args) {
		MainLayout mainLayout = new MainLayout();
	}

}
