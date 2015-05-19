/**
 * 
 */
package com.Gamesareme.TCO.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.Gamesareme.TCO.Game;
import com.Gamesareme.TCO.entity.mobs.Mob;
import com.Gamesareme.TCO.enums.GameState;
import com.Gamesareme.TCO.enums.MobState;
import com.Gamesareme.TCO.gfx.Animation;
import com.Gamesareme.TCO.gfx.Sprite;
import com.Gamesareme.TCO.input.KeyInput;
import com.Gamesareme.TCO.libs.BlockType;
import com.Gamesareme.TCO.libs.Reference;
import com.Gamesareme.TCO.main.PlayerLives;
import com.Gamesareme.TCO.main.WorldManager;
import com.Gamesareme.TCO.utils.AudioPlayer;
import com.Gamesareme.TCO.utils.LevelLock;
import com.Gamesareme.TCO.utils.SpriteSheet;
import com.Gamesareme.TCO.utils.files.TextFile;
import com.Gamesareme.TCO.world.Block;
import com.Gamesareme.TCO.world.World;

/**
 * @author Oliver
 *
 */
public class Player extends Mob {

	private static SpriteSheet sheet = new SpriteSheet("Char.png");

	private int score;
	private int xp;
	private boolean render = true;
	private boolean hacks = false;
	public boolean attacking = false;
	
	private int attackTime = 5;
	
	private int time = 20;
	private int curTime = 0;
	
	public Player(int x, int y, World world) {
		super(x, y, world);
		score = 0;
		xp = 10;
		setHealth(3);
		sprite = new Sprite(1, 3, 32, sheet);
		sprite2 = new Sprite(1, 2, 32, sheet);
		Sprite[] rights = new Sprite[] { 
				new Sprite(1, 3, 32, sheet),
				new Sprite(2, 3, 32, sheet), new Sprite(3, 3, 32, sheet),
				new Sprite(4, 3, 32, sheet), };
		Sprite[] lefts = new Sprite[] {
				new Sprite(1, 2, 32, sheet),
				new Sprite(2, 2, 32, sheet), new Sprite(3, 2, 32, sheet),
				new Sprite(4, 2, 32, sheet), };
		animeLeft = new Animation(5, lefts);
		animeRight = new Animation(5, rights);
		System.out.println("Spawn sword");
		
	}

	@Override
	public void tick() {
		velX = 0;
		if (KeyInput.getKey(KeyEvent.VK_D) || KeyInput.getKey(KeyEvent.VK_RIGHT))
			velX += 3;
		if (KeyInput.getKey(KeyEvent.VK_A) || KeyInput.getKey(KeyEvent.VK_LEFT))
			velX -= 3;
		if ((KeyInput.getKey(KeyEvent.VK_W) || KeyInput.getKey(KeyEvent.VK_UP) || KeyInput.getKey(KeyEvent.VK_SPACE))&& !jumping) {
			jumping = true;
			velY = -15;
			AudioPlayer.playSound(Reference.SOUND_JUMP);
		} 
		if(KeyInput.getKey(KeyEvent.VK_B)){
			if(curTime<=0){
				curTime = time;
				if(Game.book.render){
					Game.book.render = false;
				}else{
					Game.book.render = true;
				}
			}
		}
		if(KeyInput.getKey(KeyEvent.VK_ESCAPE)){
			if(Game.book.render){
				Game.book.render = false;
				curTime = 0;
			}
		}
		if((KeyInput.getKey(KeyEvent.VK_S) || KeyInput.getKey(KeyEvent.VK_DOWN)) && hacks){
			y += 5;
		}

		if(KeyInput.getKey(KeyEvent.VK_J) && KeyInput.getKey(KeyEvent.VK_2)){
			if(hacks) hacks = false;
			else hacks = true;
		}
		entityCollision();
		flash();
		
		if(attacking){
			if(attackTime <= 0){
				attacking = false;
				attackTime = 5;
			}else{
				attackTime--;
			}
		}
		
		if(curTime > 0){
			curTime--;
		}
		
		if(velY < 0 || velY > 0){
			onGround = false;
		}
			
		super.tick();
	}
	
