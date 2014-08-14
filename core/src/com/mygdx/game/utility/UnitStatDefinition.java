package com.mygdx.game.utility;

import com.mygdx.game.UnitStats;

public class UnitStatDefinition {
	
	
	UnitStats stat; 
	int amount;
	public UnitStatDefinition(UnitStats stat, int amount)
	{
		this.stat=stat;
		this.amount=amount;
		
	}
	public UnitStats getStat() {
		return stat;
	}
	public int getAmount() {
		return amount;
	}
	
}
