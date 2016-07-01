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
	public final static String LASER_SOUND = "89489_mparsons99_laser1_converted.wav";
	public final static String LASER_CANNON_SOUND = "Laser_Cannon-Mike_Koenig-797224747.mp3";
	public final static String SHOTGUN_SOUND = "Shotgun Sound.mp3";
	public final static String MELEE_SOUND = "60009_qubodup_swosh-22.wav";

	public ItemHandler(TiledMap map) {
		w = new ArrayList<Weapons>();
		a = new ArrayList<Armour>();
		w.add(new RangedWeapon("BoltActionRifle",LASER_SOUND,20,10,5,map));
		w.add(new RangedWeapon("AssaultRifle",LASER_SOUND,10,50,1,map));
		//w.add(new RangedWeapon("PumpShotgun",SHOTGUN_SOUND,17,10,3,map));
		//w.add(new RangedWeapon("Pistol",10,20,2,map));
		w.add(new RangedWeapon("Revolver",LASER_CANNON_SOUND,12,20,2,map));
		//w.add(new EnergyWeapons("PulseGun",15,30,2,map));
		w.add(new EnergyWeapons("PulseRifle",LASER_SOUND,20,35,3,map));
		w.add(new EnergyWeapons("ChargePistolDiverTram16by16",LASER_SOUND,10,35,2,map));
		w.add(new EnergyWeapons("Charge Rifle16by16",LASER_SOUND,15,45,3,map));
		w.add(new MeleeWeapons("Baton", MELEE_SOUND,10, 2,map));
		w.add(new MeleeWeapons("ShockBaton", MELEE_SOUND,15, 2,map));
		w.add(new MeleeWeapons("EmergencyAxe", MELEE_SOUND,10, 2,map));
		w.add(new RangedWeapon("Grey AR", LASER_SOUND,10, 2,5,map));
		w.add(new RangedWeapon("grey pistol", LASER_SOUND,10, 2,5,map));
		w.add(new MeleeWeapons("Chainsaw KatanaDiverTram16by16", MELEE_SOUND,12, 2,map));
		w.add(new MeleeWeapons("Sword", MELEE_SOUND,15, 2,map));
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




