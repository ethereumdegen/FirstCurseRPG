package com.mygdx.game.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.mygdx.game.Unit;
import com.mygdx.game.UnitState;
import com.mygdx.game.AssetMGMT.MapRegion;
import com.mygdx.game.GUI.battleinterface.InputHandler;
import com.mygdx.game.controller.InputActionManager.InputAction;
import com.mygdx.game.entities.World;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.GameState;


public class WorldController implements InputHandler {

	
	
	private World 	world;
	
	
	Player player;
	


	public WorldController(World world) {
		this.world = world;
		
	//	GameScreen.getWorldRenderer().getCam().lookAt(Player.getFocus().getPosition().x, Player.getFocus().getPosition().y, 0);
		
	}

	
	
	/** The main update method **/
	public void update(float delta) {
		processActions();
		
		for(Unit unit : world.getUnits())
		{
			unit.update(delta);
		}
		
		updatePlayerRegion();
				
		checkEnemyDistanceFromPlayer();
	}

		
	
	private void checkEnemyDistanceFromPlayer() {
		if(Player.getFocus() != null)
		{
			for(Unit unit : world.getUnits())
			{
				
				if(unit.isAlive() && unit!=Player.getFocus() && Player.getFocus().getWorldModel().getPosition().dst(unit.getWorldModel().getPosition()) < 1)
				{
					
					
					GameScreen.getBattle().addEnemy(unit);
					GameScreen.setState(GameState.BATTLE);
				}
				
			}
		}
	}



	private void updatePlayerRegion() {
		if(Player.getFocus() != null)
		{
			Player.setRegion(null);
			
			for(MapRegion region: GameScreen.getWorld().getRegions())
			{
				if(region.encapsulatesPoint(Player.getFocus().getWorldModel().getPosition().cpy().scl(16)))
				{
					Player.setRegion(region);					
				}	
			}
			
			
		}
		
	}



	/** Change Bob's state and parameters based on input controls **/
	private void processActions() {
		
		if(Player.getFocus() != null  ){
			Player.getFocus().setState(UnitState.IDLE);
			// acceleration is 0 on the x
			Player.getFocus().getWorldModel().getAcceleration().setZero();
			// horizontal speed is 0
			Player.getFocus().getWorldModel().getVelocity().setZero();
			
			if(!Player.isCinematicMode()){
			
		if (GameScreen.getInputActionManager().getActionState(InputAction.LEFT)) {
			// left is pressed
			
			Player.getFocus().getWorldModel().getVelocity().x = -1;
			
		} 
		if (GameScreen.getInputActionManager().getActionState(InputAction.RIGHT)) {
			
			Player.getFocus().getWorldModel().getVelocity().x = 1;
		} 
		if (GameScreen.getInputActionManager().getActionState(InputAction.UP)) {
		
			Player.getFocus().getWorldModel().getVelocity().y = 1;
			
		} 
		if (GameScreen.getInputActionManager().getActionState(InputAction.DOWN)) {
			
			Player.getFocus().getWorldModel().getVelocity().y = -1;
			
		}
			
			}
		}
		
	}

	

	@Override
	public boolean processInputAction(InputAction action, boolean asserted) {
		
		return false;
	}

}
