package com.core.mechanics.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.core.mechanics.classes.*;

public class ChooseClass {
	private String name;
	private String sprite;
	private float x;
	private float y;
	
	public ChooseClass(String n, float x1, float y1)
	{
		name = n;
		sprite = name + "head.png";
		x = x1;
		y = y1;
	}
	
	public Sprite sprite()
	{
		Sprite s = new Sprite(new Texture(sprite));
		s.setPosition(x, y);
		s.setScale(4.0f);
		return s;
	}
	
	public Player pick()
	{
		switch(name)
		{
		case "Juggernaut":
			return new Juggernaut();
		case "Engineer":
			return new Engineer();
		case "Secret Agent":
			return new SecretAgent();
		default:
			return new Marksman();
		}
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
	public String getName() 
	{
		return name;
	}
}
