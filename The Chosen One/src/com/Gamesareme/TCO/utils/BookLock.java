package com.Gamesareme.TCO.utils;

import com.Gamesareme.TCO.utils.files.TextFile;

public class BookLock {
	
	public static boolean WALKER = false;
	public static boolean SPRINTER = false;
	public static boolean JUMPER = false;
	
	public static void loadBookLocker(){
		if(TextFile.readFile("data/Walker.txt").equals("true")) WALKER = true;

		if(TextFile.readFile("data/Sprinter.txt").equals("true")) SPRINTER = true;

		if(TextFile.readFile("data/Jumper.txt").equals("true")) JUMPER = true;

		System.out.println("Walker: " + WALKER + " Sprinter: " + SPRINTER + " Jumper: " + JUMPER);
	}
}
