package com.mygdx.game.GUI.battleinterface;

import com.mygdx.game.controller.InputActionManager.InputAction;

public class UnitActionsScreen extends OptionsScreen implements InputHandler{

	public enum UnitActions implements BattleOption
	{
		ATTACK,
		POWERS,
		ITEMS,
		RUN, 
		CHOOSETARGET, 
		EXECUTEABILITY
		
	}
	
	
	
	UnitActionsScreen()
	{			
		super();
		
		options = new String[]{"Attack","Power","Item"};
		
		
	}

	

	@Override
	public boolean processInputAction(InputAction action, boolean asserted) {
		super.processInputAction(action,asserted);
		
		
		
		return false;
	}


	public void update(float delta)
	{ 
		super.update(delta);
		
				
		
	}
	
	
	@Override
	protected void selectAction() {
		
		switch(selectionIndex)
		{
		case 0: broadcastBattleOption(UnitActions.ATTACK);
		break;
		case 1: broadcastBattleOption(UnitActions.ITEMS);
		break;
		case 2: broadcastBattleOption(UnitActions.POWERS);
		break;
		case 3: broadcastBattleOption(UnitActions.RUN);
		break;		
		}
		
		
		
	}





	

	
	
	
}
