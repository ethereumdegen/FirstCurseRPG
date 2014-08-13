package com.mygdx.game.GUI.battleinterface;

import com.mygdx.game.GUI.battleinterface.UnitActionsScreen.UnitActions;
import com.mygdx.game.controller.InputActionManager.InputAction;

public class UnitAttacksScreen extends OptionsScreen implements InputHandler{

	
	
	
	UnitAttacksScreen()
	{			
		super();
		
	
	}

	@Override
	public void update(float delta)
	{
		super.update(delta);
		options[0] = "Slash";
	}
	
	

	@Override
	public boolean processInputAction(InputAction action, boolean asserted) {
		super.processInputAction(action,asserted);
		
		
		
		return false;
	}


	@Override
	protected void selectAction() {
		broadcastBattleOption(UnitActions.CHOOSETARGET);
		
	}
	
	

	
	
}
