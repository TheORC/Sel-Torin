package com.Gamesareme.TCO.main;

import java.util.ArrayList;

import com.Gamesareme.TCO.Game;
import com.Gamesareme.TCO.entity.Player;
import com.Gamesareme.TCO.entity.SunCrystal;
import com.Gamesareme.TCO.enums.GameState;
import com.Gamesareme.TCO.screens.Hud;
import com.Gamesareme.TCO.screens.LevelLoadScreen;
import com.Gamesareme.TCO.world.World;

public class WorldManager {
	
	private static World world;
	private static Player player;
	private static Hud hud;
	private static Camera camera;
	private static int levelNum;
	private static int currentPoint = 0;
	private static int cyrstalsCollected = 0;
	private static int numEntities = 0;
	
	private static ArrayList<SunCrystal> points = new ArrayList<SunCrystal>();
	
	public static void loadWorld(String worldName, int levelNum){
		WorldManager.levelNum = levelNum;
		Game.state = GameState.LOADING_WOLD;
		LevelLoadScreen level = new LevelLoadScreen(worldName, levelNum);
		Game.getInstance().setLeavelLoadScreen(level);
	}

	/**
	 * @return the world
	 */
	public static World getWorld() {
		return world;
	}

	/**
	 * @return the player
	 */
	public static Player getPlayer() {
		return player;
	}

	/**
	 * @return the hud
	 */
	public static Hud getHud() {
		return hud;
	}

	/**
	 * @return the camera
	 */
	public static Camera getCamera() {
		return camera;
	}

	/**
	 * @param world the world to set
	 */
	public static void setWorld(World world) {
		WorldManager.world = world;
	}

	/**
	 * @param player the player to set
	 */
	public static void setPlayer(Player player) {
		WorldManager.player = player;
	}

	/**
	 * @param hud the hud to set
	 */
	public static void setHud(Hud hud) {
		WorldManager.hud = hud;
	}

	/**
	 * @param camera the camera to set
	 */
	public static void setCamera(Camera camera) {
		WorldManager.camera = camera;
	}

	/**
	 * @return the levelNum
	 */
	public static int getLevelNum() {
		return levelNum;
	}

	/**
	 * @return the currentPoint
	 */
	public static int getCurrentPoint() {
		return currentPoint;
	}

	/**
	 * @param currentPoint the currentPoint to set
	 */
	public static void setCurrentPoint(int currentPoint) {
		WorldManager.currentPoint = currentPoint;
	}

	public static ArrayList<SunCrystal> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<SunCrystal> points) {
		WorldManager.points = points;
	}

	/**
	 * @return the cyrstalsCollected
	 */
	public static int getCyrstalsCollected() {
		return cyrstalsCollected;
	}

	/**
	 * @param cyrstalsCollected the cyrstalsCollected to set
	 */
	public static void setCyrstalsCollected(int cyrstalsCollected) {
		WorldManager.cyrstalsCollected = cyrstalsCollected;
	}
	
	public static void resetWorldCrystals(){
		setCyrstalsCollected(0);
		setCurrentPoint(0);
	}

	public static int getNumEntities() {
		return numEntities;
	}

	public static void setNumEntities(int numEntities) {
		WorldManager.numEntities = numEntities;
	}
}
