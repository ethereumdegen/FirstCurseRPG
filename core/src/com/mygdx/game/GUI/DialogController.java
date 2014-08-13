package com.mygdx.game.GUI;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.GUI.battleinterface.InputHandler;
import com.mygdx.game.camera.CameraCommand;
import com.mygdx.game.controller.InputActionManager.InputAction;


public class DialogController implements InputHandler{

	private BitmapFont font;
	Sound speechSound;
	
	List<DialogInfo> queuedInfo = new ArrayList<DialogInfo>();
	
	
	public DialogController()
	{
		
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
		if(dialogIsActive)
		{
		shapeRenderer.setColor(0.3f,0.3f,0.3f,1f);
		 shapeRenderer.begin(ShapeType.Filled);
		 shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), 100);
		 shapeRenderer.end();
		 
		 
		 spriteBatch.begin();
		 font.drawWrapped(spriteBatch, text, 140, 90, 300);
		 spriteBatch.end();
		}
		 
		
	}
	public void queueDialogAction(DialogInfo info) {
		
		queuedInfo.add(info);
		
		if(!dialogIsActive)
		{
			next();
		}
		
	}
	
	
	
	boolean dialogIsActive = false;
	
	public boolean getDialogIsActive() {
		
		return dialogIsActive;
	}
	
	public void next() {
		
		if(lerpCounter < lerpTotal)
		{
			return; //wait until text is done...
		}
		
		if(queuedInfo.isEmpty())
		{
			dialogIsActive = false;
			
		}
		else
		{			
		
		dialogIsActive = true;
		
		DialogInfo nextInfo = queuedInfo.remove(0);
		
		setText(nextInfo.speech, nextInfo.speed);
		
		}
		
		
		
	}
	@Override
	public boolean processInputAction(InputAction action, boolean asserted) {
		
	
		if(asserted)
		{
			next();
			
			return true;
		}
		
		
		return false;
	}
	

}
