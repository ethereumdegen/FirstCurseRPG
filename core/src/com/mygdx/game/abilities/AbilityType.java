package com.mygdx.game.abilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mygdx.game.UnitStats;
import com.mygdx.game.abilities.UnitManeuverEffect.UnitManeuverType;


public enum AbilityType {
	
	SLASH( new AbilityStatDefinition[]{
			new AbilityStatDefinition(AbilityStats.SPECIALCOST,10)			
			
			},
	
			new AbilityEffect[]{
				new EditUnitStatEffect(UnitStats.HEALTH, -10, EffectTargets.TARGETENEMY),			
				new UnitManeuverEffect(UnitManeuverType.RUSH, EffectTargets.TARGETENEMY)
			}
			
			
			)
			
				
	;
	
	HashMap<AbilityStats, Integer> statlist = new HashMap<AbilityStats, Integer>();
	List<AbilityEffect> effects = new ArrayList<AbilityEffect>();
	
	AbilityType(AbilityStatDefinition[] statdefinitions,AbilityEffect[] effects)
	{
		
		for(AbilityStatDefinition def : statdefinitions)
		{
			statlist.put(def.getAbilityStatType(), def.getValue());
		}
		
		for(AbilityEffect effect : effects)
		{
			this.effects.add(effect);
		}
		
		
	}

	public List<AbilityEffect> getEffects() {
		
		return effects;
	}
}
