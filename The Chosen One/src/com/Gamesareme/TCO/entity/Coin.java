package com.Gamesareme.TCO.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.Gamesareme.TCO.gfx.Animation;
import com.Gamesareme.TCO.gfx.Sprite;
import com.Gamesareme.TCO.utils.SpriteSheet;
import com.Gamesareme.TCO.world.World;

public class Coin extends Entity{
	
	private Animation animation;
	private final static SpriteSheet sheet = new SpriteSheet("spritesheet.png");

	public Coin(int x2, int y2, World world) {
		super(x2, y2, world);
		Sprite[] sprites = new Sprite[]{
				new Sprite(1, 2, 32, sheet),
				new Sprite(2, 2, 32, sheet),
				new Sprite(3, 2, 32, sheet),
				new Sprite(4, 2, 32, sheet),
				new Sprite(5, 2, 32, sheet),
				new Sprite(6, 2, 32, sheet)
		};
		animation = new Animation(5, sprites);
	}
	
	@Override
	public void render(Graphics g) {
		animation.drawAnimation(g, x, y);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x + 3, y + 3, 26, 26);
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

}
