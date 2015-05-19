/**
 * 
 */
package com.Gamesareme.TCO.utils.files;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.Gamesareme.TCO.libs.Reference;

/**
 * @author Oliver
 *
 */
public class BufferedImageLoader {

	private static BufferedImage image;
	
	public static BufferedImage loadImage(String imagePath) throws IOException{
		image = ImageIO.read(new File(Reference.RESCOURCE_LOCAION + imagePath));
		return image;
	}

}