	protected void entityCollision(){
		for(int i = 0; i < world.getEntities().size(); i++){
			Entity entity = world.getEntities().get(i);
			if(entity instanceof Walker){
				Walker walker = (Walker)entity;
				if(this.getBottom().intersects(walker.getTop())){
					walker.setHealth(walker.getHealth()-1);
					velY = -10;
					jumping = true;
					xp += walker.getXpGain();
				}else if(this.getBounds().intersects(walker.getBounds())){
					if(!hurting){
						AudioPlayer.playSound(Reference.SOUND_HURT);
						hurting = true;
						curHurtFlashTime = hurtFlashTime;
						hurtTime = 100;
						health--;
					}
				}
			}else if (entity instanceof Sprinter){
				Sprinter sprinter = (Sprinter)entity;
				if(this.getBottom().intersects(sprinter.getTop())){
					sprinter.health -= 1;
					velY = -10;
					jumping = true;
					xp += sprinter.getXpGain();
				}else if(this.getBounds().intersects(sprinter.getBounds())){
					if(!hurting){
						AudioPlayer.playSound(Reference.SOUND_HURT);
						hurting = true;
						curHurtFlashTime = hurtFlashTime;
						hurtTime = 100;
						health--;
					}
				}
			}else if(entity instanceof Jumper){
				Jumper jumper = (Jumper)entity;
				if(this.getBottom().intersects(jumper.getTop())){
					if(jumper.isJumping()){
						if(!hurting){
							AudioPlayer.playSound(Reference.SOUND_HURT);
							hurting = true;
							curHurtFlashTime = hurtFlashTime;
							hurtTime = 100;
							health--;
						}
					}else{
						jumper.health -= 1;
						velY = -10;
						jumping = true;
						xp += jumper.getXpGain();
					}
				}else if(this.getBounds().intersects(jumper.getBounds())){
					if(!hurting){
						AudioPlayer.playSound(Reference.SOUND_HURT);
						hurting = true;
						curHurtFlashTime = hurtFlashTime;
						hurtTime = 100;
						health--;
					}
				}
			}else if(entity instanceof SunCrystal){
				SunCrystal check = (SunCrystal)entity;
				if(this.getBounds().intersects(check.getBounds())){
					AudioPlayer.playSound(Reference.SOUND_CRYSTAL);
					WorldManager.setCurrentPoint(check.getCheckpoint_Number());
					check.setHealth(0);
					System.out.println(WorldManager.getCyrstalsCollected());
					int cry = WorldManager.getCyrstalsCollected();
					cry+=1;
					System.out.println(cry);

					WorldManager.setCyrstalsCollected(cry);
					System.out.println(WorldManager.getCyrstalsCollected());
					if(WorldManager.getCyrstalsCollected() >= 3){
						PlayerLives.resetGame();
						PlayerLives.resetLives();
						if(WorldManager.getLevelNum() != 4){
							TextFile.writeFile("data/Level" + (WorldManager.getLevelNum() + 1) + ".txt", "true");
							LevelLock.loadLevelLocker();
						}
						Game.state = GameState.WIN;
					}
				}
			}else if(entity instanceof NPC){
				NPC npc = (NPC)entity;
				if(this.getBounds().intersects(npc.getBounds())){
					if(npc.getNumber() == 6){
						PlayerLives.resetGame();
						PlayerLives.resetLives();
						Game.state = GameState.WIN;
					}
				}
			}
			if(entity != null && entity instanceof Coin){
				if(getBounds().intersects(entity.getBounds())){
					entity.setHealth(0);
					AudioPlayer.playSound(Reference.SOUND_COIN);
					score++;
				}
			}
		}
	}

	@Override
	protected boolean hasVerticalCollision() { // because our collision method // are now separated, we have // pixel perfect collision
		for (int i = 0; i < world.getBlocks().size(); i++) {
			Block block = world.getBlocks().get(i);
			int id = block.getId();
			// if velY > 0 that means we are trying to jump, so it should let us
			if (getBottom().intersects(block.getTop()) && velY > 0) {
				if(id == BlockType.VOID){
					health = 0;
				}
				if(!BlockType.walkThroughAblePlayer.contains(id)){					
					jumping = false; // when we are on the ground, we need to// re-allow the player to jump
					onGround = true;
					this.y = block.getY()-32;
					return true;
				}else{
					return false;
				}
			}
			// if we are jumping and hit the ceiling
			if (getTop().intersects(block.getBottom()) && velY < 0) {
				if(!BlockType.walkThroughAblePlayer.contains(id)){
					if(hacks) return false;
					velY = 0;
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}

	@Override
	protected boolean hasHorizontalCollision() {
		for (int i = 0; i < world.getBlocks().size(); i++) {
			Block block = world.getBlocks().get(i);
			int id = block.getId();
			if(hacks) return false;
			if (getRight().intersects(block.getRight()) && velX > 0) {
				if(!BlockType.walkThroughAblePlayer.contains(id)){	
					return true;
				}else{
					return false;
				}
			}
			if (getLeft().intersects(block.getLeft()) && velX < 0) {
				if(!BlockType.walkThroughAblePlayer.contains(id)){	
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}

	@Override
	public void render(Graphics g) {
		if(render) super.render(g);
	}
	
	private void flash(){
		if(hurtTime > 0){
			hurtTime--;
			curHurtFlashTime--;
			if(curHurtFlashTime <= 0){
				if(hurtInvisable){
					hurtInvisable = false;
					render = true;
					curHurtFlashTime = hurtFlashTime;
				}else{
					hurtInvisable = true;
					render = false;
					curHurtFlashTime = hurtFlashTime;
				}
			}
		}else{
			hurting = false;
			render = true;
		}
	}

	@Override
	public Rectangle getTop() {
		return new Rectangle(x, y - 4, 32, 4);
	}

	@Override
	public Rectangle getBottom() {
		return new Rectangle(x, y + 32, 32, 4);
	}

	@Override
	public Rectangle getRight() {
		return new Rectangle(x + 28, y, 4, 28);
	}

	@Override
	public Rectangle getLeft() {
		return new Rectangle(x, y, 4, 28);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x+4, y, 32-8, 32);
	}

	public int getScore() {
		return score;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param render the render to set
	 */
	public void setRender(boolean render) {
		this.render = render;
	}

	/**
	 * @return the xp
	 */
	public int getXp() {
		return xp;
	}

	/**
	 * @return the hacks
	 */
	public boolean getHacks() {
		return hacks;
	}
	
	public MobState getPlayerState(){
		return mobState;
	}
}
