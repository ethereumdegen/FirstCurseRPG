package com.mygdx.game.utility;

import com.badlogic.gdx.math.Vector2;

public class TileCoordinate {

	int x;
	int y;
	
	public TileCoordinate(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public TileCoordinate(Vector2 newpos) {
		this.x=(int) newpos.x;
		this.y=(int) newpos.y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Vector2 getPos() {
		
		return new Vector2(x,y).scl(1);
	}
	
	
}
