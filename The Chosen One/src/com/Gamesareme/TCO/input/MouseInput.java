/**
 * 
 */
package com.Gamesareme.TCO.input;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.Gamesareme.TCO.Game;
import com.Gamesareme.TCO.enums.BookState;
import com.Gamesareme.TCO.enums.GameState;
import com.Gamesareme.TCO.enums.MobType;
import com.Gamesareme.TCO.libs.Reference;
import com.Gamesareme.TCO.main.WorldManager;
import com.Gamesareme.TCO.screens.Levels;
import com.Gamesareme.TCO.screens.Menu;
import com.Gamesareme.TCO.utils.AudioPlayer;
import com.Gamesareme.TCO.utils.BookLock;
import com.Gamesareme.TCO.utils.LevelLock;

/**
 * @author Oliver
 *
 */
public class MouseInput extends MouseAdapter {

    /**
     * true if a mouse button is pressed
     */
    public static boolean pressed = false;

    /**
     * The x and y coordinates of the mouse
     */
    public static int MOUSE_X, MOUSE_Y;

    /**
     * Used to check for intersection in other classes <br> set to a 1x1 at location (1,1) by default, to avoid a NullPointerException
     */
    public static Rectangle MOUSE = new Rectangle(1, 1, 1, 1);

    @Override
    /**
     * This method is called whenever a mouse button is clicked
     */
    public void mouseClicked(MouseEvent e) {
        int mouse = e.getButton(); //used to check what button was clicked
        Rectangle rect = new Rectangle(e.getX(), e.getY(), 1, 1); //creates a 1x1 rectangle around the cursor to be used to check where it was clicked

        if (mouse == MouseEvent.BUTTON1) { //only do this stuff if the left mouse button was clicked
        	
        	if(Game.book.render){
        		if(rect.intersects(Game.book.Story)){
        			Game.book.setBookState(BookState.STORY);
        		}else if(rect.intersects(Game.book.Jumper)){
        			if(BookLock.JUMPER){
        				Game.book.setBookState(BookState.ENEMY);
        				Game.book.setMobType(MobType.JUMPER);
        			}
        		}else if(rect.intersects(Game.book.Sprinter)){
        			if(BookLock.SPRINTER){
        				Game.book.setBookState(BookState.ENEMY);
        				Game.book.setMobType(MobType.SPRINTER);
        			}

        		}else if(rect.intersects(Game.book.Walker)){
        			if(BookLock.WALKER){
        				Game.book.setBookState(BookState.ENEMY);
        				Game.book.setMobType(MobType.WALKER);
        			}

        		}
        	}

            switch (Game.state) { //depending on what state the game is in, check for the following cases
                case GAME:
                    break;
                case MENU:
                    if (rect.intersects(Game.getInstance().getMenu().play)) { //Example, if we click our menu's play button, change the state to GAME
                        AudioPlayer.playSound(Reference.SOUND_CLICK_PLAY_GAME); //make sure you play your sound before changing the game's state
                        Game.state = GameState.LEVELS;
                    }
                    break;
                case PAUSE:
                    break;
                case LEVELS:
                	LevelLock.loadLevelLocker();
                	 if (rect.intersects(Game.getInstance().getLevels().level1)) { //Example, if we click our menu's play button, change the state to GAME
                         AudioPlayer.playSound(Reference.SOUND_CLICK_PLAY_GAME); //make sure you play your sound before changing the game's state
                         WorldManager.resetWorldCrystals();
                         WorldManager.loadWorld("Level1", 1);
                         if(AudioPlayer.getMusic(Reference.MUSIC_MENU).playing()){
                        	 AudioPlayer.getMusic(Reference.MUSIC_MENU).stop();
                         }
                     } else if (rect.intersects(Game.getInstance().getLevels().level2)) {
                         WorldManager.resetWorldCrystals();
                         AudioPlayer.playSound(Reference.SOUND_CLICK_PLAY_GAME);
                         if(LevelLock.LEVEL_2 != false){
                        	 WorldManager.loadWorld("Level2", 2);
                         }
                     }else if(rect.intersects(Game.getInstance().getLevels().level3)){
                         WorldManager.resetWorldCrystals();
                    	 AudioPlayer.playSound(Reference.SOUND_CLICK_PLAY_GAME);
                         if(LevelLock.LEVEL_3 != false){
                        	 WorldManager.loadWorld("Level3", 3);
                         }
                     }else if(rect.intersects(Game.getInstance().getLevels().level4)){
                    	 WorldManager.resetWorldCrystals();
                    	 AudioPlayer.playSound(Reference.SOUND_CLICK_PLAY_GAME);
                         if(LevelLock.LEVEL_4 != false){
                        	 WorldManager.loadWorld("Level4", 4);
                         }
                     }
                	break;
                case LOSE:
                	if(rect.intersects(Game.getInstance().getLoseScreen().menu)){
                		Game.state = GameState.MENU;
                        WorldManager.resetWorldCrystals();
                		if(AudioPlayer.getMusic(Reference.MUSIC_BACKGROUND).playing())AudioPlayer.getMusic(Reference.MUSIC_BACKGROUND).stop();AudioPlayer.playMusic(Reference.MUSIC_MENU);
                	}else if(rect.intersects(Game.getInstance().getLoseScreen().repeat)){
                		WorldManager.loadWorld(WorldManager.getWorld().getName(), WorldManager.getLevelNum());
                	}
                	break;
                case WIN:
                	if(rect.intersects(Game.getInstance().getWinScreen().menu)){
                		Game.state = GameState.MENU;
                        WorldManager.resetWorldCrystals();
                		if(AudioPlayer.getMusic(Reference.MUSIC_BACKGROUND).playing())AudioPlayer.getMusic(Reference.MUSIC_BACKGROUND).stop();AudioPlayer.playMusic(Reference.MUSIC_MENU);
                	}else if(rect.intersects(Game.getInstance().getWinScreen().repeat)){
                        WorldManager.resetWorldCrystals();
                		WorldManager.loadWorld(WorldManager.getWorld().getName(), WorldManager.getLevelNum());
                	}
                	break;
                case GAME_OVER:
                	if(rect.intersects(Game.gameOverScreen.menu)){
                		Game.state = GameState.MENU;
                		Game.gameOverScreen.setCur(0);
                		Game.gameOverScreen.setFinished(false);
                		Game.gameOverScreen.setHealth(0);
                        WorldManager.resetWorldCrystals();
                		if(AudioPlayer.getMusic(Reference.MUSIC_BACKGROUND).playing())AudioPlayer.getMusic(Reference.MUSIC_BACKGROUND).stop();AudioPlayer.playMusic(Reference.MUSIC_MENU);
                	}
                default:
                    break;

            }
        }
    }

