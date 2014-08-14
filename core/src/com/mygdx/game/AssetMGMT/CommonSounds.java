package com.mygdx.game.AssetMGMT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public enum CommonSounds {

	SELECT("sounds/typing.wav"),
	WALK("sounds/grasswalk.wav"),
	DEATH("sounds/dead.wav"),
	STARTBATTLE("sounds/battle start.wav"),
	CONFIRM("sounds/confirm.wav"),
	BACK("sounds/back.wav"),
	DAMAGE("sounds/damage.wav"),
	EQUIP("sounds/equip.wav"),
	RECOVER("sounds/recover.wav"),
	POWERUP("sounds/saveload.wav"),
	
	;
	
	String path;
	
	CommonSounds(String path)
	{
	this.path=path;
	}
	
	
	public String getPath() {
		return path;
	}
	
	public Sound getSound()
	{
		
		return Gdx.audio.newSound(Gdx.files.internal(path));
		
	}


	public void play() {
		getSound().play();
		
	}


	public void play(float f) {
		getSound().play(f);
		
	}
	
	
}
