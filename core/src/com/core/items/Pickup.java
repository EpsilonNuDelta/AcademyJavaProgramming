package com.core.items;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.core.mechanics.player.Player;

public class Pickup {
	protected String sprite;
	protected float x;
	protected float y;

	Pickup(String n, TiledMap map)
	{
		sprite = n+".png";
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
	
	public Sprite sprite() {
		Sprite ps = new Sprite(new Texture(sprite));
		ps.setPosition(x, y);
		return ps;
	}
	
	public void pickUp(Player p)
	{
		
	}
}
