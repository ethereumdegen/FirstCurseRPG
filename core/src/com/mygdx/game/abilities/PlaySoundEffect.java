package com.mygdx.game.abilities;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.UnitStats;

public class PlaySoundEffect extends AbilityEffect{

	Sound sound;
	float volume = 1f;
	public PlaySoundEffect(Sound sound, float volume) {
		this.sound=sound;
		this.volume=volume;
	}

	public float getVolume() {
		return volume;
	}

	public Sound getSound() {
	
		return sound;
	}

	
	
}
