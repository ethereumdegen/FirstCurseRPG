package com.mygdx.game.story.conditions;

import com.mygdx.game.AssetMGMT.MapRegion;

public class PlayerStatCond extends ConditionType{


	public PlayerStatCond(Object val, Object mag) {
		this.region=(MapRegion)val;
		this.amount=(Integer)mag;
	}

	private MapRegion region = null;
	private int amount = 1;
	
	@Override
	public void setValue(Object val){
		region = (MapRegion)  val;		
	}
	
	@Override
	public void setMagnitude(Object val){
		amount = (Integer) val;	
	}
	
	@Override
	public Object getValue(){
		return region;
		
	}
	
	@Override
	public Object getMagnitude(){
		return amount;
		
	}
	
	
}
