package com.Gamesareme.TCO.utils;

import com.Gamesareme.TCO.entity.Entity;
import com.Gamesareme.TCO.world.Block;

public class Distance {
	
	public static int distanceFrom(Entity e1, Entity e2){
		int e1i = e1.getX();
		int e2i = e2.getX();
		return (e1i - e2i);
	}

	public static int distanceFrom(Entity e1, Block e2) {
		// TODO Auto-generated method stub
		int e1i = e1.getX();
		int e2i = e2.getX();
		return (e1i-e2i);
	}
}
