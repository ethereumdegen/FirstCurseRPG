package com.mygdx.game.entities;

import com.mygdx.game.GUI.DialogController;
import com.mygdx.game.GUI.battleinterface.BattleInterfaceController;
import com.mygdx.game.screens.GameScreen;

public class GUI {

	DialogController dialogController;
	
	BattleInterfaceController battleInterfaceController;
	
	public GUI()
	{
		dialogController = new DialogController();
		battleInterfaceController = new BattleInterfaceController();
		
	}
	

	public DialogController getDialogController() {
		return dialogController;
	}


	public BattleInterfaceController getBattleInterfaceController() {
		return battleInterfaceController;
	}

	
	
	
	
}
