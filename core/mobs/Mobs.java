package com.core.mobs;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Mobs {

	int[] mobHealth = { 50, 30, 125, 225, 75, 575 };
	int[] mobAttack = { 10, 15, 25, 5, 50, 60 };
	int[] mobArmor = { 5, 3, 2, 0, 3 , 10};
	int[] mobBaseExp = { 10, 13, 26, 56, 43, 545 };
	int[] mobExpRange = { 5, 6, 9, 11, 44, 465 };
	String[] mobNames = { "Goo", "Robot", "Warrior", "Golem", "Alien", "Goo Warlord" };
	
protected int timer;
protected String sprite;

	Random rand = new Random();
	
	Mobs(int type) {
		timer = 0;
		experience = mobBaseExp[type]+rand.nextInt(mobExpRange[type])+1;
	}
	
	private int experience;
	private int health;
	private int attack;
	private int armor;
	protected float x;
	protected float y;
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int h){
		health = h;
	}
	
	public int getExp() {
		return experience;
	}
	
	public void setExp(int exp){
		experience = exp;
	}

	public int getAttack() {
		return attack;
	}
	
	public void setAttack(int a){
		attack = a;
	}
	
	public int getArmor() {
		return armor;
	}
	
	public void setArmor(int ar){
		armor = ar;
	}
	
	public String chooseRandom(int type) {
		return mobNames[type];
	}

	public Sprite sprite() {
		Sprite s = new Sprite(new Texture(sprite));
		s.setPosition(x, y);
		return s;
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
	public void move() {
		timer++;
		if(timer%60==0)
		{
			Random rand = new Random();
			x += (rand.nextInt(3)-1)*16;
			y += (rand.nextInt(3)-1)*16;
			if(y<0)
				y = 0;
			if(x<0)
				x = 0;
		}
	}
	
}
