 /**
 * 
 */
package com.Gamesareme.TCO.utils;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import com.Gamesareme.TCO.libs.Reference;



/**
 * @author Oliver
 *
 */
public class AudioPlayer {

	private static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	
	private static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static boolean hasPlayedHover = false;
	
	public static void addSound(String key, String path){
		try {
			soundMap.put(key, new Sound(Reference.RESCOURCE_LOCAION + path));
			System.out.println("Added sound " + path);
		} catch (SlickException e) {
			System.out.println("Failed to find the sound: " + path);
			e.printStackTrace();
		}
	}
	
	public static void addMusic(String key, String path){
		try {
			musicMap.put(key, new Music(Reference.RESCOURCE_LOCAION + path));
			System.out.println("Added music " + path);
		} catch (SlickException e) {
			System.out.println("Failed to find the music: " + path);

			e.printStackTrace();
		}
	}
	
	public static Sound getSound(String key){
		return soundMap.get(key);
	}
	
	public static Music getMusic(String key){
		return musicMap.get(key);
	}
	
	public static void playSound(String key){
		soundMap.get(key).play();
	}
	
	public static void playMusic(String key){
		musicMap.get(key).loop();
	}

}
