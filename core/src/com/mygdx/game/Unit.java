package com.mygdx.game;

import java.util.HashMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.AssetMGMT.UnitModel;
import com.mygdx.game.AssetMGMT.UnitType;
import com.mygdx.game.controller.Player;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.GameState;
import com.mygdx.game.utility.TileCoordinate;

public class Unit implements ApplicationListener{
	
	Vector2 dimensions = new Vector2(1,1);
	Vector2 position = new Vector2(25,25);
	Vector2 velocity = new Vector2(0,0) ;
	Vector2 acceleration= new Vector2(0,0) ;
	
	float movespeed = 0.05f;
	
	Sound stepSound;
	Sprite worldsprite;
	
	Sprite battlesprite;
		
	HashMap<UnitStats, Integer> statvalues = new HashMap<UnitStats, Integer>();
	
	public Unit(UnitType type) {
		this.type = type;
		
		texRegion = getModel().getTextureRegion();
		

		
		worldsprite = new Sprite(texRegion);
		worldsprite.setOrigin(0, 0);
        worldsprite.setScale(1/16f);
        worldsprite.setCenter(0.5f, 0.5f);
        
        stepSound = Gdx.audio.newSound(Gdx.files.internal("sounds/grasswalk.wav"));
	}

	UnitType type;
	
	
	UnitModel getModel()
	{
		return type.getModel();
		
	}

	@Override
	public void create() {
		
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		
		

		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	float rotationangle = 0f;
	float movementcounter = 0f;
	
	int stepsoundcounter = 0;
	
	public void update(float delta) {
		
		if(GameScreen.getState() == GameState.OVERWORLD)
		{
			velocity.add(acceleration);
			
			//only allow position to change in a direction if that way is unblocked!
			//set up the new possible X arm
			Vector2 newposX = position.cpy().add(velocity.nor().scl(movespeed));
			newposX.y = position.y;
			
			//set up the new possible Y arm
			Vector2 newposY = position.cpy().add(velocity.nor().scl(movespeed));
			newposY.x = position.x;
			
			if(GameScreen.getWorld().tileHasCollision(new TileCoordinate(newposX.cpy().add(0.5f,0.5f) ) ) == false)
			{
				position.x = newposX.x;
				
			}
			
			if(GameScreen.getWorld().tileHasCollision(new TileCoordinate(newposY.cpy().add(0.5f,0.5f) ) ) == false)
			{
				position.y = newposY.y;
				
			}
			
			if(!velocity.isZero()){
				movementcounter+= delta;
				rotationangle = (float) Math.cos(movementcounter*10)*10;
				
				if(stepsoundcounter < movementcounter * 3)
				{
					stepsoundcounter++;
					float stepVol = (40 - position.dst(Player.getFocus().position)) / 100;			
					stepSound.play(stepVol);
				}
			
			}else{
				movementcounter = 0f;
				rotationangle = 0f;
				stepsoundcounter = 0;
			}
			
			
			worldsprite.setPosition(position.x, position.y);
			worldsprite.setRotation(rotationangle);
		}
		else
		{
			
			this.position.set(battleSpot); //not always, but temporarily
			
			
			//TEMP
			worldsprite.setPosition(position.x, position.y);
			worldsprite.setRotation(rotationangle);
			
			
			//in battle
			
			
			////battlesprite.setPosition(position.x, position.y);
			//battlesprite.setRotation(rotationangle);
		}
		
		
		
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

	UnitState state;
	public void setState(UnitState state) {
		this.state=state;
		
	}

	

	TextureRegion texRegion;
	public TextureRegion getFrame() {
		return texRegion;
	}

	public Vector2 getDimensions() {
		return dimensions;
	}

	public Sprite getSprite() {
		
		return worldsprite;
	}

	
	Vector2 battleSpot = new Vector2();
	public void setBattleSpot(Vector2 spot) {
		battleSpot = spot;
	}
	
	String name = "no name";

	public String getName() {
		return name;
	}

	public int getStatValue(UnitStats stat) {
		
		if(statvalues.containsKey(stat))
		{
			return statvalues.get(stat);
		}
		
		
		return 0;
	}

	

	
}
