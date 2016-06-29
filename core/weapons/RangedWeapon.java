package com.core.weapons;

public class RangedWeapon extends Weapons {
	protected int ammo;
	
	public RangedWeapon(String n, int d, int r, int a) 
	{
		super(n,d,r);
		ammo = a;
	}
}
