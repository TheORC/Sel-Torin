package com.Gamesareme.TCO.utils;

import com.Gamesareme.TCO.utils.files.TextFile;



public class LevelLock {
	
	public static boolean LEVEL_1 = true;
	public static boolean LEVEL_2 = false;
	public static boolean LEVEL_3 = false;
	public static boolean LEVEL_4 = false;
	
	public static void loadLevelLocker(){
		if(TextFile.readFile("data/Level2.txt").equals("true")) LEVEL_2 = true;
		if(TextFile.readFile("data/Level3.txt").equals("true")) LEVEL_3 = true;
		if(TextFile.readFile("data/Level4.txt").equals("true")) LEVEL_4 = true;
	}
}
