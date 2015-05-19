/**
 * 
 */
package com.Gamesareme.TCO.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.Gamesareme.TCO.gfx.Sprite;
import com.Gamesareme.TCO.world.World;

/**
 * @author Oliver
 *
 */
public abstract class Entity {
	
	protected int x;
    protected int y;
    protected int health;
    protected Sprite sprite;
    protected World world;
        
    public Entity(int x2, int y2, World world) {
        this.x = x2;
        this.y = y2;
        this.world = world;
        world.addEntity(this);
    }
    
    public abstract void tick();
    
    public void render(Graphics g){
    	if(sprite != null)
    		sprite.render(g, x, y);
    }
    
    protected boolean hasHorizontalCollision(){
        return false;
    }
    
    protected boolean hasVerticalCollision(){
        return false;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }
    
    public int getHealth(){
    	return health;
    }
    
    public void setHealth(int amount){
    	this.health = amount;
    }
    
    public abstract Rectangle getTop();
    public abstract Rectangle getBottom();
    public abstract Rectangle getRight();
    public abstract Rectangle getLeft();
    public abstract Rectangle getBounds();

	

}
