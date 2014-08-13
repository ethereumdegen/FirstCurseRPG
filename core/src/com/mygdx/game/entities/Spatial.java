package com.mygdx.game.entities;

import java.util.Comparator;

import com.badlogic.gdx.math.Vector3;

public class Spatial implements Comparable<Spatial> {

	Vector3 translation = new Vector3();

	protected Spatial parent = null;

	public void setParent(Spatial parent) {
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
