package com.mygdx.game.renderer;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.entities.Spatial;

public class GraphSprite extends Spatial {

	Sprite sprite;
	protected SpriteBatch spriteBatch;
	
	boolean active = true;
	
	public GraphSprite(TextureRegion tex)
	{
		
		
		sprite = new Sprite(tex);
		

		spriteBatch = new SpriteBatch();
	}
	
	
	@Override
	public void render()
	{
		
		if(active){
		
			if(!this.getWorldTranslation().isZero()){ //dirty fix! be careful with this
			sprite.setPosition(this.getWorldTranslation().x, this.getWorldTranslation().y);
			}
		
		spriteBatch.begin();
		sprite.draw(spriteBatch);	
		spriteBatch.end();
		}
		
		//System.out.println("rendering graphsprite" + sprite.getX() +"."+sprite.getY());
		
	}


	public void setActive(boolean active) {
		this.active=active;
	}


	public Sprite getSprite() {
		
		return sprite;
	}
	
	
	
	
}
