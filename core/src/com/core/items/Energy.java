package com.core.items;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.core.mechanics.player.Player;

public class Energy extends Item {

	public Energy(TiledMap map) {
		super("Energy", map);
	}
	
	@Override
	public void use(Player p)
	{
		p.setEnergy(p.getEnergy()+20);
		if(p.getEnergy()>p.getMaxEnergy())
			p.setEnergy(p.getMaxEnergy());
	}
}
