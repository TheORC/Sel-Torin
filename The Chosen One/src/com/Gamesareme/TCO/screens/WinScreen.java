package com.Gamesareme.TCO.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.Gamesareme.TCO.Game;
import com.Gamesareme.TCO.libs.Images;
import com.Gamesareme.TCO.libs.Reference;
import com.Gamesareme.TCO.main.WorldManager;
import com.Gamesareme.TCO.utils.Button;

public class WinScreen {
	
	public Button menu, repeat;
	
	public WinScreen(){
		int fillerY = 150;
		menu = new Button(Reference.CENTER_X - 100, fillerY, 200, 50).setText("Menu").setYOff(38);
		repeat = new Button(Reference.CENTER_X - 100, fillerY += 60, 200, 50).setText("Repeat").setYOff(38);
	}
	
	public void render(Graphics g){
		g.drawImage(Images.WALLPAPER, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		g.setColor(new Color(255, 120, 0));
		g.setFont(new Font("French Script MT", Font.BOLD, 70));
		g.drawString(Game.TITLE, Game.WIDTH/2-130, 100);
		g.setFont(new Font("French Script MT", Font.BOLD, 40));
		g.drawString("You Win!", Game.WIDTH/2 - 110, Game.HEIGHT/2+70);
		if(WorldManager.getLevelNum() == 3){
			g.setFont(new Font("French Script MT", Font.BOLD, 70));
			g.drawString("New Level Unlocked", Game.WIDTH/2 - 210, Game.HEIGHT/2+170);
		}
		//g.drawString("Score: " + WorldManager.getPlayer().getScore(), 100, 150);
		//g.drawString("XP: " + WorldManager.getPlayer().getX(), 100, 200);
		menu.drawButton(g, 55);
		repeat.drawButton(g, 35);
	}

}
