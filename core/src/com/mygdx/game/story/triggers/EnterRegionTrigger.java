package com.mygdx.game.story.triggers;

public class EnterRegionTrigger extends TriggerType{

	public EnterRegionTrigger(String val) {
		this.regionName=(String)val;
	}
	
	public EnterRegionTrigger(String val, Integer mag) {
		this.regionName=(String)val;
		this.amount=(Integer)mag;
	}

	private String regionName = null;
	private int amount = 1;
	
	@Override
	public void setValue(Object val){
		regionName = (String)  val;		
	}
	
	@Override
	public void setMagnitude(Object val){
		amount = (Integer) val;	
	}
	
	@Override
	public Object getValue(){
		return regionName;
		
	}
	
	@Override
	public Object getMagnitude(){
		return amount;
		
	}
	
	
}
