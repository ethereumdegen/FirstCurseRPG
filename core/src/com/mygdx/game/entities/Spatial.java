package com.mygdx.game.entities;

import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GUI.battleinterface.Node2D;

public class Spatial implements Comparable<Spatial> {

	Vector3 translation = new Vector3();

	protected Node2D parent = null;

	public void setParent(Node2D parent) {
		this.parent = parent;
	}

	public Vector3 getWorldTranslation()// iterative
	{

		if (parent != null) {
			return translation.cpy().add(parent.getWorldTranslation());
		} else {
			return translation.cpy();
		}

	}

	public void setTranslation(Vector3 translation) {
		this.translation = translation;
	}
	
	public void setTranslation(float x, float y, float z) {
		this.translation = new Vector3(x,y,z);
	}

	public Vector3 getLocalTranslation() {
		return translation;
	}

	public void render() {

	}

	public void update(float millis) {

	}

	@Override
	public int compareTo(Spatial other) {
		return (int) (this.translation.z - other.translation.z);
	}



}
