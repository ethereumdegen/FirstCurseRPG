package com.mygdx.game.renderer;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.entities.Spatial;

public class GraphSprite extends Spatial {

	Sprite sprite;
	SpriteBatch spriteBatch;
	
	public GraphSprite(TextureRegion tex)
	{
		
		
		sprite = new Sprite(tex);
		

		spriteBatch = new SpriteBatch();
	}
	
	
	@Override
	public void render()
	{
		sprite.setPosition(this.getWorldTranslation().x, this.getWorldTranslation().y);
		
		spriteBatch.begin();
		sprite.draw(spriteBatch);	
		spriteBatch.end();
		
		
		//System.out.println("rendering graphsprite" + sprite.getX() +"."+sprite.getY());
		
	}
	
	
	
	
}
