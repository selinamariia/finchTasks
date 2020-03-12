package uk.ac.brunel.cs1702.ZigZag;

import java.io.FileWriter; 

import java.io.BufferedWriter;

public class ReadWriteLog {

	public static void DisplayLog()
	{
		try {
			FileWriter logPrint = new FileWriter ("ZigzagLogFile.txt"); //create log .txt file and specify name
			BufferedWriter modeInfo = new BufferedWriter(logPrint);

			String fileTitle = "                                  ZIGZAG MODE LOG (User Inputs and Calculations)" + "\n";//title of log file
			String output = "The length of each zigzag section was " + MainMode.zigLength + "cm." + "\n";
			String output1 = "The number of zigzag sections was " + MainMode.zigSection + "." + "\n";
			String output2 = "The calculations carried out during the program: " + "\n";
			String output3 = "The random speed generated was: " + MainMode.randSpeed + "." + " The conversion to cm per second was " + MainMode.randSpeedcmps4D + "cm/s" + "\n";
			String output4 = "The total length of the traversed path was " + MainMode.totalZigLen + "cm." + "\n";
			String output5 = "The total duration that Ziggy, the Finch, took to complete the pattern was " + MainMode.totalZigDuration2D + " seconds." + "\n";
			String output6 = "The 'straight output' distance travelled was " + MainMode.zigStrDistance2D + "cm." + "\n";

			modeInfo.write(fileTitle); //write variable to 
			modeInfo.newLine();//insert new line

			modeInfo.write(output);
			modeInfo.newLine();

			modeInfo.write(output1);
			modeInfo.newLine();

			modeInfo.write(output2);
			modeInfo.newLine();

			modeInfo.write(output3);
			modeInfo.newLine();

			modeInfo.write(output4);
			modeInfo.newLine();

			modeInfo.write(output5);
			modeInfo.newLine();

			modeInfo.write(output6);
			modeInfo.newLine();

			modeInfo.close();//close stream
			logPrint.close();//close stream

		} catch (Exception e) {
			System.out.println("Could not create text file."); //write to console if an error arises
		}
	}//end of method

}//end of class
