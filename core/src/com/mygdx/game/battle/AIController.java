package com.mygdx.game.battle;

import com.mygdx.game.Unit;
import com.mygdx.game.abilities.AbilityExecutionInfo;
import com.mygdx.game.abilities.AbilityType;
import com.mygdx.game.entities.BattleController;

public class AIController {

	BattleController battleController;
	
	public AIController(BattleController battleController) {
		this.battleController=battleController;
	}
	
	public void initBattle()
	{
		
		for(Unit enemy : battleController.getUnits()[1])
		{			
			if(enemy!= null )
			{
				enemy.resetCooldown((float) (5 + Math.random()*3));
			}
		}
		
	}
	
	public void update(float delta)
	{
		
		for(Unit enemy : battleController.getUnits()[1])
		{
			if(enemy!= null && enemy.cooldownFinished())
			{
				battleController.executeUnitAbility(new AbilityExecutionInfo(enemy, battleController.getUnits()[0][0], AbilityType.SLASH));
				
				enemy.resetCooldown((float) (5 + Math.random()*3));
			}
			
			
			
		}
		
		
		
	}

}
