package com.Gamesareme.TCO.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.Gamesareme.TCO.gfx.Animation;
import com.Gamesareme.TCO.gfx.Sprite;
import com.Gamesareme.TCO.utils.SpriteSheet;
import com.Gamesareme.TCO.world.World;

public class Water extends Entity{
	
	private Animation animation;
	private final static SpriteSheet sheet = new SpriteSheet("Blocks2.png");


	public Water(int x2, int y2, World world) {
		super(x2, y2, world);
		Sprite[] sprites = new Sprite[]{
				//x, y
				new Sprite(11, 13, 32, sheet),
				new Sprite(12, 13, 32, sheet),
				new Sprite(13, 13, 32, sheet),
				new Sprite(12, 14, 32, sheet),
				new Sprite(13, 14, 32, sheet),
		};
		animation = new Animation(5, sprites);
	}
	
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
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
		return new Rectangle(x, y, 32, 32);
	}

}
