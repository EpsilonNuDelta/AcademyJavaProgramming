package com.core.mechanics.player;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.core.armors.Armour;
import com.core.items.Item;
import com.core.weapons.Weapons;

public class Inventory {

	private String sprite;
	private Weapons gun;
	private Armour armor;
	private Item item;
	private Player p;
	
	
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
	public void setItem(Item newItem)
	{
		item = newItem;
	}
	
	public Item getItem()
	{
		return item;
	}
	public void setP(Player newP)
	{
		p = newP;
	}
	
	public Player getP()
	{
		return p;
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
