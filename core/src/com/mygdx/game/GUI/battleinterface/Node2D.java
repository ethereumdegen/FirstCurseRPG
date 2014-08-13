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
		
		List<Spatial> lineage = getLineage();
		
		Collections.sort(lineage);//sorts the entire lineage by Z
		
		for(Spatial renderable : lineage)
		{
			if(!(renderable instanceof Node2D))//eliminates lag and multirender
			{
				renderable.render();
			}
		}
		
	}


	private List<Spatial> getLineage() {
		List<Spatial> lineage = new ArrayList<Spatial>();
		lineage.addAll(children);
		
		for(Spatial child:children)
		{	
			
			if(child instanceof Node2D)
			{
				lineage.addAll(((Node2D)child).getLineage());	
			}
			
		}		
		
		return lineage;
	}

	public void update(float delta) {
				
		for(Spatial child:children)
		{
			child.update(delta);  //recursive, not ordered by Z
			
		}
		
		
	}

	
	public boolean hasDescendant(Spatial spatial) {
		if(hasChild(spatial)){
			return true;
		}
		
		for(Spatial child : children)
		{
			if(child instanceof Node2D)
			{
				Node2D childNode = (Node2D) child;
				if( childNode.hasDescendant(spatial) )
				{
					return true;
				}
			}
			
		}
		
				
		return false;
	}

	private boolean hasChild(Spatial spatial) {
		
		return children.contains(spatial);
	}

	public void detachChild(Spatial spatial) {
		children.remove(spatial);
	}

	
}
