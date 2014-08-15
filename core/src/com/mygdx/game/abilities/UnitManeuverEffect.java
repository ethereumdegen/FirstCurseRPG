package com.mygdx.game.abilities;

import com.badlogic.gdx.math.Interpolation;
import com.mygdx.game.UnitStats;

public class UnitManeuverEffect extends AbilityEffect{

	
	UnitStats stat;
	UnitManeuverType type;
	Interpolation interpolation;
	
	public UnitManeuverEffect(UnitManeuverType type, Interpolation interpolation, EffectTargets targets) {
		this.type = type;
		this.targets=targets;
		this.interpolation=interpolation;
	}
	
	
	
	public enum UnitManeuverType
	{
		RUSH;

		public float getPlayTime() {
		
			return 1;
		}

		
	}

	public Interpolation getInterpolation() {
		
			return interpolation;
		}	
		

	public UnitManeuverType getType() {
		return type;
	}
	
	
	

}
