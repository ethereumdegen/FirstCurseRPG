package com.mygdx.game.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mygdx.game.Unit;
import com.mygdx.game.UnitState;
import com.mygdx.game.entities.World;


public class WorldController {

	enum Keys {
		LEFT, RIGHT, JUMP, FIRE
	}
	
	private World 	world;
	
	
	
	Player player;
	
	
	static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.JUMP, false);
		keys.put(Keys.FIRE, false);
	};

	public WorldController(World world) {
		this.world = world;
		
	}

	// ** Key presses and touches **************** //
	
	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
	}
	
	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
	}
	
	public void jumpPressed() {
		keys.get(keys.put(Keys.JUMP, true));
	}
	
	public void firePressed() {
		keys.get(keys.put(Keys.FIRE, false));
	}
	
	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
	}
	
	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
	}
	
	public void jumpReleased() {
		keys.get(keys.put(Keys.JUMP, false));
	}
	
	public void fireReleased() {
		keys.get(keys.put(Keys.FIRE, false));
	}
	
	/** The main update method **/
	public void update(float delta) {
		processInput();
		
		for(Unit unit : world.getUnits())
		{
			unit.update(delta);
		}
		
	}



	/** Change Bob's state and parameters based on input controls **/
	private void processInput() {
		if (keys.get(Keys.LEFT)) {
			// left is pressed
			player.getFocus().getVelocity().x = -5;
			
		}
		if (keys.get(Keys.RIGHT)) {
			// left is pressed
			player.getFocus().getVelocity().x = 5;
		}
		// need to check if both or none direction are pressed, then Bob is idle
		if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) ||
				(!keys.get(Keys.LEFT) && !(keys.get(Keys.RIGHT)))) {
			player.getFocus().setState(UnitState.IDLE);
			// acceleration is 0 on the x
			player.getFocus().getAcceleration().x = 0;
			// horizontal speed is 0
			player.getFocus().getVelocity().x = 0;
		}
	}

}
