package com.mygdx.game.screens;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.GUI.OnScreenControls;
import com.mygdx.game.GUI.battleinterface.Node2D;
import com.mygdx.game.audio.MusicController;
import com.mygdx.game.audio.SoundManager;
import com.mygdx.game.controller.InputActionManager;
import com.mygdx.game.controller.InputActionManager.InputAction;
import com.mygdx.game.controller.WorldController;
import com.mygdx.game.entities.BattleController;
import com.mygdx.game.entities.World;
import com.mygdx.game.renderer.BattleRenderer;
import com.mygdx.game.renderer.GUIController;
import com.mygdx.game.renderer.WorldRenderer;
import com.mygdx.game.story.StoryController;

public class GameScreen implements Screen {

	private static World 			world;
	private static BattleController	battleController;
	//private static BattleRenderer   battleRenderer;
	private static WorldRenderer 	worldRenderer;
	private static WorldController	controller;
	
	private static GUIController 	guicontroller;
	private static StoryController 	storycontroller;
	
	
	
	private static InputActionManager inputActionManager;
	
	private static int width;
	private static int height;
	
	static GameState currentState = GameState.OVERWORLD;
	
	static MusicController musicController;
	
	static SoundManager soundManager = new SoundManager();
	
	@Override
	public void show() {
		world = new World();
		battleController = new BattleController();
		
		inputActionManager = new InputActionManager();
		musicController = new MusicController();
		
		
		worldRenderer = new WorldRenderer(world, true);
		//battleRenderer = new BattleRenderer(battleController, true);
		
	
		controller = new WorldController(world);
		guicontroller = new GUIController();
		storycontroller = new StoryController();
		
		
		Gdx.input.setInputProcessor(inputActionManager);
		
		
		
		
	}

	@Override
	public void render(float delta) { 
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		soundManager.update(delta);
		
		guicontroller.update(delta);
		storycontroller.update(delta);
		battleController.update(delta);
		
		
		
		worldRenderer.update(delta);		
		worldRenderer.render();
		controller.update(delta);
		world.update(delta);
		
		
		checkBattleState();
		
		Gdx.gl.glEnable(GL20.GL_BLEND); //allows the GUI to be transparent over the world
	    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	    guicontroller.render();
			
		 Gdx.gl.glDisable(GL20.GL_BLEND);
		 
		 
	}
	
	private void checkBattleState() {
		
			if(getState() == GameState.BATTLE)
			{			
				if(battleController.battleIsOver())
				{
					setState(GameState.OVERWORLD);
				}
			
			}
		
		
	}


	@Override
	public void resize(int width, int height) {
		worldRenderer.setSize(width, height);
		this.width = width;
		this.height = height;
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}

	// * InputProcessor methods ***************************//

	

	
	
	
	public static World getWorld()
	{
		return world;
	}
	public static WorldRenderer getWorldRenderer()
	{
		return worldRenderer;
	}



	
	public static void setState(GameState state) {
		
		if(currentState!=state)
		{
			
			
			if(state == GameState.BATTLE)
			{
				System.out.println("starting battle");
				battleController.initBattle();
			}else
			{
				System.out.println("ending battle");
				battleController.endBattle();
			}
			
			currentState = state;
			
		}
		
		
	}

	public static MusicController getMusicController() {
		return musicController;
	}

	public static GameState getState() {
		return currentState;
	}

	public static BattleController getBattle() {
		return battleController;
	}

	public static InputActionManager getInputActionManager() {
		
		return inputActionManager;
	}

	public static boolean processInputAction(InputAction action, boolean asserted) {
		
	
		
		if(guicontroller.processInputAction(action,asserted) )
		{
			return true;
		}
		
		
		return controller.processInputAction(action,asserted);
		
		
	}

	public static int getWidth() {
		return width;
	}
	
	public static int getHeight() {
		return height;
	}

	public static GUIController getGUIController() {
	
		return guicontroller;
	}

	
	public static SoundManager getSoundManager() {
		
		return soundManager;
	}

}