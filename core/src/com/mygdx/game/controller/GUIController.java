package com.mygdx.game.controller;

import com.mygdx.game.entities.GUI;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.GameState;

public class GUIController {

	
	GUI gui;
	
	
	
	public GUIController(GUI gui) {
		this.gui=gui;
	}

	public void update(float delta) {
		
		
	}

	public boolean keyDown(int keycode) {
		
		if(DialogActive()){
			GameScreen.getGUI().getDialogController().next();			
			return true;
		}
		
		if(GameScreen.getState() == GameState.BATTLE)
		{
			GameScreen.getGUI().getBattleInterfaceController().keyDown(keycode);
			return true;
		}
		
		
		
		return false;
	}

	

	public boolean keyUp(int keycode) {
		
		if(GameScreen.getState() == GameState.BATTLE)
		{
			GameScreen.getGUI().getBattleInterfaceController().keyUp(keycode);
			return true;
		}
		
		
		return false;
	}
	
	
	
	public boolean DialogActive()
	{
		return GameScreen.getGUI().getDialogController().getDialogIsActive();
		
	}
	
	
}
