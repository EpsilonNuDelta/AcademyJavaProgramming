package com.core.weapons;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class MeleeWeapons extends Weapons {

	public MeleeWeapons(String n, String sound, int d, int w, float x1, float y1)
	{
		super(n,sound,d,w,x1,y1);
	}
	public MeleeWeapons(String n, String s, int d, int w, TiledMap m,HashMap<Cell,String> cells, ArrayList<Cell> keys)
	{
		super(n,s,d,w,m,cells,keys);
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
