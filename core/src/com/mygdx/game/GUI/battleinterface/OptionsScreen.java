package com.mygdx.game.GUI.battleinterface;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.AssetMGMT.UtilitySprites;
import com.mygdx.game.controller.InputActionManager.InputAction;
import com.mygdx.game.renderer.GraphSprite;

public class OptionsScreen extends Node2D implements InputHandler{

	protected SimpleText optionLabels[] = new SimpleText[3];

	protected int selectionIndex = 0;
	
	protected GraphSprite arrow;
	
	
	int optionOffset = 0;
	
	boolean active = false;
	
	

	String[] options = new String[]{"---","---","---"};
	
	
	OptionsScreen()
	{			
		
			for(int y= 0; y < 3; y++)
			{
			optionLabels[y] = new SimpleText();
			optionLabels[y].setTranslation(120,90 - 25 * y,10);
			this.attachChild(optionLabels[y]);
			
					
			}
		
		
						
		TextureRegion tex= UtilitySprites.SELECTIONSQUARE.getTextureRegion();
		arrow = new GraphSprite(tex);
				
		this.attachChild(arrow);
		
	}

		float arrowTimer = 0f;
	public void update(float millis) {
		
		
		
		for(int i=0;i<optionLabels.length;i++){
			optionLabels[i].setText(options[i+optionOffset]);
		}
		
		if(isActive())
		{
		
			
		if(arrowTimer < Math.PI*2)
		{
			arrowTimer+=millis * 10;
		}else{
			arrowTimer = 0;
		}		
		
		arrow.setTranslation(80 - (float)Math.cos(arrowTimer)*3,72 - 25*(selectionIndex), 0);
		
		
		}
		
		arrow.setActive(this.isActive());
		
	}
	
	
	

	@Override
	public boolean processInputAction(InputAction action, boolean asserted) {
		
		if(isActive()){
			
		if(asserted)
		{
			switch(action)
			{
			case DOWN: incrementSelectionIndex();
				break;
			case FIRE:
				break;
			case JUMP: selectAction();
				break;
			case LEFT: 
				break;
			case RIGHT: 
				break;
			case UP:   decrementSelectionIndex();
				break;
			default:
				break;						
			}			
			
			
					
			
		}
		}
		
		
		return false;
	}

	
	private void incrementSelectionIndex() {
		
		
		if(selectionIndex < 2){
			selectionIndex++;
		}else{
			if(selectionIndex + optionOffset < (options.length-1)){
				optionOffset++;
			}
		}
		
	}


	private void decrementSelectionIndex() {
		
		if(selectionIndex > 0){
			selectionIndex--;
		}else{
			if( optionOffset > 0){
				optionOffset--;
			}
		}
		
	}


	 
	

	protected void selectAction() {
		System.out.println("doing action " + selectionIndex );
		
	}

	List<BattleOptionListener> optionListeners = new ArrayList<BattleOptionListener>();

	public List<BattleOptionListener> getOptionListeners() {
		return optionListeners;
	}

	public void registerListener(BattleOptionListener optionListener) {
		optionListeners.add(optionListener);
	}
	
	protected void broadcastBattleOption(BattleOption option)
	{
		for(BattleOptionListener  listener : optionListeners)
		{
			listener.assertOption(option);
		}
	}
	
	public boolean isActive() {
		return active;
	}




	public void setActive(boolean active) {
		this.active = active;
	}
}
