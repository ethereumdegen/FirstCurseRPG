package com.mygdx.game.GUI;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.AssetMGMT.UtilitySprites;
import com.mygdx.game.GUI.battleinterface.Node2D;
import com.mygdx.game.controller.InputActionManager.InputAction;
import com.mygdx.game.renderer.GraphSprite;

public class OnScreenControls extends Node2D{
	
	
	GraphSprite pad;
	GraphSprite A;
	GraphSprite B;
	
	public OnScreenControls()
	{				
		pad = new GraphSprite(UtilitySprites.ControlPad.getTextureRegion());  
		A = new GraphSprite(UtilitySprites.A.getTextureRegion());  
		B = new GraphSprite(UtilitySprites.B.getTextureRegion());  
				
		A.setTranslation(530,90,0);
		B.setTranslation(450,20,0);
		
		this.attachChild(pad);
		this.attachChild(A);
		this.attachChild(B);
	}

	public Rectangle getHitBox(InputAction action) {
		
		Rectangle rect = pad.getSprite().getBoundingRectangle();
		
		
		switch(action){			
		case LEFT:	return new Rectangle(rect.x,rect.y+rect.height/3,rect.width/3,rect.height/3);		
		case RIGHT:	return new Rectangle(rect.x+rect.height*2/3,rect.y+rect.height/3,rect.width/3,rect.height/3); 	
		case UP:	return new Rectangle(rect.x+rect.height/3,rect.y+rect.height*2/3,rect.width/3,rect.height/3); 
		case DOWN:	return new Rectangle(rect.x+rect.height/3,rect.y,rect.width/3,rect.height/3);
		case JUMP:	return A.getSprite().getBoundingRectangle();
		case FIRE:	return B.getSprite().getBoundingRectangle();
			
		}
		
		
		
		return rect;
	}
	
}
