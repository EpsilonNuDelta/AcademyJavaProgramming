package com.core.armors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Armour {
	protected String name;
	protected int armour;
	protected String sprite;
	protected float x;
	protected float y;
	
	
	public Armour(String n, int ar)
	{
		armour = ar;
		name = n;
		sprite = name+".png";
		x= 80;
		y= 80;
	}
	public Sprite sprite() {
		Sprite ps = new Sprite(new Texture(sprite));
		ps.setPosition(x, y);
		return ps;
	}
	

}
