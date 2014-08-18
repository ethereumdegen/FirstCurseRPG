package com.mygdx.game.abilities;

public class Ability extends Executable{
		
	AbilityType type;
	
	public Ability(AbilityType type)
	{
		this.type=type;
	}
	
	public String getLabel() {
		
		return type.name();
	}

	public AbilityType getType() {
		return type;
		
	}
	
}
