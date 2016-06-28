package com.core.mobs;

import java.util.Random;

public class Mobs {

	int[] mobHealth = { 50, 30, 125, 225, 75, 575 };
	int[] mobAttack = { 10, 15, 25, 5, 50, 60 };
	int[] mobArmor = { 5, 3, 2, 0, 3 , 10 };
	int[] mobBaseExp = { 10, 13, 26, 56, 43, 545 };
	int[] mobExpRange = { 5, 6, 9, 11, 44, 465 };
	String[] mobNames = { "Slime", "Spaceman", "Alien Cadet", "Blockbot", "Alien", "Goo Warlord" };
	
	Random rand = new Random();
	
	Mobs(int type) {
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

	public void setX(float newX){
		x = newX;
	}
	
	public float getX(){
		return x;
	}
	
	public void setY(float newY){
		y = newY;
	}
	
	public float getY() {
		return y;
	}
	
}
