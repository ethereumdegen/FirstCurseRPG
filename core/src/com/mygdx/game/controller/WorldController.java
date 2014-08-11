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
import com.mygdx.game.entities.World;
import com.mygdx.game.screens.GameScreen;


public class WorldController {

	enum Action {
		LEFT, RIGHT, JUMP, FIRE, UP, DOWN
	}
	
	private World 	world;
	
	
	
	Player player;
	
	
	static Map<Integer, Action> keymap = new HashMap<Integer, Action>();
	static {
		keymap.put(Keys.LEFT, Action.LEFT);
		keymap.put(Keys.RIGHT, Action.RIGHT);
		keymap.put(Keys.UP, Action.UP);
		keymap.put(Keys.DOWN, Action.DOWN);
		keymap.put(Keys.SPACE, Action.JUMP);
		keymap.put(Keys.A, Action.FIRE);
	};
	
	static Map<Action, Boolean> actionstate = new HashMap<Action, Boolean>();
	static {
		actionstate.put(Action.LEFT, false);
		actionstate.put(Action.RIGHT, false);
		actionstate.put(Action.UP, false);
		actionstate.put(Action.DOWN, false);
		actionstate.put(Action.JUMP, false);
		actionstate.put(Action.FIRE, false);
	};

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
		
		checkRegion();
				
		
	}


	
	private void checkRegion() {
		if(Player.getFocus() != null)
		{
			Player.setRegion(null);
			
			for(MapRegion region: GameScreen.getWorld().getRegions())
			{
				if(region.encapsulatesPoint(Player.getFocus().getPosition().cpy().scl(16)))
				{
					Player.setRegion(region);					
				}	
			}
			
			
		}
		
	}



	/** Change Bob's state and parameters based on input controls **/
	private void processActions() {
		
		if(Player.getFocus() != null ){
			Player.getFocus().setState(UnitState.IDLE);
			// acceleration is 0 on the x
			Player.getFocus().getAcceleration().setZero();
			// horizontal speed is 0
			Player.getFocus().getVelocity().setZero();
			
			
		if (actionstate.get(Action.LEFT)) {
			// left is pressed
			Player.getFocus().getVelocity().x = -1;
			
		} if (actionstate.get(Action.RIGHT)) {
			// left is pressed
			Player.getFocus().getVelocity().x = 1;
		} if (actionstate.get(Action.UP)) {
			// left is pressed
			Player.getFocus().getVelocity().y = 1;
			
		} if (actionstate.get(Action.DOWN)) {
			// left is pressed
			Player.getFocus().getVelocity().y = -1;
			
		}
			
		
		
		}
		
	}

	public boolean keyDown(int keycode) {
		if( keymap.get(keycode) != null  )
			return actionstate.put(keymap.get(keycode),true);
		
		return false;
	}

	public boolean keyUp(int keycode) {
		if( keymap.get(keycode) != null  )
			return actionstate.put(keymap.get(keycode),false);
			
		return false;
	}

}
