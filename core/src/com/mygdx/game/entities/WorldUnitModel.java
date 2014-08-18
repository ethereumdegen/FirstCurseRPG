package com.mygdx.game.entities;

import java.awt.event.ActionListener;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.AssetMGMT.CommonSounds;
import com.mygdx.game.controller.Player;
import com.mygdx.game.renderer.GraphSprite;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.GameState;
import com.mygdx.game.utility.TileCoordinate;

public class WorldUnitModel extends UnitModel{

	
	
	public WorldUnitModel(TextureRegion tex) {
		super(tex);
		
		
		
	}
	
	
	
	public void draw(Color def, Matrix4 combined) {
		defaultTint = def;
		
	
		spriteBatch.setProjectionMatrix(combined);
		
		if(isVisible())
		{
		super.render();
		}
		
		
	}

	
	

	public void update(float delta) {
		super.update(delta);
		
		
		
			velocity.add(acceleration);
			
			//only allow position to change in a direction if that way is unblocked!
			//set up the new possible X arm
			Vector2 newposX = position.cpy().add(velocity.nor().scl(movespeed));
			newposX.y = position.y;
			
			//set up the new possible Y arm
			Vector2 newposY = position.cpy().add(velocity.nor().scl(movespeed));
			newposY.x = position.x;
			
			if(GameScreen.getWorld().tileHasCollision(new TileCoordinate(newposX.cpy().add(0.5f,0.5f) ) ) == false)
			{
				position.x = newposX.x;
				
			}
			
			if(GameScreen.getWorld().tileHasCollision(new TileCoordinate(newposY.cpy().add(0.5f,0.5f) ) ) == false)
			{
				position.y = newposY.y;
				
			}
			
			if(!velocity.isZero()){
				
				movementcounter+= delta;
				
				rotationangle = (float) Math.cos(movementcounter*10+Math.random())*10;
				
				if(stepsoundcounter < movementcounter * 3)
				{
					stepsoundcounter++;
					float stepVol = (4000 - (float) Math.pow( position.dst(Player.getFocus().getWorldModel().getPosition()),3)) / 40000f;		
					
					
					
					CommonSounds.WALK.play(stepVol);
				}
			
			}else{
				movementcounter = 0f;
				rotationangle = 0f;
				stepsoundcounter = 0;
			}
			
			
			setPosition(position.x, position.y);
			getSprite().setRotation(rotationangle);
		
		
		
	}

	float roamTimerGoal = 1f;
	float roamTimer = 0f;
	public void updateAIRoaming(float delta) {
		roamTimer+=delta;
		
		if(roamTimer > roamTimerGoal)
		{
			roamTimer=0f;
			
			
			movespeed = 0.01f;
			if(Math.random() < 0.1f){
				setVelocity((float)(Math.random()-0.5f),(float)(Math.random()-0.5f));
			}
			else
			{
				setVelocity(0,0);
			}
			
		}
		
	}
	
	

}
