package com.core.weapons;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class EnergyWeapons extends Weapons{
	protected int energy;
	
	
	public EnergyWeapons(String n, String s, int d, int e, int r, TiledMap m)
	{
		super(n,s,d,r,m);
		energy = e;
	}
	
	public int getEnergy () {
		switch(quality){
		case "Awful":
			return (int)Math.round(energy-0);
		case "Stoddy":
			return (int)Math.round(energy-5);
		case "Stock":
			return (int)Math.round(energy-10);
		case "Great":
			return (int)Math.round(energy-15);
		case "Epic":
			return (int)Math.round(energy-20);
		default:
			return energy;
		}
	}
}
