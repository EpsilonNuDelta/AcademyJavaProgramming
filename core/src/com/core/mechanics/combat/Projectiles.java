package com.core.mechanics.combat;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.core.mechanics.player.Player;
import com.core.mobs.Mobs;

public class Projectiles {
	
	protected int damage;
	protected int speed;
	protected boolean ended;
	protected int dir;
	protected float x;
	protected float y;
	protected String sprite;
	protected int timer = 0;

	public Projectiles(float newX, float newY, int d, int dam, int spd)
	{
		damage = dam;
		speed = spd;
		sprite = "Laezabeem.png";
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
			}
			else if(dir==1)
			{
				x -= 16;
			}
			else if(dir==2)
			{
				y += 16;
			}
			else if(dir==3)
			{
				y -= 16;
			}
			timer = 0;
		}
	}
	public boolean dealDamage(Mobs m, Player p) //if the coordinates are the same as the end ones the deal damage
	{
		if(x == m.getX() && y == m.getY()){
			int dam = (int)Math.round(damage*((50+p.getRangedAttack())/(50+m.getArmor())));
			m.setHealth(m.getHealth()-dam);
			return true;
		}
		return false;
	}
	public Sprite sprite()
	{
		Sprite s = new Sprite(new Texture(sprite)); //creates the sprite
		s.setPosition(x, y); //sets the sprite to the coordinates of the projectile
		if(dir==1)
			s.setFlip(true, false);
		else if(dir==2)
			s.rotate(90);
		else if(dir==3)
			s.rotate(-90);
		return s;
	}
}
