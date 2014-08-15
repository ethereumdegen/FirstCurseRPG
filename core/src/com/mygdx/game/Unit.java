package com.mygdx.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.mygdx.game.AssetMGMT.UnitType;
import com.mygdx.game.abilities.UnitManeuverEffect.UnitManeuverType;
import com.mygdx.game.entities.BattleUnitModel;
import com.mygdx.game.entities.UnitModelAnimation;
import com.mygdx.game.entities.WorldUnitModel;
import com.mygdx.game.utility.ObjectListener;
import com.mygdx.game.utility.UnitStatDefinition;

public class Unit implements ApplicationListener{
	
	
	

	
	
	//Sprite worldsprite;
	
	BattleUnitModel battlemodel;
		
	HashMap<UnitStats, Integer> statvalues = new HashMap<UnitStats, Integer>();
	
	WorldUnitModel model;
	
	float actionCooldownTotal = 5f;
	float actionCooldown = 0f;
	
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
	
	//only runs during battle right now!
	public void update(float delta)
	{
		if(isAlive())
		{
		pollStatEdits(delta);
		
		
		pollCooldown(delta);
		}
	}
	

	private void pollCooldown(float delta) {
		if(actionCooldown > 0)
		{
			actionCooldown -= delta;
			
			if(actionCooldown <= 0)
			{
				actionCooldown = 0f;
				//cooldownFinished();
			}
		}
				
	}

	public boolean cooldownFinished() {
		//add stuff!
		return actionCooldown == 0f;
	}
	
	public float getCooldownProgress()
	{
		return 1f - (actionCooldown / actionCooldownTotal);
	}

	private void pollStatEdits(float delta) {
		
		QueuedStatValueEdit[] array = new QueuedStatValueEdit[queuedStatEdits.size()];
		queuedStatEdits.toArray(array);
		
		for(QueuedStatValueEdit edit : array)
		{
			if(edit.update(delta))
			{
				queuedStatEdits.remove(edit);				
			}
		}
						
	}

	public void queueStatValueEdit(UnitStats stat, int delta, float delay) {
		queuedStatEdits.add(new QueuedStatValueEdit(stat,delta,delay));
	}
	
	List<QueuedStatValueEdit> queuedStatEdits = new ArrayList<QueuedStatValueEdit>();
	
	
	public void editStatValue(QueuedStatValueEdit edit) {
		statvalues.put(edit.getStat(), statvalues.get(edit.getStat()) + edit.getDelta() );
		checkStats();
	}
	
	
	class QueuedStatValueEdit
	{
		UnitStats stat;
		int delta;
		float delay;
		
		float counter;

		QueuedStatValueEdit(UnitStats stat, int delta, float delay)
		{
			this.stat=stat;
			this.delta=delta;
			this.delay=delay;
			
			
		}
		
		public UnitStats getStat() {
			return stat;
		}

		public int getDelta() {
			return delta;
		}

		public float getDelay() {
			return delay;
		}
		
		public boolean update(float delta)
		{
			
			counter+=delta;
			if(counter > delay)
			{
				editStatValue(this);
				return true;
			}
			
			return false;
		}
		
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
		},
					0f
		);
		
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

	public void playAnimation(UnitModelAnimation anim, float delay) {
		getBattleModel().queueAnimation(anim, null, delay);

	}

	public void beginManeuver(UnitManeuverType maneuver,Interpolation interpolation, Unit[] targets, float delay) {
		getBattleModel().beginManeuver(maneuver, interpolation, targets,delay);
	}

	public void resetCooldown(float cooldown) {
		actionCooldown=cooldown;
		actionCooldownTotal=cooldown;
		
	}

	

	
}
