package com.mygdx.game.GUI.battleinterface;

import com.mygdx.game.controller.InputActionManager.InputAction;

public class UnitAttacksScreen extends OptionsScreen implements InputHandler{

	
	
	
	UnitAttacksScreen()
	{			
		super();
		
		options = new String[]{"Slash","---","---","---"};
		
		
	}

	

	@Override
	public boolean processInputAction(InputAction action, boolean asserted) {
		super.processInputAction(action,asserted);
		
		
		
		return false;
	}


	@Override
	protected void selectAction() {
		System.out.println("doing action " + selectionIndex );
		
	}

	
	
}
