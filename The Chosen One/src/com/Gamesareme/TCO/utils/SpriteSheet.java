/**
 * 
 */
package com.Gamesareme.TCO.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.Gamesareme.TCO.utils.files.BufferedImageLoader;

/**
 * @author Oliver
 *
 */
public class SpriteSheet {

	private BufferedImage image;
	private String path;
	
	public SpriteSheet(String path){
		this.path = path;
		load();
	}
	
    private void load(){
    	try {
			image = BufferedImageLoader.loadImage("sprites/" + path);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       /* File file = null;
        try {
            file = new File(Reference.SPRITE_LOCATION + path);
            image = ImageIO.read(file);
        } catch (IOException e) {
            System.err.println("Make sure file < " + path + " > is in " + file.getAbsolutePath());
            e.printStackTrace();
        }*/
    }
	
	public BufferedImage getSprite(int x, int y, int size){
		return image.getSubimage((x * size) - size, (y * size) - size, size, size);
	}
}
