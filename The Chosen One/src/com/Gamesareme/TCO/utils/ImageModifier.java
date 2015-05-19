/**
 * 
 */
package com.Gamesareme.TCO.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * @author Oliver
 *
 */
public class ImageModifier {
	
	@Deprecated
	public static BufferedImage resizeImage(BufferedImage originalImage, int type, int x, int y, int initWidth, int initHeight, double scale){
		initWidth *= scale;
		initHeight *= scale;
		BufferedImage resizedImage = new BufferedImage(initWidth, initHeight, type);
		Graphics2D g2d = resizedImage.createGraphics();
		g2d.drawImage(originalImage, x, y, initWidth, initHeight, null);
		g2d.dispose();
		return resizedImage;
	}

}
