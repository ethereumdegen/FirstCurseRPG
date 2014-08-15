package com.mygdx.game.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Unit;
import com.mygdx.game.AssetMGMT.CommonSounds;
import com.mygdx.game.abilities.UnitManeuverEffect.UnitManeuverType;
import com.mygdx.game.utility.ObjectListener;

public class UnitModel extends WorldSprite{

	public UnitModel(TextureRegion tex) {
		super(tex);
	}
	
	
	
	
	float animationDelay = 0f;
	float animationTimerGoal = 0f;
	float animationTimer = 0f;
	UnitModelAnimation currentAnimation;
	ObjectListener animCompleteListener;
	protected Color previousTint = null;
	protected Color finalTint = null;
	
	List<QueuedAnimation> animationQueue =new ArrayList<QueuedAnimation>();

	public void queueAnimation(UnitModelAnimation anim, ObjectListener animCompleteListener, float delay) {
		animationQueue.add(new QueuedAnimation(anim,animCompleteListener,delay));	
		
	}
	
	public void playAnimation(QueuedAnimation queuedAnim) {
		UnitModelAnimation anim = queuedAnim.getAnimation();
		ObjectListener animCompleteListener = queuedAnim.getListener();
		
		animationDelay = queuedAnim.getDelay();
		animationTimerGoal = anim.getPlayTime();
		animationTimer = 0f;
		this.animCompleteListener=animCompleteListener;
		
		currentAnimation = anim;
		
		if(tint == null)
		{
			previousTint = defaultTint.cpy();
		}else{
			previousTint = tint.cpy();
		}
		
		if(anim==UnitModelAnimation.DAMAGED)
		{
			finalTint = Color.RED.cpy();
		}
		
		
		if(anim==UnitModelAnimation.DEATH)
		{
			CommonSounds.DEATH.play(0.5f);
			finalTint = Color.BLACK.cpy();
		}
		
	}

	protected Color tint = null;
	protected Color defaultTint = Color.WHITE.cpy();
	
	public void update(float delta)
	{
		super.update(delta);

		pollAnimations(delta);
		
		pollManeuvers(delta);
		
	}
	
	
	public void render()
	{
		
		if(tint != null)
		{
		sprite.setColor(tint);
		spriteBatch.setColor(tint);
		}else{
			sprite.setColor(defaultTint);
			spriteBatch.setColor(defaultTint);
		}
		
		if(isVisible())
		{
		super.render();
		}
	}


	private void pollAnimations(float delta) {
		
		if(animationTimerGoal > 0f)
		{
			
			
			animationTimer+=delta;
			
			if(animationTimer < animationDelay)
				return;
			
			
			float progress = (animationTimer-animationDelay) / animationTimerGoal;
			
			if(currentAnimation==UnitModelAnimation.DEATH)
			{
				
				tint = Color.RED.cpy().lerp(finalTint.cpy(), progress - (float)Math.sin(progress*66)/20 );
			
			}
			
			if(currentAnimation==UnitModelAnimation.DAMAGED)
			{
				tint = previousTint.cpy().lerp(finalTint.cpy(), (float) Math.sin(progress*70) );
			}
			
			
			if(animationTimer-animationDelay >= animationTimerGoal )
			{
				
				endAnimation();
			}
			
		}
		else
		{
			if(!animationQueue.isEmpty())
			{
			playAnimation(animationQueue.remove(0));
			}
		}
		
	}


	private void endAnimation() {
		animationTimerGoal = 0f;
		animationTimer = 0f;
		
		tint = previousTint.cpy();
		
		if(animCompleteListener!=null)
		{
		animCompleteListener.actionPerformed(currentAnimation );
		}
		
		
	}
	
	float maneuverDelay = 0f;
	float maneuverTimerGoal = 0f;
	float maneuverTimer = 0f;
	UnitManeuverType currentManeuver;
	Vector2 originalPosition;
	Vector2 targetPosition;
	Unit[] currentManeuverTargets;
	Interpolation currentInterpolation = Interpolation.linear;
	
	private void pollManeuvers(float delta) {
		if(maneuverTimerGoal > 0f)
		{
			maneuverTimer+=delta;
			
			if(maneuverTimer < maneuverDelay)
				return;
			
			
			float progress = (maneuverTimer-maneuverDelay) / maneuverTimerGoal;
			
			progress = 1f - Math.abs(progress*2 - 1f );
			
			
			if(currentManeuver==UnitManeuverType.RUSH)
			{
				
				//this.position.set(originalPosition.cpy().interpolate(targetPosition.cpy(), progress, Interpolation.swing));
				this.position.set(originalPosition.cpy().interpolate(targetPosition.cpy(), progress, currentInterpolation ));
				
				
			}
			
			
			if(maneuverTimer-maneuverDelay >= maneuverTimerGoal )
			{				
				endManeuver();
			}
			
		}
		else
		{
			if(!maneuverQueue.isEmpty())
			{
				playManeuver(maneuverQueue.remove(0));
			}
		}
		
	}
	
	
	private void endManeuver() {
		maneuverTimerGoal = 0f;
		maneuverTimer = 0f;
		this.position.set(battleSpot.cpy());
	}

	private void playManeuver(QueuedManeuver maneuver) {
		maneuverDelay = maneuver.getDelay();
		maneuverTimerGoal = maneuver.getManeuver().getPlayTime();
		maneuverTimer = 0f;
		currentManeuver = maneuver.getManeuver();
		
		originalPosition = this.getPosition().cpy();
		targetPosition = maneuver.getTargets()[0].getBattleModel().getPosition().cpy();
		currentInterpolation = maneuver.getInterpolation();
	}





	List<QueuedManeuver> maneuverQueue =new ArrayList<QueuedManeuver>();
	public void beginManeuver(UnitManeuverType maneuver, Interpolation interpolation, Unit[] targets, float delay) {
		maneuverQueue.add(new QueuedManeuver(maneuver,interpolation,targets,delay));		
	}
	
	
	class QueuedManeuver
	{
		
		UnitManeuverType maneuver; 
		Unit[] targets;
		Interpolation interpolation;
		float delay;
	

		QueuedManeuver(UnitManeuverType maneuver,Interpolation interpolation,Unit[] targets, float delay)
		{
			this.maneuver=maneuver;
			this.targets=targets;
			this.interpolation=interpolation;
			this.delay=delay;
		}

		public Interpolation getInterpolation() {
			return interpolation;
		}

		public UnitManeuverType getManeuver() {
			return maneuver;
		}


		public Unit[] getTargets() {
			return targets;
		}

		public float getDelay()
		{
			return delay;
		}
		
	}
	
	
	
	
	class QueuedAnimation
	{
		UnitModelAnimation anim;
		ObjectListener animCompleteListener;
		float delay;
		
		QueuedAnimation(UnitModelAnimation anim,ObjectListener animCompleteListener, float delay)
		{
			this.anim=anim;
			this.animCompleteListener=animCompleteListener;
			this.delay = delay;
		}

		public ObjectListener getListener() {
			
			return animCompleteListener;
		}

		public UnitModelAnimation getAnimation() {
			
			return anim;
		}
		
		public float getDelay()
		{
			return delay;
		}
		
		
	}
	

}
