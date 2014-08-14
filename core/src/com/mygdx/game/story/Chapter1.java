package com.mygdx.game.story;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.AssetMGMT.MapRegion;
import com.mygdx.game.GUI.DialogInfo;
import com.mygdx.game.camera.CameraCommand;
import com.mygdx.game.camera.PanCameraCommand;
import com.mygdx.game.controller.Player;
import com.mygdx.game.renderer.ScreenEffect;
import com.mygdx.game.renderer.TintScreenEffect;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.story.actions.*;
import com.mygdx.game.story.conditions.ConditionType;
import com.mygdx.game.story.conditions.PlayerStatCond;
import com.mygdx.game.story.triggers.EnterRegionTrigger;
import com.mygdx.game.story.triggers.TriggerType;

public class Chapter1 extends StoryChapter{

	
	List<StoryEvent> pages = new ArrayList<StoryEvent>();  //have to happen in sequential order
	
	
	List<StoryEvent> asyncEvents = new ArrayList<StoryEvent>();
	
	
	int nextPage = 0;
	
	Chapter1()
	{
		
		addPage(new StoryEvent(
				null,
				null,
				new ActionType[]{new QueueDialogAction(new DialogInfo("Ugh.. Where am I?")),
						new CameraAction(new PanCameraCommand("playerstart", 0f)),
						new ScreenEffectAction(new TintScreenEffect(Color.BLACK) ),
						
						new ScreenEffectAction(new TintScreenEffect(Color.CLEAR,4) )
				
				
				}				
				) );
		
		
		addPage(new StoryEvent(
				new TriggerType[]{new EnterRegionTrigger("town")},
				null,
				new ActionType[]{new QueueDialogAction(new DialogInfo("I have to find my way back home!"))}				
				) );
		
		
		
		//addAsyncEvent();
		
		
	}

	private void addPage(StoryEvent storyEvent) {
		pages.add(storyEvent);
		
	}
	
	private void addAsyncEvent(StoryEvent storyEvent) {
		asyncEvents.add(storyEvent);
		
	}
	
	@Override
	public void update(float delta)
	{
		if(pages.size() > nextPage && canAdvancePage(pages.get(nextPage)))
		{
			executePage( pages.get(nextPage) );
			
			System.out.println("TURNING TO PAGE" + nextPage);
			nextPage++;	
			
			
		}
		
		
		for(StoryEvent event : asyncEvents)
		{
			if(canAdvancePage(event))
			{
				executePage( event );
				
			}
			
		}
		
	}

	

	private boolean canAdvancePage(StoryEvent storyEvent) {
		
		if(storyEvent.getTriggers() != null){
			for(TriggerType trig : storyEvent.getTriggers())
			{
				if(!triggerFulfilled(trig))
				{
					return false;				
				}
			}
		}
		
		if(storyEvent.getConditions() != null){
			for(ConditionType cond : storyEvent.getConditions())
			{
				if(!conditionFulfilled(cond))
				{
					return false;				
				}
			}
		}
				
		return true;
	}
	
	private void executePage(StoryEvent storyEvent) {
		storyEvent.executionCount++; //hopefully this works!?
		
		for(ActionType action : storyEvent.getActions())
		{
			executeAction(action);
		}			
		
	}


	private boolean triggerFulfilled(TriggerType trig) {
		
		if(trig instanceof EnterRegionTrigger)
		{
			if(Player.getFocus() != null)
			{
				
				MapRegion region = GameScreen.getWorld().getRegionByName( (String) ((EnterRegionTrigger)trig).getValue() );
				
				
				if(region.encapsulatesPoint(Player.getFocus().getWorldModel().getPosition().cpy().scl(16)))
				{
					return true;
					
				}
				
				
			}
		}
		
		
		return false;
	}
	
	private boolean conditionFulfilled(ConditionType cond) {
		
		if(cond instanceof PlayerStatCond)
		{
			
			
		}		
		
		return false;
	}

	
	

	private void executeAction(ActionType action) {
		
		if(action instanceof QueueDialogAction)
		{
			System.out.println("STORY STARTING DIALOG");
			
			DialogInfo info = (DialogInfo) ((QueueDialogAction)action).getValue();
			
			//queue this up
			GameScreen.getGUIController().getDialogController().queueDialogAction(info);  
			
								
		}	
		
		if(action instanceof CameraAction)
		{
			//queue this up
			
			CameraCommand command = (CameraCommand) ((CameraAction)action).getValue();
			
			GameScreen.getWorldRenderer().getCameraManager().queueCameraCommand( command );
			
			
			
		}	
		
		if(action instanceof ScreenEffectAction)
		{
			//queue this up
			
			ScreenEffect command = (ScreenEffect) ((ScreenEffectAction)action).getValue();
			
			GameScreen.getGUIController().getScreenEffectManager().queueScreenEffect( command );
			
			
			
		}	
		
	}
	
	
}
