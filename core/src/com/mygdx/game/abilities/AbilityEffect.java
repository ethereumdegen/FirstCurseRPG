package com.mygdx.game.abilities;

public class AbilityEffect {

	float delay = 0f;
	EffectTargets targets;

	public EffectTargets getTargets() {
		return targets;
	}
	
	
	public AbilityEffect delay(float delay) {
		 this.delay=delay;
		 return this;
	}


	public float getDelay() {
		return delay;
	}
	
}
