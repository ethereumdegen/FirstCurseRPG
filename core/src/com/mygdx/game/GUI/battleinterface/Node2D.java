package com.mygdx.game.GUI.battleinterface;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

public class Node2D {

	Vector2 translation = new Vector2();
	
	Node2D parent = null;
	
	List<Node2D> children = new ArrayList<Node2D>();
	
	public void setTranslation(Vector2 translation) {
		this.translation = translation;
	}

	public Vector2 getLocalTranslation()
	{
		return translation;		
	}
	
	
	
	public Vector2 getWorldTranslation()//iterative
	{
		
		if(parent!=null){
		return translation.cpy().add(parent.getWorldTranslation());
		}else{
			return translation.cpy();
		}
		
		
	}
	
	public void attachChild(Node2D child){
		child.setParent(this);
		children.add(child);
	}

	private void setParent(Node2D parent) {
		this.parent = parent;
		
	}
	
	
}
