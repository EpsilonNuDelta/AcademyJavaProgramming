package com.core.items;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.core.mechanics.player.Player;

public class Health extends Item {

	public Health(TiledMap map, HashMap<Cell,String> cells, ArrayList<Cell> keys) {
		super("HealthGel", map, cells, keys);
	}

}
