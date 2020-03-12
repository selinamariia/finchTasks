package uk.ac.brunel.cs1702.DetectObject;

public class CuriousFinchMode  {

	public static long startTime = System.currentTimeMillis(); //starts timer
	public static long endTime; 
	public static long totalTime =startTime-endTime; // duration of the program

	public static int objectCount = 0; // setting objectCount at 0
	public static boolean encounter = false;
	public static boolean objectFound; //This is to stop the object count increasing even though it only saw the object once

	public static long lastTimeFinchFoundAnObject = System.currentTimeMillis();
	public static boolean hasntFoundMoveInNewDirection = false;

	public static String modeRan=""; //STORES MODE RAN
	public static boolean startpro = true; // to start the program

	public static void updateLastTimeFinchFoundObject() {
		hasntFoundMoveInNewDirection = false;
		lastTimeFinchFoundAnObject = System.currentTimeMillis();
	}

	public static void main(final String[] args) {
		CuriousFinch();
	}
	public static void CuriousFinch(){
		modeRan="Curious Finch";
		GUI_task.myFinch.saySomething("now running Curious Finch"); // making the finch speak
		while (startpro) {// start the program

			// Run so long as the Finch is leveled
			if (GUI_task.myFinch.isFinchLevel()) {
				/*
				 * If there's an obstacle on the left,right set led blue, stop wheels
				 */
				if (GUI_task.myFinch.isObstacleLeftSide() && GUI_task.myFinch.isObstacleRightSide())
				{
					updateLastTimeFinchFoundObject();
					stopMoving(); // calls stop moving method
				}
				else if (GUI_task.myFinch.isObstacleLeftSide()) {
					updateLastTimeFinchFoundObject();
					objectFound=false; // setting objectFound to false since it hasn't found the object 
					moveLeft(); // calls move left method
				} else if (GUI_task.myFinch.isObstacleRightSide()) {
					updateLastTimeFinchFoundObject();
					objectFound=false;// setting objectFound to false since it hasn't found the object
					moveRight(); // calls move right method
				}
				// finch keeping on moving forward if it doesn't find a object
				//
				else if (!GUI_task.myFinch.isObstacleLeftSide() || !GUI_task.myFinch.isObstacleRightSide()) {
					objectFound=false;
					fiveSecs(); // calls fivesec method

				}
			}
			else if (GUI_task.myFinch.isBeakUp()) {
				startpro=false; // stop checking the sensors 
				endTime = System.currentTimeMillis(); // notes end time
				GUI_task.myFinch.stopWheels();// stops wheel 
				Execution.log(); // call execution class

			}
		}
	}
	public static void fiveSecs() {
		//  if hasn't found for five seconds checking on lastTimeFinchFoundAnObject 
		if ( System.currentTimeMillis()-lastTimeFinchFoundAnObject >= 5000 && hasntFoundMoveInNewDirection == false) {
			System.out.println("new direction");
			hasntFoundMoveInNewDirection = true; // wont repeat the new direction method over and over again until it re calls all the if statments
			// then move to new direciton 
			NewDirection();
		}
		else {
			// otherwise just carry on ...
			goFasterIfObjectFound(); // calls goFasterIfObjectFound method
		}
	}
	public static void moveLeft() {
		GUI_task.myFinch.setLED(0, 0, 255);// led blue
		GUI_task.myFinch.setWheelVelocities(-100, 100); // turn left
		System.out.println("found object on the left");
	}

	public static void moveRight() {
		GUI_task.myFinch.setLED(0, 0, 255); // led blue
		GUI_task.myFinch.setWheelVelocities(100, -100); // turn left
		System.out.println("found object on the right");
	}

	public static void stopMoving() {
		encounter = true; // encounter is true so after the object is moved it will so fast
		GUI_task.myFinch.setLED(0, 0, 255);// led blue
		GUI_task.myFinch.stopWheels(); //stops wheel moving
		found();// calls found method which will increase the counter by 1 as the object has been found 

		System.out.println("found object at the front, stopping");
	}
	public static void found() { //calling the whole method once
		if (objectFound==false) { //checking if objectfound is false
			UpdateObjectFound(); //if it is false, my counter will increase by 1 because I then set the objectFound to true
			objectFound=true; // setting back to true
		}
	}
	public static void UpdateObjectFound() {
		objectCount++;// INCREASE THE OBJECT COUNT WHEN IT FINDS A OBJECT
	}
	public static void NewDirection() {

		System.out.println("sleep");
		GUI_task.myFinch.setWheelVelocities(0, 0, 1000);	// make the finch stop for secs

		System.out.println("found nothing, going another way");
		GUI_task.myFinch.setWheelVelocities(100, -100, 1500); // move in a new direction
		System.out.println("finding object");

	}
	public static void goFasterIfObjectFound() {
		if (encounter == true) {
			GUI_task.myFinch.setLED(0, 255, 0); // led green only when it has encunter the object 
			makeFinchMoveFaster();//CALLS makeFinchMoveFaster METHOD

		} else {
			makeFinchMoveSlower();//CALLS makeFinchMoveSlower METHOD
		}

	}
	private static void makeFinchMoveSlower() {
		GUI_task.myFinch.setWheelVelocities(50, 50);//SLOW SPEED WHEN encounter IS FALSE
		CuriousFinch();
	}

	private static void makeFinchMoveFaster() {
		GUI_task.myFinch.setWheelVelocities(200, 200);//SLOW SPEED WHEN encounter IS TRUE
		CuriousFinch();
	}
}
