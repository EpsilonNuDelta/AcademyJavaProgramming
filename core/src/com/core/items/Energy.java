package com.core.items;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.core.mechanics.player.Player;

public class Energy extends Item {

	public Energy(TiledMap map, HashMap<Cell,String> cells, ArrayList<Cell> keys) {
		super("Energy", map, cells, keys);
	}
	
	@Override
	public void use(Player p)
	{
		p.setEnergy(p.getEnergy()+20);
		if(p.getEnergy()>p.getMaxEnergy())
			p.setEnergy(p.getMaxEnergy());
	}
}
