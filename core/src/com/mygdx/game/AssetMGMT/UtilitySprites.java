package com.mygdx.game.AssetMGMT;

public enum UtilitySprites {

	SELECTIONSQUARE(SpriteSheet.BASICSHEET,122);
	
	int index;
	SpriteSheet sheet;
	
	UtilitySprites(SpriteSheet sheet, int index)
	{
		this.index = index;
		this.sheet = sheet;		
		
	}
	
}
