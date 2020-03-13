package uk.ac.brunel.cs1702.DrawShape;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class Main 
{
	public static Finch myFinch;
	public static ArrayList <String> shapeList = new ArrayList<String>();
	public static ArrayList <Double> timeList = new ArrayList<Double>();
	public static ArrayList <Integer> areaList = new ArrayList<Integer>();
	public static ArrayList <Integer> circleCount = new ArrayList<Integer>();
	public static ArrayList <Integer> squareCount = new ArrayList<Integer>();
	public static ArrayList <Integer> triangleCount = new ArrayList<Integer>();
	public static ArrayList <Integer> rectangleCount = new ArrayList<Integer>();

    public static void drawShapeMain() throws IOException
    {
    	//creates the GUI
    	 myFinch = new Finch();
        FinchGUI fgui = new FinchGUI();
        javax.swing.SwingUtilities.invokeLater(fgui);
    }
    
    public static void DrawCircle()
    {
    	System.out.println("You have selected a circle!");
        //beep once
        myFinch.setLED(255, 0, 255, 1000);
        myFinch.sleep(1000);
        
        //one wheel runs to make the finch complete a 360 turn and draw a circle
        myFinch.setWheelVelocities(100, 0, 5300);
        
        //beep once
        myFinch.setLED(255, 0, 255, 1000);
        myFinch.sleep(1000);
        
        //area of the circle is calculated based on the diameter which was pre-measured
        int area = (int) (Math.pow((23.5/2),2)*Math.PI);
        
        //add the circle and its circumference to the list of shapes
        shapeList.add("Circle: "+Math.round((Math.PI*23.5))+" (circumference)");
        
        //add the area of the circle and time taken to draw the shape to lists and add 
        //1 to the circle count list to tally which shape has been drawn the most
        areaList.add(area);
        circleCount.add(1);
        timeList.add(5.3);
    }
    public static void DrawSquare(int side)
    {
    	System.out.println("You have selected a square!");

    	 //beep once
        myFinch.setLED(255, 0, 255, 1000);
        myFinch.sleep(1000);
        
        //finch code to draw the square, first calculate how much time the finch needs to
        //move the given time 
        int time = ((side*5)/46)*1000;
        
        //set the wheels to move for this amount of time, with 90 degree turns between the sides
        myFinch.setWheelVelocities(100, 100, time);
        myFinch.setWheelVelocities(0, 100, 1325);
        myFinch.setWheelVelocities(100, 100, time);
        myFinch.setWheelVelocities(0, 100, 1325);
        myFinch.setWheelVelocities(100, 100, time);
        myFinch.setWheelVelocities(0, 100, 1325);
        myFinch.setWheelVelocities(100, 100, time);
        
        //beep once
        myFinch.setLED(255, 0, 255, 1000);
        myFinch.sleep(1000);
        
        //calculate the area and the total time taken to draw the shape
        int area = (int)Math.pow(side,2);
        double totalTime=((1325*3)+(time*4))/1000;
        
        //store everything to the array lists to access when writing the log file
        shapeList.add("Square: "+side);
        areaList.add(area);
        timeList.add(totalTime);
        squareCount.add(1);
    }
    /**
     * this method makes the finch draw a triangle by first calling methods which calculat the angles in the triangle
     * it then adds relevant information to their respective lists
     * @param side1
     * @param side2
     * @param side3
     */
    public static void drawTriangle(int side1, int side2, int side3)
    {
    	System.out.println("You have selected a triangle!");

    	 //get angles in the triangle
        double angle1 = getAngle(side1, side2, side3);
        double angle2 = getAngle(side2, side3, side1);            
        double angle3 = getLastAngle(angle1, angle2);

        System.out.println("angles in this triangle: " +angle1+ " " + angle2 +" "+ angle3);
        
        //finch code to draw the triangle
        //calculate how much time the finch needs to turn to make each angle of the triangle
        int time1 = (int) angle1 * (265/18);
        int time2 = (int) angle2 * (265/18);
        
        //calculate how much time the finch needs to go straight for each side
        int time3 = ((side1*5)/46)*1000;
        int time4 = ((side2*5)/46)*1000;
        int time5 = ((side3*5)/46)*1000;
        
        //beep once
        myFinch.setLED(255, 0, 255, 1000);
        myFinch.sleep(1000);
        
        //make finch draw shape with these times
        myFinch.setWheelVelocities(100, 100, time3);
        myFinch.setWheelVelocities(0, 100, time1);
        myFinch.setWheelVelocities(100, 100, time4);
        myFinch.setWheelVelocities(0, 100, time2);
        myFinch.setWheelVelocities(100, 100, time5);
        
        //beep once
        myFinch.setLED(255, 0, 255, 1000);
        myFinch.sleep(1000);
        
        //calculate total time taken to draw shape
        double totalTime = (double) (time1+time2+time3+time4+time5)/1000;
        timeList.add(totalTime);
        
        //calculate area of shape using sine rule
        int area = (int) (((side1*side2)/2)*Math.sin(angle1));
        
        shapeList.add("Triangle: "+side1+", "+side2+", "+side3+" (angles: "+angle1+", "+angle2+", "+angle3+")");
        areaList.add(area);
        triangleCount.add(1);
    }
    
    /**
     * method to calculate an angle based on the known lengths of the 3 sides, using the cos rule
     * @param a, b and c
     * @return angle in degrees
     */
    public static double getAngle(int a, int b, int c)
    {
        double x = a;
        double y = b;
        double z = c;
        
        Math.toRadians(x);
        Math.toRadians(y);
        Math.toRadians(z);
        
        double angle = (double) Math.acos((Math.pow(x, 2)+Math.pow(y, 2)-Math.pow(z, 2))/(2*x*y));
        angle = Math.round((angle * (180/Math.PI)));
        return angle;
    }
    
    /**
     * method to calculate the last of 3 angles to make sure they add up to 180 (since converting to and from radians decreases answer accuracy
     * @param angle1, angle2
     * @return angle 3
     */
    public static double getLastAngle(double angle1, double angle2)
    {
        double angle3 = 180 - (angle1+angle2);
        return angle3;
    }
    
    /**
     * this method makes the finch draw the rectangle as well as adds the rectangle stats (area, time taken to draw) to the relevant lists
     * @param side1
     * @param side2
     */
    public static void drawRectangle(int side1, int side2)
    {
    	System.out.println("You have selected a rectangle!");

    	//beep once
        myFinch.setLED(255, 0, 255, 1000);
        myFinch.sleep(1000);
        
        //here goes the finch code to draw the rectangle
        int time1 = ((side1*5)/46)*1000;
        int time2 = ((side2*5)/46)*1000;
        myFinch.setWheelVelocities(100, 100, time1);
        myFinch.setWheelVelocities(0, 100, 1325);
        myFinch.setWheelVelocities(100, 100, time2);
        myFinch.setWheelVelocities(0, 100, 1325);
        myFinch.setWheelVelocities(100, 100, time1);
        myFinch.setWheelVelocities(0, 100, 1325);
        myFinch.setWheelVelocities(100, 100, time2);
        
        //beep once
        myFinch.setLED(255, 0, 255, 1000);
        myFinch.sleep(1000);
        
      //calculate area and time taken to draw the shape, store in array
        int area = side1*side2;
        double totalTime = (time1*2 + time2*2 + 1325*3)/1000;
        shapeList.add("Rectangle: " + side1 + ", " + side2);
        timeList.add(totalTime);
        areaList.add(area);
        rectangleCount.add(1);
    }
    /**
     * find which shape was the largest
     * @return string that says the largest shape drawn and what its area is
     */
    public static String compareAreas()
	{
	    //compare areas, get largest area
		 int maxArea = Collections.max(Main.areaList);
		 int i = Main.areaList.indexOf(maxArea);
	     String maxShape = Main.shapeList.get(i);
		    
		 String largestShape = "Largest shape drawn: " + maxShape + ", area = " + maxArea;
		 
		 return largestShape;
	}
    /**
     * compare list sizes to find out which shape was drawn the most frequently by comparing the size of each array list
     * @return string that says which shape was drawn the most frequently
     */
	public static String compareListSizes()
	{
		//get the number of times each shape was drawn
	    int triangleNum = Main.triangleCount.size();
	    int circleNum = Main.circleCount.size();
	    int squareNum = Main.squareCount.size();
	    int rectangleNum = Main.rectangleCount.size();
	    
	    //compare these numbers and write to file
	    String frequentShape = "";
	    	            
        if((triangleNum>circleNum)&&(triangleNum>squareNum)&&(triangleNum>rectangleNum))
        {
	        frequentShape = ("Most frequently drawn shape: triangle, times drawn: "+triangleNum);
        }
        else if((circleNum>triangleNum)&&(circleNum>squareNum)&&(circleNum>rectangleNum))
        {
	        frequentShape = ("Most frequently drawn shape: circle, times drawn: "+ circleNum);
        }
        else if((squareNum>triangleNum)&&(squareNum>circleNum)&&(squareNum>rectangleNum))
        {
	        frequentShape = ("Most frequently drawn shape: square, times drawn: "+ squareNum);
        }
        else if((rectangleNum>triangleNum)&&(rectangleNum>circleNum)&&(rectangleNum>squareNum))
        {
	        frequentShape = ("Most frequently drawn shape: rectangle, times drawn: "+rectangleNum);
        }      
        else
	    {
	        frequentShape = ("Most frequently drawn shape: none");
	        //write to file                
	    }

	    return frequentShape;
	    
	}
   /**
    * calculates the average time taken to draw a shape by dividing the total of all elements in the time list by the number of shapes drawn
    * @return string that says what the average time taken was
    */
	public static String getAverageTime()
	{
		//get average time taken, first get total time
	    int shapeTotal = Main.shapeList.size();
	    double timeTotal = 0;
	    
	    for(int j=0; j<Main.timeList.size();j++)
	    {
	        timeTotal += Main.timeList.get(j);
	    }
	    
	    //divide total time by total amount of shapes drawn
	    double avgTime = timeTotal/shapeTotal;
	    String averageTime = ("Average time taken to draw a shape: " + avgTime+" seconds");
	    
	    return averageTime;
	}
	/**
	 * this method combines all previous methods to write the relevant information to a file
	 */
	public static void writeToFile()
	{ 
		//try to create file
        try 
        {
        	//this is to use \n to get a new line in the file instead of the fw writing everything on a single line
        	System.getProperty("line.separator");
        	
            File file = new File("drawShapeLog.txt");
            
            if (file.createNewFile()) 
            {
              System.out.println("File created: " + file.getName());
            } else 
            {
              System.out.println(file.getName()+" file already exists.");
            }
            //date formatter to have the time and date on top of the log
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("\n yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            
            FileWriter fw = new FileWriter(file, true);
            //add each element using the filewriter
            fw.write(dtf.format(now));
            fw.write("\n");
            fw.write("All shapes drawn:\n");
            for(int i=0; i<shapeList.size(); i++)
            {
            	fw.write(shapeList.get(i));
                fw.write("\n");
            }
            fw.write(compareAreas());
            fw.write("\n");

            fw.write(compareListSizes());
            fw.write("\n");

            fw.write(getAverageTime());
            fw.write("\n");

            fw.close();
        } 
        //if the file could not be created, display error message
        catch (IOException e) 
        {
            System.out.println("An error occurred.");
        }
	}
}
