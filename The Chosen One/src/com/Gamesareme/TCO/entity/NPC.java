package com.Gamesareme.TCO.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.Gamesareme.TCO.entity.mobs.MobNPC;
import com.Gamesareme.TCO.gfx.Sprite;
import com.Gamesareme.TCO.libs.BlockType;
import com.Gamesareme.TCO.main.WorldManager;
import com.Gamesareme.TCO.utils.Distance;
import com.Gamesareme.TCO.utils.SpriteSheet;
import com.Gamesareme.TCO.world.Block;
import com.Gamesareme.TCO.world.World;

public class NPC extends MobNPC{
	
	private static SpriteSheet sheet = new SpriteSheet("Char.png");

	
	private ArrayList<String> lines = new ArrayList<String>();
	
	private boolean render = false;
	
	private int number = 0;

	public NPC(int x, int y, World world, ArrayList<String> lines2) {
		super(x, y, world);
		this.lines = lines2;
		sprite = new Sprite(1, 3, 32, sheet);
		sprite2 = new Sprite(1, 2, 32, sheet);
		System.out.println("NPC added");
	}
	
	@Override
	public void tick() {
    	int distance = Distance.distanceFrom(WorldManager.getPlayer(), this);
    	int distanceFromPlayer = (distance < 0 ? -distance : distance);
    	
    	if(distanceFromPlayer < (32 * 4)){
    		render = true;
    	}else{
    		render = false;
    	}
    	
		super.tick();
	}
	
	@Override
	public void render(Graphics g) {
		if(render){
			int yof = getY();
			g.setColor(Color.WHITE);
			if(lines.size() == 3)
				g.fillRect(getX()-20, getY() - 75, 100, 60);
			if(lines.size() == 2)
				g.fillRect(getX()-20, getY() - 55, 100, 40);
			if(lines.size() == 1)
				g.fillRect(getX()-20, getY() - 35, 100, 20);
			for(int i = lines.size()-1; i>-1; i--){
				g.setColor(Color.BLACK);
				g.drawString(lines.get(i), getX() - 15, yof-=20);
			}
		}
		super.render(g);
	}
	
	@Override
	protected boolean hasVerticalCollision() { // because our collision methods
												// are now separated, we have
												// pixel perfect collision
		for (int i = 0; i < world.getBlocks().size(); i++) {
			Block block = world.getBlocks().get(i);
			int id = block.getId();
			// if velY > 0 that means we are trying to jump, so it should let us
			if (getBottom().intersects(block.getTop()) && velY > 0) {
				if(id == BlockType.VOID){
					health = 0;
				}
				if(!BlockType.walkThroughAbleEntity.contains(id)){
					this.y = block.getY()-32;
					return true;
				}else{
					return false;
				}
			}
			// if we are jumping and hit the ceiling
			if (getTop().intersects(block.getBottom()) && velY < 0) {
				if(!BlockType.walkThroughAbleEntity.contains(id)){
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
