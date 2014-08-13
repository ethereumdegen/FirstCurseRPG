package com.mygdx.game.AssetMGMT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public enum SpriteSheet {
	
	
	BASICSHEET("sheets/basicsheet.png",16),
	UIPACKRPG("sheets/uipack_rpg_sheet.png","sheets/uipack_rpg_sheet.xml");	
	
		public  String filepath = "";
		public  String xmlpath = "";
		public  int tilesize = 16;
		public int tilesPerRow = 10;
	
	SpriteSheet(String filepath, int tilesize){		
		
		this.filepath=filepath;
		this.tilesize=tilesize;
		
	}

	
	SpriteSheet(String filepath, String xmlpath){
		this.filepath=filepath;
		this.xmlpath=xmlpath;
		
	}


	public String getFilePath() {
		
		return filepath;
	}


	public Texture getTexture() {
		
		return AssetCenter.getManager().get(filepath,Texture.class);
	}


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
	
}
