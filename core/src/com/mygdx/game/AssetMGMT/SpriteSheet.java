package com.mygdx.game.AssetMGMT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public enum SpriteSheet {
	
	
	BASICSHEET("sheets/bitrpg.png",16),
	UIPACKRPG("sheets/uipackrpg.png","sheets/uipackrpg.atlas"), 
	ONSCREENCONTROLS("sheets/controls.png","sheets/controls.atlas"),
	RAVENMORE("sheets/ravenmoreui.png","sheets/ravenmoreui.atlas")
	
	;	
	
		public  String filepath = "";
		public  String atlaspath = "";
		public  int tilesize = 16;
		public int tilesPerRow = 10;
	
	SpriteSheet(String filepath, int tilesize){		
		
		this.filepath=filepath;
		this.tilesize=tilesize;
		
	}

	
	SpriteSheet(String filepath, String atlaspath){
		this.filepath=filepath;
		this.atlaspath=atlaspath;
		
	}


	public String getFilePath() {
		
		return filepath;
	}


	public Texture getTexture() {
		
		return AssetCenter.getManager().get(filepath,Texture.class);
	}

/*
	public Rectangle getElementRectFromXML(String elementId) {
		System.out.println("reading XML");
		Rectangle rect = new Rectangle();
		
		
		
		FileHandle file = Gdx.files.internal(xmlpath);
		String data = file.readString();
		
		XmlReader reader = new XmlReader();
		Element root = reader.parse(data);
		
		
		Array<Element> items = root.getChildrenByName("SubTexture");
		
		
		
		for (Element child : items)
		{
			if(child.getAttribute("name").equalsIgnoreCase(elementId)){			
			rect.x = Integer.parseInt( child.getAttribute("x"));
			rect.y = Integer.parseInt( child.getAttribute("y"));
			rect.width = Integer.parseInt( child.getAttribute("width"));
			rect.height = Integer.parseInt( child.getAttribute("height"));
			break;
			}
			
		 
		}
		
		return rect;
	}
	*/
	
	public Rectangle getRegionRectangleFromAtlas(String elementId) {
		TextureAtlas atlas;
		atlas = new TextureAtlas(Gdx.files.internal(atlaspath));
		AtlasRegion region = atlas.findRegion(elementId);
		
		System.out.println(elementId  );
		Rectangle rect = new Rectangle(region.getRegionX(),region.getRegionY(),region.getRegionWidth(),region.getRegionHeight());
				
		return rect;
	}


	public Sprite getSpriteFromAtlas(String elementId) {
		TextureAtlas atlas;
		atlas = new TextureAtlas(Gdx.files.internal(atlaspath));
		//AtlasRegion region = atlas.findRegion(elementId);
		
		
		return atlas.createSprite(elementId);
	}
	
	
}
