package com.mygdx.game.entities;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.game.Unit;
import com.mygdx.game.AssetMGMT.UnitModels.Human;
import com.mygdx.game.controller.Player;



public class World {

	/** The blocks making up the world **/
	List<TerrainTile> tiles = new ArrayList<TerrainTile>();
	
	List<Unit> units = new ArrayList<Unit>();

	public World() {
		initWorld();
		createDemoWorld();
	}

	private void initWorld() {
		Player.setFocus(spawnUnit());
		
	}

	private Unit spawnUnit() {
		Unit unit = new Unit(new Human());
		return unit;
	}

	private void createDemoWorld() {
		
		/*tiles.add(new TerrainTile())
		
		for (int i = 0; i < 10; i++) {
			blocks.add(new Block(new Vector2(i, 0)));
			blocks.add(new Block(new Vector2(i, 6)));
			if (i > 2)
				blocks.add(new Block(new Vector2(i, 1)));
		}
		blocks.add(new Block(new Vector2(9, 2)));
		blocks.add(new Block(new Vector2(9, 3)));
		blocks.add(new Block(new Vector2(9, 4)));
		blocks.add(new Block(new Vector2(9, 5)));

		blocks.add(new Block(new Vector2(6, 3)));
		blocks.add(new Block(new Vector2(6, 4)));
		blocks.add(new Block(new Vector2(6, 5)));*/
	}

	public List<Unit> getUnits() {
		
		return units;
	}
}