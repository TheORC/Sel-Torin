package com.Gamesareme.TCO.entity.mobs.enemies;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.Gamesareme.TCO.entity.Entity;
import com.Gamesareme.TCO.enums.Directions;
import com.Gamesareme.TCO.gfx.Animation;
import com.Gamesareme.TCO.world.World;

public class MobWalker extends Entity{
	
	protected float velX;
    protected float velY;
    protected int maxVelY = 7;
    protected float gravity = 0.98f;
    protected Directions direction = Directions.RIGHT;
    protected boolean falling = true; //true;
    protected Animation animeLeft;
    protected Animation animeRight;
    protected float speed = 1.5f;
    protected int xpGain = 10;

    public MobWalker(int x, int y, World world) {
        super(x, y, world);
    }

    @Override
    public void tick() {
        
    	 if (velX > 0) direction = Directions.RIGHT;
         else if (velX < 0) direction = Directions.LEFT;
         if (!hasHorizontalCollision()){
        	 x += velX;
         }else{
        	 if(direction == Directions.LEFT){
        		 direction = Directions.RIGHT;
        	 }else{
        		 direction = Directions.LEFT;
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
