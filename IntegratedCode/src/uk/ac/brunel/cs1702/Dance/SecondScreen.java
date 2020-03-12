package uk.ac.brunel.cs1702.Dance;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SecondScreen extends JPanel {

	MainLayout mainFrame;
	JTextField usertext = new JTextField(20);
	//takes input from user
	JLabel label = new JLabel("Please enter Hexadecimal number "); // Print message
	JButton submit = new JButton("Submit");
	JLabel errorText = new JLabel("Please enter valid hexadecimal number");// Print message

	public SecondScreen(MainLayout mainFrame) {

		this.mainFrame = mainFrame;

		setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		setLayout(new GridLayout(0, 1));

		setFocusable(true);

		setLayout(null);

		errorText.setBounds(40, 80, 300, 100);
		add(errorText);
		errorText.setVisible(false);

		label.setBounds(10, 20, 250, 25);
		add(label);

		this.requestFocus();
		this.requestFocusInWindow();

		usertext.setBounds(250, 20, 165, 25);
		add(usertext);

		submit.setBounds(100, 50, 200, 50);
		add(submit);
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Convert();
			}
		});

	}

	public void Convert() {
		String hexadecimal = this.usertext.getText();
		errorText.setVisible(false);
		if (validhex(hexadecimal)) {
			this.mainFrame.hexadecimal = hexadecimal;

			this.mainFrame.decimal = HexToDecimal(hexadecimal);

			// Converting decimal number to binary in Java
			this.mainFrame.binary = DecimalToBinary(this.mainFrame.decimal);

			// Converting decimal to Octal in Java
			this.mainFrame.octal = DecimalToOctal(this.mainFrame.decimal);
			
			mainFrame.addHexToList(hexadecimal);

			mainFrame.layout.show(mainFrame.cards, "third panel");

		} else {
			errorText.setVisible(true);
		}

	}
	
	public int HexToDecimal(String hex){
		
        String digits = "0123456789ABCDEF";
        
        hex = hex.toUpperCase();
        
        int result = 0;
        
        for (int i = 0; i < hex.length(); i++) {
        	
            char c = hex.charAt(i);
            int d = digits.indexOf(c);
            result = 16*result + d;
            
        }
        
        return result;
	}
	
	public String DecimalToOctal(int decimal) {
		
		int remainder; //declaring variable to store remainder
		
		String octal = "";
		 //declaring array of octal numbers 
		char octalchars[] = { '0', '1', '2', '3', '4', '5', '6', '7' };
		
		while (decimal > 0) {
			
			remainder = decimal % 8;
			octal = octalchars[remainder] + octal;
			decimal = decimal / 8;
			
		}
		
		return octal;

	}
	
	public String DecimalToBinary(int decimal) {
		
	     int binaryArray[] = new int[8]; 
	     String binaryString = "";
	     int index = 0;    
	     
	     while(decimal > 0){    
	    	 
	       binaryArray[index++] = decimal%2;    
	       decimal = decimal/2; 
	       
	     }  
	     
	     for(int i = index-1; i >= 0; i--){    //for loop to loop through the string right to left
	    	 
	       binaryString = binaryString + String.valueOf(binaryArray[i]); //adds each character to binaryString
	       
	     }    
	     
	     
	     return binaryString;

	}

	public boolean validhex(String hex) {

		// Check hexadecimal length
		if (hex.length() > 2) {
			return false;
		}

		try { // Check is valid hexadecimal by converting to decimal. If it is invalid an
				// exception will be thrown.
			int value = Integer.parseInt(hex, 16);
			// make sure it is a positive value
			if (value > 0)
				return true;
			else
				throw new NumberFormatException();
		} catch (NumberFormatException nfe) {
			// not a valid hex
			System.out.println("not a valid hex");
			return false;
		}

	}

}
