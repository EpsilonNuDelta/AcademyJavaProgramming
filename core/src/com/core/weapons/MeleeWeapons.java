package com.core.weapons;

import com.badlogic.gdx.maps.tiled.TiledMap;

public class MeleeWeapons extends Weapons {
	
	public MeleeWeapons(String n, String s, int d, int w, TiledMap m)
	{
		super(n,s,d,0,m);
		reload = w;
	}
	
	public int getWait () {
		switch(quality){
		case "Awful":
			return (int)Math.round(reload-0);
		case "Stoddy":
			return (int)Math.round(reload-0);
		case "Stock":
			return (int)Math.round(reload-0);
		case "Great":
			return (int)Math.round(reload-1);
		case "Epic":
			return (int)Math.round(reload-1);
		default:
			return reload;
		}
	}

}
