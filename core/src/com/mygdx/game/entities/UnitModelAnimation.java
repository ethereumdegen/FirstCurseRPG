package com.mygdx.game.entities;

public enum UnitModelAnimation {
	DEATH(1f),
	
	DAMAGED(0.2f)
	;
	
	
	float playTime = 1f;
	
	UnitModelAnimation(float playTime)
	{
		this.playTime=playTime;
		
		
	}
	public float getPlayTime() {
		return playTime;
	}

}
