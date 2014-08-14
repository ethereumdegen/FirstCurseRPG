package com.mygdx.game.abilities;
import com.mygdx.game.Unit;

public class AbilityExecutionInfo
	{
		
		
		Unit caster;
		Unit target;
		AbilityType type;
		
		public AbilityExecutionInfo(Unit caster, Unit target, AbilityType type)
		{
		this.caster=caster;	
		this.target=target;
		this.type=type;
		}
		
		
		
		public Unit getCaster() {
			return caster;
		}

		public Unit getTarget() {
			return target;
		}

		public AbilityType getType() {
			return type;
		}
		
	}