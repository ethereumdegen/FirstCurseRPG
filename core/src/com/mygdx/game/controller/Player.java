package com.mygdx.game.controller;

import com.mygdx.game.Unit;

public class Player {
	static Unit focus;
	
	public static void setFocus(Unit unit)
	{
		focus = unit;	
		
	}
	
	public static Unit getFocus()
	{
		return focus;
	}
	
}
