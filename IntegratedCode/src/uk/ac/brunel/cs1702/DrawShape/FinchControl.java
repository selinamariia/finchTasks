package uk.ac.brunel.cs1702.DrawShape;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Random;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class FinchControl extends JPanel implements ActionListener 
{
	private static final long serialVersionUID = 193548327L;
	private static JButton doneButton, randomButton, confirmButton, quitButton;
	private static JLabel startLabel, labelCaption, squareLabel, triangleLabel, rectangleLabel, triangleErrorLabel;
	private static JTextField textField1, textField2, textField3;
	private static Timer timer, gameStart;
	private static String[] choicesList = {"circle", "square", "triangle", "rectangle"};
	private static String choice;
	private static JComboBox<Array> choices;
	
	public FinchControl()
	{

		//changing font
		Font font = new Font("0", Font.PLAIN, 15);
		
		//create the array with all possible choices
		choices = new JComboBox(choicesList);
		choices.setFont(font);

		//here are labels to explain what-to-do to the user that will be displayed at the appropriate times
		startLabel = new JLabel("Finch must be level for program to start!");
		startLabel.setFont(font);
		
		labelCaption = new JLabel("Select a shape for the finch to draw!");
		labelCaption.setFont(font);

		squareLabel = new JLabel("Type in a side length value between 15 and 85cm:");
		squareLabel.setFont(font);

		triangleLabel = new JLabel("Type in three side length values between 15 and 85cm");
		triangleLabel.setFont(font);

		rectangleLabel = new JLabel("Type in two side length values between 15 and 85cm");
		rectangleLabel.setFont(font);

		
		triangleErrorLabel = new JLabel("ERROR! The sum of any two sides must be greater than the third side to form a valid triangle");
		triangleLabel.setFont(font);
		
		//textfield for the user to input side lengths
		//since the greatest amount of textfields needed is three for triangle, i made three different ones
		textField1 = new JTextField(3);
		textField1.setFont(font);
		textField2 = new JTextField(3);
		textField2.setFont(font);
		textField3 = new JTextField(3);
		textField3.setFont(font);
		
		//this button is to select a random shape
		randomButton = new JButton("Random");
		randomButton.setFont(font);
		randomButton.setVerticalTextPosition(AbstractButton.CENTER);
		randomButton.setHorizontalTextPosition(AbstractButton.CENTER);
		randomButton.setActionCommand("Random");
				
		//button to click once a shape has been selected
		doneButton = new JButton("Done");
		doneButton.setFont(font);
		doneButton.setVerticalTextPosition(AbstractButton.CENTER);
		doneButton.setHorizontalTextPosition(AbstractButton.CENTER);
		doneButton.setActionCommand("Done");
		
		//confirm
		confirmButton = new JButton("Confirm");
		confirmButton.setFont(font);
		confirmButton.setVerticalTextPosition(AbstractButton.CENTER);
		confirmButton.setHorizontalTextPosition(AbstractButton.CENTER);
		confirmButton.setActionCommand("Confirm");
		
		//button to click when the user wants to exit the program and write to a log file
		quitButton = new JButton("Quit");
		quitButton.setFont(font);
		quitButton.setVerticalTextPosition(AbstractButton.CENTER);
		quitButton.setHorizontalTextPosition(AbstractButton.CENTER);
		quitButton.setActionCommand("Quit");
		
		//listener to know when buttons have been clicked
		doneButton.addActionListener(this);
		randomButton.addActionListener(this);
		confirmButton.addActionListener(this);
		quitButton.addActionListener(this);

		//explanation of what each button does when user hovers over them
		doneButton.setToolTipText("Click this button once you've selected the shape to draw");
		randomButton.setToolTipText("Click this button to select a random shape to draw");
		confirmButton.setToolTipText("Click this button once you have entered side lengths");
		quitButton.setToolTipText("Click this button to exit the program and write to a log file");
		
		//adding all components to the JFrame so they show up
		add(startLabel);
		add(labelCaption);
		add(choices);
		add(randomButton);
		add(doneButton);
		add(confirmButton);
		add(quitButton);

		add(squareLabel);
		add(triangleLabel);
		add(rectangleLabel);
		add(triangleErrorLabel);
		
		add(textField1);
		add(textField2);
		add(textField3);
					
		//these only need to come up when a choice has been made so they are set to invisible for the time being
		squareLabel.setVisible(false);
		triangleLabel.setVisible(false);
		rectangleLabel.setVisible(false);
		
		triangleErrorLabel.setVisible(false);
			
		textField1.setVisible(false);
		textField2.setVisible(false);
		textField3.setVisible(false);
		
		//this button should not be enabled until side length(s) have been inputted
		confirmButton.setEnabled(false);

		//this is a timer to start the game
		//checks if the finch is level
		gameStart = new Timer(10, this);
		gameStart.setActionCommand("GameStart");
		gameStart.setInitialDelay(0);
		gameStart.start();
		
		//this is a timer for the choices
        timer = new Timer(10, this);
        timer.setActionCommand("Timer");
        timer.setInitialDelay(0);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//check if finch is level before enabling done button
		if(e.getActionCommand().equals("GameStart"))
		{
			if(Main.myFinch.isFinchLevel()==false) 
			{
				//if not level, disable buttons so the task cannot begin
				doneButton.setEnabled(false);
				randomButton.setEnabled(false);
			}
			else
			{
				//if level, enable buttons
				doneButton.setEnabled(true);
				randomButton.setEnabled(true);
			}
		}
		//if random is selected, generate a random number and access that choice from the array 
		if(e.getActionCommand().equals("Random"))
		{
			int num;
			Random r = new Random();
			//generate random number
	        num = (r.nextInt((4 - 1) + 1) + 1)-1;
	        //get shape at this index
	        choice = choicesList[num];
	        choices.setSelectedIndex(num);
	        //display message to user to inform them of shape selected
	        System.out.println("You have selected to draw a "+choice+"! If you are happy with this choice, click done");
	        System.out.println("if not you can click 'random' again or select a choice from the drop-down menu");
		}
		//get choice when done button is clicked
		if(e.getActionCommand().equals("Done"))
		{
			//since the game has started the finch no longer needs to check if it is level
			gameStart.stop();
			
			//start the timer that will check for user input
			timer.start();
			
			//make done button and random button un-selectable so that the choice cannot be changed mid-way
			doneButton.setEnabled(false);
			randomButton.setEnabled(false);
			
			//make quit button selectable so now that a choice has been made, the user can exit the program when they want to
			quitButton.setEnabled(true);
			
			//store the selected shape in a string
			choice = (String) choices.getSelectedItem();
						
			if(choice=="circle")
			{
				//finch draws circle
				Main.DrawCircle();
				
				//done and random button reappears
				doneButton.setEnabled(true);
				randomButton.setEnabled(true);
			}
			else if(choice=="square")
			{
				//display "type in a side length between 15 and 85"
				squareLabel.setVisible(true);
				//set the textfield to visible
				textField1.setVisible(true);
			}
			else if(choice=="triangle")
			{
				//display "type in three side lengths between 15 and 85" and textfields for user to input values
				triangleLabel.setVisible(true);

				textField1.setVisible(true);
				textField2.setVisible(true);
				textField3.setVisible(true);
			}	
			else if(choice=="rectangle")
			{
				//display "type in two side length values between 15 and 85" and textfields for user to input values
				rectangleLabel.setVisible(true);
				
				textField1.setVisible(true);
				textField2.setVisible(true);
			}
		}	
		//timer to constantly check user input
		if(e.getActionCommand().equals("Timer"))
		{
			choice = (String) choices.getSelectedItem();
	
			//as each choice requires a different number of inputs
			//each shape is checked separately
			if(choice=="square") 
			{
				//calls on method to check that values are within the accepted range
				if(enableConfirm(2)==true)
				{
					confirmButton.setEnabled(true);
				}				
			}
			
			if(choice=="triangle") 
			{
				//calls on method to check that values are within the accepted range
				if(enableConfirm(3)==true)
				{
					//store user input 
					int value1 = Integer.valueOf(textField1.getText());
					int value2 = Integer.valueOf(textField2.getText());
					int value3 = Integer.valueOf(textField3.getText());

					//check if a valid triangle can be made with these three values
					//if it cannot:
					try 
					{
						if(isTriangleValid(value1, value2, value3)==false)
						{
							//display error message
							triangleErrorLabel.setVisible(true);
							
							//clear input so user can re-enter valid values
							textField1.setText(null);
							textField2.setText(null);
							textField3.setText(null);
							
							//store user input 
							value1 = Integer.valueOf(textField1.getText());
							value2 = Integer.valueOf(textField2.getText());
							value3 = Integer.valueOf(textField3.getText());
						}
						else
						{
							confirmButton.setEnabled(true);
						}
					}
					catch(NumberFormatException e1)
					{
						//to avoid numberformatexception errors in case the input is not a number
					}
				}
			}
			
			if(choice=="rectangle") 
			{
				//calls on method to check that values are within the accepted range, if they are then the confirm button is enabled
				if(enableConfirm(4)==true)
				{
					confirmButton.setEnabled(true);
				}
			}
		}
		
		if(e.getActionCommand().equals("Confirm"))
		{
			if(choice == "square")
			{
				//store user input
				String TFValue1 = textField1.getText();
				//convert user input to int
				int Value1 = Integer.valueOf(TFValue1);
				
				//draw square with this side length 
				Main.DrawSquare(Value1);

				//hide unnecessary fields
				textField1.setText(null);
				textField1.setVisible(false);
				squareLabel.setVisible(false);
			}
			else if(choice == "triangle")
			{
				//hide error message
				triangleErrorLabel.setVisible(false);
				triangleLabel.setVisible(false);
				
				//store user input 
				int value1 = Integer.valueOf(textField1.getText());
				int value2 = Integer.valueOf(textField2.getText());
				int value3 = Integer.valueOf(textField3.getText());
				
				//draw triangle
				Main.drawTriangle(value1, value2, value3);
				
				//reset text fields and hide unnecessary fields
				textField1.setText(null);
				textField2.setText(null);
				textField3.setText(null);
				
				textField1.setVisible(false);
				textField2.setVisible(false);
				textField3.setVisible(false);					
			}
			else if(choice == "rectangle")
			{
				//store user input
				int value1 = Integer.valueOf(textField1.getText());
				int value2 = Integer.valueOf(textField2.getText());
				
				//draw rectangle with these side length values
				Main.drawRectangle(value1, value2);

				//reset text fields and hide unnecessary fields
				textField1.setText(null);
				textField2.setText(null);
				
				textField1.setVisible(false);
				textField2.setVisible(false);
				
				rectangleLabel.setVisible(false);
			}
			
			doneButton.setEnabled(true);
			randomButton.setEnabled(true);
			confirmButton.setEnabled(false);
			
		}

		//if quit button is clicked, exit the program
		if(e.getActionCommand().equals("Quit"))
		{
			JFrame parent = (JFrame)SwingUtilities.getWindowAncestor(this);
			parent.setVisible(false);
			parent.dispose();
			
			//make finch dance
            System.out.println("Celebratory dance!");
            Main.myFinch.setLED(255, 0, 255, 1000);
            Main.myFinch.sleep(500);
            Main.myFinch.setLED(25, 25, 255, 1000);
            Main.myFinch.setWheelVelocities(-100, -50, 1000);
            Main.myFinch.setWheelVelocities(50, 100, 1000);
            Main.myFinch.setWheelVelocities(100, 0, 1000);
            Main.myFinch.setWheelVelocities(0, 100, 1000);
            Main.myFinch.setWheelVelocities(-100, 0, 1000);
            
            //quit finch
			Main.myFinch.quit();
			
			//call on method to write log file
			Main.writeToFile();
			
			try 
			{
				uk.ac.brunel.cs1702.Assignment2.main(null);
			} 
			catch (IOException e1) 
			{
				System.out.println("An error occurred.");
			}
		}
		
	}
	/**
	 * this method enables the confirm button by checking if side values are in the accepted range
	 * it checks separately for each shape as each shape has different number of parameters
	 * @param shapeSelected
	 * @return true or false 
	 */
	private boolean enableConfirm(int shapeSelected)
	{
		String TFValue1 = textField1.getText();
		String TFValue2 = textField2.getText();
		String TFValue3 = textField3.getText();
		
		boolean isValue1Valid = false;
		boolean isValue2Valid = false;
		boolean isValue3Valid = false;
		//try catch so that number format exception errors are caught if the user enters something other than a number
		try
		{
			if(TFValue1!=null)
			{
				int Value1 = Integer.valueOf(TFValue1);
				if((Value1>=15) && (Value1<=85))
				
					isValue1Valid = true;
			}
			if(TFValue2!=null)
			{
				int Value2 = Integer.valueOf(TFValue2);
				if((Value2>=15) && (Value2<=85))
				{
					isValue2Valid = true;
				}
			}
			if(TFValue3!=null)
			{
				int Value3 = Integer.valueOf(TFValue3);
				if((Value3>=15) && (Value3<=85))
				{
					isValue3Valid = true;
				}
			}
		} 
		catch(NumberFormatException e)
		{
			
		}
		//for each shape, different number of side lengths are needed so each is checked separately
		if((shapeSelected == 2)&&(isValue1Valid==true)&&(isValue2Valid==false)&&(isValue3Valid==false))
		{
			return true;
		}
		else if((shapeSelected == 3)&&(isValue1Valid==true)&&(isValue2Valid==true)&&(isValue3Valid==true))
		{
			return true;
		}
		else if((shapeSelected == 4)&&(isValue1Valid==true)&&(isValue2Valid==true)&&(isValue3Valid==false))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
/**
 * this method checks if a triangle can be made out of the three side lengths by seeing if the sum of any two sides is greater than the third side
 * @param side1
 * @param side2
 * @param side3
 * @return true or false
 */
	   public static boolean isTriangleValid(int side1, int side2, int side3)
	    {
		   try
		   {
			   if((side1 + side2)>side3)
			   {
				   if((side1 + side3)>side2)
				   {
					   if((side2 + side3)>side1)
					   {
						   return true;
					   }
				   }
			   }
		   }

		   catch(NumberFormatException e)
		   {
		   }
	        return false;
	    }
}