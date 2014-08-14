package com.mygdx.game.AssetMGMT;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.controller.Player;
import com.mygdx.game.entities.WorldSprite;
import com.mygdx.game.renderer.GraphSprite;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.GameState;
import com.mygdx.game.utility.TileCoordinate;

public class BattleUnitModel extends WorldSprite{

	
	
	public BattleUnitModel(TextureRegion tex) {
		super(tex);
		
		
		
	}
	
	
	public enum UnitModelAnimation
	{		
		DEATH
	}


	public void playAnimation(UnitModelAnimation anim) {
		if(anim==UnitModelAnimation.DEATH)
		{
			tint = Color.BLACK.cpy();
			
		}
		
	}

	Color tint = null;
	
	public void draw(Color def, Matrix4 combined) {
	
		
		spriteBatch.setColor(def); //unless there is a better color...
		
		if(tint!=null)
		{
			
			spriteBatch.setColor(tint);
		}
		
		spriteBatch.setProjectionMatrix(combined);
		
		super.render();
		
		
		
	}

	
	

	public void update(float delta) {
		
		if(GameScreen.getState() == GameState.BATTLE)
		{
			
			this.position.set(battleSpot); //not always, but temporarily
			
			//TEMP
			//setPosition(position.x, position.y);
			getSprite().setRotation(rotationangle);
			
			
			//in battle
			
			
			////battlesprite.setPosition(position.x, position.y);
			//battlesprite.setRotation(rotationangle);
		}
		
		
		
	}
	
	

}
