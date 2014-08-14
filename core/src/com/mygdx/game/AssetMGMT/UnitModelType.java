package com.mygdx.game.AssetMGMT;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;



public enum UnitModelType {

	HUMAN("human",SpriteSheet.BASICSHEET,190),
	EYEMONSTER("eyemonster",SpriteSheet.BASICSHEET,216),
	MOUSE("mouse",SpriteSheet.BASICSHEET,210),
	
	
	
	;

	UnitModelType(String name,SpriteSheet sheet, int index)
	{
		this.name=name;
		this.sheet=sheet;
		this.index=index;
		
		texRegion = new TextureRegion(sheet.getTexture(),getX(),getY(),getSheet().tilesize,getSheet().tilesize);
		
	}	
	
	UnitModelType(String name,SpriteSheet sheet, String elementId)
	{
		this.name=name;
		this.sheet=sheet;
		
		Rectangle rect = sheet.getRegionRectangleFromAtlas(elementId);
		
		texRegion = new TextureRegion(sheet.getTexture(),rect.x,rect.y,rect.width,rect.height);
		
	}	
	
	
	public String name = "";
	
	public  SpriteSheet sheet = SpriteSheet.BASICSHEET;
	
	public  int index = 200;
	
	
	
	public SpriteSheet getSheet() {
		
		return sheet;
	}

	public int getX() {
		
		return (index % sheet.tilesPerRow) * sheet.tilesize;
	}
	
	public int getY() {
		
		return (index / sheet.tilesPerRow) * sheet.tilesize ;
	}

	TextureRegion texRegion = null;
	
	public TextureRegion getTextureRegion() {
		
		return texRegion;
	}
	
	
	

}
