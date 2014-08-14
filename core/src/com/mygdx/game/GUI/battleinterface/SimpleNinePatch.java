package com.mygdx.game.GUI.battleinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.entities.Spatial;

public class SimpleNinePatch extends Spatial{

	NinePatch patch;
	
	
	SpriteBatch spriteBatch;

	boolean active = true;
	
	static final int PADDING = 20;
	public SimpleNinePatch(TextureRegion tex) {
		spriteBatch = new SpriteBatch();
		patch = new NinePatch(tex,PADDING,PADDING,PADDING,PADDING);
	}

	
	@Override
	public void render() {
		if(active){
		
		spriteBatch.begin();
		
		patch.draw(spriteBatch, 0, 0, Gdx.graphics.getWidth(), 100);
		
		
		spriteBatch.end();
		
		}
	}

	
	
	public void setActive(boolean active) {
		this.active=active;
	}

	
	
}
