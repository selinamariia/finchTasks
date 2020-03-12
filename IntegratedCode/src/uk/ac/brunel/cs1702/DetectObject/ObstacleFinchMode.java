package uk.ac.brunel.cs1702.DetectObject;

public class ObstacleFinchMode {
	public static long startTime = System.currentTimeMillis(); //starts timer
	public static long endTime; 
	public static long totalTime =startTime-endTime;

	public static int objectCount = 0; // starting the count at 0 for number of objects
	public static boolean encounter = false;

	public static String modeRan=""; // store rode ran as global so that i can access it later
	public static boolean startpro = true; // to start the program

	public static void main(final String[] args) {
		ObstacleFinch();
	}
	public static void ObstacleFinch(){
		// Run so long as the Finch is leveled

		modeRan="Curious Finch";
		GUI_task.myFinch.saySomething("now running Obstacle Finch");
		while (startpro) {
			// making the finch speak
			// Run so long as the Finch is leveled
			if (GUI_task.myFinch.isFinchLevel()) {

				/* If there's an obstacle on the left/right, turn LED red, back up for 750 ms
	           and turn for 500 ms
				 */ 

				if (GUI_task.myFinch.isObstacleLeftSide() && GUI_task.myFinch.isObstacleRightSide())
				{ 
					avoid(); //CALLS AVOID METHOD
				}

				else if(GUI_task.myFinch.isObstacleLeftSide())
				{   
					avoidL();//CALLS AVOIDL METHOD
				}
				else if (GUI_task.myFinch.isObstacleRightSide())
				{
					avoidR();//CALLS AVOIDR METHOD
				}
				// finch keeping on moving forward if it doesn't find a object
				else if (!GUI_task.myFinch.isObstacleLeftSide() || !GUI_task.myFinch.isObstacleRightSide()) {
					GUI_task.myFinch.setLED(0,255,0);// led green
					goFasterIfObjectFound();// calls the goFasterIfObjectFound to set the speed

				}
			}
			else if (GUI_task.myFinch.isBeakUp()) {
				startpro=false;
				endTime = System.currentTimeMillis();
				GUI_task.myFinch.stopWheels();
				Execution.log();
			}
		}

	}
	public static void avoid() {
		System.out.println("object detected, Front");
		GUI_task.myFinch.setLED(255,0,0);
		GUI_task.myFinch.setWheelVelocities(-100,-100,750); //BACKS UP
		GUI_task.myFinch.setWheelVelocities(100,-255, 500);// TURNS FINCH
		GUI_task.myFinch.buzz(880, 500); //BUZZ NOISE
		encounter = true; //  SETS ENCOUNTER TO TRUE SO IT WILL LOOK IN THE goFasterIfObjectFound METHOD
		objectCount++; // INCREASE THE OBJECT COUNT WHEN IT FINDS A OBJECT
	}
	public static void avoidL() {
		System.out.println("object detected, Left");
		GUI_task.myFinch.setLED(255,0,0); //LED RED
		GUI_task.myFinch.setWheelVelocities(-100,-100,750);//BACKS UP
		GUI_task.myFinch.setWheelVelocities(100,-255, 500);
		GUI_task.myFinch.buzz(880, 500);
		encounter = true;
		objectCount++;
	}
	public static void avoidR() {
		System.out.println("object detected, Right");
		GUI_task.myFinch.setLED(255,0,0);
		GUI_task.myFinch.setWheelVelocities(-100,-100,750);
		GUI_task.myFinch.setWheelVelocities(-255, 100, 500);
		GUI_task.myFinch.buzz(880, 500);
		encounter = true;
		objectCount++;
	}

	public static void goFasterIfObjectFound() { 
		if (encounter == true) {
			makeFinchMoveFaster(); //CALLS makeFinchMoveFaster METHOD

		} else {
			makeFinchMoveSlower(); //CALLS makeFinchMoveSlower METHOD
		}

	}
	private static void makeFinchMoveSlower() {
		GUI_task.myFinch.setWheelVelocities(50, 50); //SLOW SPEED WHEN encounter IS FALSE
	}

	private static void makeFinchMoveFaster() {
		GUI_task.myFinch.setWheelVelocities(120, 120); //FAST SPEED WHEN encounter IS TRUE
	}


}
