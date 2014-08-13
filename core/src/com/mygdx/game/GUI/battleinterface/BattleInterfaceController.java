package com.mygdx.game.GUI.battleinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.GameState;

public class BattleInterfaceController {

	private BitmapFont font;
	Sound speechSound;
	
	Node2D GUINode = new Node2D();
	Node2D leftNode = new Node2D();
	Node2D rightNode = new Node2D();
	
	
	PartyInfoScreen partyInfoScreen = new PartyInfoScreen();
	

	
	public BattleInterfaceController()
	{
		GUINode.attachChild(leftNode);
		GUINode.attachChild(rightNode);
		
		leftNode.attachChild(partyInfoScreen);
		
		font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.setScale(1.2f);
        
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        
       speechSound = Gdx.audio.newSound(Gdx.files.internal("sounds/typing.wav"));
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
			lastCharCount=0;
		
			nextText = text;
		}
	}
	
	int lastCharCount = 0;
	public void update(float delta)
	{
		if(lerpTotal > 0)
		{
			if(lerpCounter < lerpTotal)
			{		 
				lerpCounter+=delta;
				float amt = (lerpCounter/lerpTotal);
				 
				int charCount = (int) (nextText.length() * amt);
				if(charCount > lastCharCount)
				{
					lastCharCount = charCount;
					speechSound.play(0.5f);
				}
				
				
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
		
		
		
		
		if(isActive() )
		{
		shapeRenderer.setColor(0.3f,0.3f,0.3f,1f);
		 shapeRenderer.begin(ShapeType.Filled);
		 shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), 100);
		 shapeRenderer.end();
		 
		 
		 spriteBatch.begin();
		 font.drawWrapped(spriteBatch, text, 140, 90, 300);
		 spriteBatch.end();
		
		 GUINode.render();
		}
	}
	
	
	
	
//	boolean dialogIsActive = true;
	
	public boolean isActive() {
		
		return GameScreen.getState() == GameState.BATTLE;
	}

	public void keyDown(int keycode) {
		// TODO Auto-generated method stub
		
	}

	public void keyUp(int keycode) {
		// TODO Auto-generated method stub
		
	}

}
