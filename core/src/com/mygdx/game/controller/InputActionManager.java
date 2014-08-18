package com.mygdx.game.controller;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyRPG;
import com.mygdx.game.screens.GameScreen;

public class InputActionManager implements InputProcessor{

	public enum InputAction {
		LEFT, RIGHT, JUMP, FIRE, UP, DOWN
	}
	
	
	public InputActionManager()
	{
		
		
		
	}
	
	
	static Map<Integer, InputAction> keymap = new HashMap<Integer, InputAction>();
	static {
		keymap.put(Keys.LEFT, InputAction.LEFT);
		keymap.put(Keys.RIGHT, InputAction.RIGHT);
		keymap.put(Keys.UP, InputAction.UP);
		keymap.put(Keys.DOWN, InputAction.DOWN);
		keymap.put(Keys.SPACE, InputAction.JUMP);
		keymap.put(Keys.A, InputAction.FIRE);
	};
	
	static Map<InputAction, Boolean> actionstate = new HashMap<InputAction, Boolean>();
	static {
		actionstate.put(InputAction.LEFT, false);
		actionstate.put(InputAction.RIGHT, false);
		actionstate.put(InputAction.UP, false);
		actionstate.put(InputAction.DOWN, false);
		actionstate.put(InputAction.JUMP, false);
		actionstate.put(InputAction.FIRE, false);
	};
	
	
	public boolean getActionState(InputAction action)
	{
		return actionstate.get(action);
	}

	
	@Override
	public boolean keyDown(int keycode) {
		
		boolean success = false;
		if( keymap.get(keycode) != null  )
		{
			actionstate.put(keymap.get(keycode),true);//for checking from polling
			GameScreen.processInputAction(keymap.get(keycode),true);//for instantaneous reaction
		}
		
		
	return success;
					
	}

	@Override
	public boolean keyUp(int keycode) {
		
		boolean success = false;
		if( keymap.get(keycode) != null  )
		{
			 actionstate.put(keymap.get(keycode),false);//for checking from polling
			 GameScreen.processInputAction(keymap.get(keycode),false);//for instantaneous reaction
		}
		
		
	return success;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (!MyRPG.onMobile())
			return false;
		
		Vector2 touchVector = new Vector2(x,GameScreen.getHeight() - y);
		
		for(InputAction action : InputAction.values())
		{
			
		if( GameScreen.getGUIController().getControls().getHitBox(action).contains(touchVector)  )
		{			
			
			actionstate.put(action, true);
			GameScreen.processInputAction(action,true);
		}
		}
		
		/*if (x < GameScreen.getWidth() / 2 && y > GameScreen.getHeight() / 2) {
			//controller.leftPressed();
			keyDown(Keys.LEFT);
		}
		
		if (x > GameScreen.getWidth() / 2 && y > GameScreen.getHeight() / 2) {
			keyDown(Keys.RIGHT);
			//controller.rightPressed();
		}*/
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!MyRPG.onMobile())
			return false;
		
		
		releaseKeys();
				
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	/*@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}*/
	
	
	

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}


	public void releaseKeys() {
		
		actionstate.put(InputAction.LEFT, false);
		actionstate.put(InputAction.RIGHT, false);
		actionstate.put(InputAction.UP, false);
		actionstate.put(InputAction.DOWN, false);
		actionstate.put(InputAction.FIRE, false);
		actionstate.put(InputAction.JUMP, false);
	}
	
	
	
	
}
