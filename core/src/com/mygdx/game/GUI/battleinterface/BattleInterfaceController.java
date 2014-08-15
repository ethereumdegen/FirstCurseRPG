package com.mygdx.game.GUI.battleinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.Unit;
import com.mygdx.game.AssetMGMT.CommonSounds;
import com.mygdx.game.AssetMGMT.UtilitySprites;
import com.mygdx.game.GUI.GUIShape;
import com.mygdx.game.GUI.battleinterface.UnitActionsScreen.UnitActions;
import com.mygdx.game.abilities.AbilityExecutionInfo;
import com.mygdx.game.abilities.AbilityType;
import com.mygdx.game.controller.InputActionManager.InputAction;
import com.mygdx.game.entities.Spatial;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.GameState;

public class BattleInterfaceController extends Node2D implements InputHandler,BattleOptionListener {

	
	SimpleNinePatch background;
	
	
	Node2D leftNode = new Node2D();
	Node2D centerNode = new Node2D();
	Node2D rightNode = new Node2D();
	
	
	PartyInfoScreen partyInfoScreen = new PartyInfoScreen();
	
	TargetSelectScreen targetSelectScreen = new TargetSelectScreen();
	UnitActionsScreen unitActionScreen = new UnitActionsScreen();
	UnitAttacksScreen unitAttacksScreen = new UnitAttacksScreen();
		
	
	
	public BattleInterfaceController()
	{
		
		 background = new SimpleNinePatch(UtilitySprites.GRAYBACKGROUND.getTextureRegion());
		
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
			case ATTACK: menuOpen = CurrentMenuOpen.ATTACKSCREEN; break;				
			case CHOOSETARGET: menuOpen = CurrentMenuOpen.TARGETSCREEN; break;
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
		
		
		
		menuOpen = CurrentMenuOpen.NONE;
		
		unitCurrentlyCasting = null;
		
		
	}




	public void update(float delta){
		super.update(delta);
		
		pollUnitCooldowns();
		updateMenuStates();
	}


	private void updateMenuStates() {
		switch(menuOpen)
		{
		case ACTIONSCREEN:
			unitActionScreen.setVisible(true);
			unitAttacksScreen.setVisible(false);
			targetSelectScreen.setVisible(true);
			unitAttacksScreen.setActive(false);
			unitActionScreen.setActive(true);
			break;
		case ATTACKSCREEN:
			unitActionScreen.setVisible(false);
			unitAttacksScreen.setVisible(true);
			targetSelectScreen.setVisible(true);
			targetSelectScreen.setVisible(true);
			unitActionScreen.setActive(false);
			unitAttacksScreen.setActive(true);
			break;
		case TARGETSCREEN:
			unitActionScreen.setVisible(false);
			//unitAttacksScreen.setVisible(false);
			targetSelectScreen.setVisible(true);
			unitAttacksScreen.setActive(false);
			unitActionScreen.setActive(false);
			targetSelectScreen.setActive(true);
			break;
		case NONE:
			unitAttacksScreen.setVisible(false);
			
			targetSelectScreen.setActive(false);
			unitAttacksScreen.setActive(false);
			unitActionScreen.setActive(false);
			break;
		}
		
		
	}


	private void pollUnitCooldowns() {
		
		if(waitingForCooldowns())
		{
			for(Unit unit : GameScreen.getBattle().getUnits()[0])
			{
				if(unit!=null && unit.cooldownFinished())
				{
					
					showActionMenusForUnit(unit);
				}
			}
		}
		
	}

	private void showActionMenusForUnit(Unit unit) {
		unitCurrentlyCasting = unit;
		menuOpen= CurrentMenuOpen.ACTIONSCREEN;
		
	}

	public Unit getUnitCurrentlyCasting() {
		return unitCurrentlyCasting;
	}

	CurrentMenuOpen menuOpen = CurrentMenuOpen.NONE;
	Unit unitCurrentlyCasting = null;
	private boolean waitingForCooldowns() {
		
		return unitCurrentlyCasting == null;
	}

	enum CurrentMenuOpen
	{
		NONE,
		ACTIONSCREEN,
		ATTACKSCREEN,
		TARGETSCREEN
		
	}

}
