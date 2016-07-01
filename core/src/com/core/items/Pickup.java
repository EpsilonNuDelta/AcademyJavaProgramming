package com.core.items;

import java.util.ArrayList;
import java.util.HashMap;
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

	Pickup(String n, TiledMap map, HashMap<Cell,String> cells, ArrayList<Cell> keys)
	{
		sprite = n+".png";
		boolean valid = false;
		x = -16;
		y = -16;
		float tileX = 0;
		float tileY = 0;
		int tries = 0;
		Cell cell = null;
    	TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(5);
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
