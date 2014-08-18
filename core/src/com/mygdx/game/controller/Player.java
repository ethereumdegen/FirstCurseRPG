package com.mygdx.game.controller;

import com.mygdx.game.Unit;
import com.mygdx.game.AssetMGMT.MapRegion;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.GameState;

public class Player {
	static Unit focus;
	static MapRegion region;
	
	//static boolean cinematicMode = false;
	
	public static boolean isCinematicMode() {
		return GameScreen.getGUIController().getDialogController().dialogIsActive()
				|| GameScreen.getState() == GameState.BATTLE;
	}

	//public static void setCinematicMode(boolean cinematicMode) {
	//	Player.cinematicMode = cinematicMode;
	//}

	public static void setFocus(Unit unit)
	{
		focus = unit;	
		
	}
	
	public static Unit getFocus()
	{
		return focus;
	}

	public static void setRegion(MapRegion reg) {
		region=reg;
		
	}

	public static MapRegion getRegion() {
	
		return region;
	}


	public static Unit[] getBattlingParty() {
		Unit[] battlingParty = new Unit[3];
		battlingParty[0] = focus;
		
		
		return battlingParty;
	}
	
}
