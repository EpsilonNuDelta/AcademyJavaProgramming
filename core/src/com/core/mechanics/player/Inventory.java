package com.core.mechanics.player;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.core.armors.Armour;
import com.core.weapons.Weapons;

public class Inventory {

	private String sprite;
	private Weapons gun;
	private ArrayList<Armour> armor;
	
	
	public Inventory()
	{
		sprite = "Inventory.png";
		armor = new ArrayList<Armour>();
	}
	
	public Sprite sprite()
	{
		Sprite is = new Sprite(new Texture(sprite));
		is.setScale(4.0f);
		return is;
	}
	
	public void setGun(Weapons newGun)
	{
		gun = newGun;
	}
	
	public Weapons getGun()
	{
		return gun;
	}
	
	public void addArmor(Armour a)
	{
		armor.add(a);
	}
	
	public Armour getArmor(int am)
	{
		return armor.get(am);
	}
	
	public int getArmorSize()
	{
		return armor.size();
	}
	
}
