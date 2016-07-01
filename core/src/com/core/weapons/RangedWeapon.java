package com.core.weapons;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.core.mechanics.player.Player;

public class RangedWeapon extends Weapons {
	protected int ammo;
	protected int maxAmmo;
	public RangedWeapon(String n, String sound, int d, int r, int a, float x1, float y1)
	{
		super(n,sound,d,r,x1,y1);
		ammo = a;
		maxAmmo = ammo;
	}
	public RangedWeapon(String n, String s, int d, int r, int a, TiledMap m, HashMap<Cell,String> cells, ArrayList<Cell> keys) 
	{
		super(n,s,d,r,m,cells,keys);
		ammo = a;
		maxAmmo = ammo;
	}
	public void addAmmo(int num)
	{
		ammo += num;
		if(ammo>maxAmmo)
			ammo = maxAmmo;
	}
	@Override
	public boolean fire(Player p) {
		if(ammo>0)
		{
			ammo -= 1;
			return true;
		}
		return false;
	}
}
