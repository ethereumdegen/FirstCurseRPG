package com.mygdx.game.abilities;

import com.mygdx.game.UnitStats;

public class UnitManeuverEffect extends AbilityEffect{

	
	EffectTargets targets;
	UnitStats stat;
	UnitManeuverType type;
	
	public UnitManeuverEffect(UnitManeuverType type, EffectTargets targets) {
		this.type = type;
		this.targets=targets;
	}
	
	
	
	public enum UnitManeuverType
	{
		RUSH	
		
	}
	
	
	

}
