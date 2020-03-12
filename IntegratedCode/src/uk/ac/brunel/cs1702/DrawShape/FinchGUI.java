package uk.ac.brunel.cs1702.DrawShape;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class FinchGUI implements Runnable 
{
	public void run()
	{
		//sets GUI to match OS
		try 
		{ 
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		//runs GUI
		JFrame frame = new JFrame("Draw Shape");
		//set action on close
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		FinchControl newContentPane = new FinchControl();
		
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.setBounds(250,250,550,250);
		//frame.pack();
		frame.setVisible(true);	
	}
}
