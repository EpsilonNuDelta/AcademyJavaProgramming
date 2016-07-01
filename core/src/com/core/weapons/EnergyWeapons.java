package com.core.weapons;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.core.mechanics.player.Player;

public class EnergyWeapons extends Weapons{
	protected int energy;
	

	public EnergyWeapons(String n, String sound, int d, int r, float x1, float y1)
	{
		super(n,sound,d,r,x1,y1);
	}
	public EnergyWeapons(String n, String s, int d, int e, int r, TiledMap m, HashMap<Cell,String> cells, ArrayList<Cell> keys)
	{
		super(n,s,d,r,m,cells,keys);
		energy = e;
	}
	
	public int getEnergy () {
		switch(quality){
		case "Awful":
			return (int)Math.max(energy-0,1);
		case "Stoddy":
			return (int)Math.max(energy-5,1);
		case "Stock":
			return (int)Math.max(energy-10,1);
		case "Great":
			return (int)Math.max(energy-15,1);
		case "Epic":
			return (int)Math.max(energy-20,1);
		default:
			return energy;
		}
	}
	
	@Override
	public boolean fire(Player p) {
		if(p.getEnergy()-getEnergy()>=0)
		{
			p.setEnergy(p.getEnergy()-getEnergy());
			return true;
		}
		return false;
	}
}
