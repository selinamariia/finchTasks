package uk.ac.brunel.cs1702.Dance;


import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class StartScreen extends JPanel {

	MainLayout mainFrame;

	public StartScreen(MainLayout mainFrame) {

		this.mainFrame = mainFrame;
		// Setting borders for start screen
		setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		setLayout(new GridLayout(0, 1));
		addKeyListener(new KeyListener() { // This key event is for the '0' & '1' which begins and terminates the game
			@Override
			public void keyPressed(KeyEvent e) {

			}

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) { 
				if (e.getKeyCode() == KeyEvent.VK_0) {
					System.out.println("You have pressed 0"); // prints message on console
					mainFrame.layout.show(mainFrame.cards, "second panel");
				} else if (e.getKeyCode() == KeyEvent.VK_1) {
					System.exit(0);
				}
			}
		});

		setFocusable(true);

		setLayout(null);

		JLabel label = new JLabel("0 to begin game"); // Print on GUI
		label.setBounds(20, 20, 110, 25);
		add(label);

		JLabel end = new JLabel("1 to terminate game"); // Print on GUI
		end.setBounds(20, 50, 150, 25);
		add(end);

	}

}
