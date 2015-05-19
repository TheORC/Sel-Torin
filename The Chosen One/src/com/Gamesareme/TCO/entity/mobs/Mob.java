/**
 * 
 */
package com.Gamesareme.TCO.entity.mobs;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.Gamesareme.TCO.entity.Entity;
import com.Gamesareme.TCO.enums.Directions;
import com.Gamesareme.TCO.enums.MobState;
import com.Gamesareme.TCO.gfx.Animation;
import com.Gamesareme.TCO.gfx.Sprite;
import com.Gamesareme.TCO.world.World;

/**
 * @author Oliver
 *
 */
public class Mob extends Entity{
	
	protected float velX;
    protected float velY;
    protected int maxVelY = 7;
    protected float gravity = 0.98f;
    protected Directions direction = Directions.RIGHT;
    protected boolean falling = true; //true;
    protected boolean jumping = false;
    protected boolean moving = false;
    protected boolean onGround = false;
    protected Animation animeLeft;
    protected Animation animeRight;
    protected Sprite sprite2;
    
    protected float hurtFlashTime = 10, curHurtFlashTime = 0;
    protected float hurtTime = 0;
    protected boolean hurtInvisable = false;
    protected boolean hurting = false;
    
    protected int fallDistance = 0;
    protected int maxFallDistance = 60*6;
    
    protected MobState mobState;

    public Mob(int x, int y, World world) {
        super(x, y, world);
    }

    @Override
    public void tick() {
        if (velX > 0){ direction = Directions.RIGHT;}
        else if (velX < 0){ direction = Directions.LEFT;}
        if(velX == 0)mobState = MobState.IDLE;
        else mobState = MobState.MOVING;
        
        if (!hasHorizontalCollision()) x += velX;
        if (!hasVerticalCollision()) y += velY;
        fall();
        if (velX != 0) moving = true;
        else moving = false;
        if (moving) getAnimation().runAnimation();
        
        if(!onGround)fallDistance++;
        else fallDistance = 0;
        if(fallDistance>=maxFallDistance)setHealth(0);
    }

    @Override
    public void render(Graphics g) {
        if (moving) getAnimation().drawAnimation(g, x, y);
        else getStandingStill().render(g, x, y);
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

    public boolean isMoving() {
        return moving;
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

}
