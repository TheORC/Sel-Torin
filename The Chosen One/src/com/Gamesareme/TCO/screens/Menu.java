/**
 * 
 */
package com.Gamesareme.TCO.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.Gamesareme.TCO.Game;
import com.Gamesareme.TCO.libs.Images;
import com.Gamesareme.TCO.libs.Reference;
import com.Gamesareme.TCO.utils.Button;

/**
 * @author Oliver
 *
 */
public class Menu {
	
	public Button play, quit;
	
	public Menu(){
		int fillerY = 150;
		play = new Button(Reference.CENTER_X - 100, fillerY, 200, 50).setText("Play").setYOff(38);
		quit = new Button(Reference.CENTER_X - 100, fillerY += 60, 200, 50).setText("Quit").setYOff(38);
	}

	
	public void renderer(Graphics g){
		g.drawImage(Images.WALLPAPER, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		g.setColor(new Color(255, 120, 0));
		g.setFont(new Font("French Script MT", Font.BOLD, 70));
		g.drawString(Game.TITLE, Game.WIDTH/2-130, 100);
		g.setFont(new Font("French Script MT", Font.BOLD, 40));
		
		play.drawButton(g, 55);
		quit.drawButton(g, 65);

	}

}
