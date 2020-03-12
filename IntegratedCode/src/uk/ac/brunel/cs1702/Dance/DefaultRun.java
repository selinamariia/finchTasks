package uk.ac.brunel.cs1702.Dance;

public class DefaultRun {
	MainLayout mainFrame;
	String hexadecimal;
	String binary;
	String octal;
	int decimal;
	Defaultsetup setup;
	int speed;
	int[] colourValues;

	public DefaultRun(MainLayout mainFrame) {
		this.mainFrame = mainFrame;
		this.hexadecimal = mainFrame.hexadecimal;
		this.binary = mainFrame.binary;
		this.octal = mainFrame.octal;
		this.decimal = mainFrame.decimal;
		this.setup = new Defaultsetup(this.mainFrame);
	}

	public void initialise() {
		this.setup.setLED();
		this.colourValues = this.setup.getColours();
		this.speed = this.setup.getSpeed(octal);
		System.out.println(this.hexadecimal + ", " + this.decimal + ", " + this.octal + ", " + this.binary
				+ ", speed = " + this.speed + ", LED colour (red " + this.colourValues[0] + ", " + "green "
				+ this.colourValues[1] + ", " + "blue " + this.colourValues[2] + ").");

	}

	public void Finchmove() {

		int duration = 0;

		if (hexadecimal.length() == 1) {
			duration = 1000;
		}

		else {
			duration = 500;
		}

		for (int i = binary.length() - 1; i >= 0; i--) // use FOR loop to loop through binary string right to left
		{
			char c = binary.charAt(i); // gets every character (1 or 0) and makes it equal to c
			if (c == '1') // if c is equal to 1
			{

				mainFrame.myFinch.setWheelVelocities(this.speed, this.speed, duration); // set wheels to forward with
																						// speed calculated for 1 second
			} else // if it's not 1 then it's 0
			{
			mainFrame.myFinch.setWheelVelocities(-this.speed, -this.speed, duration); // set wheels to backward with
																							// speed calculated for 1
																							// second
			}

		}
		mainFrame.myFinch.setLED(0, 0, 0); // turn LED off
	}

}
