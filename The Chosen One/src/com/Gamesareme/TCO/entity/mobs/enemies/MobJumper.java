package com.Gamesareme.TCO.entity.mobs.enemies;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.Gamesareme.TCO.entity.Entity;
import com.Gamesareme.TCO.enums.Directions;
import com.Gamesareme.TCO.gfx.Animation;
import com.Gamesareme.TCO.gfx.Sprite;
import com.Gamesareme.TCO.main.WorldManager;
import com.Gamesareme.TCO.utils.Distance;
import com.Gamesareme.TCO.world.World;

public class MobJumper extends Entity{
	
	protected float velX;
    protected float velY;
    protected int maxVelY = 7;
    protected float gravity = 0.98f;
    protected Directions direction = Directions.RIGHT;
    protected boolean falling = true; //true;
    protected boolean attacking = false;
    protected Animation animeLeft;
    protected Animation animeRight;
    protected Sprite sprite2;
    protected float speed = 2.5f;
    protected int xpGain = 10;
    
    protected int jumFrequ = 0;
    protected int currentJumpTime = jumFrequ;
    protected int jumpHieght = 22;
    
    protected boolean jumping = false;

    public MobJumper(int x, int y, World world) {
        super(x, y, world);
    }

    @Override
    public void tick() {
    	
    	int distance = Distance.distanceFrom(WorldManager.getPlayer(), this);
    	int distanceFromPlayer = (distance < 0 ? -distance : distance);
    	
    	if(distanceFromPlayer < (32 * 4)){
    		if(distance <= 0){
    			direction = Directions.LEFT;
    		}else{
    			direction = Directions.RIGHT;
    		}
    	}
    	if (!hasVerticalCollision()) y += velY;
    	else jumping = false;
         fall();
    }

    @Override
    public void render(Graphics g) {
    	getStandingStill().render(g, x, y);
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
    
    
    public Sprite getStandingStill() {
        return direction == Directions.LEFT ? sprite2 : sprite;
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

	/**
	 * @return the jumping
	 */
	public boolean isJumping() {
		return jumping;
	}

}
