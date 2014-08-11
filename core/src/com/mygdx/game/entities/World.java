package com.mygdx.game.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Unit;
import com.mygdx.game.AssetMGMT.AssetCenter;
import com.mygdx.game.AssetMGMT.UnitModel;
import com.mygdx.game.controller.Player;



public class World {

	/** The blocks making up the world **/
	//List<TerrainTile> tiles = new ArrayList<TerrainTile>();
	
	List<Unit> units = new ArrayList<Unit>();
	
	TiledMap map;

	public World() {
		initWorld();
		createDemoWorld();
	}

	private void initWorld() {
		Player.setFocus(spawnUnit());
		
		map = AssetCenter.getManager().get("maps/untitled.tmx");
		
		
	}
	
	public boolean tileHasCollision(int x, int y) //the collision property is contained in the layer
	{
		for(MapLayer layer: map.getLayers())
		{
			if(layer.getProperties().get("collision")!=null && 
					((String) layer.getProperties().get("collision")).equals("true"))
			{
				TiledMapTileLayer tilelayer = (TiledMapTileLayer) layer;
				Cell cell = tilelayer.getCell(x,y);
			//	tilelayer.getProperties()
				
				if(cell != null)
				{
					return true;
				}
			}
			
			
		}
		
		
		return false;
	}
	
	
	public TiledMap getMap()
	{
		return map;
	}
	

	private Unit spawnUnit() {
		Unit unit = new Unit(UnitModel.HUMAN);
		units.add(unit);
		System.out.println("SPAWNING ");
		return unit;
	}

	private void createDemoWorld() {
		
		/*tiles.add(new TerrainTile())
		
		for (int i = 0; i < 10; i++) {
			blocks.add(new Block(new Vector2(i, 0)));
			blocks.add(new Block(new Vector2(i, 6)));
			if (i > 2)
				blocks.add(new Block(new Vector2(i, 1)));
		}
		blocks.add(new Block(new Vector2(9, 2)));
		blocks.add(new Block(new Vector2(9, 3)));
		blocks.add(new Block(new Vector2(9, 4)));
		blocks.add(new Block(new Vector2(9, 5)));

		blocks.add(new Block(new Vector2(6, 3)));
		blocks.add(new Block(new Vector2(6, 4)));
		blocks.add(new Block(new Vector2(6, 5)));*/
	}

	public List<Unit> getUnits() {
		
		return units;
	}
}