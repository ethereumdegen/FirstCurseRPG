package com.mygdx.game.renderer;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.camera.PanCameraCommand;

public class ScreenEffectManager {

	/** Does effects on the screen such as color fades and battle sequence intros using coloring, particles, and more */
	
	List<ScreenEffect> queuedEffects = new ArrayList<ScreenEffect>();
	
	ScreenEffectManager()
	{
		shapeRenderer = new ShapeRenderer();
	}
	
	
	ScreenEffect currentEffect = null;
	float effectTimer = 0f;
	
	Color previousColor = Color.BLACK.cpy();
	Color nextColor = Color.BLACK.cpy();
	
	Color currentColor = Color.BLACK.cpy();
	
	public void update(float delta) {
		
		if(currentEffect != null)
		{
			updateEffect(delta);
			
			
		}
		else
		{
			
			//currentColor.set(Color.CLEAR);
			
			if(!queuedEffects.isEmpty())
			{
				
				setCurrentEffect(queuedEffects.remove(0));
								
				
			}
		}
	}

	
	
	private void setCurrentEffect(ScreenEffect effect) {
		currentEffect = effect;
		effectTimer = 0f;
		
		previousColor.set(currentColor.cpy());
		nextColor.set(effect.getColor().cpy());
		
	}

	private void updateEffect(float delta) {
		if(effectTimer <= currentEffect.getTime()){
			
			effectTimer+=delta;
			
			if(currentEffect instanceof TintScreenEffect)
			{
				
				
				
				float lerp = effectTimer / currentEffect.getTime();		
				
				
				if(!Float.isInfinite(lerp)){
					currentColor.set(previousColor.cpy().lerp(nextColor, lerp));
					
				}
			}
			
				
		}
		else
		{
			currentColor.set(nextColor.cpy());
			
			//done with the command, delete it
			effectTimer=0f;
			currentEffect = null; 
			
			
		}
		
	}

	public void queueScreenEffect(ScreenEffect command) {
		queuedEffects.add(command);
		
	}



	ShapeRenderer shapeRenderer;
	
	public void render() {
		
		
		
		 shapeRenderer.setColor(currentColor);
		 shapeRenderer.begin(ShapeType.Filled);
		 shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
		 shapeRenderer.end();
		 
		 
		 
		
	}

	
	
	

}
