package com.core.weapons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.core.mechanics.player.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
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
	protected String shot;
	protected int timer;
	protected boolean jammed;

	public Weapons(String n, String sound, int d, int r, float x1, float y1)
	{
		name = n;
		damage = d;
		reload = r;
		x = x1;
		y = y1;
		sprite = name+".png";
		shot = sound;
		timer = 0;
		jammed = false;
		Random rand = new Random();
		quality = qualities[rand.nextInt(5)];
	}
	public Weapons(String n, String sound, int d, int r, TiledMap map, HashMap<Cell,String> cells, ArrayList<Cell> keys)
	{
		name = n;
		damage = d;
		reload = r;
		Random rand = new Random();
		quality = qualities[rand.nextInt(5)];
		x = -16;
		y = -16;
		sprite = name+".png";
		shot = sound;
		boolean valid = false;
		float tileX = 0;
		float tileY = 0;
		int tries = 0;
		Cell cell = null;
    	TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(3);
		while(!valid&&tries<100)
		{
			Random ra = new Random();
			Cell key = keys.get(ra.nextInt(keys.size()));
			tileX = Integer.parseInt(cells.get(key).split(",")[0]);
			tileY = Integer.parseInt(cells.get(key).split(",")[1]);
        	cell = layer.getCell((int)(tileX/16), (int)(tileY/16));
        	valid = (cell!=null);
        	tries++;
		}
		layer.setCell((int)(tileX/16), (int)(tileY/16),null);
		if(cell!=null)
		{
			x = tileX;
			y = tileY;
		}
		timer = 0;
		jammed = false;
	}
	
	public String getName() {
		return quality+" "+name;
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float nX) {
		x = nX;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float nY) {
		y = nY;
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
		Sprite ps = new Sprite(new Texture(sprite));
		ps.setPosition(x, y);
		return ps;
	}
	public Sound sound() {
		return Gdx.audio.newSound(Gdx.files.internal(shot));
	}
	public boolean fire(Player p) {
		return true;
	}
	public void reload() {
		if(jammed == true)
		{
			timer++;
			if(timer%reload==0)
			{
				jammed = false;
			}
		}
	}
	public boolean isJammed() {
		return jammed;
	}
}
