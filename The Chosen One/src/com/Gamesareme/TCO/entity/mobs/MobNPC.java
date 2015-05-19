package com.Gamesareme.TCO.entity.mobs;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.Gamesareme.TCO.entity.Entity;
import com.Gamesareme.TCO.enums.Directions;
import com.Gamesareme.TCO.gfx.Animation;
import com.Gamesareme.TCO.gfx.Sprite;
import com.Gamesareme.TCO.main.WorldManager;
import com.Gamesareme.TCO.utils.Distance;
import com.Gamesareme.TCO.world.World;

public class MobNPC extends Entity {

	protected float velX;
	protected float velY;
	protected int maxVelY = 7;
	protected float gravity = 0.98f;
	protected Directions direction = Directions.RIGHT;
	protected boolean falling = true; // true;
	protected boolean attacking = false;
	protected Animation animeLeft;
	protected Animation animeRight;
	protected Sprite sprite2;

	public MobNPC(int x2, int y2, World world) {
		super(x2, y2, world);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		int distance = Distance.distanceFrom(WorldManager.getPlayer(), this);
		int distanceFromPlayer = (distance < 0 ? -distance : distance);

		if (distanceFromPlayer < (32 * 4)) {
			if (distance <= 0) {
				direction = Directions.LEFT;
			} else {
				direction = Directions.RIGHT;
			}
		}
		if (!hasVerticalCollision())
			y += velY;
		fall();
	}

	protected void fall() {
		if (falling) {
			velY += gravity;
			if (velY > maxVelY)
				velY = maxVelY;
		}
	}

    @Override
    public void render(Graphics g) {
    	getStandingStill().render(g, x, y);
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
}
