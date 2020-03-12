package uk.ac.brunel.cs1702;

import java.awt.Font;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Array;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

	public class Assignment2 implements ActionListener
	{
		private static String[] taskList = {"task 1: search for light", "task 2: draw shape", "task 3: navigation", "task 4: zig zag", "task 5: detect object", "task 6: dance"};
		private static String selection="";
		private static JComboBox<Array> selected;
	    private static JButton selectButton;
	    //finch control
	    //private static Finch myFinch = new Finch();// initisling finch
	    
	    public static void main(String[] args) throws IOException
	    {
	    	//creates the GUI
	    	//myFinch = new Finch();
	        Assignment2 fgui = new Assignment2();
	    }
	    public Assignment2() 
	    {  
	    	//set font to make text bigger
			Font font = new Font("0", Font.PLAIN, 17);

			//sets GUI to match OS
			try 
			{ 
			    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			    e.printStackTrace();
			}
			
			//create frame
	        JFrame frame0=new JFrame("Select a task!");  
			frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			//create panel and label
			JPanel p = new JPanel();
			JLabel l = new JLabel("task");
			l.setText("Pick a task for the finch!");
			l.setFont(font);
			
	        //create combobox with each task
			selected = new JComboBox(taskList);
			selected.setFont(font);
			
	        // Creating Button          
	        selectButton=new JButton("Select");
	        selectButton.setFont(font);
			selectButton.setActionCommand("Select");
			
			//add action listener
			selectButton.addActionListener(this);
			
	        //add all elements to panel
			p.add(l);
			p.add(selected);
	        p.add(selectButton); 
	        
	        //add panel to frame
			frame0.add(p);

			//set frame bounds
			frame0.setBounds(150,150,400,150);
			//frame.pack();
			frame0.setVisible(true);

	}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getActionCommand().equals("Select"))
			{
				//store choice as string
				selection = (String) selected.getSelectedItem();
				
				if(selection == "task 1: search for light")
				{
					System.out.println("task 1");
				}
				else if(selection == "task 2: draw shape")
				{
				}
				else if(selection == "task 3: navigation")
				{
					
				}
				else if(selection == "task 4: zig zag")
				{
					
				}
				else if(selection == "task 5: detect object")
				{
					
				}
				else if(selection == "task 6: dance")
				{
					
				}
				else
				{
					System.out.println("An error occurred.");
				}
			}
		}

}
