package com.mygdx.game.AssetMGMT;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MapRegion {
	Rectangle rect;
	
	String name;
	Color tint;
	
	public MapRegion(String name, Rectangle rect, Color color)
	{
		this.name = name;
		this.rect = rect;
		this.tint = color;
		
		
	}
	
	public Rectangle getRect() {
		return rect;
	}

	public String getName() {
		return name;
	}

	public Color getTint() {
		return tint;
	}

	

}
