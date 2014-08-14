package com.mygdx.game.GUI.battleinterface;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.AssetMGMT.UtilitySprites;
import com.mygdx.game.controller.InputActionManager.InputAction;
import com.mygdx.game.renderer.GraphSprite;

public class OptionsScreen extends Node2D implements InputHandler{

	protected SimpleText optionLabels[] = new SimpleText[3];

	protected int selectionIndex = 0;
	
	protected GraphSprite arrow;
	
	Sound selectSound;
	
	int optionOffset = 0;
	
	boolean active = false;
	
	

	String[] options = new String[3];
	
	
	OptionsScreen()
	{			
		
			for(int y= 0; y < 3; y++)
			{
			optionLabels[y] = new SimpleText();
			optionLabels[y].setTranslation(120,90 - 25 * y,10);
			this.attachChild(optionLabels[y]);
			
					
			}
		
		
		selectSound = Gdx.audio.newSound(Gdx.files.internal("sounds/typing.wav"));
			 
						
		TextureRegion tex= UtilitySprites.SILVERARROW.getTextureRegion();
		arrow = new GraphSprite(tex);
				
		this.attachChild(arrow);
		
	}

		float arrowTimer = 0f;
	public void update(float millis) {
		
		
		
		for(int i=0;i<optionLabels.length;i++){
			if(options[i+optionOffset] != null){
				optionLabels[i].setText(options[i+optionOffset]);
			}else{
				optionLabels[i].setText("---");
			}
			
			//optionLabels[i].setActive(this.isActive());
			
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
			if(options[selectionIndex + optionOffset + 1] != null){
				selectionIndex++;
				selectSound.play();
			}
		}else{
			if(selectionIndex + optionOffset < (options.length-1)){
				optionOffset++;
				selectSound.play();
				
			}
		}
		
	}


	private void decrementSelectionIndex() {
		
		if(selectionIndex > 0){
			selectionIndex--;
			selectSound.play();
			
		}else{
			if( optionOffset > 0){
				optionOffset--;
				selectSound.play();
			}
		}
		
	}


	 
	

	protected void selectAction() {
		
		
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


	public int getSelectionIndex() {
		return selectionIndex;
	}



	public void setActive(boolean active) {
		this.active = active;
	}
}
