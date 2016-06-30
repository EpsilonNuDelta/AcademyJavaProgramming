package com.core.decals;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Decal {
	Random rand = new Random();
	private String name;
	private float x;
	private float y;
	private String sprite;
	public Decal(String n, float x1, float y1)
	{
		name = n;
		x = x1;
		y = y1;
		sprite = name + ".png";
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
	
	public Sprite sprite() {

		Sprite ps = new Sprite(new Texture(sprite));
		ps.setPosition(x, y);
		return ps;
	}
}
