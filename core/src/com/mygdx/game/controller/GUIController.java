package com.mygdx.game.controller;

import com.mygdx.game.GUI.battleinterface.InputHandler;
import com.mygdx.game.controller.InputActionManager.InputAction;
import com.mygdx.game.entities.GUI;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.GameState;

public class GUIController implements InputHandler{

	
	GUI gui;
	
	
	
	public GUIController(GUI gui) {
		this.gui=gui;
	}

	public void update(float delta) {
		
		
	}

		
	
	public boolean DialogActive()
	{
		return GameScreen.getGUI().getDialogController().getDialogIsActive();
		
	}

	@Override
	public boolean processInputAction(InputAction action, boolean asserted) {
		
		if(DialogActive()){
			GameScreen.getGUI().getDialogController().processInputAction(action,asserted);
			return true;
		}
		
		if(GameScreen.getState() == GameState.BATTLE)
		{
			GameScreen.getGUI().getBattleInterfaceController().processInputAction(action,asserted);
			return true;
		}
		
		
		
		return false;
	}
	
	
}
