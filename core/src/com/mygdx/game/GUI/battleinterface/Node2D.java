package com.mygdx.game.GUI.battleinterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.Spatial;

public class Node2D extends Spatial{

	
	
	List<Spatial> children = new ArrayList<Spatial>();

	
	public void attachChild(Spatial child){
		
		child.setParent(this);
		
		if(!children.contains(child))
		{
			children.add(child);
			sortChildrenByZ();
		
		}
		
	}

	private void sortChildrenByZ() {			
		
		Collections.sort(children); //uses the overridden CompareTo to look at the Z
		
	}

	public void detachAllChildren()
	{
		children.clear();
	}
	public List<Spatial> getChildren()
	{
		return children;
	}
	
	public void render() {//does not execute for nodes in the middle of the tree
		
		List<Spatial> lineage = new ArrayList<Spatial>();
				
		//collect all renderables and sort them by Z
		for(Spatial child:children)
		{
			lineage.add(child);	
			
			if(child instanceof Node2D)
			{
				lineage.addAll(((Node2D)child).getChildren());	
			}
			
		}
				
		Collections.sort(lineage);//sorts the entire lineage by Z
		
		for(Spatial renderable : lineage)
		{
			if(!(renderable instanceof Node2D))//eliminates lag and multirender
			{
				renderable.render();
			}
		}
		
	}


	public void update(float millis) {
		
		
	}

	
	
	
}
