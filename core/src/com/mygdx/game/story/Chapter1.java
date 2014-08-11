package com.mygdx.game.story;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.GUI.DialogInfo;
import com.mygdx.game.camera.CameraCommand;
import com.mygdx.game.camera.PanCameraCommand;
import com.mygdx.game.renderer.ScreenEffect;
import com.mygdx.game.renderer.TintScreenEffect;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.story.actions.*;
import com.mygdx.game.story.conditions.ConditionType;
import com.mygdx.game.story.conditions.PlayerStatCond;
import com.mygdx.game.story.triggers.EnterRegionTrigger;
import com.mygdx.game.story.triggers.TriggerType;

public class Chapter1 extends StoryChapter{

	
	List<StoryPage> pages = new ArrayList<StoryPage>();  //have to happen in sequential order
	
	int nextPage = 0;
	
	Chapter1()
	{
		
		addPage(new StoryPage(
				null,
				null,
				new ActionType[]{new QueueDialogAction(new DialogInfo("Where am I?")),
						new CameraAction(new PanCameraCommand("playerstart", 2f)),
						new ScreenEffectAction(new TintScreenEffect(Color.BLACK) ),
						new ScreenEffectAction(new TintScreenEffect(Color.CLEAR,4) )
				
				
				}				
				) );
		
		
		addPage(new StoryPage(
				new TriggerType[]{new EnterRegionTrigger("town")},
				null,
				new ActionType[]{new QueueDialogAction(new DialogInfo("this is a test"))}				
				) );
		
		
		
		
		
		
	}

	private void addPage(StoryPage storyPage) {
		pages.add(storyPage);
		
	}
	
	@Override
	public void update(float delta)
	{
		if(canAdvancePage(pages.get(nextPage)))
		{
			executePage( pages.get(nextPage) );
			
			System.out.println("TURNING TO PAGE" + nextPage);
			
			nextPage++;		
			
			
		}
		
		
		
		
	}

	

	private boolean canAdvancePage(StoryPage storyPage) {
		
		if(storyPage.getTriggers() != null){
			for(TriggerType trig : storyPage.getTriggers())
			{
				if(!triggerFulfilled(trig))
				{
					return false;				
				}
			}
		}
		
		if(storyPage.getConditions() != null){
			for(ConditionType cond : storyPage.getConditions())
			{
				if(!conditionFulfilled(cond))
				{
					return false;				
				}
			}
		}
				
		return true;
	}
	
	private void executePage(StoryPage storyPage) {
		for(ActionType action : storyPage.getActions())
		{
			executeAction(action);
		}			
		
	}


	private boolean triggerFulfilled(TriggerType trig) {
		
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
			
			//queue this up
			GameScreen.getGUI().getDialogController().setText("Where am I?",0.05f);
			
								
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
			
			GameScreen.getGUIRenderer().getScreenEffectManager().queueScreenEffect( command );
			
			
			
		}	
		
	}
	
	
}
