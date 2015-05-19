/**
 * This is my games main class.      
 * This is where I load every thing that I need.
 */
package com.Gamesareme.TCO;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import org.lwjgl.openal.AL;

import com.Gamesareme.TCO.Particls.RainEffect;
import com.Gamesareme.TCO.enums.GameState;
import com.Gamesareme.TCO.libs.BlockType;
import com.Gamesareme.TCO.libs.Images;
import com.Gamesareme.TCO.main.Window;
import com.Gamesareme.TCO.main.WorldManager;
import com.Gamesareme.TCO.screens.Book;
import com.Gamesareme.TCO.screens.GameLoadScreen;
import com.Gamesareme.TCO.screens.GameOverScreen;
import com.Gamesareme.TCO.screens.LevelLoadScreen;
import com.Gamesareme.TCO.screens.Levels;
import com.Gamesareme.TCO.screens.LoseScreen;
import com.Gamesareme.TCO.screens.Menu;
import com.Gamesareme.TCO.screens.Notifications;
import com.Gamesareme.TCO.screens.WinScreen;

/**
 * @author Oliver
 * 
 */
public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -590962872869808290L; // My games serial // version.

	public static final int WIDTH = 640; // 640
	public static final int HEIGHT = WIDTH / 4 * 3;
	public static final String TITLE = "Sel Torin";
	
	public static GameState state = GameState.LOADING;

	private boolean running = false;
	private Thread thread;
	public static Menu menu;
	public static Levels levels;
	public static WinScreen winScreen;
	public static LevelLoadScreen leavelLoadScreen;
	public static LoseScreen loseScreen;
	public static GameOverScreen gameOverScreen;
	public static Notifications notifications;
	
	public static Book book = new Book();
	public static Game game = new Game();  //The instance of our game that we will be using
	
	public GameLoadScreen loadScreen = new GameLoadScreen(this);
	public BlockType bt = new BlockType();
	
	public RainEffect rain;
	
	//This is the first thing that will run in this game.
	//This is used to set up the screen, and the variabls.
	public static void main(String args[]) {	
		//Create a game class.
		Game game = new Game();
		//create a window and set it to the game class.
		Window.createWindow(game, TITLE);
		//Start the game.
		game.start();
	}
	
    /**
     * Used to access the Game class <i>non-static members</i>
     * @return the instance of the game
     */
    public static Game getInstance(){
        return game;
    }
    
    public Menu getMenu(){
        return menu;
    }

	public void tick() {
		if (state == GameState.LOADING){
			loadScreen.tick();
		}else if(state == GameState.LOADING_WOLD){
			if(leavelLoadScreen != null){
				leavelLoadScreen.tick();
			}
		}else if(state == GameState.GAME){
			 if(WorldManager.getWorld() != null){
				 WorldManager.getWorld().tick(); 
				 WorldManager.getCamera().tick(WorldManager.getWorld().getPlayer());
			 }else{
				 System.out.println("No World");
			 }
		}else if(state == GameState.GAME_OVER){
			gameOverScreen.tick();
		}
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3); // Creates the buffer strategy if there // isn't already one
			return;
		}

		Graphics g = bs.getDrawGraphics(); // We want to use the graphics that
											// comes from our buffer strategy
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(new Color(6, 0, 40));
		g.fillRect(0, 0, WIDTH, HEIGHT); // We are creating a background for our
											// game here

		if(state == GameState.WIN){
			winScreen.render(g);
		}else if(state == GameState.LOSE){
			loseScreen.render(g);
		}else if(state == GameState.LOADING_WOLD){
			if(leavelLoadScreen !=null){
				leavelLoadScreen.render(g);
			}
		}else if(state == GameState.LEVELS){
			levels.render(g);
		}else if (state == GameState.MENU) {
			menu.renderer(g);
		}else if (state == GameState.LOADING) {
			loadScreen.render(g);
		}else if(state == GameState.GAME_OVER){
			gameOverScreen.render(g);
		}else if (WorldManager.getWorld() != null) {
			g.drawImage(Images.BACKGROUND, 0, 0,Game.WIDTH, Game.HEIGHT, null);
			g2d.translate(WorldManager.getCamera().getX(), WorldManager.getCamera().getY()); // do this before the // foreground and // after the // background
			WorldManager.getWorld().render(g);
			g2d.translate(-WorldManager.getCamera().getX(), -WorldManager.getCamera().getY()); // do this after the // foreground
			WorldManager.getHud().render(g, WorldManager.getWorld().getPlayer());
			if(notifications.showN){
				notifications.render(g);
			}
			book.render(g);
		}

		// /////////////////////////////////////////////////

		g.dispose(); // Disposes our graphics context (if we did not do this, // animations would not work properly, it would also eat // up a lot of memory
		bs.show(); // Shows whatever graphics were just disposed of
	}

	@Override
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		final double numTicks = 60.0;
		double nanoSeconds = 1000000000 / numTicks;
		double delta = 0;
		double frames = 0;
		int ticks = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			long currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / nanoSeconds;
			lastTime = currentTime;
			if (delta >= 1) {
				ticks++;
				delta--;
				tick();
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println(ticks + " Ticks, FPS: " + frames);
				Window.setTile(TITLE + "      FPS: " + frames + "   Ticks: " + ticks);
				ticks = 0;
				frames = 0;
			}
		}
		stop();
	}

	private synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	private synchronized void stop() {
		if (!running)
			return;
		running = false;
		cleanUp();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}

	private static void cleanUp() {
		AL.destroy();
	}

	public static void exit() {
		cleanUp();
		System.exit(0);
	}

	/**
	 * @return the levels
	 */
	public Levels getLevels() {
		return levels;
	}

	/**
	 * @param leavelLoadScreen the leavelLoadScreen to set
	 */
	public void setLeavelLoadScreen(LevelLoadScreen leavelLoadScreen) {
		Game.leavelLoadScreen = leavelLoadScreen;
	}

	/**
	 * @return the loadScreen
	 */
	public GameLoadScreen getLoadScreen() {
		return loadScreen;
	}

	/**
	 * @return the loseScreen
	 */
	public LoseScreen getLoseScreen() {
		return loseScreen;
	}

	/**
	 * @return the winScreen
	 */
	public WinScreen getWinScreen() {
		return winScreen;
	}
}
