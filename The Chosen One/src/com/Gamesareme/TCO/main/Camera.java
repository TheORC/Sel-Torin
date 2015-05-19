/**
 * This is my games camera class.
 * This is where I tell the camera where to move.
 */
package com.Gamesareme.TCO.main;

import com.Gamesareme.TCO.Game;
import com.Gamesareme.TCO.entity.Player;

/**
 * @author Oliver
 *
 */
public class Camera {
	
	private float x, y;  //The cameras x and y location.
	@SuppressWarnings("unused")
	private Player player;  //This is my 
	
	public void tick(Player player){  //This runes 60 times a second.
		x += ((-player.getX() + Game.WIDTH/2) - x) * 0.095f;  //Update the camera to the players location.
	}

	/**
	 * @return the cameras x location.
	 */
	public float getX() {
		return x;
	}

	/**
	 * @return the cameras y location.
	 */
	public float getY() {
		return y;
	}
}
