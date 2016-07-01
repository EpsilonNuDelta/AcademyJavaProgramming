package com.core.items;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.core.mechanics.player.Inventory;
import com.core.weapons.RangedWeapon;


public class Ammo extends Pickup {
	public Ammo(TiledMap map, HashMap<Cell,String> cells, ArrayList<Cell> keys) {
		super("Reload", map, cells, keys);
	}

	public void pickUp(Inventory i)
	{
		if(i.getGun()!=null&&i.getGun() instanceof RangedWeapon)
		{
			((RangedWeapon)i.getGun()).addAmmo(10);
		}
	}
}
