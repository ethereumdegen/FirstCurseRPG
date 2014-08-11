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
import com.mygdx.game.utility.TileCoordinate;



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
		
		
		map = AssetCenter.getManager().get("maps/untitled.tmx");
		
		replaceMonsters();
		
	}
	
	
	/** Loop through every tile and do special things to them if they have special properties*/
	private void replaceMonsters() {
		
		for(MapLayer layer: map.getLayers())
		{
			TiledMapTileLayer tilelayer = (TiledMapTileLayer) layer;
			
				for(int x = 0 ; x < (Integer) map.getProperties().get("width"); x++){
					for(int y = 0 ; y < (Integer) map.getProperties().get("height"); y++){
						Cell cell = tilelayer.getCell(x,y);
							if(cell!=null && cell.getTile().getProperties().containsKey("unittype"))
							{
								System.out.println("replaced monster" + x +"."+ y);
								spawnUnit(new TileCoordinate(x,y));
								tilelayer.setCell(x, y, null);//delete the tile
								
								
							}
							
							if(cell!=null && cell.getTile().getProperties().containsKey("player"))
							{
								System.out.println("spawned player" + x +"."+ y);
								Player.setFocus(spawnUnit(new TileCoordinate(x,y)));
								tilelayer.setCell(x, y, null);//delete the tile
								
								
							}
						
						
					}
				}
				
			
			
		}
		
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
	

	private Unit spawnUnit(TileCoordinate tileCoordinate) {
		
		return spawnUnit(tileCoordinate.getPos());
	}

	
	private Unit spawnUnit(Vector2 pos) {
		Unit unit = new Unit(UnitModel.HUMAN);
		unit.getPosition().set(pos);
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