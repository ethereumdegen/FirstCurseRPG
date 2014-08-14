package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Shape;
import com.mygdx.game.entities.Spatial;

public class GUIShape extends Spatial{

	ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	public GUIShape()
	{
		
		
		
	}
	
	Color color = new Color(0.3f,0.3f,0.3f,1f);
	Rectangle rect = new Rectangle(0, 0, Gdx.graphics.getWidth(), 100);
	
	public GUIShape(Rectangle rect, Color color)
	{
		
	this.color=color;
	this.rect=rect;
		 
	}
	
	@Override
	public void render()
	{
		
		
		shapeRenderer.setColor(color);
		 shapeRenderer.begin(ShapeType.Filled);
		 shapeRenderer.rect(rect.x,rect.y,rect.width,rect.height);
		 shapeRenderer.end();
		
		
	}
	
	 
	
	 
}