    @Override
    /**
     * This is called while we have a mouse button held down
     */
    public void mousePressed(MouseEvent e) {
        pressed = true;
        MOUSE = new Rectangle(e.getX(), e.getY(), 1, 1);
        if(Game.state == GameState.MENU){
            if(MOUSE.intersects(Game.getInstance().getMenu().quit)){
                AudioPlayer.playSound(Reference.SOUND_CLICK_PLAY_GAME);
            }
        }
    }

    @Override
    /**
     * This is called whenever we release the mouse button
     */
    public void mouseReleased(MouseEvent e) {
        pressed = false;
        MOUSE = new Rectangle(e.getX(), e.getY(), 1, 1);
        if(Game.state == GameState.MENU){
            if(MOUSE.intersects(Game.getInstance().getMenu().quit)){
                Game.exit();
            }
        }

    }

    @Override
    /**
     * This is called whenever the mouse is moved
     */
    public void mouseMoved(MouseEvent e) {
        MOUSE_X = e.getX();
        MOUSE_Y = e.getY();
        
        Levels level = Game.getInstance().getLevels();
        Menu menu = Game.getInstance().getMenu();

        MOUSE = new Rectangle(MOUSE_X, MOUSE_Y, 1, 1);

        switch (Game.state) {
            case GAME:
                break;
            case MENU:
                if ((MOUSE.intersects(menu.play)           //Only do this if the mouse is hovering over a button and the sound has not already played
                        || MOUSE.intersects(menu.quit))
                        && !AudioPlayer.hasPlayedHover) {
                    
                    AudioPlayer.playSound(Reference.SOUND_CLICK);
                    AudioPlayer.hasPlayedHover = true;  //The sound has played, so lets set it to true
                    
                }else if(!(MOUSE.intersects(menu.play) || MOUSE.intersects(menu.quit)) && AudioPlayer.hasPlayedHover){//If the mouse is not hovering over a button, then reset the boolean to false
                    AudioPlayer.hasPlayedHover = false;
                }
                break;
            case PAUSE:
                break;
            case LEVELS:
            	if ((MOUSE.intersects(level.level1) || MOUSE.intersects(level.level2) || MOUSE.intersects(level.level3) || MOUSE.intersects(level.level4)) && !AudioPlayer.hasPlayedHover) {//Only do this if the mouse is hovering over a button and the sound has not already played
                    AudioPlayer.playSound(Reference.SOUND_CLICK);
                    AudioPlayer.hasPlayedHover = true;  //The sound has played, so lets set it to true
                }else if(!(MOUSE.intersects(level.level1) || MOUSE.intersects(level.level2) || MOUSE.intersects(level.level3) || MOUSE.intersects(level.level4)) && AudioPlayer.hasPlayedHover) {//If the mouse is not hovering over a button, then reset the boolean to false
                    AudioPlayer.hasPlayedHover = false;
                }
            	break;
            default:
                break;

        }

    }
	
}
