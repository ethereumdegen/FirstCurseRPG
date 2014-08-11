package com.mygdx.game.story.actions;

import com.mygdx.game.renderer.ScreenEffect;

public class ScreenEffectAction extends ActionType{


	public ScreenEffectAction(Object val, Object mag) {
		this.effect=(ScreenEffect)val;
		this.amount=(Integer)mag;
	}



	public ScreenEffectAction(ScreenEffect val) {
		this.effect=(ScreenEffect)val;
	}

	private ScreenEffect effect = null;
	private int amount = 1;
	
	@Override
	public void setValue(Object val){
		effect = (ScreenEffect)  val;		
	}
	
	@Override
	public void setMagnitude(Object val){
		amount = (Integer) val;	
	}
	
	@Override
	public Object getValue(){
		return effect;
		
	}
	
	@Override
	public Object getMagnitude(){
		return amount;
		
	}
	
	
}
