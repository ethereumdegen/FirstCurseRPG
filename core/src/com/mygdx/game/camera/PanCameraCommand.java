package com.mygdx.game.camera;

import com.badlogic.gdx.math.Interpolation;

public class PanCameraCommand extends CameraCommand{

	public PanCameraCommand(String name, float time) {
		this.focusRegion=name;
		this.time=time;	
	}
	
	public PanCameraCommand(String name, float time, Interpolation method ) {
		this.focusRegion=name;
		this.time=time;	
		this.method=method;
	}

	String focusRegion;

	Interpolation method = Interpolation.exp5;
	
	public Interpolation getInterpolationMethod() {
		
		return method;
	}
	
	
	
	
	
}
