package com.mygdx.game.GUI.battleinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.GUI.battleinterface.UnitActionsScreen.UnitActions;
import com.mygdx.game.controller.InputActionManager.InputAction;
import com.mygdx.game.entities.Spatial;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.GameState;

public class BattleInterfaceController implements InputHandler,BattleOptionListener {

	private BitmapFont font;
	Sound speechSound;
	
	Node2D GUINode = new Node2D();
	Node2D leftNode = new Node2D();
	Node2D centerNode = new Node2D();
	Node2D rightNode = new Node2D();
	
	
	PartyInfoScreen partyInfoScreen = new PartyInfoScreen();
	
	TargetSelectScreen targetSelectScreen = new TargetSelectScreen();
	UnitActionsScreen unitActionScreen = new UnitActionsScreen();
	UnitAttacksScreen unitAttacksScreen = new UnitAttacksScreen();
		
	public BattleInterfaceController()
	{
		unitActionScreen.registerListener(this);
		unitAttacksScreen.registerListener(this);
		targetSelectScreen.registerListener(this);
		
		
		leftNode.setTranslation(30, 0, 0);
		centerNode.setTranslation(200, 0, 0);
		rightNode.setTranslation(400, 0, 0);
		
		GUINode.attachChild(leftNode);
		GUINode.attachChild(centerNode);
		GUINode.attachChild(rightNode);
		
		rightNode.attachChild(targetSelectScreen);
		centerNode.attachChild(unitActionScreen);
		leftNode.attachChild(partyInfoScreen);
		
		font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.setScale(1.2f);
        
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        
       speechSound = Gdx.audio.newSound(Gdx.files.internal("sounds/typing.wav"));
       
       initBattle();
       
	}
	
	
	public void initBattle()
	{
		unitActionScreen.setActive(true);
		
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
		
		GUINode.update(delta);
		
		
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





	@Override
	public boolean processInputAction(InputAction action, boolean asserted) {
		
			partyInfoScreen.processInputAction(action,asserted);
			
			if(GUINode.hasDescendant(unitActionScreen))
			{
			unitActionScreen.processInputAction(action,asserted);
			}
						
			if(GUINode.hasDescendant(unitAttacksScreen))
			{
			unitAttacksScreen.processInputAction(action,asserted);
			}
		
		return false;
	}




	@Override
	public void assertOption(BattleOption option) {
		
		if(option instanceof UnitActions)
		{
			UnitActions action = (UnitActions) option;
			switch(action)
			{
			case ATTACK: showAttackScreen(); break;	
			
			}
			
			
			
			
		}
		
		
	}


	

	
	private void showAttackScreen() {
		
		centerNode.detachAllChildren();
		centerNode.attachChild(unitAttacksScreen);
		
		unitAttacksScreen.setActive(true);
	}


	

}
