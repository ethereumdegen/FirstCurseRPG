package com.mygdx.game.AssetMGMT;

import com.mygdx.game.UnitStats;
import com.mygdx.game.utility.UnitStatDefinition;



public enum UnitType {

	HUMAN("human",UnitModelType.HUMAN, new UnitStatDefinition[]{
			new UnitStatDefinition(UnitStats.HEALTH, 60)
			
	}),
	EYEMONSTER("eyemonster",UnitModelType.EYEMONSTER, new UnitStatDefinition[]{
			new UnitStatDefinition(UnitStats.HEALTH, 60)
			
	}),
	FIELDMOUSE("fieldmouse",UnitModelType.MOUSE, new UnitStatDefinition[]{
			new UnitStatDefinition(UnitStats.HEALTH, 5)
			
	}),
	
	
	;

	UnitStatDefinition[] basestats;
	
	UnitType(String name,UnitModelType model,UnitStatDefinition[] basestats)
	{
		this.name=name;
		this.model=model;
		this.basestats=basestats;
	}	
	public String name = "";
	
	public  UnitModelType model;

	public UnitModelType getModelType() {
		return model;
	}

	public static UnitType getFromString(String string) {
		for(UnitType type : UnitType.values()){
			if(type.name.equals(string))
			{
				return type;
			}
			
		}
		return null;
	}

	public String getName() {
		
		return name;
	}

	public UnitStatDefinition[] getBaseStats() {
		
		return basestats;
	}
	
	
	

}
