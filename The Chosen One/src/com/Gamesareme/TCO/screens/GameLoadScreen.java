package com.Gamesareme.TCO.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.Gamesareme.TCO.Game;
import com.Gamesareme.TCO.enums.GameState;
import com.Gamesareme.TCO.input.KeyInput;
import com.Gamesareme.TCO.input.MouseInput;
import com.Gamesareme.TCO.libs.Images;
import com.Gamesareme.TCO.libs.Reference;
import com.Gamesareme.TCO.main.PlayerLives;
import com.Gamesareme.TCO.utils.AudioPlayer;
import com.Gamesareme.TCO.utils.BookLock;
import com.Gamesareme.TCO.utils.LevelLock;
import com.Gamesareme.TCO.utils.ResourceLoader;

public class GameLoadScreen{
	
	private int time = 50;
	private int counter = 0;
	
    private static int width = 540;
    private static int numResources = 5;
    private static int loadAdd = width / numResources;
    private static int loadStatus = 0;
    
    private static String msg = "Loading resources...";
    
    private Game game;
    
    public GameLoadScreen(Game game){
    	this.game = game;
    }
    
    public void tick(){
    	time--;
    	if(time <= 0){
    		load();
    		time = 50;
    	}	
    }
    
    void load(){
    	 switch(counter){
         case 0:
        	 setMessage("Loading images...");
        	 ResourceLoader.loadImages();
        	 counter++;
        	 loadMore();
        	 return;
         case 1:
        	 setMessage("Loading fonts...");
        	 counter++;
        	 loadMore();
        	 return;
         case 2:
        	 setMessage("Loading sounds...");
        	 ResourceLoader.loadSounds();
        	 counter++;
        	 loadMore();
        	 return;
         case 3:
        	 setMessage("Loading GUI...");
        	 Game.menu = new Menu();
        	 Game.levels = new Levels();
        	 Game.winScreen = new WinScreen();
        	 Game.loseScreen = new LoseScreen();
        	 Game.gameOverScreen = new GameOverScreen();
        	 Game.notifications = new Notifications();
        	 counter++;
        	 loadMore();
        	 return;
         case 4:
        	 setMessage("Loading other...");
        	 MouseInput mouse = new MouseInput();
        	 game.addMouseListener(mouse);
        	 game.addMouseMotionListener(mouse);
        	 game.addKeyListener(new KeyInput());
        	 
        	 LevelLock.loadLevelLocker();
        	 PlayerLives.loadLives();
        	 BookLock.loadBookLocker();
     		 counter++;
     		 loadMore();
     		 Game.state = GameState.MENU;
     		 AudioPlayer.playMusic(Reference.MUSIC_MENU);
     		 return;
		 }
    }
	
    public void render(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        if(Images.WALLPAPER != null)
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
    	GameLoadScreen.msg = msg;
    }
}
