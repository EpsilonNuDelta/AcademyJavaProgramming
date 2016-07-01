package com.core.weapons;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.core.mechanics.player.Player;

public class RangedWeapon extends Weapons {
	protected int ammo;
	protected int maxAmmo;
	public RangedWeapon(String n, String s, int d, int r, int a, TiledMap m) 
	{
		super(n,s,d,r,m);
		ammo = a;
		maxAmmo = ammo;
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
