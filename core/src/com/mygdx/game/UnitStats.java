package com.mygdx.game;

public enum UnitStats {
	HEALTH(20),
	HEALTHREGEN(0),
	SPECIAL(10),  //required for most moves
	SPECIALREGEN(0),
	SPEED(10), //determines battle order
	ARMOR(0),  //reduces physical damage taken
	FORTITUDE(0), //reduces magic damage taken
	STRENGTH(5), //improves physical damage done
	INTELLECT(5),  //improves magic damage
	STAMINA(5)  //time between attacks..diff amount added for each move
	;
	
	int defaultAmount;
	
	

	UnitStats(int defaultAmount)
	{
		this.defaultAmount=defaultAmount;
	}
	
	public int getDefaultAmount() {
		return defaultAmount;
	}
	
}
