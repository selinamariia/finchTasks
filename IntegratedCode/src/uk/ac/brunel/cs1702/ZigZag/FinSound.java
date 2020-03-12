package uk.ac.brunel.cs1702.ZigZag;

import java.io.File;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class FinSound {

	public static void main (String[] args)
	{
		File Tada = new File("Tada.wav"); //follow path to find and load .wav clip
		PlaySound(Tada);//call PlaySound method

	}

	static void PlaySound (File Sound) // function that plays sound clip
	{
		try {
			Clip clip = AudioSystem.getClip(); //initialise sound and retrieve from system
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();//start sound

			Thread.sleep(clip.getMicrosecondLength()/1000);//stop execution code temporarily to allow sound to play fully
		}
		catch (Exception e) //generate error message 
		{
			System.out.println("Sound could not be played");
		}
	}
}
