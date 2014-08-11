package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


public class DialogController {

	private BitmapFont font;
	
	public DialogController()
	{
		
		font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.setScale(1.2f);
        
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        
       
	}
		
	String text = "";	
	
	
	
	float lerpCounter = 0;
	float lerpTotal = 0;
	
	String nextText = "";
	
	public void setText(String text, float lerp)
	{
		if(!this.text.equals(text) && !nextText.equals(text)){
		
			lerpTotal = lerp * text.length();
			lerpCounter = 0;
		
			nextText = text;
		}
	}
	
	public void update(float delta)
	{
		if(lerpTotal > 0)
		{
			if(lerpCounter < lerpTotal)
			{		 
				lerpCounter+=delta;
				float amt = (lerpCounter/lerpTotal);
				 
				int charCount = (int) (nextText.length() * amt);
				
				text = nextText.substring(0, charCount);
				
				
			}else
			{
				lerpTotal = 0;
				lerpCounter = 0;
				
				text = nextText;
			}
			
		}
		
		
	}
	SpriteBatch spriteBatch;
	ShapeRenderer shapeRenderer;
	public void render() {
		
		shapeRenderer.setColor(0.3f,0.3f,0.3f,1f);
		 shapeRenderer.begin(ShapeType.Filled);
		 shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), 100);
		 shapeRenderer.end();
		 
		 
		 spriteBatch.begin();
		 font.drawWrapped(spriteBatch, text, 140, 90, 300);
		 spriteBatch.end();
		 
		 
		
	}
	

}
