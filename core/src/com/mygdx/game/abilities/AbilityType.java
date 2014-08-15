package com.mygdx.game.abilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.math.Interpolation;
import com.mygdx.game.UnitStats;
import com.mygdx.game.AssetMGMT.CommonSounds;
import com.mygdx.game.abilities.UnitManeuverEffect.UnitManeuverType;
import com.mygdx.game.entities.UnitModelAnimation;


public enum AbilityType {
	
	SLASH( new AbilityStatDefinition[]{
			new AbilityStatDefinition(AbilityStats.SPECIALCOST,10),	
			new AbilityStatDefinition(AbilityStats.COOLDOWN,5)			
			},
	
			new AbilityEffect[]{
							
				new UnitManeuverEffect(UnitManeuverType.RUSH, Interpolation.pow2In, EffectTargets.TARGETENEMY),
				
				new EditUnitStatEffect(UnitStats.HEALTH, -10, EffectTargets.TARGETENEMY).delay(0.9f),
				new UnitAnimationEffect(UnitModelAnimation.DAMAGED, EffectTargets.TARGETENEMY).delay(0.5f),
				new PlaySoundEffect(CommonSounds.DAMAGE.getSound(),0.5f).delay(0.5f)
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

	public int getStatValue(AbilityStats stat)
	{
		return statlist.get(stat);
	}
	
	public List<AbilityEffect> getEffects() {
		
		return effects;
	}
}
