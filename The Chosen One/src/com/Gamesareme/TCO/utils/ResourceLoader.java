/**
 * 
 */
package com.Gamesareme.TCO.utils;

import java.io.IOException;

import com.Gamesareme.TCO.libs.Images;
import com.Gamesareme.TCO.libs.Reference;
import com.Gamesareme.TCO.utils.files.BufferedImageLoader;

/**
 * @author Oliver
 *
 */
public class ResourceLoader {
	
	public static void loadImages(){
		
		try{
			Images.WALLPAPER = BufferedImageLoader.loadImage("sprites/wallpaperMenu.jpg");
			Images.BACKGROUND = BufferedImageLoader.loadImage("sprites/BackGround.png");
			Images.BOOK = BufferedImageLoader.loadImage("sprites/book-pages.png");
			Images.LORE = BufferedImageLoader.loadImage("Lore/Lore.png");
			Images.WALKER_LORE = BufferedImageLoader.loadImage("Lore/Walker-Lore.png");
			Images.SPRINTER_LORE = BufferedImageLoader.loadImage("Lore/Sprinter-Lore.png");
			Images.JUMPER_LORE = BufferedImageLoader.loadImage("Lore/Jumper-Lore.png");
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void loadSounds(){
		System.out.println("loading sounds");
		AudioPlayer.addSound(Reference.SOUND_CLICK, "sounds/CLICK20A.ogg");
		System.out.println("1");
		AudioPlayer.addSound(Reference.SOUND_CLICK_PLAY_GAME, "sounds/Mouse_Click.wav");
		System.out.println("2");
		AudioPlayer.addSound(Reference.SOUND_COIN, "sounds/Coin.ogg");
		System.out.println("3");
		AudioPlayer.addSound(Reference.SOUND_CRYSTAL, "sounds/CrystalSound.wav");
		System.out.println("4");
		AudioPlayer.addSound(Reference.SOUND_JUMP, "sounds/Bounce.wav");
		System.out.println("5");
		AudioPlayer.addSound(Reference.SOUND_HURT, "sounds/Hurt.wav");
		System.out.println("6");
		AudioPlayer.addMusic(Reference.MUSIC_ARMY_SONG, "sounds/Army_Song.wav");
		System.out.println("7");
		AudioPlayer.addMusic(Reference.MUSIC_MENU, "sounds/Menu.wav");
		System.out.println("8");
		AudioPlayer.addMusic(Reference.MUSIC_BACKGROUND, "sounds/The-Last.wav");
		System.out.println("9");

	}
}
