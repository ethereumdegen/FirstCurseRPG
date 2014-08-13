package com.mygdx.game.renderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GUI.DialogController;
import com.mygdx.game.controller.Player;
import com.mygdx.game.entities.GUI;

public class GUIRenderer {

	
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	
	private ScreenEffectManager screenEffectManager;
	
	GUI gui;
	
	public GUIRenderer(GUI gui)
	{
		
		spriteBatch = new SpriteBatch();
		screenEffectManager = new ScreenEffectManager();
		
		font = new BitmapFont();
        font.setColor(Color.RED);
        
        this.gui = gui;
	}
	
	
	public void render() {  
		screenEffectManager.render();
		
	//	spriteBatch.setColor(unitColor);
		spriteBatch.begin();
		spriteBatch.end();
		
		
		
		gui.getBattleInterfaceController().render();
		gui.getDialogController().render();
		
	}


	public void update(float delta) {
		screenEffectManager.update(delta);
		gui.getDialogController().update(delta);
		gui.getBattleInterfaceController().update(delta);
	}

	public ScreenEffectManager getScreenEffectManager() {
		return screenEffectManager;
	}
	
}
