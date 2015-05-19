package com.Gamesareme.TCO.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.Gamesareme.TCO.gfx.Animation;
import com.Gamesareme.TCO.gfx.Sprite;
import com.Gamesareme.TCO.utils.SpriteSheet;
import com.Gamesareme.TCO.world.World;

public class SunCrystal extends Entity{
	
	private int Checkpoint_Number = 0;
	
	private Animation animation;
	private final static SpriteSheet sheet = new SpriteSheet("spritesheet.png");

	public SunCrystal(int x2, int y2, World world) {
		super(x2, y2, world);
		Sprite[] sprites = new Sprite[]{ new Sprite(1, 5, 32, sheet),
										 new Sprite(2, 5, 32, sheet),
										 new Sprite(3, 5, 32, sheet),
										 new Sprite(4, 5, 32, sheet),
										};
		
		animation = new Animation(5, sprites);
	}
	
	@Override
	public void render(Graphics g) {
		animation.drawAnimation(g, x, y);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		animation.runAnimation();
	}

	@Override
	public Rectangle getTop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBottom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getRight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x + 3, y + 3, 26, 26);
	}

	public int getCheckpoint_Number() {
		return Checkpoint_Number;
	}

	public void setCheckpoint_Number(int checkpoint_Number) {
		Checkpoint_Number = checkpoint_Number;
	}

}
