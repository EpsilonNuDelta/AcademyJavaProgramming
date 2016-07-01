package com.core.items;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.core.mechanics.player.Inventory;
import com.core.weapons.RangedWeapon;


public class Ammo extends Pickup {
	public Ammo(TiledMap map) {
		super("Reload", map);
	}

	public void pickUp(Inventory i)
	{
		if(i.getGun()!=null&&i.getGun() instanceof RangedWeapon)
		{
			((RangedWeapon)i.getGun()).addAmmo(10);
		}
	}
}
