package com.core.mechanics.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerHead {
	private String sprite;
	private float x;
	private float y;

	public PlayerHead() {
		sprite = "defaulthead.png";
		x = 60;
		y = 586;
	}
	
	public void setSprite(String s)
	{
		sprite = s;
	}
	
	public Sprite sprite()
	{
		Sprite s = new Sprite(new Texture(sprite));
		s.setPosition(x, y);
		s.setScale(4.0f);
		return s;
	}
	
	public String getSprite()
	{
		return sprite;
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
