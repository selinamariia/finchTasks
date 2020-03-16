package uk.ac.brunel.cs1702.Navigate;
import java.io.BufferedReader;
import java.time.LocalTime;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class NavigationTaskv3 {
	//Null would suggest there is no prior parameter being used so it is being initiated.
	private static Finch navigationFinch = null;
	//IOException would suggest there is an unexpected event of an input or output which the program prepares for
	public static void main(String args[]) throws IOException
	{
		//Creates a new variable in the format of a string called Commands
		String Commands = "";
		//Creates a new ArrayList, and sets the format accepted as a String and is called Instructions
		ArrayList<String> Instructions = new ArrayList<String>();
		//Creates a new object fileName to identify the file as an abstract file name
		File fileName = new File("Commands.txt");
		//Creates a new variable in the format of a string called line1
		String line1 = "";
		//Creates a new ArrayList and sets the format as a String and is called readFile
		ArrayList<String> readFile = new ArrayList<String>();

		//Initiates the Finch robot as an object
		navigationFinch = new Finch();
		//Sets a do-while loop which causes the program to constantly do this whilst a condition is not met
		do
		{
			//Sets the string variable Commands to inherit all the values of the method FinchMenu()
			Commands = FinchMenu();
			//Refers to option selected from menu to the right method it should call
			if (Commands.contentEquals("Movement")) moveCommand(Commands, Instructions);
			if (Commands.contentEquals("Write to File")) WriteCommand(Instructions, fileName);
			if (Commands.contentEquals("Read from File")) ReadCommand(Instructions, fileName, line1, readFile);
			if (Commands.contentEquals("Retrace Commands")) RetraceCommand(Commands, Instructions);
		//Sets the do-while loop condition
		} while (!(Commands.equals("Quit")));
		//Terminates the Finch robot
		navigationFinch.quit();
	}
	private static String FinchMenu()
	{
		//Sets the type of data (input) and sets it as an object for the menu
		Object[] Inputs = {"Movement", "Write to File", "Read from File", "Retrace Commands", "Quit"};
		//Sets the GUI Frame info such as the title of the frame and the text displayed.
		String Commands = (String)JOptionPane.showInputDialog(null, "Petko Kolev - 1902637\nChoose a command from:\n\n","Navigation Program", JOptionPane.PLAIN_MESSAGE, null, Inputs,"Movement");
		//Sets the if statement to check if eitherthe length of the options available is 0 or commands have no value.
		if (Commands == null || Commands.length() == 0) Commands = "Quit";
		//Output the command to the user if it is a valid option
		return(Commands);
	}

	private static void WriteCommand(ArrayList<String> Instructions, File fileName) throws IOException
	{
		//Defines a block of code to be tested for errors
		try {
			FileWriter fw = new FileWriter(fileName);
			Writer output = new BufferedWriter(fw);
			LocalTime current = LocalTime.now();
			output.write(current.toString() + "\n");
			int size = Instructions.size();
			for (int i = 0; i < size; i++) {
				output.write(Instructions.get(i).toString() + "\n");
			}
			output.close();
		//Defines a block of code to be executed if an error does appear
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "This file cannot be created!","|File Error|", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private static void ReadCommand(ArrayList<String> Instructions, File fileName, String line1, ArrayList<String> readFile) throws IOException
	{
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			if (!input.ready()) {
				extracted();
			}
			while ((line1 = input.readLine()) !=null) {
				// if the String doesnt start with a int
				char ch = line1.charAt(0);
            
            			if(Character.isDigit(ch) == false){
					//adds the instructions into the Instructions array
					Instructions.add(line1);
					System.out.println(line1);
					//run the command
					runSingleCommand(line1);

				}
			}

			//String found = Instructions.get(list1);
			
			//String cmd = (line1);
			//String[] cmdSplit = cmd.split(cmd);
			//System.out.println(cmdSplit);
			
			input.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "This file cannot be read!","|File Error|", JOptionPane.ERROR_MESSAGE);
			
		}
		//int size = Instructions.size();
		//for (int i = 0; i < size; i++) {
			//System.out.println(Instructions.get(i).toString());
			
			//readFile.add(Instructions.get(i).toString());
			//System.out.println(readFile);
		//String[] line1 = lin.split(line1);
		//}
	}
	
	
	private static void extracted() throws IOException {
		throw new IOException();
		
		
	}
	
	private static void  RetraceCommand(String Commands, ArrayList<String> Instructions) {
		int retraceSteps = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of steps to retrace: "));

		String traceCommand = "T "+ retraceSteps;
		int arraySize = Instructions.size();

		//array to hold the retrace steps
		ArrayList<String> retraceInstructions = new ArrayList<String>();
		
		int tCmd = 0;

		if (retraceSteps<arraySize) {
			//for loop that has i = 1 to start from the back of the list
			for (int i = 1; i <= retraceSteps; i++) {
				
				
	
				int instructionIndex = arraySize - (i+ tCmd);
				System.out.println(instructionIndex);
				String retrace = Instructions.get(instructionIndex);
				System.out.println(retrace);



				//run the command on Finch here

				//if the Trace command is a T then skip it as you do not repeat T commands
				String[] cmdArray = retrace.split(" ");
				String direction = cmdArray[0];
				if(direction.equals("T")){
					tCmd = tCmd+1;
					retrace = Instructions.get(instructionIndex + 1);
					
				}

				//add the instruction to the retract List which we will use to add to the back of instructions once complete
				retraceInstructions.add(retrace);

				runSingleCommand(retrace);
			}
			Instructions.add(traceCommand);
			Instructions.addAll(retraceInstructions);
		}
		else{
			System.out.println("Your number of steps are more that what can be traced");
		}

		//test all the instructions
		System.out.print(Instructions);
	}

    /**
	 * pass in the command string ie F 50 50
	 * break up the string into commands and speed and duration
	 * call move the robot
	 */
	private static void moveCommand(String command, ArrayList<String> Instructions) {
		String direction = (String)(JOptionPane.showInputDialog("Please enter a direction of travel: "));
		
		if (direction.equals ("F")) {
			int duration = Integer.parseInt(JOptionPane.showInputDialog("Please enter the duration of the movement: "));
			int durationSec = duration * 1000	;
			if (durationSec <= 6000) {
				int speed = Integer.parseInt(JOptionPane.showInputDialog("Please enter the speed of the movement: "));
				if (speed <= 200) {
					Instructions.add("F" + " " + duration + " " + speed);
					System.out.println(Instructions);
					navigationFinch.setWheelVelocities(speed,speed,durationSec);
				}
				else {
					JOptionPane.showMessageDialog(null, "This exceeds the maximum speed of 200","|Speed Error|", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "This exceeds the maximum time of 6 seconds!","|Duration Error|", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (direction.equals ("B")) {
			int duration = Integer.parseInt(JOptionPane.showInputDialog("Please enter the duration of the movement: "));
			int durationSec = duration * 1000	;
			if (durationSec <= 6000) {
				int speed = Integer.parseInt(JOptionPane.showInputDialog("Please enter the speed of the movement: "));
				if (speed <= 200) {
					Instructions.add("B" + " " + duration + " " + speed);
					System.out.println(Instructions);
					navigationFinch.setWheelVelocities(-speed,-speed,durationSec);
				}
				else {
					JOptionPane.showMessageDialog(null, "This exceeds the maximum speed of 200","|Speed Error|", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "This exceeds the maximum time of 6 seconds!","|Duration Error|", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (direction.equals ("L")) {
			int duration = Integer.parseInt(JOptionPane.showInputDialog("Please enter the duration of the movement: "));
			int durationSec = duration * 1000	;
			if (durationSec <= 6000) {
				int speed = Integer.parseInt(JOptionPane.showInputDialog("Please enter the speed of the movement: "));
				if (speed <= 200) {
					Instructions.add("L" + " " + duration + " " + speed);
					System.out.println(Instructions);
					navigationFinch.setWheelVelocities(-100,100,1000);
					navigationFinch.setWheelVelocities(speed,speed,durationSec);
				}
				else {
					JOptionPane.showMessageDialog(null, "This exceeds the maximum speed of 200","|Speed Error|", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "This exceeds the maximum time of 6 seconds!","|Duration Error|", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (direction.equals ("R")) {
			int duration = Integer.parseInt(JOptionPane.showInputDialog("Please enter the duration of the movement: "));
			int durationSec = duration * 1000	;
			if (durationSec <= 6000) {
				int speed = Integer.parseInt(JOptionPane.showInputDialog("Please enter the speed of the movement: "));
				if (speed <= 200) {
					Instructions.add("R" + " " + duration + " " + speed);
					System.out.println(Instructions);
					navigationFinch.setWheelVelocities(100,-100,1000);
					navigationFinch.setWheelVelocities(speed,speed,durationSec);
				}
				else {
					JOptionPane.showMessageDialog(null, "This exceeds the maximum speed of 200","|Speed Error|", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "This exceeds the maximum time of 6 seconds!","|Duration Error|", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "This is not a valid direction of travel!","|Direction Error|", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void runSingleCommand(String cmd){
		
		//break the command up into 3 part array

		String[] cmdArray = cmd.split(" ");
		String direction = cmdArray[0];
		int duration = Integer.parseInt(cmdArray[1]);
		int speed = Integer.parseInt(cmdArray[2]);

		if (direction.equals ("F")) {
			int durationSec = duration * 1000	;
			if (durationSec <= 6000) {
				if (speed <= 200) {
					navigationFinch.setWheelVelocities(speed,speed,durationSec);
				}
				else {
					JOptionPane.showMessageDialog(null, "This exceeds the maximum speed of 200","|Speed Error|", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "This exceeds the maximum time of 6 seconds!","|Duration Error|", JOptionPane.ERROR_MESSAGE);
			}
		}else if (direction.equals ("B")) {
			int durationSec = duration * 1000	;
			if (durationSec <= 6000) {
				if (speed <= 200) {
					navigationFinch.setWheelVelocities(-speed,-speed,durationSec);
				}
				else {
					JOptionPane.showMessageDialog(null, "This exceeds the maximum speed of 200","|Speed Error|", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "This exceeds the maximum time of 6 seconds!","|Duration Error|", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (direction.equals ("L")) {
			int durationSec = duration * 1000	;
			if (durationSec <= 6000) {
				if (speed <= 200) {
					navigationFinch.setWheelVelocities(-100,100,1000);
					navigationFinch.setWheelVelocities(speed,speed,durationSec);
				}
				else {
					JOptionPane.showMessageDialog(null, "This exceeds the maximum speed of 200","|Speed Error|", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "This exceeds the maximum time of 6 seconds!","|Duration Error|", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (direction.equals ("R")) {
			int durationSec = duration * 1000	;
			if (durationSec <= 6000) {
				if (speed <= 200) {
					navigationFinch.setWheelVelocities(100,-100,1000);
					navigationFinch.setWheelVelocities(speed,speed,durationSec);
				}
				else {
					JOptionPane.showMessageDialog(null, "This exceeds the maximum speed of 200","|Speed Error|", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "This exceeds the maximum time of 6 seconds!","|Duration Error|", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "This is not a valid direction of travel!","|Direction Error|", JOptionPane.ERROR_MESSAGE);
		}

	}
}
