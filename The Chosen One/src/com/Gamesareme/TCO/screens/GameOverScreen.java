package com.Gamesareme.TCO.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.Gamesareme.TCO.Game;
import com.Gamesareme.TCO.libs.Images;
import com.Gamesareme.TCO.libs.Reference;
import com.Gamesareme.TCO.utils.Button;

public class GameOverScreen {
	
	public Button menu;
	
	private float incresSpeed = 0.5f*60;
	private int cur = 0;
	private int health = 0;
	private boolean finished = false;
	
	public GameOverScreen(){
		int fillerY = 150;
		menu = new Button(Reference.CENTER_X - 100, fillerY, 200, 50).setText("Menu").setYOff(38);
	}
	
	public void render(Graphics g){
		g.drawImage(Images.WALLPAPER, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		g.setColor(new Color(255, 120, 0));
		g.setFont(new Font("French Script MT", Font.BOLD, 70));
		g.drawString(Game.TITLE, Game.WIDTH/2-130, 100);
		g.setFont(new Font("French Script MT", Font.BOLD, 40));
		g.drawString("Game Over!", Game.WIDTH/2 - 110, Game.HEIGHT/2+70);
		g.drawString("Lives remaining: " + health, Game.WIDTH/2 - 110, Game.HEIGHT/2+100);
		menu.drawButton(g, 55);
	}
	
	public void tick(){
		if(!finished){
			cur++;
			if(cur >= incresSpeed){
				cur=0;
				health++;
				if(health >= 5){
					finished = true;
				}
			}
		}
	}

	/**
	 * @return the cur
	 */
	public int getCur() {
		return cur;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @return the finished
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * @param cur the cur to set
	 */
	public void setCur(int cur) {
		this.cur = cur;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @param finished the finished to set
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}

}
