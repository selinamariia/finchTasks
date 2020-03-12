package uk.ac.brunel.cs1702.DetectObject;

public class AnyMode {

	public static void main(final String[] args) {
		any();
	}	
	static public void any(){

		int number = (int)(Math.random() * 3) + 1; // Random generate a number between 1-3
		if (number == 1){
			System.out.println("Curious Finch Mode");
			CuriousFinchMode.CuriousFinch(); // calls CuriousFinchMode class
		} else if (number == 2){
			System.out.println("Scaredy Finch Mode");
			ScaredyFinchMode.ScaredyFinch();// calls ScaredyFinchMode class
		} else if (number == 3){
			System.out.println("obstacle Finch Mode");
			ObstacleFinchMode.ObstacleFinch();//calls ScaredyFinchMode class
		} 
	}


}
