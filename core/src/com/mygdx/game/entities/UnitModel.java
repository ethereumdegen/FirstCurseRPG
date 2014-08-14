package com.mygdx.game.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Unit;
import com.mygdx.game.AssetMGMT.CommonSounds;
import com.mygdx.game.abilities.UnitManeuverEffect.UnitManeuverType;
import com.mygdx.game.utility.ObjectListener;

public class UnitModel extends WorldSprite{

	public UnitModel(TextureRegion tex) {
		super(tex);
	}
	
	
	
	
	
	float animationTimerGoal = 0f;
	float animationTimer = 0f;
	UnitModelAnimation currentAnimation;
	ObjectListener animCompleteListener;
	protected Color previousTint = null;
	protected Color finalTint = null;
	
	List<QueuedAnimation> animationQueue =new ArrayList<QueuedAnimation>();

	public void queueAnimation(UnitModelAnimation anim, ObjectListener animCompleteListener) {
		animationQueue.add(new QueuedAnimation(anim,animCompleteListener));	
		
	}
	
	public void playAnimation(QueuedAnimation queuedAnim) {
		UnitModelAnimation anim = queuedAnim.getAnimation();
		ObjectListener animCompleteListener = queuedAnim.getListener();
		
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
		
		if(animationTimerGoal > 0)
		{
			
			
			animationTimer+=delta;
			
			float progress = animationTimer / animationTimerGoal;
			
			if(currentAnimation==UnitModelAnimation.DEATH)
			{
				
				tint = Color.RED.cpy().lerp(finalTint.cpy(), progress - (float)Math.sin(progress*66)/20 );
			
			}
			
			if(currentAnimation==UnitModelAnimation.DAMAGED)
			{
				tint = previousTint.cpy().lerp(finalTint.cpy(), (float) Math.sin(progress*70) );
			}
			
			
			if(animationTimer >= animationTimerGoal )
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
		
		if(animCompleteListener!=null)
		{
		animCompleteListener.actionPerformed(currentAnimation );
		}
		
		
	}
	
	

	public void beginManeuver(UnitManeuverType maneuver, Unit[] targets) {
		
	}
	
	
	class queuedManeuver
	{
		
		UnitManeuverType maneuver; 
		Unit[] targets;
		
		
		
	}
	
	
	
	
	
	class QueuedAnimation
	{
		UnitModelAnimation anim;
		ObjectListener animCompleteListener;
		
		QueuedAnimation(UnitModelAnimation anim,ObjectListener animCompleteListener)
		{
			this.anim=anim;
			this.animCompleteListener=animCompleteListener;
			
		}

		public ObjectListener getListener() {
			
			return animCompleteListener;
		}

		public UnitModelAnimation getAnimation() {
			
			return anim;
		}
		
		
		
		
	}
	

}
