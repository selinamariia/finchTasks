package uk.ac.brunel.cs1702.DetectObject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import javax.swing.BorderFactory; 
import javax.swing.JButton; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class GUI_task implements ActionListener{
	JLabel L,L2;
	JButton CuriousButton,ScaredyButton, ObstacleButton, AnyButton, HelpButton;
	static Finch myFinch = new Finch();// initisling finch

	public GUI_task() {  
		GUI_task.myFinch.saySomething("welcome to Task 5: Object Detect");

		JFrame frame=new JFrame("TASK 5: Detect Object");  

		// Creating Curious Finch Button          
		CuriousButton=new JButton("Curious Finch");
		// Creating Scaredy Finch Button          
		ScaredyButton=new JButton("Scaredy Finch");
		// Creating Obstacle Finch Button  
		ObstacleButton=new JButton("Obstacle Finch");
		// Creating any mode Button
		AnyButton=new JButton("any mode");
		// Creating HELP Button
		HelpButton=new JButton("HELP");

		/* the location and size of button.
		 * 1st number is position of button on the screen(width), 
		 * 2nd number is  position of button on the screen(height), 
		 * 3rd number is width,
		 * 4th number is height
		 */        

		JPanel panel = new JPanel();

		L = new JLabel("Detect Objects"); // create labels 
		L.setFont(new Font("Calibri (Body)", Font.ITALIC, 30));//label front size 
		L2 = new JLabel("Select one of the options"); // create lable 

		//adds labels to panel
		panel.add(L); 
		panel.add(L2);

		panel.setBorder(BorderFactory.createEmptyBorder(35, 50, 20, 30)); //TOP,BOTTOM, LEFT,RIGHT
		panel.setLayout(new GridLayout(0, 1)); 
		panel.setBounds(200, 200,150,20); 

		//setting colour yellow
		Color color=new Color(255,255,0);
		//Setting buttons to the colour yellow
		CuriousButton.setBackground(color);
		ScaredyButton.setBackground(color);
		ObstacleButton.setBackground(color);
		AnyButton.setBackground(color);
		HelpButton.setBackground(color);


		panel.add(CuriousButton); // adds CuriousButton to panel
		CuriousButton.addActionListener(new ActionListener() { //when CuriousButton is click  it will call CuriousFinchMode class
			public void actionPerformed(ActionEvent event) {
				CuriousFinchMode.CuriousFinch(); //CuriousFinchMode class
			}
		});
		panel.add(ScaredyButton);// adds ScaredyButton to panel
		ScaredyButton.addActionListener(new ActionListener() {//when ScaredyButton is click  it will call ScaredyButton class
			public void actionPerformed(ActionEvent event) {
				ScaredyFinchMode.ScaredyFinch(); //calls ScaredyFinchMode class
			}
		});
		panel.add(ObstacleButton); // adds ObstacleButton to panel
		ObstacleButton.addActionListener(new ActionListener() {//when ObstacleButton is click  it will call ObstacleButton class
			public void actionPerformed(ActionEvent event) {
				ObstacleFinchMode.ObstacleFinch(); //ObstacleFinchMode class
			}
		});
		panel.add(AnyButton); // adds AnyButton to panel
		AnyButton.addActionListener(new ActionListener() {//when AnyButton is click  it will call AnyButton class
			public void actionPerformed(ActionEvent event) {
				AnyMode.any();	// calls AnyMode class
			}
		});
		panel.add(HelpButton); // adds HelpButton to panel
		HelpButton.addActionListener(new ActionListener() {//when HelpButton is click  it will call HelpButton class
			public void actionPerformed(ActionEvent event) {
				HelpSection.help(); //calls help class
			}
		});

		frame.add(panel, BorderLayout.CENTER); // makes button and labels be centre with the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close program when ever the user wants 
		frame.setTitle("TASK 5: Detect Object");//setting up the frame 
		frame.pack(); 
		frame.setVisible(true); 

	}    
	public static void main (String[] args) {  
		new GUI_task();  
	}       

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
} 
