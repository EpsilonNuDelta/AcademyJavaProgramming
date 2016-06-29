package com.core.mobs;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class Cadet{
	private String sprite;
	public Cadet() {
		sprite = "alien cadet.png";
		timer = 0;
	}

	
float x;
float y;
private int timer;

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
