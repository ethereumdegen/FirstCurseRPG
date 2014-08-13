package com.mygdx.game.abilities;

import com.mygdx.game.UnitStats;

public class EditUnitStatEffect extends AbilityEffect{

	
	EffectTargets targets;
	UnitStats stat;
	int delta;
	
	public EditUnitStatEffect(UnitStats stat, int delta, EffectTargets targets) {
		this.targets = targets;
		this.stat=stat;
		this.delta=delta;
	}

}
