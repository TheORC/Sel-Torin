/**
 * 
 */
package com.Gamesareme.TCO.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Oliver
 *
 */
public class KeyInput extends KeyAdapter{
	
	private static boolean[] keys = new boolean[300];
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		keys[key] = true;
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyAdapter#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		keys[key] = false;
	}
	
    public static boolean getKey(int key){
        return keys[key];
    }
}
