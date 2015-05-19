package com.Gamesareme.TCO.Particls;
import java.awt.Color;
import java.util.Random;

import com.Gamesareme.TCO.world.World;


public class Particle_Rain {
	
	private int screenWidth = 450;
	private int scip = 10;
	private int cur;
	private boolean spawn = false;
	private World world;
	private int xloc;
		
	
	public Particle_Rain(int x, int screnW, int scip, World world){
		this.screenWidth = screnW;
		this.scip = scip;
		this.world = world;
		this.xloc = x;
		
	}
	
	
	public void spawnRain(){
		//i can = 2000 width = 640
		//System.out.println("Player x loc: " + WorldManager.getPlayer().getX() + " Rain x loc: " + xloc);

		for(int i = xloc; i < (xloc + screenWidth) + 600; i++){
			Random r = new Random();
			spawn = r.nextBoolean();
			if(spawn){
				float chance = r.nextFloat();
				if(chance <= 0.01f){
					if(cur <= scip){
						if(world !=null){
							world.getParticles().add(new Particle(i, -10, -6, 7, (int) (Math.random() * 4),  (int) Math.random() * (120) + 380, Color.BLUE));
							cur = 0;
						}
					}else{
						cur++;
					}
				}
			}
		}
	}


	/**
	 * @param xloc the xloc to set
	 */
	public void setXloc(int xloc) {
		this.xloc = xloc;
	}

}
