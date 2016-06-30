package com.core.armors;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class Armour {
	protected String name;
	protected int armour;
	protected String sprite;
	protected float x;
	protected float y;
	
	
	
	public Armour(String n, int ar, TiledMap map)
	{
		armour = ar;
		name = n;
		sprite = name+".png";
		Random r = new Random();
		boolean valid = false;
		float tileX = 0;
		float tileY = 0;
		while(!valid)
		{
			tileX = r.nextInt(map.getProperties().get("width", Integer.class)*map.getProperties().get("tilewidth", Integer.class));
			tileY = r.nextInt(map.getProperties().get("height", Integer.class)*map.getProperties().get("tileheight", Integer.class));
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
	public float getX() {
		return x;
	}
	
	public void setX(int nX) {
		x = nX;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(int nY) {
		y = nY;
	}
	public Sprite sprite() {
		Sprite ps = new Sprite(new Texture(sprite));
		ps.setPosition(x, y);
		return ps;
	}
	

}
