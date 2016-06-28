package com.core.weapons;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Weapons {
	protected String name;
	protected int damage;
	protected int reload;
	protected String quality;
	protected final String[] qualities = {"Shoddy","Awful","Stock","Great","Epic"};
	protected String sprite;
	protected float x;
	protected float y;
	
	public Weapons(String n, int d, int r)
	{
		name = n;
		damage = d;
		reload = r;
		Random rand = new Random();
		quality = qualities[rand.nextInt(5)];
		x = 1000;
		y = 1000;
		sprite = name+" Right.png";
	}
	
	public String getName() {
		return quality+" "+name;
	}
	
	public int getDamage() {
		switch(quality) {
		case "Awful":
			return (int)Math.round(damage+0);
		case "Shoddy":
			return (int)Math.round(damage+10);
		case "Stock":
			return (int)Math.round(damage+20);
		case "Great":
			return (int)Math.round(damage+30);
		case "Epic":
			return (int)Math.round(damage+45);
		default:
			return damage;
		}
	}
	public Sprite sprite() {
		return new Sprite(new Texture(sprite));
	}
}