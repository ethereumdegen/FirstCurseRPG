package com.mygdx.game.AssetMGMT;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MapRegion {
	Rectangle rect;
	Polygon poly;
	
	String name;
	Color tint = null;
	
	String teleportTo;
	
/*	public MapRegion(String name, Rectangle rect, Color color)
	{
		this.name = name;
		this.rect = rect;
		this.tint = color;
		
		
	}
	
	public MapRegion(String name, Polygon polygon, Color color) {
		this.name = name;
		this.poly = polygon;
		this.tint = color;
	}

	public MapRegion(String name, Rectangle rect) {
		this.name = name;
		this.rect = rect;
	}*/

	public MapRegion(MapObject obj) {
		
		this.name = obj.getName();
		
		if(obj instanceof PolygonMapObject)
		{
			PolygonMapObject poly = (PolygonMapObject) obj;
			
			this.poly = poly.getPolygon();
			
			
			
			//regions.
			//add(new MapRegion(obj.getName(),poly.getPolygon(),tint ));
		
		}
		
		if(obj instanceof RectangleMapObject){
		RectangleMapObject rect = (RectangleMapObject) obj;
		
		this.rect = rect.getRectangle();
		
		
		//regions.add(
		//	new MapRegion(obj.getName(),rect.getRectangle(),tint ));
		}
		
	
		if( obj.getProperties().containsKey("tint"))
			{
				tint = Color.valueOf((String) obj.getProperties().get("tint"));
			}
		
		if( obj.getProperties().containsKey("teleport"))
		{
			teleportTo = (String) obj.getProperties().get("teleport");
		}
	
	
	}

	public boolean encapsulatesPoint(Vector2 vec){
		if(rect!=null)
		{
			return rect.contains(vec);
		}
		if(poly!=null)
		{
			return poly.contains(vec.x,vec.y);
		}
		
		
		return false;
		
	}

	public String getName() {
		return name;
	}

	public Color getTint() {
		return tint;
	}

	public Vector2 getCenter() {
		if(rect!=null)
		{
			return rect.getCenter(new Vector2());
		}
		
		if(poly!=null)
		{
			return poly.getBoundingRectangle().getCenter(new Vector2());
		}
		return null;
	}

	

}
