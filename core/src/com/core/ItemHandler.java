package com.core;

import com.core.armors.Armour;
import com.core.weapons.EnergyWeapons;
import com.core.weapons.MeleeWeapons;
import com.core.weapons.RangedWeapon;
import com.core.weapons.Weapons;

public class ItemHandler{

	public ItemHandler() {
		RangedWeapon w = new RangedWeapon("Bolt Action Rifle",20,10,5);
		w.getName();
		RangedWeapon w2 = new RangedWeapon("Assault Rifle",10,50,1);
		RangedWeapon w3 = new RangedWeapon("Pump Shotgun",17,10,3);
		RangedWeapon w4 = new RangedWeapon("Pistol",10,20,2);
		RangedWeapon w5 = new RangedWeapon("Revolver",12,20,2);
		EnergyWeapons w6 = new EnergyWeapons("PulseGun",15,30,2);
		EnergyWeapons w7 = new EnergyWeapons("PulseRifle",20,35,3);
		EnergyWeapons w8 = new EnergyWeapons("Charge Pistol",10,35,2);
		EnergyWeapons w9 = new EnergyWeapons("Charge Rifle",15,45,3);
		MeleeWeapons w10 = new MeleeWeapons("Baton", 10, 2);
		MeleeWeapons w11 = new MeleeWeapons("Shock Baton", 15, 2);
		MeleeWeapons w12 = new MeleeWeapons("Emergancy Axe", 10, 2);
		MeleeWeapons w13 = new MeleeWeapons("Saw", 12, 2);
		MeleeWeapons w14 = new MeleeWeapons("Sword", 15, 2);
		Armour w15 = new Armour("Light Armour", 15);
		Armour w16 = new Armour("Medium Armour", 20);
		Armour w17 = new Armour("Heavy Armour", 25);
	}
}




