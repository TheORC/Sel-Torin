package com.Gamesareme.TCO.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.Gamesareme.TCO.entity.mobs.enemies.MobSprinter;
import com.Gamesareme.TCO.enums.Directions;
import com.Gamesareme.TCO.gfx.Animation;
import com.Gamesareme.TCO.gfx.Sprite;
import com.Gamesareme.TCO.libs.BlockType;
import com.Gamesareme.TCO.utils.SpriteSheet;
import com.Gamesareme.TCO.world.Block;
import com.Gamesareme.TCO.world.World;

public class Sprinter extends MobSprinter{

	private static SpriteSheet sheet = new SpriteSheet("Sprinter.png");
	
	public int health = 1;

	public Sprinter(int x, int y, World world) {
		super(x, y, world);
		setHealth(1);
		Sprite[] rights = new Sprite[] { new Sprite(1, 3, 32, sheet),
				new Sprite(2, 3, 32, sheet), new Sprite(3, 3, 32, sheet),
				new Sprite(4, 3, 32, sheet), };
		Sprite[] lefts = new Sprite[] { new Sprite(1, 2, 32, sheet),
				new Sprite(2, 2, 32, sheet), new Sprite(3, 2, 32, sheet),
				new Sprite(4, 2, 32, sheet), };
		animeLeft = new Animation(5, lefts);
		animeRight = new Animation(5, rights);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		velX = 0;
		if(direction == Directions.RIGHT){
			velX = speed;
		}else if(direction == Directions.LEFT){
			velX = -speed + 1;
		}
		
		super.tick();
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
	protected boolean hasHorizontalCollision() {
		for (int i = 0; i < world.getBlocks().size(); i++) {
			Block block = world.getBlocks().get(i);
			int id = block.getId();
			if (getRight().intersects(block.getRight()) && velX > 0) {
				if(!BlockType.walkThroughAbleEntity.contains(id)){
					if(attacking)return false;
					return true;
				}else{
					return false;
				}
			}
			if (getLeft().intersects(block.getLeft()) && velX < 0) {
				if(!BlockType.walkThroughAbleEntity.contains(id)){	
					if(attacking)return false;
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
		super.render(g);
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
	
	@Override
	public int getXpGain() {
		// TODO Auto-generated method stub
		return super.getXpGain();
	}

	public int getHealth() {
		return health;
	}
}
