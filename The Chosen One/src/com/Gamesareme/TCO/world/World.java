/**
 * 
 */
package com.Gamesareme.TCO.world;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.Gamesareme.TCO.Game;
import com.Gamesareme.TCO.Particls.Particle;
import com.Gamesareme.TCO.Particls.Particle_Rain;
import com.Gamesareme.TCO.entity.Coin;
import com.Gamesareme.TCO.entity.Entity;
import com.Gamesareme.TCO.entity.Jumper;
import com.Gamesareme.TCO.entity.NPC;
import com.Gamesareme.TCO.entity.Player;
import com.Gamesareme.TCO.entity.Sprinter;
import com.Gamesareme.TCO.entity.SunCrystal;
import com.Gamesareme.TCO.entity.Walker;
import com.Gamesareme.TCO.enums.GameState;
import com.Gamesareme.TCO.libs.Reference;
import com.Gamesareme.TCO.main.PlayerLives;
import com.Gamesareme.TCO.main.WorldManager;
import com.Gamesareme.TCO.utils.BookLock;
import com.Gamesareme.TCO.utils.Distance;

/**
 * @author OliverArrayList<E>
 */
public class World {

	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Block> blocks = new ArrayList<Block>();
	private ArrayList<Particle> particles = new ArrayList<Particle>(500);

	private int levelNum;
	private String name;

	private int width;
	private int height;
	private int[] pixels;
	
	private Particle_Rain rain = new Particle_Rain(0, Game.WIDTH, 10, this);
	
	@SuppressWarnings("unused")
	private NPCVoices vo = new NPCVoices();


	// private Particle_Rain rain = new Particle_Rain(Game.WIDTH, 10, this);

	public World(String path, int levelNum) {
		this.levelNum = levelNum;
		BufferedImage image = null;
		try {
			System.out.println(path);
			// image = ImageIO.read(new
			// File("./resources/sprites/Levels/Level2.png"));
			image = ImageIO.read(new File(Reference.LEVEL_LOCATION + path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = image.getWidth();
		height = image.getHeight();
		pixels = new int[width * height];
		image.getRGB(0, 0, width, height, pixels, 0, width);
		int entityCount = 0;
		int checkPoint = 1;
		int npcnum = 0;
		WorldManager.getPoints().clear();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				// pixels[x + y * width] gives us the hex of the pixel at the x,
				// y location on the file
				if (pixels[x + y * width] == 0XFFc9c700) {
					Coin c = new Coin(x * 32, y * 32, this);
					c.setHealth(1);
					entityCount++;
				} else if (pixels[x + y * width] == 0XFF380101) {
					Walker w = new Walker(x * 32, y * 32, this);
					w.setHealth(1);
					entityCount++;
				} else if (pixels[x + y * width] == 0XFF441616) {
					Sprinter s = new Sprinter(x * 32, y * 32, this);
					s.setHealth(2);
				} else if (pixels[x + y * width] == 0XFF2c1010) {
					Jumper j = new Jumper(x * 32, y * 32, 2, this);
					j.setHealth(1);
				} else if (pixels[x + y * width] == 0XFF122f8e) {
					SunCrystal c = new SunCrystal(x * 32, y * 32, this);
					c.setHealth(1);
					c.setCheckpoint_Number(checkPoint);
					checkPoint++;
					WorldManager.getPoints().add(c);
				}else if(pixels[x + y * width] == 0XFFd200ff){
					npcnum++;
					NPC npc = new NPC(x * 32, y * 32, this, NPCVoices.getNpcVoices().get("npc" + npcnum));
					npc.setHealth(1);
					npc.setNumber(npcnum);
				}
				if (Block.getFromID(pixels[x + y * width]) != null) // only add// the block// if there// is a// non-null// block
					blocks.add(new Block(pixels[x + y * width], x, y));
			}
		}
		System.out.println("Number of Entites: " + (entityCount));
	}

	public void tick() {
		if(Game.notifications.showN) Game.notifications.tick();
		if (WorldManager.getPlayer().getHealth() <= 0) {
			PlayerLives.playerDie();
			if (PlayerLives.currentLifes <= 0) {
				System.out.println("Game Over");
				Game.state = GameState.GAME_OVER;
				PlayerLives.resetGame();
				PlayerLives.resetLives();
			} else {
				System.out.println("You Still Have More Lives");
				Game.state = GameState.LOSE;
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
			if (e.getHealth() <= 0) {
				if (!(e instanceof Player)) {
					entities.remove(i);
					System.out.println("Anemy Killed!");
				}
			}
			
			int distance = Distance.distanceFrom(WorldManager.getPlayer(), e);
	    	int distanceFromPlayer = (distance < 0 ? -distance : distance);
	    	
			if(distanceFromPlayer < (32 * 6)){
				if(e instanceof Walker){
					if(!BookLock.WALKER)
						Game.notifications.walkerFound();
				}else if(e instanceof Sprinter){
					if(!BookLock.SPRINTER)
						Game.notifications.sprinterFound();
				}else if(e instanceof Jumper){
					if(!BookLock.JUMPER)
						Game.notifications.jumperFound();
				}
			}
		}

		if(levelNum != 4){
			rain.setXloc((int)WorldManager.getPlayer().getX() - (Game.WIDTH/2) - 100);
			rain.spawnRain();

			for (int i = 0; i <= particles.size() - 1; i++) {
				if (particles.get(i).update())
					particles.remove(i);
			}
		}
	}

	public void render(Graphics g) {
		// g.drawImage(Images.BACKGROUND, 0, 0, null);
		for (int i = 0; i < blocks.size(); i++){
			int distance = Distance.distanceFrom(WorldManager.getPlayer(), blocks.get(i));
	    	int distanceFromPlayer = (distance < 0 ? -distance : distance);
	    	
	    	if(distanceFromPlayer < (32 * 12))
	    		blocks.get(i).render(g);
		}
			
		for (int x = 0; x < entities.size(); x++){
			int distance = Distance.distanceFrom(WorldManager.getPlayer(), entities.get(x));
	    	int distanceFromPlayer = (distance < 0 ? -distance : distance);
	    	
	    	if(distanceFromPlayer < (32 * 12))
	    		entities.get(x).render(g);
		}
			
		Graphics2D g2d = (Graphics2D) g;
		if(levelNum != 4)
			renderParticles(g2d);
	}

	public void renderParticles(Graphics2D g2d) {
		for (int i = 0; i <= particles.size() - 1; i++) {
			particles.get(i).render(g2d);
		}
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public ArrayList<Block> getBlocks() {
		return blocks;
	}

	public void removeEntity(int index) {
		entities.remove(index);
	}

	public Player getPlayer() {
		for (Entity e : entities)
			if (e instanceof Player)
				return (Player) e;
		return null;
	}

	/**
	 * @return the levelName
	 */
	public int getLevelNum() {
		return levelNum;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the particles
	 */
	public ArrayList<Particle> getParticles() {
		return particles;
	}
}
