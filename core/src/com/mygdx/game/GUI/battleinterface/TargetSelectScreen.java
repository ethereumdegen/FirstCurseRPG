package com.mygdx.game.GUI.battleinterface;

import com.mygdx.game.GUI.battleinterface.UnitActionsScreen.UnitActions;
import com.mygdx.game.controller.InputActionManager.InputAction;
import com.mygdx.game.screens.GameScreen;

public class TargetSelectScreen extends OptionsScreen implements InputHandler{

	TargetSelectScreen()
	{			
		super();
		
		for(int i = 0; i < 3 ;i++)
		{
			if(GameScreen.getBattle().getUnits()[1][i]!=null){
				options[i] = GameScreen.getBattle().getUnits()[1][i].getName();
			}
		}
		
		
				
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
