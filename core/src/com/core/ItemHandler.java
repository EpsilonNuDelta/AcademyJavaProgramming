package com.core;

import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.core.armors.Armour;
import com.core.items.*;
import com.core.mobs.Mobs;
import com.core.weapons.EnergyWeapons;
import com.core.weapons.MeleeWeapons;
import com.core.weapons.RangedWeapon;
import com.core.weapons.Weapons;

public class ItemHandler{
	private ArrayList<Weapons> w;
	private ArrayList<Armour> a;
	private ArrayList<Pickup> p;
	private ArrayList<Item> it;
	public final static String LASER_SOUND = "89489_mparsons99_laser1_converted.wav";
	public final static String BULLET_SOUND = "150137_gregsmedia_gun-shot-sound-01_converted.wav";
	public final static String SHOTGUN_SOUND = "Shotgun Sound.mp3";
	public final static String MELEE_SOUND = "60009_qubodup_swosh-22.wav";

	public ItemHandler(TiledMap map) {
		w = new ArrayList<Weapons>();
		a = new ArrayList<Armour>();
		p = new ArrayList<Pickup>();
		it = new ArrayList<Item>();
		w.add(new RangedWeapon("BoltActionRifle",BULLET_SOUND,15,5,10,map));
		w.add(new RangedWeapon("AssaultRifle",BULLET_SOUND,5,1,50,map));
		//w.add(new RangedWeapon("PumpShotgun",SHOTGUN_SOUND,17,10,3,map));
		//w.add(new RangedWeapon("Pistol",10,20,2,map));
		w.add(new RangedWeapon("Revolver",BULLET_SOUND,7,2,20,map));
		//w.add(new EnergyWeapons("PulseGun",15,30,2,map));
		w.add(new EnergyWeapons("PulseRifle",LASER_SOUND,30,2,35,map));
		w.add(new EnergyWeapons("ChargePistolDiverTram16by16",LASER_SOUND,20,35,2,map));
		w.add(new EnergyWeapons("Charge Rifle16by16",LASER_SOUND,25,45,3,map));
		w.add(new MeleeWeapons("Baton", MELEE_SOUND,7, 2,map));
		w.add(new MeleeWeapons("ShockBaton", MELEE_SOUND,10, 2,map));
		w.add(new MeleeWeapons("EmergencyAxe", MELEE_SOUND,5, 2,map));
		w.add(new RangedWeapon("Grey AR", BULLET_SOUND,10, 2,15,map));
		w.add(new RangedWeapon("grey pistol", BULLET_SOUND,10, 2,15,map));
		w.add(new MeleeWeapons("Chainsaw KatanaDiverTram16by16", MELEE_SOUND,12, 2,map));
		w.add(new MeleeWeapons("Sword", MELEE_SOUND,12, 2,map));
		
		a.add(new Armour("LightArmor", 15, map));
		a.add(new Armour("MediumArmor", 20, map));
		
		for(int i = 0; i<5; i++)
			it.add(new Energy(map));
		for(int i = 0; i<5; i++)
			p.add(new Ammo(map));
		for(int i = 0; i<5; i++)
			it.add(new Health(map));
	}
	public Weapons getW(int i) {
		return w.get(i);
	}
	public void addW(Weapons i) {
		w.add(i);
	}
	public Item getI(int i) {
		return it.get(i);
	}
	public void addI(Item i) {
		it.add(i);
	}
	public Pickup getP(int i) {
		return p.get(i);
	}
	public void addP(Pickup i) {
		p.add(i);
	}
	public void addA(Armour i) {
		a.add(i);
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
	public int getISize() {
		return it.size();
	}
	
	public void remI(int i) {
		it.remove(i);
	}
	public int getPSize() {
		return p.size();
	}
	
	public void remP(int i) {
		p.remove(i);
	}
}




