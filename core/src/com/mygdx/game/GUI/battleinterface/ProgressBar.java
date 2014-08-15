package com.mygdx.game.GUI.battleinterface;

import com.mygdx.game.AssetMGMT.UtilitySprites;
import com.mygdx.game.renderer.GraphSprite;

public class ProgressBar extends Node2D {

	SimpleNinePatch background = new SimpleNinePatch(UtilitySprites.PROGRESSBACKGROUND.getTextureRegion(),5);
	GraphSprite bar = new GraphSprite(UtilitySprites.PROGRESSBAR.getTextureRegion());
	
	
	ProgressBar()
	{
		
		background.setTranslation(-5, -5, 0);
		
		background.setRect(0,0,70,25);
		this.attachChild(background);
		
		this.attachChild(bar);
	}

	public void setProgress(float cooldownProgress) {
		
		
		bar.getSprite().setSize(60*cooldownProgress, bar.getSprite().getHeight());
		
	}
	
	
	
}
