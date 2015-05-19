package com.Gamesareme.TCO.Particls;
import java.io.File;
import java.io.IOException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

import com.Gamesareme.TCO.Game;

public class RainEffect {
	
	private ParticleSystem rain;
	
	public RainEffect(){
		try {
			init();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init() throws SlickException{
		Image image = new Image("resources/Rain.png", false);
		rain = new ParticleSystem(image, 5000);
		try {
			File file = new File("resources/Particls_Rain.xml");
			ConfigurableEmitter emitter = ParticleIO.loadEmitter(file);
			emitter.setPosition(Game.WIDTH/2, -10);
			rain.addEmitter(emitter);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		rain.setBlendingMode(ParticleSystem.BLEND_ADDITIVE);
	}

	public void tick(double delta){
		rain.update((int)delta);
	}
	
}
