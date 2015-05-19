package com.Gamesareme.TCO.main;

import com.Gamesareme.TCO.utils.files.TextFile;

public class PlayerLives {
	
	public static final int lifeReset = 5;
	public static int currentLifes = 5;
	
	public static void loadLives(){
		currentLifes = Integer.parseInt(TextFile.readFile("data/Lives.txt"));
	}
	
	public static void resetLives(){
		currentLifes = lifeReset;
		TextFile.writeFile("data/Lives.txt", String.valueOf(lifeReset));
	}
	
	public static void playerDie(){
		currentLifes--;
		TextFile.writeFile("data/Lives.txt", String.valueOf(currentLifes));
	}
	
	public static void resetGame(){
		WorldManager.setCurrentPoint(0);
		WorldManager.setCyrstalsCollected(0);
		resetLives();
	}

}
