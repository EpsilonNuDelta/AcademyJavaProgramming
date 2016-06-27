package com.core.mechanics.player;

import com.badlogic.gdx.graphics.Texture;

public class Player {
	
	protected String name;
	protected int attack;
	private int health;
	private int armor;
	private int energy;
	private int exp;
	protected String sprite;
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
		x = 1000;
		y = 1000;
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
	public Texture sprite() {
		return new Texture(sprite);
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
	

}
