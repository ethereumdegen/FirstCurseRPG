package com.mygdx.game.GUI.battleinterface;

import com.mygdx.game.GUI.battleinterface.UnitActionsScreen.UnitActions;
import com.mygdx.game.controller.InputActionManager.InputAction;
import com.mygdx.game.screens.GameScreen;

public class TargetSelectScreen extends OptionsScreen implements InputHandler{

	TargetSelectScreen()
	{			
		super();
		
	
		
		
				
	}

	

	@Override
	public boolean processInputAction(InputAction action, boolean asserted) {
		super.processInputAction(action,asserted);
		
		
		
		return false;
	}


	public void update(float delta)
	{ 
		super.update(delta);
		
		for(int i = 0; i < 3 ;i++)
		{
			if(GameScreen.getBattle().getUnits()[1][i]!=null){
				options[i] = GameScreen.getBattle().getUnits()[1][i];
			}
		}
	
	}
	
	
	@Override
	protected void selectAction() {
		//set ability.... whatever
		
		
		
		broadcastBattleOption(UnitActions.EXECUTEABILITY);
		
	}



	

	
	
	
}
