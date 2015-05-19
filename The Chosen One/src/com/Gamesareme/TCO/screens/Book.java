package com.Gamesareme.TCO.screens;

import java.awt.Font;
import java.awt.Graphics;

import com.Gamesareme.TCO.Game;
import com.Gamesareme.TCO.enums.BookState;
import com.Gamesareme.TCO.enums.MobType;
import com.Gamesareme.TCO.libs.Images;
import com.Gamesareme.TCO.libs.Reference;
import com.Gamesareme.TCO.utils.BookLock;
import com.Gamesareme.TCO.utils.Button;

public class Book {
	
	private BookState bookState;
	private MobType mobType;
	
	public Button Story, Walker, Sprinter, Jumper;
	
	public boolean render = false;
	
	public Book(){
		this.bookState = BookState.STORY;
		setUpButtons();
	}
	
	public Book(MobType mobType){
		this.bookState = BookState.ENEMY;
		this.mobType = mobType;
		setUpButtons();
	}
	
	public void setUpButtons(){
		int fillerY = 350;
		int CENTER = (Reference.CENTER_X - 50);
		Story = new Button(CENTER - 210, fillerY, 100, 20).setText("Story").setYOff(17);
		Walker = new Button(CENTER - 100, fillerY, 100, 20).setText("Walker").setYOff(17);
		Jumper = new Button(CENTER + 210, fillerY, 100, 20).setText("Jumper").setYOff(17);
		Sprinter = new Button(CENTER + 100, fillerY, 100, 20).setText("Sprinter").setYOff(17);
	}
	
	public void render(Graphics g){
		if(!render) return;
		g.setFont(new Font("French Script MT", Font.BOLD, 20));
		Story.drawButton(g, 25);
		if(BookLock.WALKER)
			Walker.drawButton(g, 15);
		if(BookLock.JUMPER)
			Jumper.drawButton(g, 15);
		if(BookLock.SPRINTER)
			Sprinter.drawButton(g, 10);
		switch(bookState){
		case ENEMY:
			if(mobType == MobType.JUMPER){
				g.drawImage(Images.JUMPER_LORE, Game.WIDTH - Images.BOOK.getWidth(null)-5, Game.HEIGHT - (Images.BOOK.getHeight(null)-50), null);
				g.drawImage(Images.jumper, Game.WIDTH/2 + 150, Game.HEIGHT/2-100, null);
			}else if(mobType == MobType.WALKER){
				g.drawImage(Images.WALKER_LORE, Game.WIDTH - Images.BOOK.getWidth(null)-5, Game.HEIGHT - (Images.BOOK.getHeight(null)-50), null);
				g.drawImage(Images.walker, Game.WIDTH/2 + 150, Game.HEIGHT/2-100, null);
			}else if(mobType == MobType.SPRINTER){
				g.drawImage(Images.SPRINTER_LORE, Game.WIDTH - Images.BOOK.getWidth(null)-5, Game.HEIGHT - (Images.BOOK.getHeight(null)-50), null);
				g.drawImage(Images.sprinter, Game.WIDTH/2 + 150, Game.HEIGHT/2-100, null);
			}
			break;
		case STORY:
			g.drawImage(Images.LORE, Game.WIDTH - Images.BOOK.getWidth(null)-5, Game.HEIGHT - (Images.BOOK.getHeight(null)-50), null);
			break;
		default:
			break;
		
		}
	}

	/**
	 * @return the bookState
	 */
	public BookState getBookState() {
		return bookState;
	}

	/**
	 * @param bookState the bookState to set
	 */
	public void setBookState(BookState bookState) {
		this.bookState = bookState;
	}

	/**
	 * @return the mobType
	 */
	public MobType getMobType() {
		return mobType;
	}

	/**
	 * @param mobType the mobType to set
	 */
	public void setMobType(MobType mobType) {
		this.mobType = mobType;
	}

}
