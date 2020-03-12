package uk.ac.brunel.cs1702.Dance;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ThirdScreen extends JPanel {

	MainLayout mainFrame;
	JLabel hexadecimalLabel = new JLabel();
	JLabel restartLabel = new JLabel();

	DefaultRun run;

	public ThirdScreen(MainLayout mainFrame) {

		this.mainFrame = mainFrame;
		this.setFocusable(true);

		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {

				requestFocus();
				requestFocusInWindow();

				run = new DefaultRun(mainFrame);
				run.initialise();
				hexadecimalLabel.setText(run.hexadecimal + ", " + run.decimal + ", " + run.octal + ", " + run.binary
						+ ", speed = " + run.speed + ", LED colour (red " + run.colourValues[0] + ", " + "green "
						+ run.colourValues[1] + ", " + "blue " + run.colourValues[2] + ")."); // Prints on GUI
				run.Finchmove();
				System.out.println("Done");// Prints on console
				restartLabel.setText("If user wants to run again press 0 or press 1 to exit"); // Prints on GUI

			}

		});

		setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30)); // Creates Borders for third screen
		setLayout(new GridLayout(0, 1));

		hexadecimalLabel.setBounds(20, 20, 110, 25);

		add(hexadecimalLabel);
		add(restartLabel);

		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_0) {
					System.out.println("You have pressed 0");
					hexadecimalLabel.setText("");
					mainFrame.layout.show(mainFrame.cards, "second panel");
				} else if (e.getKeyCode() == KeyEvent.VK_1) {
					mainFrame.printHexaToFile();
					System.exit(0);
				}
			}
		});

	}

}
