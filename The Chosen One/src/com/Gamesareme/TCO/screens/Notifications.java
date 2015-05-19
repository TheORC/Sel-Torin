package com.Gamesareme.TCO.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.Gamesareme.TCO.Game;
import com.Gamesareme.TCO.enums.MobType;
import com.Gamesareme.TCO.libs.Images;
import com.Gamesareme.TCO.utils.BookLock;
import com.Gamesareme.TCO.utils.files.TextFile;

public class Notifications {
	
	private int time = 60*4;
	private int currentTime = time;
	
	public boolean showN = false;
	
	private MobType type;
	
	public void tick(){
		if(showN){
			if(currentTime <= 0){
				showN = false;
				currentTime = time;
			}else{
				currentTime--;
			}
		}
	}
	
	public void render(Graphics g){
		if(!showN)return;
		g.drawRect(Game.WIDTH/2-200, 40, 400, 36);
		g.setFont(new Font("Arial", Font.ITALIC, 20));
		g.setColor(Color.YELLOW);
		g.drawString("Press 'b' to view book.", Game.WIDTH/2- 200, Game.HEIGHT-100);
		g.setFont(new Font("French Script MT", Font.ITALIC, 30));
		if(type == MobType.WALKER){
			g.drawImage(Images.walker, Game.WIDTH/2+(200-32), 41, null);
			g.drawString("Walker discovered", Game.WIDTH/2-200, 45 + (36/2));
		}else if(type == MobType.JUMPER){
			g.drawImage(Images.jumper, Game.WIDTH/2+(200-32), 41, null);
			g.drawString("Jumper discovered", Game.WIDTH/2-200, 45 + (36/2));

		}else if(type == MobType.SPRINTER){
			g.drawImage(Images.sprinter, Game.WIDTH/2+(200-32), 41, null);
			g.drawString("Sprinter discovered", Game.WIDTH/2-200, 45 + (36/2));
		}
		
	}
	
	public void walkerFound(){
		showN = true;
		currentTime = time;
		type = MobType.WALKER;
		TextFile.writeFile("data/Walker.txt", "true");
		BookLock.loadBookLocker();
	}
	
	public void sprinterFound(){
		showN = true;
		currentTime = time;
		type = MobType.SPRINTER;
		TextFile.writeFile("data/Sprinter.txt", "true");
		BookLock.loadBookLocker();
	}
	
	public void jumperFound(){
		showN = true;
		currentTime = time;
		type = MobType.JUMPER;
		TextFile.writeFile("data/Jumper.txt", "true");
		BookLock.loadBookLocker();
	}

}
