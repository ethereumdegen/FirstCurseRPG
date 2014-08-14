package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.AssetMGMT.CommonSounds;
import com.mygdx.game.controller.Player;
import com.mygdx.game.entities.Spatial;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.GameState;
import com.mygdx.game.utility.TileCoordinate;

public class WorldSprite extends Spatial {

	
	
	protected Vector2 dimensions = new Vector2(1,1);
	protected Vector2 position = new Vector2(25,25);
	protected Vector2 velocity = new Vector2(0,0) ;
	protected Vector2 acceleration= new Vector2(0,0) ;
	
	
	Sprite sprite;
	protected SpriteBatch spriteBatch;
	
	boolean active = true;
	
	public WorldSprite(TextureRegion tex)
	{
		
		
		sprite = new Sprite(tex);
		

		spriteBatch = new SpriteBatch();
	}
	
	
	@Override
	public void render()
	{
		
		if(active){
		sprite.setPosition(position.x, position.y);	
		
		spriteBatch.begin();
		sprite.draw(spriteBatch);	
		spriteBatch.end();
		}
		
		//System.out.println("rendering graphsprite" + sprite.getX() +"."+sprite.getY());
		
	}


	public void setActive(boolean active) {
		this.active=active;
	}


	public Sprite getSprite() {
		
		return sprite;
	}
	
	public void setPosition(float x, float y) {
		this.position = new Vector2(x,y);
		
	}
	
	public Vector2 getPosition() {
		
		return position;
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	

	public Vector2 getAcceleration() {
		return acceleration;
	}
	
	
	protected float rotationangle = 0f;
	protected float movementcounter = 0f;
	
	protected int stepsoundcounter = 0;
	protected float movespeed = 0.05f;
	
	
	
	
	public Vector2 getDimensions() {
		return dimensions;
	}
	
	protected Vector2 battleSpot = new Vector2();
	public void setBattleSpot(Vector2 spot) {
		battleSpot = spot;
	}
	
}
