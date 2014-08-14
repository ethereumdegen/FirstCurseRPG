package com.mygdx.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import com.mygdx.game.AssetMGMT.CommonSounds;
import com.mygdx.game.AssetMGMT.UnitModelType;
import com.mygdx.game.AssetMGMT.UnitType;
import com.mygdx.game.abilities.UnitManeuverEffect.UnitManeuverType;
import com.mygdx.game.controller.Player;
import com.mygdx.game.entities.BattleUnitModel;
import com.mygdx.game.entities.Spatial;
import com.mygdx.game.entities.UnitModelAnimation;
import com.mygdx.game.entities.WorldUnitModel;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.GameState;
import com.mygdx.game.utility.ObjectListener;
import com.mygdx.game.utility.TileCoordinate;
import com.mygdx.game.utility.UnitStatDefinition;

public class Unit implements ApplicationListener{
	
	
	

	
	
	//Sprite worldsprite;
	
	BattleUnitModel battlemodel;
		
	HashMap<UnitStats, Integer> statvalues = new HashMap<UnitStats, Integer>();
	
	WorldUnitModel model;
	
	public Unit(UnitType type) {
		this.type = type;
		
		texRegion = type.getModelType().getTextureRegion();
		
		model = new WorldUnitModel(texRegion);
		battlemodel = new BattleUnitModel(texRegion);
		
		//worldsprite = new Sprite(texRegion);
		model.getSprite().setOrigin(0, 0);
		model.getSprite().setScale(1/16f);
		model.getSprite().setCenter(0.5f, 0.5f);
		
		battlemodel.getSprite().setOrigin(0, 0);
		battlemodel.getSprite().setScale(1/16f);
		battlemodel.getSprite().setCenter(0.5f, 0.5f);
        
       loadBaseStats();
	}

	private void loadBaseStats() {
		
		for(UnitStats stats : UnitStats.values())
		{
			statvalues.put(stats, stats.defaultAmount );
		}
		
		
		for(UnitStatDefinition def : type.getBaseStats())
		{
			statvalues.put(def.getStat(), def.getAmount() );
			
		}
		
	}

	UnitType type;
	
	
	

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



	

	UnitState state;
	public void setState(UnitState state) {
		this.state=state;
		
	}

	

	TextureRegion texRegion;
	public TextureRegion getFrame() {
		return texRegion;
	}

	

	public Sprite getSprite() {
		
		return model.getSprite();
	}

	
	
	
	String name = null;

	public String getName() {
		
		if(name==null)
		{
			return getUnitType().getName();
		}
		
		
		return name;
	}

	private UnitType getUnitType() {
		
		return type;
	}

	public int getStatValue(UnitStats stat) {
		
		if(statvalues.containsKey(stat))
		{
			return statvalues.get(stat);
		}
		
		
		return 0;
	}

	public void editStatValue(UnitStats stat, int delta) {
		System.out.println(stat);
		
		statvalues.put(stat, statvalues.get(stat) + delta );
		checkStats();
	}

	private void checkStats() {
		if(statvalues.get(UnitStats.HEALTH) <= 0)
		{
			die();
			
		
		}
		
	}

	
	
	private void die() {
		System.out.println(this + "just died!!");
		
		getBattleModel().queueAnimation(UnitModelAnimation.DEATH,
				new ObjectListener(){
					
					@Override
					public void actionPerformed(Object o) {
						alive=false;
						getBattleModel().setVisible(false);
						getWorldModel().setVisible(false);
					}
		});
		
	}

	public WorldUnitModel getWorldModel() {
		
		return model;
	}
	
	public BattleUnitModel getBattleModel() {
		
		return battlemodel;
	}

	
	boolean alive = true;
	public boolean isAlive() {
		
		return alive;
	}

	public void playAnimation(UnitModelAnimation anim) {
		getBattleModel().queueAnimation(anim, null);

	}

	public void beginManeuver(UnitManeuverType maneuver, Unit[] targets) {
		getBattleModel().beginManeuver(maneuver, targets);
	}

	

	
}
