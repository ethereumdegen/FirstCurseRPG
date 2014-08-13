package com.mygdx.game.GUI.battleinterface;

import com.badlogic.gdx.math.Vector2;

public interface BattleInterfaceScreen {

	Vector2 offset = new Vector2(0,0);
	
	
	void render();
	
	void update(float millis);
	
}
