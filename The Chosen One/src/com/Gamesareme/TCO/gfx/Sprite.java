/**
 * 
 */
package com.Gamesareme.TCO.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.Gamesareme.TCO.utils.SpriteSheet;

/**
 * @author Oliver
 *
 */
public class Sprite {
	
	@SuppressWarnings("unused")
	private int x;
    @SuppressWarnings("unused")
	private int y;
    @SuppressWarnings("unused")
	private int size;
    @SuppressWarnings("unused")
	private SpriteSheet spritesheet;
    private BufferedImage image;
    
    public Sprite(int x, int y, int size, SpriteSheet spritesheet){
        this.x = x;
        this.y = y;
        this.size = size;
        this.spritesheet = spritesheet;
        image = spritesheet.getSprite(x, y, size);
    }
    
    public void render(Graphics g, int x, int y){
        g.drawImage(image, x, y, null);
    }

}
