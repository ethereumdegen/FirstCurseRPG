package com.mygdx.game.GUI.battleinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.AssetMGMT.CommonSounds;
import com.mygdx.game.GUI.GUIShape;
import com.mygdx.game.GUI.battleinterface.UnitActionsScreen.UnitActions;
import com.mygdx.game.abilities.AbilityExecutionInfo;
import com.mygdx.game.abilities.AbilityType;
import com.mygdx.game.controller.InputActionManager.InputAction;
import com.mygdx.game.entities.Spatial;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.GameState;

public class BattleInterfaceController extends Node2D implements InputHandler,BattleOptionListener {

	
	GUIShape background = new GUIShape();
	
	
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
		
		attachChild(background);
		attachChild(leftNode);
		attachChild(centerNode);
		attachChild(rightNode);
		
		rightNode.attachChild(targetSelectScreen);
		centerNode.attachChild(unitActionScreen);
		centerNode.attachChild(unitAttacksScreen);
		leftNode.attachChild(partyInfoScreen);
		
		
      

        
      
       
       this.setVisible(false);
       
	}
	
	
	public void initBattle()
	{
		
		this.setVisible(true);
		
		
		unitActionScreen.setVisible(true);
		unitAttacksScreen.setVisible(false);
		
		
		unitActionScreen.setActive(true);
		
	}
	
	
	public void endBattle() {
		this.setVisible(false);
		
	}
	
	
	
	
	public void exitBattle()
	{
		this.detachAllChildren();
		
	}
	


	
	
	
	
	
	
	
//	boolean dialogIsActive = true;
	
	public boolean isActive() {
		
		return GameScreen.getState() == GameState.BATTLE;
	}





	@Override
	public boolean processInputAction(InputAction action, boolean asserted) {
		
			partyInfoScreen.processInputAction(action,asserted);
			
			if(unitActionScreen.isActive())
			{
			unitActionScreen.processInputAction(action,asserted);
		
			}
			else if( unitAttacksScreen.isActive())
			{
			unitAttacksScreen.processInputAction(action,asserted);
			
			}
			else if(targetSelectScreen.isActive())
			{
				targetSelectScreen.processInputAction(action,asserted);
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
			case CHOOSETARGET: showTargetScreen(); break;
			case EXECUTEABILITY: assertUnitAbility(); break;
			}
			
		
			
			CommonSounds.CONFIRM.play(0.2f);
		}
		
		
	}


	
	private void assertUnitAbility() {
		System.out.println("executing ability!");
		
		if(hasDescendant(unitAttacksScreen))
		{
			int casterIndex = 0;
			int attackTypeIndex = unitAttacksScreen.getSelectionIndex();
			int victimIndex = targetSelectScreen.getSelectionIndex();
			
			System.out.println(attackTypeIndex +":" + victimIndex);
			
			AbilityExecutionInfo info = new AbilityExecutionInfo(GameScreen.getBattle().getUnits()[0][0],
					GameScreen.getBattle().getUnits()[1][0], AbilityType.SLASH );  //testing
			
			
			GameScreen.getBattle().executeUnitAbility(info );
		}
		
		
		centerNode.detachChild(unitAttacksScreen);
		
		targetSelectScreen.setActive(false);
		unitAttacksScreen.setActive(false);
		unitActionScreen.setActive(false);
		
		checkForCooledDownPartyMembers();
	}


	private void checkForCooledDownPartyMembers() {
		
		
		//if a member is ready...
	//	unitActionScreen.setActive(true);
		//centerNode.attachChild(unitActionScreen);
	}


	private void showTargetScreen() {
		
		unitActionScreen.setActive(false);
		unitAttacksScreen.setActive(false);
		targetSelectScreen.setActive(true);
	}


	private void showAttackScreen() {
		unitActionScreen.setVisible(false);
		unitAttacksScreen.setVisible(true);
		
		
		unitActionScreen.setActive(false);
		unitAttacksScreen.setActive(true);
	}


	


	

}
