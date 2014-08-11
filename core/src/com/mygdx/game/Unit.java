package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.AssetMGMT.UnitModel;

public class Unit implements ApplicationListener{
	
	Vector2 dimensions = new Vector2(25,25);
	Vector2 position = new Vector2(25,25);
	Vector2 velocity = new Vector2(0,0) ;
	Vector2 acceleration= new Vector2(0,0) ;
	
	
	SpriteBatch batch;
	Texture img;
	
	public Unit(UnitModel model) {
		this.model = model;
		
		frame = new TextureRegion(model.getSheet().getTexture(),model.getX(),model.getY(),model.getSheet().tilesize,model.getSheet().tilesize);
		
		
	}

	UnitModel model;

	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture(model.getSheet().getFilePath());
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();// TODO Auto-generated method stub
		
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

	public void update(float delta) {
		
		velocity.add(acceleration);
		position.add(velocity);
		
		
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

	

	TextureRegion frame;
	public TextureRegion getFrame() {
		// TODO Auto-generated method stub
		return frame;
	}

	public Vector2 getDimensions() {
		// TODO Auto-generated method stub
		return dimensions;
	}

	

	
}
