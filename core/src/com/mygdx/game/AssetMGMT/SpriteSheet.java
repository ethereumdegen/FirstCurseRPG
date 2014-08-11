package com.mygdx.game.AssetMGMT;

import com.badlogic.gdx.graphics.Texture;

public enum SpriteSheet {
	
	
	BASICSHEET("sheets/basicsheet.png",16);	
	
	
		public  String filepath = "";
		public  int tilesize = 16;
		public int tilesPerRow = 10;
	
	SpriteSheet(String filepath, int tilesize){		
		
		this.filepath=filepath;
		this.tilesize=tilesize;
		
	}


	public String getFilePath() {
		
		return filepath;
	}


	public Texture getTexture() {
		
		return AssetCenter.getManager().get(filepath,Texture.class);
	}
	
}
