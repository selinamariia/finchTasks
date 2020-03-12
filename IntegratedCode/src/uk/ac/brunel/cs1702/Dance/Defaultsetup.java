package uk.ac.brunel.cs1702.Dance;

public class Defaultsetup {
	MainLayout mainFrame; 
	static int decimal;
	int[] colourValues = new int[3];

	public Defaultsetup(MainLayout mainFrame) {
		this.mainFrame = mainFrame;
		decimal = mainFrame.decimal;

	}

	public void setLED() { // it assign Finch the led light
		int red, blue, green;
		red = decimal;
		green = (decimal % 80) * 3;
		if (green > red) {
			blue = green;

		} else {
			blue = red;
		}

		colourValues[0] = red;
		colourValues[1] = green;
		colourValues[2] = blue;

		mainFrame.myFinch.setLED(red, green, blue);

	}

	public int getSpeed(String octal) { // Sets up the speed according to the octal output
		Integer Intoctal = Integer.valueOf(octal);
		if (Intoctal < 60) {
			octal = (octal + 50);
			return Intoctal;
		}

		if (Intoctal > 250) {
			Intoctal = 250;
			return Intoctal;
		}
		return Intoctal;
	}

	public int[] getColours() {
		return this.colourValues; 
	}
}
