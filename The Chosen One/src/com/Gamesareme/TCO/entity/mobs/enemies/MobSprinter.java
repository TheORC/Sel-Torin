package com.Gamesareme.TCO.entity.mobs.enemies;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.Gamesareme.TCO.entity.Entity;
import com.Gamesareme.TCO.enums.Directions;
import com.Gamesareme.TCO.gfx.Animation;
import com.Gamesareme.TCO.main.WorldManager;
import com.Gamesareme.TCO.utils.Distance;
import com.Gamesareme.TCO.world.World;

public class MobSprinter extends Entity{
	
	protected float velX;
    protected float velY;
    protected int maxVelY = 7;
    protected float gravity = 0.98f;
    protected Directions direction = Directions.RIGHT;
    protected boolean falling = true; //true;
    protected boolean attacking = false;
    protected Animation animeLeft;
    protected Animation animeRight;

    protected float speed = 2.5f;
    protected int xpGain = 10;

    public MobSprinter(int x, int y, World world) {
        super(x, y, world);
    }

    @Override
    public void tick() {
    	
    	int distance = Distance.distanceFrom(WorldManager.getPlayer(), this);
    	int distanceFromPlayer = (distance < 0 ? -distance : distance);
    	
    	if(distanceFromPlayer < (32 * 4)){
    		attacking = true;
    		if(distance <= 0){
    			direction = Directions.LEFT;
    		}else{
    			direction = Directions.RIGHT;
    		}
    	}else{
    		attacking = false;
    	}
        
    	if(!attacking){
    		if (velX > 0) direction = Directions.RIGHT;
         	else if (velX < 0) direction = Directions.LEFT;
    	}
    	 
         if (!hasHorizontalCollision()){
        	 x += velX;
         }else{
        	 if(attacking){
        		 y -= 15;
        		 velX = 0;
        	 }else{
        		 if(direction == Directions.LEFT){
        		 	direction = Directions.RIGHT;
        		 }else{
        		 	direction = Directions.LEFT;
        	 	 }
        	 }
         }
         if (!hasVerticalCollision()) y += velY;
         fall();
         getAnimation().runAnimation();
    }

    @Override
    public void render(Graphics g) {
        getAnimation().drawAnimation(g, x, y);
    }

    protected void fall() {
        if (falling) {
            velY += gravity;
            if (velY > maxVelY) velY = maxVelY;
        }
    }

    public Directions getDirection() {
        return direction;
    }

    public Animation getAnimation() {
        return direction == Directions.LEFT ? animeLeft : animeRight;
    }

	/* (non-Javadoc)
	 * @see com.Gamesareme.nova.entity.Entity#getTop()
	 */
	@Override
	public Rectangle getTop() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.Gamesareme.nova.entity.Entity#getBottom()
	 */
	@Override
	public Rectangle getBottom() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.Gamesareme.nova.entity.Entity#getRight()
	 */
	@Override
	public Rectangle getRight() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.Gamesareme.nova.entity.Entity#getLeft()
	 */
	@Override
	public Rectangle getLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.Gamesareme.nova.entity.Entity#getBounds()
	 */
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the xpGain
	 */
	public int getXpGain() {
		return xpGain;
	}

}
