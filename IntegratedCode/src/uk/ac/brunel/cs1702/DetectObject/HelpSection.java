package uk.ac.brunel.cs1702.DetectObject;

import java.awt.Color;
import java.io.File;// import the file class
import java.io.FileNotFoundException; // import this calls to hanle errotrs
import java.util.Scanner; //imports the scanner to read text files
public class HelpSection {

	public static void main(final String[] args) {
		help();
	}
	public static void help(){


		//UNI FILE // File myObj = new File("\\\\ikb\\home\\15\\1816815\\My Documents\\CS1702\\help.txt");
		//HOME FILE// File myObj = new File("C:\\Users\\Student\\Documents\\Group Project\\assignment 2\\code\\help.txt");
		GUI_task.myFinch.setLED(Color.YELLOW); //led beak yellow
		try {
			File myObj = new File("\\\\ikb\\home\\15\\1816815\\My Documents\\CS1702\\help.txt"); // PATH TO FIND THE HELP SECTION
			Scanner myReader = new Scanner(myObj); // reads the text file
			// reads line by line
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine(); // process each line
				System.out.println(data);
			}
			myReader.close();// close reader
		} 
		catch (FileNotFoundException g) {
			System.out.println("An error occurred.");
			g.printStackTrace();
		}
	}
}
