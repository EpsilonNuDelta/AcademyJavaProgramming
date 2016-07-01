package com.core.items;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.core.mechanics.player.Inventory;
import com.core.mechanics.player.Player;

public class Item extends Pickup{

	public Item(String n, TiledMap m, HashMap<Cell,String> cells, ArrayList<Cell> keys)
	{
		super(n,m, cells, keys);
	}

	public void use(Player p)
	{
		
	}
}
