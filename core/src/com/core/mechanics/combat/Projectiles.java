package com.core.mechanics.combat;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.core.mobs.Mobs;

public class Projectiles {
	
	private int damage;
	private int speed;
	private boolean ended;
	private int dir;
	private float x;
	private float y;
	private String sprite;
	protected boolean up;
	protected int timer = 0;

	public Projectiles(float newX, float newY, int d)
	{
		damage = 5;
		speed = 1;
		sprite = "Laezabeem.png";
		up = false;
		x = newX;
		y = newY;
		dir = d;
		ended = false;
	}
	
	public void setDamage(int d)
	{
		damage = d;
	}
	public int getDam()
	{
		return damage;
	}
	public boolean reachedEnd() {
		return ended;
	}
	public void end() {
		ended = true;
	}
	public void setSpeed(int spd)
	{
		speed = spd;
	}
	public int getSpeed()
	{
		return speed;
	}
	public void move()
	{
		timer++;
		if(timer%3==0)
		{
			if(dir==0)
			{
				x += 16;
				up = false;
			}
			else if(dir==1)
			{
				x -= 16;
				up = false;
			}
			else if(dir==2)
			{
				y += 16;
				up = true;
			}
			else if(dir==3)
			{
				y -= 16;
				up = true;
			}
			timer = 0;
		}
	}
	public boolean dealDamage(Mobs m) //if the coordinates are the same as the end ones the deal damage
	{
		if(x == m.getX() && y == m.getY()){
			m.setHealth(m.getHealth()-damage);
			return true;
		}
		return false;
	}
	public Sprite sprite()
	{
		Sprite s = new Sprite(new Texture(sprite)); //creates the sprite
		s.setPosition(x, y); //sets the sprite to the coordinates of the projectile
		if(up)
			s.rotate(90);
		return s;
	}
}
