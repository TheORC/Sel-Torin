/**
 * 
 */
package com.Gamesareme.TCO.main;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.Gamesareme.TCO.Game;
import com.Gamesareme.TCO.libs.Reference;


/**
 * @author Oliver
 *
 */
public class Window{
	
	private static JFrame frame;
	
	public static void createWindow(Game game, String title){
		frame = new JFrame(title);
		Toolkit toolKit = Toolkit.getDefaultToolkit();
		Image icon = toolKit.getImage(Reference.SPRITE_LOCATION + "rocket.png");
		//Image cursor = toolKit.getImage(Reference.SPRITE_LOCATION + "cursor.gif");
		frame.add(game);
		frame.setIconImage(icon);
		//frame.setCursor(toolKit.createCustomCursor(cursor, new Point(frame.getX(), frame.getY()), "cursor"));
		frame.setSize(Game.WIDTH, Game.HEIGHT);
		frame.addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						Game.exit();
					}
		});
		frame.setFocusable(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
	}
	
	public static void setTile(String title){
		frame.setTitle(title);
	}

}
