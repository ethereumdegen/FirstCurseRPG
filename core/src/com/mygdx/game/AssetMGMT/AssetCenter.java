package com.mygdx.game.AssetMGMT;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;


public class AssetCenter {

	static AssetManager manager;
	
	public static void init()
	
	{
	manager = new AssetManager();
	manager.load("sheets/basicsheet.png", Texture.class);
	
	manager.finishLoading();//lag until done
	}
	
	
	public static AssetManager getManager() {
	
		return manager;
	}
	
	
	
}
