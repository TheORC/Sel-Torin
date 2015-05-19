/**
 * 
 */
package com.Gamesareme.TCO.world;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import com.Gamesareme.TCO.gfx.Sprite;
import com.Gamesareme.TCO.utils.SpriteSheet;

/**
 * @author Oliver
 *
 */
public class Block {

	 private static final SpriteSheet sheet = new SpriteSheet("Blocks2.png");

	    private static Map<Integer, Block> blockMap = new HashMap<Integer, Block>(); //so we can remember the IDs and blocks/sprites associated with it
		private int id;
	    private int x;
	    private int y;
	    private Sprite sprite;

	    public static final Block grass = new Block(0xFFFFFFFF, new Sprite(4, 1, 32, sheet));
	    public static final Block dirt = new Block(0xFFFF0000, new Sprite(3, 1, 32, sheet));
	    public static final Block stone = new Block(0XFFdc0000, new Sprite(6, 7, 32, sheet));
	    public static final Block tree_stump = new Block(0XFF00dc00, new Sprite(5, 6, 32, sheet));
	    public static final Block tree_leaves = new Block(0XFF00c800, new Sprite(9, 1, 32, sheet));
	    public static final Block grass_Stalk = new Block(0XFFdcffff, new Sprite(13, 9, 32, sheet));
	    public static final Block world_void = new Block(0XFFff00f6, new Sprite(11, 15, 32, sheet));
	    public static final Block empty = new Block(0XFF005aff, new Sprite(2, 3, 32, sheet));
	    public static final Block mushroom = new Block(0XFF00ffa8, new Sprite(15, 9, 32, sheet));

	    private Block(int id, Sprite sprite) { //yeah, I know, we need a better system, it will be better once I find a way to make collision be friendly when there is 
	                                           // only 1 object of each type, just being renderered multiple times
	    	this.id = id;
	        this.sprite = sprite;
	        blockMap.put(id, this);
	        System.out.println(id);
	    }

	    public Block(int id, int x, int y) {
	    	this.id = id;
	        this.sprite = getFromID(id);
	        this.x = x * 32;
	        this.y = y * 32;
	    }

	    public void render(Graphics g) {
	        sprite.render(g, x, y);
	    }

	    public Rectangle getBounds() {
	        return new Rectangle(x, y, 32, 32);
	    }

	    public Rectangle getTop() {
	        return new Rectangle(x + 4, y, 24, 4);
	    }

	    public Rectangle getBottom() {
	        return new Rectangle(x + 4, y + 28, 24, 4);
	    }

	    public Rectangle getRight() {
	        return new Rectangle(x, y, 4, 32);
	    }

	    public Rectangle getLeft() {
	        return new Rectangle(x + 28, y, 4, 32);
	    }

	    public static Sprite getFromID(int id) {
	        if (blockMap.get(id) != null) return blockMap.get(id).sprite;
	        return null;
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		/**
		 * @return the x
		 */
		public int getX() {
			return x;
		}

		/**
		 * @return the y
		 */
		public int getY() {
			return y;
		}
}
