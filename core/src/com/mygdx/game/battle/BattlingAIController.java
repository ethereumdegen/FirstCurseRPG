package com.mygdx.game.battle;

import com.mygdx.game.Unit;
import com.mygdx.game.abilities.AbilityExecutionInfo;
import com.mygdx.game.abilities.AbilityType;
import com.mygdx.game.entities.BattleController;

public class BattlingAIController {

	BattleController battleController;
	
	public BattlingAIController(BattleController battleController) {
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
			Unit victim = chooseVictim();
			
			if(enemy!= null && enemy.cooldownFinished() && victim.isAlive() && enemy.isAlive())
			{
				battleController.executeUnitAbility(new AbilityExecutionInfo(enemy, victim, chooseAbilityType()));
				
				//enemy.resetCooldown((float) (5 + Math.random()*3));
			}
			
			
			
		}
		
		
		
	}

	private AbilityType chooseAbilityType() {
		return AbilityType.BITE;
	}

	private Unit chooseVictim() {
		
		return battleController.getUnits()[0][0];
	}

}
