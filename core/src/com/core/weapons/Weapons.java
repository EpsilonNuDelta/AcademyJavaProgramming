package com.core.weapons;

import java.util.Random;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
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
	
	public Weapons(String n, int d, int r, TiledMap map)
	{
		name = n;
		damage = d;
		reload = r;
		Random rand = new Random();
		quality = qualities[rand.nextInt(5)];
		x = 64;
		y = 64;
		sprite = name+".png";
		boolean valid = false;
		float tileX = 0;
		float tileY = 0;
		while(!valid)
		{
			Random ra = new Random();
			tileX = ra.nextInt(map.getProperties().get("width", Integer.class)*map.getProperties().get("tilewidth", Integer.class));
			tileY = ra.nextInt(map.getProperties().get("height", Integer.class)*map.getProperties().get("tileheight", Integer.class));
			tileX = ((float)Math.ceil((tileX)/16)*16)-16;
			tileY = ((float)Math.ceil((tileY)/16)*16)-16;
	    	TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(1);
        	Cell cell = layer.getCell(((int)tileX/16)+1, ((int)tileY/16));
        	valid = (cell==null);
		}
		x = tileX;
		y = tileY;
		System.out.println(x/16+","+y/16);
	}
	
	public String getName() {
		return quality+" "+name;
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float x2) {
		x = x2;
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
}
