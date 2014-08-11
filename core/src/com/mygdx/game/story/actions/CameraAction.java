package com.mygdx.game.story.actions;

import com.mygdx.game.camera.CameraCommand;

public class CameraAction extends ActionType{


	public CameraAction(Object val, Object mag) {
		this.command=(CameraCommand)val;
		this.amount=(Integer)mag;
	}



	public CameraAction(CameraCommand val) {
		this.command=(CameraCommand)val;
	}

	private CameraCommand command = null;
	private int amount = 1;
	
	@Override
	public void setValue(Object val){
		command = (CameraCommand)  val;		
	}
	
	@Override
	public void setMagnitude(Object val){
		amount = (Integer) val;	
	}
	
	@Override
	public Object getValue(){
		return command;
		
	}
	
	@Override
	public Object getMagnitude(){
		return amount;
		
	}
	
	
}
