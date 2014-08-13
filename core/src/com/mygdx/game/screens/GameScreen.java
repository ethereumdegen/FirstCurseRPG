package com.mygdx.game.screens;



import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.Unit;
import com.mygdx.game.audio.MusicController;
import com.mygdx.game.controller.GUIController;
import com.mygdx.game.controller.WorldController;
import com.mygdx.game.entities.Battle;
import com.mygdx.game.entities.GUI;
import com.mygdx.game.entities.World;
import com.mygdx.game.renderer.BattleRenderer;
import com.mygdx.game.renderer.GUIRenderer;
import com.mygdx.game.renderer.WorldRenderer;
import com.mygdx.game.story.StoryController;

public class GameScreen implements Screen, InputProcessor {

	private static World 			world;
	private static Battle			battle;
	private static GUI 				gui;
	private static BattleRenderer   battleRenderer;
	private static WorldRenderer 	worldRenderer;
	private static WorldController	controller;
	private static GUIRenderer 		guirenderer;
	private static GUIController 	guicontroller;
	private static StoryController 	storycontroller;
	
	private int width, height;
	
	static GameState currentState = GameState.OVERWORLD;
	
	static MusicController musicController;
	
	@Override
	public void show() {
		world = new World();
		battle = new Battle();
		gui = new GUI();
		
		musicController = new MusicController();
		
		
		worldRenderer = new WorldRenderer(world, true);
		battleRenderer = new BattleRenderer(battle, true);
		
		guirenderer = new GUIRenderer(gui);
		controller = new WorldController(world);
		guicontroller = new GUIController(gui);
		storycontroller = new StoryController();
		
		
		Gdx.input.setInputProcessor(this);
		
		
		
		
	}

	@Override
	public void render(float delta) { 
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		

		controller.update(delta);
		guicontroller.update(delta);
		storycontroller.update(delta);
		
		guirenderer.update(delta);
		
		if(inOverworld())
		{
		worldRenderer.update(delta);		
		worldRenderer.render();
		}
		else
		{
		battleRenderer.update(delta);		
		battleRenderer.render();
		
		}
		
		
		
		Gdx.gl.glEnable(GL20.GL_BLEND); //allows the GUI to be transparent over the world
	    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
		guirenderer.render();
	
		 Gdx.gl.glDisable(GL20.GL_BLEND);
	}
	
	private boolean inOverworld() {
		
		return currentState == GameState.OVERWORLD;
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

	
	
	
	
	@Override
	public boolean keyDown(int keycode) {	
		
		if(guicontroller.keyDown(keycode)){
			
			return true;
		}
		
		return controller.keyDown(keycode);
		
		
	}

	@Override
	public boolean keyUp(int keycode) {
		
		if(guicontroller.keyUp(keycode)){
			
			return true;
		}

		return controller.keyUp(keycode);
		
		
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (!Gdx.app.getType().equals(ApplicationType.Android))
			return false;
		if (x < width / 2 && y > height / 2) {
			//controller.leftPressed();
			controller.keyDown(Keys.LEFT);
		}
		if (x > width / 2 && y > height / 2) {
			controller.keyDown(Keys.RIGHT);
			//controller.rightPressed();
		}
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!Gdx.app.getType().equals(ApplicationType.Android))
			return false;
		if (x < width / 2 && y > height / 2) {
			controller.keyUp(Keys.LEFT);
		}
		if (x > width / 2 && y > height / 2) {
			controller.keyUp(Keys.RIGHT);
		}
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
	
	
	public static World getWorld()
	{
		return world;
	}
	public static WorldRenderer getWorldRenderer()
	{
		return worldRenderer;
	}

	public static GUIRenderer getGUIRenderer() {
		return guirenderer;
	}

	public static GUI getGUI() {
		
		return gui;
	}

	
	public static void setState(GameState state) {
		
		if(currentState!=state)
		{
			
			
			if(state == GameState.BATTLE)
			{
				System.out.println("starting battle");
				battle.initBattle();
			}else
			{
				
				
				
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

	public static Battle getBattle() {
		return battle;
	}

}