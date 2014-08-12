package com.mygdx.game.controller;

import com.mygdx.game.entities.GUI;
import com.mygdx.game.screens.GameScreen;

public class GUIController {

	
	GUI gui;
	
	
	
	public GUIController(GUI gui) {
		this.gui=gui;
	}

	public void update(float delta) {
		
		
	}

	public boolean keyDown(int keycode) {
		if(GUIFocused()){
			GameScreen.getGUI().getDialogController().next();
			
			return true;
		}
		
		
		return false;
	}

	

	public boolean keyUp(int keycode) {
		
		
		return false;
	}
	
	
	
	public boolean GUIFocused()
	{
		return GameScreen.getGUI().getDialogController().getDialogIsActive();
		
	}
	
	
}
