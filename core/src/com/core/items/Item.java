package com.core.items;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.core.mechanics.player.Inventory;
import com.core.mechanics.player.Player;

public class Item extends Pickup{

	public Item(String n, TiledMap m)
	{
		super(n,m);
	}

	public void use(Player p)
	{
		
	}
}
