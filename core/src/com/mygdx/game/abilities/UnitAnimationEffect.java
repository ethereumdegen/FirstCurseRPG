package com.mygdx.game.abilities;

import com.mygdx.game.entities.UnitModelAnimation;

public class UnitAnimationEffect extends AbilityEffect{

	
	UnitModelAnimation type;
	
	public UnitAnimationEffect(UnitModelAnimation type, EffectTargets targets) {
		this.type = type;
		this.targets=targets;
	}

	public UnitModelAnimation getType() {
		return type;
	}
	
	
	
	
	

}
