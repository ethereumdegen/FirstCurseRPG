package com.mygdx.game.utility;

import com.badlogic.gdx.graphics.Color;

public class TransitionalColor extends Color{

	public TransitionalColor(float f, float g, float h, float i) {
		super(f,g,h,i);
	}

	Color previousColor = new Color();
	Color nextColor = new Color();
	
	public void update(float delta) {  
		
		
		if(lerpTotal > 0f){
			
			if(lerpCounter < lerpTotal)
			{ 
				lerpCounter += delta;
				
				float amt =  lerpCounter / lerpTotal;
				
				this.set(previousColor.cpy().lerp(nextColor, amt));
				
			}else{
				lerpCounter = 0;
				lerpTotal = 0;
				
				this.set(nextColor.r,nextColor.g,nextColor.b,nextColor.a);
			}
			
		}
		
		
		
	}

	float lerpTotal = 0;
	float lerpCounter = 0;
	
	public void set(Color tint, float lerp) {
		
		if(!(tint.r == r && tint.g == g && tint.b==b) && lerpTotal==0){
			previousColor.set(r,g,b,a);
			nextColor.set(tint);
		
		
			lerpCounter = 0;
			lerpTotal = lerp;
		
			System.out.println("setting as "+nextColor);
		}
	}

	
	
	
}
