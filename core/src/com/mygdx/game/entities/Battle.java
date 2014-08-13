package com.mygdx.game.entities;


import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Unit;
import com.mygdx.game.AssetMGMT.AssetCenter;
import com.mygdx.game.AssetMGMT.MapRegion;
import com.mygdx.game.AssetMGMT.UnitModel;
import com.mygdx.game.AssetMGMT.UnitType;
import com.mygdx.game.audio.MusicController;
import com.mygdx.game.audio.MusicalEmotion;
import com.mygdx.game.controller.Player;
import com.mygdx.game.renderer.ScreenEffect;
import com.mygdx.game.renderer.TintScreenEffect;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.utility.TileCoordinate;



public class Battle {

	/** The blocks making up the world **/
	//List<TerrainTile> tiles = new ArrayList<TerrainTile>();
	
	//List<Unit> units = new ArrayList<Unit>();
	//List<MapRegion> regions = new ArrayList<MapRegion>();
	MapRegion unitPositions[][] = new MapRegion[2][3];
	
	Unit units[][] = new Unit[2][3];
	
	
	TiledMap map;

	public Battle() {
		
		map = AssetCenter.getManager().get("maps/grassbattle.tmx");
		
		loadRegions();
		
	}
	
	
	private void loadRegions()
	{
		
		for(MapLayer layer : map.getLayers())
		{
			if(layer.getName().equals("regions")){
				for(MapObject obj: layer.getObjects())
				{
					Rectangle rect = null;
					
					if(obj instanceof PolygonMapObject)
					{
						PolygonMapObject poly = (PolygonMapObject) obj;
											
						rect = poly.getPolygon().getBoundingRectangle();
					
					}
					
				if(obj instanceof RectangleMapObject){
					rect = ((RectangleMapObject) obj).getRectangle();
				}
				
				if(rect!=null)
				{
					int team = 0;
					int unit = 0;
					
					if(obj.getProperties().containsKey("team") && obj.getProperties().containsKey("unit"))
					{
					team = Integer.parseInt((String) obj.getProperties().get("team"));
					unit = Integer.parseInt((String) obj.getProperties().get("unit"));
					
					unitPositions[team][unit] = new MapRegion(obj.getName(),rect);
					}
					
				}
				
			
				
				}
			}
			
		}
		
	}
	

	public void initBattle() {
		
	GameScreen.getGUIRenderer().getScreenEffectManager().forceScreenEffect( new TintScreenEffect(Color.BLACK) );		
	GameScreen.getGUIRenderer().getScreenEffectManager().queueScreenEffect(new TintScreenEffect(Color.CLEAR,4));
		
		
				
		GameScreen.getMusicController().setMusicalEmotion(MusicalEmotion.BATTLE);
		
		units[0] = Player.getBattlingParty();
		
		
		
		
		for(int team = 0; team < 2;team++)
		{
			for(int unit=0;unit < 3;unit++)
			{
				
				if(getUnits()[team][unit] != null && getUnits()[team][unit].getSprite()!=null){
					
					getUnits()[team][unit].setBattleSpot(
							getUnitPositions()[team][unit].getCenter().cpy().scl(1/16f)    );
					
					
					}
				
				
			}
			
		}
	}
	
	
	
	
	public TiledMap getMap()
	{
		return map;
	}
	

	/*private Unit spawnUnit(UnitType type, TileCoordinate tileCoordinate) {
		
		return spawnUnit(type, tileCoordinate.getPos());
	}
		
	private Unit spawnUnit(UnitType type,Vector2 pos) {
		Unit unit = new Unit(type);
		unit.getPosition().set(pos);
		units.add(unit);
		System.out.println("SPAWNING ");
		return unit;
	}*/

	
	public Unit[][] getUnits() {
		
		return units;
	}


	public MapRegion[][] getUnitPositions() {
		
		return unitPositions;
	}


	public void addEnemy(Unit unit) {
		units[1][0] = unit;
		
	}


	

	
}