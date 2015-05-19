/**
 * 
 */
package com.Gamesareme.TCO.libs;

import java.awt.image.BufferedImage;

import com.Gamesareme.TCO.Game;

/**
 * @author Oliver
 *
 */
public class Reference {

	public static final int CENTER_X = Game.WIDTH /2;
	public static final int CENTER_Y = Game.HEIGHT /2;
	
	public static final String RESCOURCE_LOCAION = "./resources/";
	public static final String SPRITE_LOCATION = RESCOURCE_LOCAION + "sprites/";
	public static final String LEVEL_LOCATION = "./resources/sprites/Levels/";
	public static final String FONT_LOCATION = RESCOURCE_LOCAION + "fonts/";
	
	public static final String SOUND_LOCATION = RESCOURCE_LOCAION + "sounds/";
	
	public static final int ALPHA_RGB = BufferedImage.TYPE_INT_RGB;
	
	public static final String VERSION = "@VERSION@";
	
	/**
	 * AUDIO
	 */
	public static final String SOUND_CLICK = "sound_Click";
	public static final String SOUND_CLICK_PLAY_GAME = "sound_Play";
	public static final String SOUND_COIN = "Feeling_Watched";
	public static final String MUSIC_FEELING = "Feeling_Watched";
	public static final String MUSIC_ARMY_SONG = "Army_Song";
	public static final String SOUND_CRYSTAL = "Crystal_Sound";
	public static final String SOUND_JUMP = "Jump";
	public static final String SOUND_HURT = "Hurt";
	public static final String MUSIC_MENU = "Menu";
	public static final String MUSIC_BACKGROUND = "background";
	

}
