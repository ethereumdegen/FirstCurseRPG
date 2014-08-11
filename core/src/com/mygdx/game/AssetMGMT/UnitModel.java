package com.mygdx.game.AssetMGMT;



public enum UnitModel {

	HUMAN(SpriteSheet.BASICSHEET,190);
	

	UnitModel(SpriteSheet sheet, int index)
	{
		this.sheet=sheet;
		this.index=index;
		
	}	
	
	public  SpriteSheet sheet = SpriteSheet.BASICSHEET;
	
	public  int index = 200;
	
	public int tilesPerRow = 10;
	
	public SpriteSheet getSheet() {
		
		return sheet;
	}

	public int getX() {
		
		return (index % tilesPerRow) * sheet.tilesize;
	}
	
	public int getY() {
		
		return (index / tilesPerRow) * sheet.tilesize ;
	}

}
