package com.Gamesareme.TCO.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.Gamesareme.TCO.Game;
import com.Gamesareme.TCO.libs.Images;
import com.Gamesareme.TCO.libs.Reference;
import com.Gamesareme.TCO.utils.Button;
import com.Gamesareme.TCO.utils.LevelLock;

public class Levels {
	
	public Button level1, level2, level3, level4;
	
	public Levels(){
		update();
	}
	
	public void update(){
		int fillerY = 150;
		level1 = new Button(Reference.CENTER_X - 100, fillerY, 200, 50).setText("Level 1").setYOff(38);
		
		if(LevelLock.LEVEL_2 == false)
			level2 = new Button(Reference.CENTER_X - 100, fillerY += 60, 200, 50).setText("Level 2   \nLocked").setYOff(38);
		else level2 = new Button(Reference.CENTER_X - 100, fillerY += 60, 200, 50).setText("Level 2").setYOff(38);
		if(LevelLock.LEVEL_3 == false)
			level3 = new Button(Reference.CENTER_X - 100, fillerY += 60, 200, 50).setText("Level 3   \nLocked").setYOff(38);
		else level3 = new Button(Reference.CENTER_X - 100, fillerY += 60, 200, 50).setText("Level 3").setYOff(38);
		
		level4 = new Button(Reference.CENTER_X - 100, fillerY += 60, 200, 50).setText("Level 4").setYOff(38);
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.drawImage(Images.WALLPAPER, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		g.setColor(new Color(255, 120, 0));
		g.setFont(new Font("French Script MT", Font.BOLD, 70));
		g.drawString(Game.TITLE, Game.WIDTH/2-130, 100);
		g.setFont(new Font("French Script MT", Font.BOLD, 40));
		
		level1.drawButton(g, 25);
		level2.drawButton(g, 25);
		level3.drawButton(g, 25);
		if(LevelLock.LEVEL_4)
			level4.drawButton(g, 25);
		update();
	}

}
