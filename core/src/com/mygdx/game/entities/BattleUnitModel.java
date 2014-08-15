package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.abilities.UnitManeuverEffect.UnitManeuverType;
import com.mygdx.game.controller.Player;
import com.mygdx.game.renderer.GraphSprite;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.GameState;
import com.mygdx.game.utility.TileCoordinate;

public class BattleUnitModel extends UnitModel{

	
	
	public BattleUnitModel(TextureRegion tex) {
		super(tex);
		
		
		
	}
	
	
	
	
	public void draw(Color def, Matrix4 combined) {
	
		defaultTint = def;
		
		spriteBatch.setProjectionMatrix(combined);
		
		sprite.setColor(Color.CYAN.cpy());
		spriteBatch.setColor(Color.CYAN.cpy());
		
		if(isVisible())
		{
		super.render();
		}
		
		
	}

	
	GameState previousState;

	public void update(float delta) {
		super.update(delta);
		
		if(GameScreen.getState()!=previousState){
			if(GameScreen.getState() == GameState.BATTLE)
			{
			
			this.position.set(battleSpot); //not always, but temporarily
			
			getSprite().setRotation(rotationangle);
			
			
			//in battle
			
			
			////battlesprite.setPosition(position.x, position.y);
			//battlesprite.setRotation(rotationangle);
			}
		}
		
		previousState = GameScreen.getState();
	}




	

}
