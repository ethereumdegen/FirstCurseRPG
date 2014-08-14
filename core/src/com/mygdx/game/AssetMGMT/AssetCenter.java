package com.mygdx.game.AssetMGMT;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;


public class AssetCenter {

	static AssetManager manager;
	
	public static void init()
	
	{
		
		
	manager = new AssetManager();
	manager.load("sheets/basicsheet.png", Texture.class);
	manager.load("sheets/uipackrpg.png", Texture.class);
	manager.load("sheets/controls.png", Texture.class);
	//manager.load("sheets/controls.png", Atlas.class);
	
	manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
	manager.load("maps/untitled.tmx", TiledMap.class);
	manager.load("maps/grassbattle.tmx", TiledMap.class);
	
	loadSounds();
	
	
	manager.finishLoading();//lag until done
	}
	
	
	private static void loadSounds() {
		for(CommonSounds sound : CommonSounds.values())
		{
			manager.load(sound.getPath(), Sound.class);
			
			
		}
		
	}


	public static AssetManager getManager() {
	
		return manager;
	}
	
	
	
}
