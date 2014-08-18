package com.mygdx.game.camera;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.AssetMGMT.MapRegion;
import com.mygdx.game.controller.Player;
import com.mygdx.game.screens.GameScreen;

public class BattleCameraManager {

	
	List<CameraCommand> queuedCommands = new ArrayList<CameraCommand>();
	
	
	OrthographicCamera cam;
	
	public BattleCameraManager(OrthographicCamera cam) {
		this.cam=cam;
		
	}

	
	CameraCommand currentCommand = null;
	float commandTimer = 0f;
	
	public void update(float delta)
	{
		
		
		if(currentCommand != null)
		{
			updateCommand(delta);
			
			
		}
		else
		{
			//lockToPlayer(delta);
			
			
			if(!queuedCommands.isEmpty())
			{
				
				setCurrentCommand(queuedCommands.remove(0));
					
				
				
			}
		}
		
	}
	

	private void lockToPlayer(float delta) {
		
		if(Player.getFocus() != null){
			//this.cam.position.set(Player.getFocus().getPosition().x - Player.getFocus().getDimensions().x/(2*ppuX),  Player.getFocus().getPosition().y - Player.getFocus().getDimensions().y/(2*ppuY), 0);	
			
			//constrain cam about player
			
			Vector2 camvec = new Vector2(cam.position.x,cam.position.y);
			
		
				Vector2 diff = Player.getFocus().getWorldModel().getPosition().cpy().sub(camvec);
					if(diff.len() > 2){
				
				camvec = camvec.add(diff.nor().scl(0.05f * diff.len()));	
				
				cam.position.x = camvec.x;
				cam.position.y = camvec.y;
			}
			
			
			
		}
		
	}


	private void setCurrentCommand(CameraCommand command) {
		currentCommand = command;
		commandTimer = 0;
		
		previousPos = cam.position.cpy();
		
		
		
		if(currentCommand instanceof PanCameraCommand)
		{
			
			
			MapRegion region = GameScreen.getWorld().getRegionByName(((PanCameraCommand) currentCommand).focusRegion);
			
			if(region != null){
				Vector2 regionCenter = region.getCenter();
			
		   nextPos.x = regionCenter.x/16;
		   nextPos.y = regionCenter.y/16;
		   
		}
		
		else
		{
			System.err.println("Could not find region for camera manager pan");
		}
		}
		
	}

	Vector3 previousPos = new Vector3();
	Vector3 nextPos = new Vector3();
	
	private void updateCommand(float delta) {
		if(commandTimer <= currentCommand.getTime()){
			
			commandTimer+=delta;
		
			if(currentCommand instanceof PanCameraCommand)
			{
								
				
				float lerp = commandTimer / currentCommand.getTime();
				
			
				cam.position.set(previousPos.cpy().interpolate(nextPos, lerp, ((PanCameraCommand )currentCommand).getInterpolationMethod()));
				
				
				
				
			
			}
		
		}
		else
		{
			//done with the command, delete it
			commandTimer=0f;
			currentCommand = null; 
			
			
		}
		
	}

	public void queueCameraCommand(CameraCommand command) {
		queuedCommands.add(command);		
		
	}
	
	
}
