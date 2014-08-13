package com.mygdx.game.GUI.battleinterface;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.AssetMGMT.UtilitySprites;
import com.mygdx.game.controller.InputActionManager.InputAction;
import com.mygdx.game.renderer.GraphSprite;

public class UnitActionsScreen extends Node2D implements InputHandler{

	private SimpleText options[][] = new SimpleText[2][2];

	int selectionIndex = 0;
	
	GraphSprite arrow;
	
	UnitActionsScreen()
	{			
		for(int x = 0; x < 2; x++)
		{
			for(int y= 0; y < 2; y++)
			{
			options[x][y] = new SimpleText();
			options[x][y].setTranslation(40 + 80 * x,90 - 30 * y,10);
			this.attachChild(options[x][y]);
			
					
			}
		}
		
		options[0][0].setText("Attack");
		options[0][1].setText("Powers");
		options[1][0].setText("Items");
		options[1][1].setText("Run");
		
		
		TextureRegion tex= UtilitySprites.SELECTIONSQUARE.getTextureRegion();
		arrow = new GraphSprite(tex);
		
		
		this.attachChild(arrow);
		
	}

		float arrowTimer = 0f;
	public void update(float millis) {
		if(arrowTimer < Math.PI*2)
		{
			arrowTimer+=millis * 10;
		}else{
			arrowTimer = 0;
		}		
		
		arrow.setTranslation(15 + 80*(selectionIndex%2) - (float)Math.cos(arrowTimer)*3,72 - 30*(selectionIndex/2), 0);
		
	}
	
	

	@Override
	public boolean processInputAction(InputAction action, boolean asserted) {
		
		if(asserted)
		{
			switch(action)
			{
			case DOWN: selectionIndex+=2;
				break;
			case FIRE:
				break;
			case JUMP: selectAction();
				break;
			case LEFT: selectionIndex-=1;
				break;
			case RIGHT: selectionIndex+=1;
				break;
			case UP:   selectionIndex-=2;
				break;
			default:
				break;						
			}			
			
			selectionIndex = selectionIndex % 4;
			if (selectionIndex<0) selectionIndex += 4;
						
			
		}
		
		
		
		return false;
	}



	private void selectAction() {
		System.out.println("doing action " + selectionIndex );
		
	}

	
	
	
}
