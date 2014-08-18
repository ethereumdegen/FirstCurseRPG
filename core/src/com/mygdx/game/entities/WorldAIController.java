package com.mygdx.game.entities;

import com.mygdx.game.Unit;

public class WorldAIController {

	World world;
	WorldAIController(World world)
	{
		this.world=world;
		
		
	}
	public void update(float delta) {
		for(Unit unit : world.getUnits())
		{
			if(unit!=null && unit.getOwner() == UnitOwner.ENEMY)
			{
				unit.getWorldModel().updateAIRoaming(delta);
				
			}
			
			
		}
		
	}
	
	
	
}
