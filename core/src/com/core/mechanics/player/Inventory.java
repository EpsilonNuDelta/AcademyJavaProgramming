package com.core.mechanics.player;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.core.armors.Armour;
import com.core.weapons.Weapons;

public class Inventory {

	private String sprite;
	private Weapons gun;
	private Armour armor;
	
	
	public Inventory()
	{
		sprite = "Inventory.png";
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
	
	public void setArmor(Armour a)
	{
		armor = a;
	}
	
	public Armour getArmor()
	{
		return armor;
	}
}
