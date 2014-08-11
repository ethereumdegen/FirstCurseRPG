package com.mygdx.game.renderer;

import com.badlogic.gdx.graphics.Color;

public class TintScreenEffect extends ScreenEffect{

	
	
	public TintScreenEffect(Color tint) {
		this.tint = tint;
	}
	
	public TintScreenEffect(Color tint, float time) {
		this.tint = tint;
		this.time = time;
	}

	
	
}
