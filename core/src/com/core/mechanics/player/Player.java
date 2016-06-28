package com.core.mechanics.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
	
	protected String name;
	protected int attack;
	private int health;
	private int armor;
	private int energy;
	private int exp;
	public String sprite;
	protected float x;
	protected float y;
	
	protected Player(String n, int h, int a, int am, int e, int xp)
	{
		name = n;
		health = h;
		attack = a;
		armor = am;
		energy = e;
		exp = xp;
		x = 128;
		y = 128;
		sprite = n + "down.png";
	}
	
	public void setName(String n)
	{
		name = n;
	}
	public void setHealth(int h)
	{
		health = h;
	}
	public int getHealth()
	{
		return health;
	}
	public void setAttack(int a)
	{
		attack = a;
	}
	public int getAttack()
	{
		return attack;
	}
	public void setArmor(int am)
	{
		armor = am;
	}
	public int getArmor()
	{
		return armor;
	}
	public void setEnergy(int e)
	{
		energy = e;
	}
	public int getEnergy()
	{
		return energy;
	}
	public void setXP(int xp)
	{
		exp = xp;
	}
	public int getXP()
	{
		return exp;
	}
	public void setX(float newX)
	{
		x = newX;
	}
	public float getX()
	{
		return x;
	}
	public void setY(float newY)
	{
		y = newY;
	}
	public float getY()
	{
		return y;
	}
	public void setSprite(String newSprite)
	{
		if(newSprite.equals("left"))
		{
			if(sprite.equals(name + "left.png"))
				sprite = name + "walkleft.png";
			else	
				sprite = name + "left.png";
		}
		if(newSprite.equals("right"))
		{
			if(sprite.equals(name + "right.png"))
				sprite = name + "walkright.png";
			else	
				sprite = name + "right.png";
		}
		if(newSprite.equals("up"))
			sprite = name + "up.png";
		if(newSprite.equals("down"))
			sprite = name + "down.png";
	}
	public Sprite sprite() {
		Sprite ps = new Sprite(new Texture(sprite));
		ps.setPosition(x, y);
		return ps;
	}
	

}
