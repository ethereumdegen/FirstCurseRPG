package com.mygdx.game.story;

import com.mygdx.game.story.actions.ActionType;
import com.mygdx.game.story.conditions.ConditionType;
import com.mygdx.game.story.triggers.TriggerType;

public class StoryPage {

	private TriggerType[] triggers;
	private ConditionType[] conditions;
	private ActionType[] actions;




	public StoryPage(TriggerType[] triggers, ConditionType[] conditions,
			ActionType[] actions) {
	
		this.triggers = triggers;
		this.conditions = conditions;
		this.actions = actions;
		
	}




	public TriggerType[] getTriggers() {
		return triggers;
	}




	public ConditionType[] getConditions() {
		return conditions;
	}




	public ActionType[] getActions() {
		return actions;
	}

	
	
}
