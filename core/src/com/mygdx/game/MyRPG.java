package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.mygdx.game.AssetMGMT.AssetCenter;
import com.mygdx.game.screens.GameScreen;

/* First Curse */


public class MyRPG extends Game {
	
	
	
	static boolean onMobile = false;
	
	
	@Override
	public void create() {
		
		onMobile = Gdx.app.getType().equals(ApplicationType.Android) || Gdx.app.getType().equals(ApplicationType.iOS);
		//onMobile = true; //testing
		
		
		
		AssetCenter.init();
		
		
		
		setScreen(new GameScreen());
		
	}


	public static boolean onMobile() {
	
		return onMobile;
	}
	
	
	
}
