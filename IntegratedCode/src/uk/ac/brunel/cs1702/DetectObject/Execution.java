package uk.ac.brunel.cs1702.DetectObject;

import java.util.Scanner;

public class Execution {

	public static void main(final String[] args) {
		log();
	}
	public static void log(){
		String choice;
		if (GUI_task.myFinch.isBeakUp()==true) {

			GUI_task.myFinch.setLED(0,0,0);// no colour on beak
			System.out.println("Would you like to view log of execution?");
			System.out.println("please enter yes or no");

			Scanner sc = new Scanner(System.in);
			choice = sc.nextLine(); // saves users entry to choice that is on he the next line

			if (choice.equals("yes")) {
				if	( CuriousFinchMode.modeRan == "Curious Finch") {
					System.out.println("Mode:"+CuriousFinchMode.modeRan+" "+"Number of encounters:"+" "+CuriousFinchMode.objectCount +" "+ "Duration:"+" "+ CuriousFinchMode.totalTime);
					System.exit(0);	//STOPS PROGRAM RUNNING

				}
				else if ( ScaredyFinchMode.modeRan == "Scaredy Finch") {
					System.out.println("Mode:"+""+ScaredyFinchMode.modeRan +" " + "Number of encounters:"+" "+ScaredyFinchMode.objectCount +" "+ "Duration:"+" "+ ScaredyFinchMode.totalTime);
					System.exit(0);	
				}
				else if ( ScaredyFinchMode.modeRan == "Obstacle Finch") {
					System.out.println("Mode:"+ObstacleFinchMode.modeRan+" "+ "Number of encounters:"+" "+ObstacleFinchMode.objectCount +" "+ "Duration:"+" "+ ObstacleFinchMode.totalTime);
					System.exit(0);			
				}
			}
			else if (choice.equals("no")){
				System.out.println("bye");
				System.exit(0);	

			}
			sc.close();


		}
	}
}
