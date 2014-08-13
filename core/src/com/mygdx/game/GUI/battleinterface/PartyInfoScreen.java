package com.mygdx.game.GUI.battleinterface;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.UnitStats;
import com.mygdx.game.AssetMGMT.UtilitySprites;
import com.mygdx.game.controller.InputActionManager.InputAction;
import com.mygdx.game.screens.GameScreen;

public class PartyInfoScreen extends Node2D implements InputHandler{

	
	private SimpleText vitals[][] = new SimpleText[3][3];
	
	
	
	
	static final int UNIT_1 = 0;
	static final int UNIT_2 = 1;
	static final int UNIT_3 = 2;
	
	static final int NAME = 0;
	static final int HP = 1;
	static final int SP = 2;
	
	PartyInfoScreen()
	{
		
		
		
		
		
		for(int unit = UNIT_1; unit < UNIT_3; unit++)
		{
			vitals[unit][NAME] = new SimpleText();
			vitals[unit][NAME].setTranslation(100,90 - unit*25,10);
			this.attachChild(vitals[unit][NAME]);
			
			vitals[unit][HP] = new SimpleText();
			vitals[unit][HP].setTranslation(200,90 - unit*25,10);
			this.attachChild(vitals[unit][HP]);
			
			vitals[unit][SP] = new SimpleText();
			vitals[unit][SP].setTranslation(240,90 - unit*25,10);	
			this.attachChild(vitals[unit][SP]);			
			
		}
		
		
		
		
	}
	
	
	public void update(float millis) {
		
		
		 for(int unit = UNIT_1; unit < UNIT_3; unit++)
			{
			 if(GameScreen.getBattle().getUnits()[0][unit]!=null)
			 {
				 	vitals[unit][NAME].setText(GameScreen.getBattle().getUnits()[0][unit].getName());
		
				 	vitals[unit][HP].setText(""+GameScreen.getBattle().getUnits()[0][unit].getStatValue(UnitStats.HEALTH));
		
				 	vitals[unit][SP].setText(""+GameScreen.getBattle().getUnits()[0][unit].getStatValue(UnitStats.SPECIAL));
		 
			 }
			}
		
	}



	@Override
	public boolean processInputAction(InputAction action, boolean asserted) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
}
