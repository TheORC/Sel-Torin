package com.Gamesareme.TCO.libs;

import java.awt.Image;
import java.awt.image.BufferedImage;

import com.Gamesareme.TCO.utils.SpriteSheet;

public class Images {
	public static Image LORE;
	public static Image WALKER_LORE;
	public static Image SPRINTER_LORE;
	public static Image JUMPER_LORE;
	public static Image BACKGROUND;
	public static Image WALLPAPER;
	public static Image BOOK;
	
	private static SpriteSheet sheet = new SpriteSheet("Walker.png");
	public static BufferedImage walker = sheet.getSprite(1, 1, 32);
	private static SpriteSheet sheet1 = new SpriteSheet("Sprinter.png");
	public static BufferedImage sprinter = sheet1.getSprite(1, 1, 32);
	private static SpriteSheet sheet2 = new SpriteSheet("Jumper.png"); 
	public static BufferedImage jumper = sheet2.getSprite(1, 1, 32);
	
}
