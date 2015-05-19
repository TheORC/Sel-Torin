package com.Gamesareme.TCO.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.Gamesareme.TCO.Game;
import com.Gamesareme.TCO.entity.Player;
import com.Gamesareme.TCO.gfx.Sprite;
import com.Gamesareme.TCO.main.PlayerLives;
import com.Gamesareme.TCO.main.WorldManager;
import com.Gamesareme.TCO.utils.SpriteSheet;

public class Hud {
	
	private static SpriteSheet sheet = new SpriteSheet("Hud.png");
	private static int nextHeart = 30;
	
	private static Sprite heart[];
	private static Sprite Crystals[];
	
	public Hud(){
		heart = new Sprite[]{
			new Sprite(1, 1, 32, sheet),
			new Sprite(1, 1, 32, sheet),
			new Sprite( 1, 1, 32, sheet)
		};
		Crystals = new Sprite[]{
				new Sprite(2, 1, 64, sheet),
				new Sprite(3, 1, 64, sheet),
				new Sprite(4, 1, 64, sheet),
				new Sprite(2, 2, 64, sheet)
		};
	}
	
	public void render(Graphics g, Player p){
		if(p.getHacks()){
			g.setColor(Color.RED);
			g.drawString("Hax On!", 10, 200);
		}
		g.setFont(new Font("French Script MT", Font.BOLD, 24));

		g.setColor(Color.YELLOW);
		int score = p.getScore();
		int health = p.getHealth();
		int xp = p.getXp();
		g.drawString("Score: " + score, 10, 30);
		g.drawString("Xp: " + xp, 200, 30);
		int distance = 0;
		for(int x = 0; x < health; x++){
			distance += nextHeart;
			heart[x].render(g, 1 + distance, 400);
		}
		g.drawString("Lives x" + PlayerLives.currentLifes, 1 + 30, 380);
		
		if(WorldManager.getLevelNum() != 4){
			int cry = WorldManager.getCyrstalsCollected();
			if(cry == 0){
				Crystals[3].render(g, Game.WIDTH-100, 10);
			}else if(cry == 1){
				Crystals[2].render(g, Game.WIDTH-100, 10);
			}else if (cry == 2){
				Crystals[1].render(g, Game.WIDTH-100, 10);
			}else if (cry == 3){
				Crystals[0].render(g, Game.WIDTH-100, 10);
			}
		}
	}

}
