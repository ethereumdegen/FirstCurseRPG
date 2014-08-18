package com.mygdx.game.GUI.battleinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.entities.Spatial;

public class SimpleNinePatch extends Spatial{

	NinePatch patch;
	
	
	SpriteBatch spriteBatch;

	boolean active = true;
	Rectangle rect = new Rectangle(0, 0, Gdx.graphics.getWidth(), 100);

	public SimpleNinePatch(TextureRegion tex) {
		spriteBatch = new SpriteBatch();
		int PADDING = 20;
		patch = new NinePatch(tex,PADDING,PADDING,PADDING,PADDING);
	}
	
	public SimpleNinePatch(TextureRegion tex, int padding) {
		spriteBatch = new SpriteBatch();
		patch = new NinePatch(tex,padding,padding,padding,padding);
	}

	
	@Override
	public void render() {
		if(active){
		
		spriteBatch.begin();
		
		patch.draw(spriteBatch, rect.x + this.getWorldTranslation().x ,rect.y + this.getWorldTranslation().y,rect.width,rect.height);
		
		
		spriteBatch.end();
		
		}
	}

	
	
	public void setActive(boolean active) {
		this.active=active;
	}

	public void setRect(int i, int j, int k, int l) {
		rect.set(i, j, k, l);
		
	}

	
	
}
