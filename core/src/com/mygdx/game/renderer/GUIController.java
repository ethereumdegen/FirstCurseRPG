package com.mygdx.game.renderer;

import com.mygdx.game.MyRPG;
import com.mygdx.game.GUI.DialogController;
import com.mygdx.game.GUI.OnScreenControls;
import com.mygdx.game.GUI.battleinterface.BattleInterfaceController;
import com.mygdx.game.GUI.battleinterface.InputHandler;
import com.mygdx.game.GUI.battleinterface.Node2D;
import com.mygdx.game.controller.InputActionManager.InputAction;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.GameState;

public class GUIController extends Node2D implements InputHandler{


	private ScreenEffectController screenEffectController;
	

	
	DialogController dialogController;
	
	BattleInterfaceController battleInterfaceController;
	
	
	OnScreenControls controls;
	
	public GUIController()
	{
		
		
		screenEffectController = new ScreenEffectController();
		dialogController = new DialogController();
		battleInterfaceController = new BattleInterfaceController();
		controls = new OnScreenControls();
		
        
        this.attachChild(screenEffectController); 
        this.attachChild(dialogController); 
        this.attachChild(battleInterfaceController); 
        
        if (!MyRPG.onMobile())        
        	return;
        
        
        this.attachChild(controls); 
        
	}
	
	

	
	
	

	@Override
	public boolean processInputAction(InputAction action, boolean asserted) {
		
		if(getDialogController().dialogIsActive()){
			getDialogController().processInputAction(action,asserted);
			return true;
		}
		
		if(GameScreen.getState() == GameState.BATTLE)
		{
			getBattleInterfaceController().processInputAction(action,asserted);
			return true;
		}
		
		
		
		return false;
	}


	public OnScreenControls getControls() {
		return controls;
	}


	public DialogController getDialogController() {
		return dialogController;
	}


	public BattleInterfaceController getBattleInterfaceController() {
		return battleInterfaceController;
	}
	

	public ScreenEffectController getScreenEffectManager() {
		return screenEffectController;
	}
	
}
