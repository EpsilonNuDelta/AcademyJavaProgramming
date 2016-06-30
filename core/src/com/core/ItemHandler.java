package com.core;

import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.core.armors.Armour;
import com.core.mobs.Mobs;
import com.core.weapons.EnergyWeapons;
import com.core.weapons.MeleeWeapons;
import com.core.weapons.RangedWeapon;
import com.core.weapons.Weapons;

public class ItemHandler{
	private ArrayList<Weapons> w;
	private ArrayList<Armour> a;

	public ItemHandler(TiledMap map) {
		w = new ArrayList<Weapons>();
		a = new ArrayList<Armour>();
		w.add(new RangedWeapon("BoltActionRifle",20,10,5,map));
		w.add(new RangedWeapon("AssaultRifle",10,50,1,map));
		//w.add(new RangedWeapon("PumpShotgun",17,10,3,map));
		//w.add(new RangedWeapon("Pistol",10,20,2,map));
		w.add(new RangedWeapon("Revolver",12,20,2,map));
		//w.add(new EnergyWeapons("PulseGun",15,30,2,map));
		w.add(new EnergyWeapons("PulseRifle",20,35,3,map));
		w.add(new EnergyWeapons("ChargePistolDiverTram16by16",10,35,2,map));
		w.add(new EnergyWeapons("Charge Rifle16by16",15,45,3,map));
		w.add(new MeleeWeapons("Baton", 10, 2,map));
		w.add(new MeleeWeapons("ShockBaton", 15, 2,map));
		w.add(new MeleeWeapons("EmergencyAxe", 10, 2,map));
		w.add(new MeleeWeapons("Grey AR", 10, 2,map));
		w.add(new MeleeWeapons("grey pistol", 10, 2,map));
		w.add(new MeleeWeapons("Chainsaw KatanaDiverTram16by16", 12, 2,map));
		w.add(new MeleeWeapons("Sword", 15, 2,map));
		a.add(new Armour("LightArmor", 15, map));
		a.add(new Armour("MediumArmor", 20, map));
	}
	public Weapons getW(int i) {
		return w.get(i);
	}
	public void addW(Weapons i) {
		w.add(i);
	}
	public int getWSize() {
		return w.size();
	}
	
	public void remW(int i) {
		w.remove(i);
	}
	public Armour getA(int i) {
		return a.get(i);
	}
	
	public int getASize() {
		return a.size();
	}
	
	public void remA(int i) {
		a.remove(i);
	}
}




