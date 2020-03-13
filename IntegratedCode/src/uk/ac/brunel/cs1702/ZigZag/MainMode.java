package uk.ac.brunel.cs1702.ZigZag;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

import java.awt.Color;

import java.text.DecimalFormat;

import java.util.Random;

import javax.swing.JOptionPane;

public class MainMode{

	protected static double zigLength = 0; //Declaration of global variables 
	protected static int zigSection = 0;
	protected static int randSpeed = 0;
	protected static double totalZigLen = 0;
	protected static String randSpeedcmps4D = "";
	protected static String totalZigDuration2D = "";
	protected static String zigStrDistance2D = "";

	private static int getRandomInt(int min, int max) // creating a method to get a random integer  
	{
		Random r = new Random();
		return randSpeed = r.nextInt ((max - min) + 1) + min; // declaring randSpeed as integer where the random integer is stored
	}

	public static void main(String[] args) // main method 
	{
		Finch ziggyF = new Finch(); //initialising Finch, giving it the name 'Ziggy'

		//Taking input from the User 

		boolean validInput = false; //declaring variable as boolean and assigning false to it
		while (!validInput) //while validInput true or not false, start while loop 
		{
			if (ziggyF.isFinchLevel() == false) //if Finch is not levelled, display alert option box
			{
				Object[] options = {"Cancel","OK"}; // array storing values for option box
				JOptionPane.showOptionDialog(null,"Please ensure Finch is levelled before beginning program.","Alert",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options,options[1]);
			}
			else
			{

				System.out.println("Finch is levelled, mode will proceed shortly. Enjoy! :)"); // Message to be displayed if Finch is levelled
				validInput=true; //set variable to true
			}

		}//end while loop

		boolean validLength = false;//declaring variable as boolean and assigning false to it
		while (!validLength)//while validLength true or not false, start while loop 
		{
			//Prompt user to enter value of length and store in variable zigLength
			zigLength = Double.parseDouble(JOptionPane.showInputDialog(null,"Please enter the length of each zigzag section (in centimetres)."));
			System.out.println("The length you entered was " + zigLength + "cm."); //write variable to console

			//check user input meets specified conditions
			if (zigLength < 15 || zigLength > 85)  //if input meets condition, an error message is displayed
			{
				JOptionPane.showMessageDialog(null, "Please check that the length entered is between 15 and 85 cm.", "ERROR!", 
						JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				validLength = true;// set variable to true
			}
		}//end while loop

		boolean validSection = false;//declaring variable as boolean and assigning false to it
		while (!validSection)//while validSection true or not false, start while loop
		{
			//Prompt user to enter amount of sections and store in variable zigSection
			zigSection = Integer.parseInt(JOptionPane.showInputDialog(null,"Please enter the number of zigzag sections."));
			System.out.println("The amount of sections you entered was " + zigSection + "."); // write variable to console

			//check user input meets specified conditions
			if (zigSection % 2 !=0 || zigSection == 0 || zigSection >12) //if input meets condition, an error message is displayed
			{
				JOptionPane.showMessageDialog(null, "Please check that the number of sections entered is even (excluding 0) and not greater than 12.", "ERROR!", 
						JOptionPane.ERROR_MESSAGE);

			}
			else 
			{
				validSection = true; //set variable to true
			}
		} //end while loop

		if (validLength == true && validSection == true) //check if value stored in variables are true 
		{
			randSpeed = getRandomInt(100,255); //get random integer from getRandomInt method and assign it to the variable randSpeed
			System.out.println("The random speed generated by the program: " + randSpeed + "."); //write variable to console

			//calculations to be used later in code

			double randSpeedcmps = randSpeed * 24.78 / 80; //declare and assign result to variable
			System.out.println("The random speed converted to cm/s: " + randSpeedcmps + "."); //write variable to console

			double timesec = zigLength / randSpeedcmps; //declare and assign result to variable
			System.out.println("The duration Ziggy will take to complete the pattern in seconds: " + timesec + "."); //write variable to console

			double zigDuration = timesec * 1000; //declare and assign result to variable
			System.out.println("The duration converted to milliseconds: " + zigDuration + "."); //write to console

			//Movement, section count and turn code

			int sectCount = 0; //set count to 0
			while (sectCount != zigSection) // while count not equal to zigSection start while loop 
			{

				sectCount ++; // increment count variable each time the while loop loops

				if (sectCount % 2 == 0) // check if count value is even
				{
					ziggyF.setLED(Color.blue);
					ziggyF.sleep(500);
					ziggyF.setWheelVelocities(randSpeed, randSpeed, (int) zigDuration);
					ziggyF.sleep(1000);
					ziggyF.setWheelVelocities(0, 150, 1000); //Finch turns right
				}
				else
				{
					ziggyF.setLED(Color.green);
					ziggyF.sleep(500);
					ziggyF.setWheelVelocities(randSpeed, randSpeed, (int) zigDuration);
					ziggyF.sleep(1000);
					ziggyF.setWheelVelocities(150, 0, 1000); // Finch turns left
				}

				// before looping carry out the code below 
				ziggyF.buzz(200,1000);
				ziggyF.saySomething("Section " + sectCount + "completed", 1000);
				System.out.println("Section travelled: " + sectCount);
			}

			//Retracing code
			sectCount = zigSection; // count variable equals zigSection
			if (sectCount == zigSection) //check if count equals zigSection
			{//if condition is met, carry out retracing code

				ziggyF.setWheelVelocities(0, 100, 2500); 
				ziggyF.buzz(300,1000);
				ziggyF.saySomething("I am retracing now",1000);

			}
			System.out.println(sectCount); // write value of count at retrace to console

			sectCount = zigSection; //count variable equals zigSection
			while (sectCount != 0) //while sectCount not equal to 0 start while loop
			{	
				if (sectCount % 2 == 0) // check if count value is even
				{ 
					ziggyF.setLED(Color.blue); 
					ziggyF.sleep(500);
					ziggyF.setWheelVelocities(randSpeed, randSpeed, (int) zigDuration);
					ziggyF.sleep(1000);
					ziggyF.setWheelVelocities(150, 0, 1000);
				} 
				else 
				{
					ziggyF.setLED(Color.green); 
					ziggyF.sleep(500);
					ziggyF.setWheelVelocities(randSpeed, randSpeed, (int) zigDuration);
					ziggyF.sleep(1000);
					ziggyF.setWheelVelocities(0, 150, 1000);
				}
				
				// before looping carry out the code below 
				ziggyF.buzz(300,1000);
				ziggyF.saySomething("Section " + sectCount + "retraced", 2000);
				
				sectCount --; //decrement count variable each time the while loop loops
				
				System.out.println("Section retraced: " + sectCount);

			}
			//Ending and playing sound code 
			ziggyF.buzz(300, 1000);
			ziggyF.setWheelVelocities(-70, 100, 2000);
			ziggyF.stopWheels();
			ziggyF.setLED(0, 0, 0);
			
			FinSound.main(args); // calling sound from FinSound class
			ziggyF.sleep(500);

			ziggyF.saySomething("Pattern completed", 2000);


			//assigning desired decimal format pattern to result variables
			DecimalFormat result_format2D = new DecimalFormat("#.##");
			DecimalFormat result_format4D = new DecimalFormat("#.####");

			//Calculations to be displayed on log file
			totalZigLen = zigLength * zigSection; //assigning value to variable through calculation 

			double totalZigDuration = ((zigDuration * zigSection)/1000); //declaring variable
			totalZigDuration2D = result_format2D.format(totalZigDuration); //assigning formatted string value to variable

			double zigStrDistance = Math.sqrt(Math.pow(zigLength, 2) + Math.pow(zigLength, 2)) * (zigSection/2);
			zigStrDistance2D = result_format2D.format(zigStrDistance);

			randSpeedcmps4D = result_format4D.format(randSpeedcmps);
			
			//write variables to console
			System.out.println("Total length travelled: " + totalZigLen + ".");
			System.out.println("Total time took to travel: " + totalZigDuration2D + ".");
			System.out.println("Total straight line distance travelled: " + zigStrDistance2D + ".");

			Object[] options = {"Cancel","OK"}; // store option box choices in variable
			//prompt user to click desired option
			int response = JOptionPane.showOptionDialog(null,"Your zigzag pattern has been completed. Press OK to view mode log.","Alert",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE,null, options,options[1]);
			if (response == 1)
			{
				ReadWriteLog.DisplayLog();// if user selected ok, the display log will be shown
			}
			else
			{
				System.out.println("The mode log will not be shown for this execution of the program. ");//write to console 
			}


		}// end if statement

		ziggyF.quit();//close Finch object
		System.exit(0);//exit program 
	} //end of main method

}//end of class

