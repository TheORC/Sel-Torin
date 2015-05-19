package com.Gamesareme.TCO.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.Gamesareme.TCO.Game;
import com.Gamesareme.TCO.entity.Player;
import com.Gamesareme.TCO.entity.SunCrystal;
import com.Gamesareme.TCO.enums.GameState;
import com.Gamesareme.TCO.libs.Images;
import com.Gamesareme.TCO.libs.Reference;
import com.Gamesareme.TCO.main.Camera;
import com.Gamesareme.TCO.main.WorldManager;
import com.Gamesareme.TCO.utils.AudioPlayer;
import com.Gamesareme.TCO.world.World;

public class LevelLoadScreen {
	
	private int time = 50;
	private int counter = 0;
	
    private static int width = 540;
    private static int numResources = 5;
    private static int loadAdd = width / numResources;
    private static int loadStatus = 0;
    
    private static String msg = "Loading resources...";
    
    private String levelName;
    private int levelNumber;
	    
	public LevelLoadScreen(String levelName, int levelNumber){
		this.levelName = levelName;
		this.levelNumber = levelNumber;
		Game.state = GameState.LOADING_WOLD;
		Game.getInstance().setLeavelLoadScreen(this);
	}
	
    public void tick() {
    	time--;
    	if(time <= 0){
    		load();
    		time = 50;
    	}	
    }
    
    void load(){
    	 switch(counter){
         case 0:
        	 LevelLoadScreen.msg = "Loading world...";
        	 WorldManager.setWorld(new World(levelName + ".png", levelNumber));
        	 WorldManager.getWorld().setName(levelName);
        	 if(WorldManager.getWorld() == null){
        		 System.out.println("No World");
        		 Game.state = GameState.PAUSE;
        		 return;
        	 }
             counter++;
             loadMore();
             return;
         case 1:
        	 LevelLoadScreen.msg = "Loading player...";
        	 if(WorldManager.getCurrentPoint() == 0){
        		 System.out.println("No Checkpoint");
        		 WorldManager.setPlayer(new Player(160, 100, WorldManager.getWorld()));
        	 }else{
    			 System.out.println("Checkpoint passed");
    			 for(int i = 0; i < WorldManager.getPoints().size(); i++){
    				 SunCrystal c = WorldManager.getPoints().get(i);
    				 if(c.getCheckpoint_Number() == WorldManager.getCurrentPoint()){
    					 c.setHealth(0);
    					 WorldManager.setPlayer(new Player(c.getX(), c.getY(), WorldManager.getWorld()));
    				 }else if(c.getCheckpoint_Number() < WorldManager.getCurrentPoint()){
    					 c.setHealth(0);
    				 }
    			 }

        	 }
        	 counter++;
             loadMore();
             return;
         case 2:
        	 LevelLoadScreen.msg = "Loading hud...";
        	 WorldManager.setHud(new Hud());
        	 counter++;
             loadMore();
        	 return;
         case 3:
        	 LevelLoadScreen.msg = "Loading camera...";
        	 WorldManager.setCamera(new Camera());
        	 counter++;
             loadMore();
        	 return;
         case 4:
        	 Game.state = GameState.GAME;
             loadMore();
             counter = 0;
             loadStatus = 0;
             AudioPlayer.playMusic(Reference.MUSIC_BACKGROUND);
        	 return;
		 }
    }
	
    public void render(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        g.drawImage(Images.WALLPAPER, 0, 0, Game.WIDTH, Game.HEIGHT, null);
        g.setColor(Color.RED);
        g.drawRect(49, 399, width, 51); //draws the outline of the bar
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString(msg, 51, 395);
        g.setColor(Color.BLUE);
        g.fillRect(50, 400, loadStatus, 50); //fills in the bar as resources are loaded
    }
    
    /**
     * Fills in the blue loading bar
     */
    public static void loadMore(){
        loadStatus += loadAdd;
    }
    
    /**
     * Sets the message to be displayed above the loading bar
     * @param msg the message to be displayed
     */
    public static void setMessage(String msg){
        LevelLoadScreen.msg = msg;
    }

}
