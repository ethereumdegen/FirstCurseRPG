package com.mygdx.game.GUI.battleinterface;

import com.mygdx.game.GUI.battleinterface.UnitActionsScreen.UnitActions;
import com.mygdx.game.abilities.Ability;
import com.mygdx.game.controller.InputActionManager.InputAction;

public class UnitAttacksScreen extends OptionsScreen implements InputHandler{

	
	BattleInterfaceController controller;
	
	UnitAttacksScreen(BattleInterfaceController controller)
	{			
		super();
		
		this.controller=controller;
	}

	@Override
	public void update(float delta)
	{
		super.update(delta);
		if(controller.getUnitCurrentlyCasting()!=null){
			
			options[0] = controller.getUnitCurrentlyCasting()
					.getAbilities()[0];
		}
		
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
	
	public Ability getSelectedAbility()
	{
		return  controller.getUnitCurrentlyCasting()
				.getAbilities()[this.selectionIndex];
		
	}

	
	
}
