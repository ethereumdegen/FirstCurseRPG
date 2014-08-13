package com.mygdx.game.abilities;

import java.util.HashMap;

	
	
	

public class AbilityStatDefinition
{
	
	private AbilityStats stat;
	private int value;

	AbilityStatDefinition(AbilityStats stat, int value)
	{
	this.stat=stat;
	this.value=value;
		
	}

	public AbilityStats getAbilityStatType() {
		
		return stat;
	}

	public Integer getValue() {
		
		return value;
	}
	
}



