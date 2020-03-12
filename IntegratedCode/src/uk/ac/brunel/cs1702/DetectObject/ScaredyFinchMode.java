package uk.ac.brunel.cs1702.DetectObject;

public class ScaredyFinchMode {
	public static long startTime = System.currentTimeMillis(); //starts timer
	public static long endTime; 
	public static long totalTime =startTime-endTime;
	public static int objectCount = 0; // starting the count at 0 for number of objects
	public static boolean encounter =false;// speed setting
	public static String modeRan="";
	public static boolean startpro = true; // to start the program
	public static void main(final String[] args) {
		ScaredyFinch();
	}
	public static void ScaredyFinch(){

		GUI_task.myFinch.saySomething("now running Scaredy Finch");// making the finch speak
		modeRan="Scaredy Finch";

		while (startpro) { // starts the program 
			// Run so long as the Finch is leveled
			if (GUI_task.myFinch.isFinchLevel()) {

				// System.out.println("finding object");

				/* If there's an obstacle on the left,right
				     set led red
				      call run method
				 */ 
				if (GUI_task.myFinch.isObstacleLeftSide() && GUI_task.myFinch.isObstacleRightSide())
				{ 
					run();
				}

				else if(GUI_task.myFinch.isObstacleLeftSide())
				{   
					run();
				}
				else if (GUI_task.myFinch.isObstacleRightSide())
				{
					run();
				}
				// finch keeping on moving forward if it doesn't find a object
				else if (!GUI_task.myFinch.isObstacleLeftSide() || !GUI_task.myFinch.isObstacleRightSide()) {
					goFasterIfObjectFound();	// calls the goFasterIfObjectFound to set the speed

				}
			}
			else if (GUI_task.myFinch.isBeakUp()) {
				startpro=false; // STOP CHECK SENSORS
				endTime = System.currentTimeMillis();// NOTES THE END TIME
				GUI_task.myFinch.stopWheels();// STOP THE FINCH MOVING
				Execution.log();// CALLS EXCUTION LOG CLASS
			}
		}
	}

	public static void run() {
		encounter=true; encounter = true; //  SETS ENCOUNTER TO TRUE SO IT WILL LOOK IN THE goFasterIfObjectFound METHOD
		GUI_task.myFinch.setLED(255,0,0); //LED RED
		System.out.println("found object");
		GUI_task.myFinch.buzz(440, 500); // buzz noise
		GUI_task.myFinch.setWheelVelocities(-155,-155,1500);//back up 
		GUI_task.myFinch.setWheelVelocities(100,-125,1500); //turn around //:))))))) working!!!!! adding on time constraint for it to work
		objectCount++; // INCREASE THE OBJECT COUNT WHEN IT FINDS A OBJECT
	}

	public static void goFasterIfObjectFound() { 
		if (encounter == true) {
			makeFinchMoveFaster(); //CALLS makeFinchMoveFaster METHOD

		} else {
			makeFinchMoveSlower(); //CALLS makeFinchMoveSlower Method
		}

	}
	private static void makeFinchMoveSlower() {
		GUI_task.myFinch.setLED(0,255,0);// led green
		GUI_task.myFinch.setWheelVelocities(50, 50); //SLOW SPEED WHEN encounter IS FALSE
		ScaredyFinch();
	}

	private static void makeFinchMoveFaster() {
		GUI_task.myFinch.setLED(0,255,0);// led green
		GUI_task.myFinch.setWheelVelocities(200, 200); //FAST SPEED WHEN encounter IS TRUE
		ScaredyFinch();
	}

}
