package com.mygdx.game.AssetMGMT;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;


public class AssetCenter {

	static AssetManager manager;
	
	public static void init()
	
	{
	manager = new AssetManager();
	manager.load("sheets/basicsheet.png", Texture.class);
	
	
	manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
	manager.load("maps/untitled.tmx", TiledMap.class);
	manager.load("maps/grassbattle.tmx", TiledMap.class);
	
	
	manager.finishLoading();//lag until done
	}
	
	
	public static AssetManager getManager() {
	
		return manager;
	}
	
	
	
}
