package com.mygdx.game.AssetMGMT;



public enum UnitType {

	HUMAN("human",UnitModel.HUMAN),
	EYEMONSTER("eyemonster",UnitModel.EYEMONSTER),
	
	
	
	;

	UnitType(String name,UnitModel model)
	{
		this.name=name;
		this.model=model;
		
	}	
	public String name = "";
	
	public  UnitModel model;

	public UnitModel getModel() {
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
	
	
	

}
