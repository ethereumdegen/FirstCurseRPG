package com.mygdx.game.story.actions;

import com.mygdx.game.AssetMGMT.MapRegion;
import com.mygdx.game.GUI.DialogInfo;

public class QueueDialogAction extends ActionType{


	public QueueDialogAction(Object val, Object mag) {
		this.dialoginfo=(DialogInfo)val;
		this.amount=(Integer)mag;
	}

	public QueueDialogAction(DialogInfo val) {
		this.dialoginfo=(DialogInfo)val;
	}

	private DialogInfo dialoginfo = null;
	private int amount = 1;
	
	@Override
	public void setValue(Object val){
		dialoginfo = (DialogInfo)  val;		
	}
	
	@Override
	public void setMagnitude(Object val){
		amount = (Integer) val;	
	}
	
	@Override
	public Object getValue(){
		return dialoginfo;
		
	}
	
	@Override
	public Object getMagnitude(){
		return amount;
		
	}
	
	
}
