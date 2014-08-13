package com.mygdx.game.GUI.battleinterface;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.entities.Spatial;

public class SimpleText extends Spatial{

	BitmapFont font = new BitmapFont();
	String text = "";
	
	SpriteBatch spriteBatch;

	boolean active = true;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		
	}

	public SimpleText( String text) {
		this.text=text;
		spriteBatch = new SpriteBatch();
	}
	
	public SimpleText(BitmapFont font, String text) {
		this.font = font;
		this.text=text;
		spriteBatch = new SpriteBatch();
	}

	public SimpleText() {
		spriteBatch = new SpriteBatch();
	}

	
	@Override
	public void render() {
		if(active){
		
		spriteBatch.begin();
		font.draw(spriteBatch, text, this.getWorldTranslation().x, this.getWorldTranslation().y);	
		spriteBatch.end();
		
		}
	}

	
	
	public void setActive(boolean active) {
		this.active=active;
	}

	
	
}
