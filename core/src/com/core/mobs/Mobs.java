package com.core.mobs;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class Mobs {

	int[] mobHealth = { 50, 30, 125, 225, 75, 575 };
	int[] mobAttack = { 10, 15, 25, 5, 50, 60 };
	int[] mobArmor = { 5, 3, 2, 0, 3 , 10};
	int[] mobBaseExp = { 10, 13, 26, 56, 43, 545 };
	int[] mobExpRange = { 5, 6, 9, 11, 44, 465 };
	String[] mobNames = { "Slime", "Spaceman", "Cadet", "Blockbot", "Alien", "Slime Warlord" };
	
protected int timer;
protected String sprite;

	Random rand = new Random();
	
	Mobs(int type) {
		timer = 0;
		experience = mobBaseExp[type]+rand.nextInt(mobExpRange[type])+1;
		aggro = true;
		x = 96;
		y = 96;
	}
	
	private int experience;
	private int health;
	private int attack;
	private int armor;
	private boolean aggro;
	protected float x;
	protected float y;
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int h){
		health = h;
	}
	
	public int getExp() {
		return experience;
	}
	
	public void setExp(int exp){
		experience = exp;
	}

	public int getAttack() {
		return attack;
	}
	
	public void setAttack(int a){
		attack = a;
	}
	
	public int getArmor() {
		return armor;
	}
	
	public void setArmor(int ar){
		armor = ar;
	}
	
	public String chooseRandom(int type) {
		return mobNames[type];
	}

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
	public void setAggro(boolean a)
	{
		aggro = a;
	}
	
	private boolean checkValid(float x, float y, TiledMap m)
	{
    	TiledMapTileLayer layer = (TiledMapTileLayer)m.getLayers().get(1);
		Cell cell = layer.getCell((int)(x/16), (int)((y/16)));
    	return (cell==null);
	}
	
	private float constrainX(float newX, TiledMap m)
	{
		if(newX<0)
			newX = 0;
		if(newX>(m.getProperties().get("width",Integer.class)*m.getProperties().get("tilewidth",Integer.class)))
			newX = m.getProperties().get("width",Integer.class)*m.getProperties().get("tilewidth",Integer.class);
		return newX;
	}

	private float constrainY(float newY, TiledMap m)
	{
		if(newY<0)
			newY = 0;
		if(newY>(m.getProperties().get("height",Integer.class)*m.getProperties().get("tileheight",Integer.class)))
			newY= m.getProperties().get("height",Integer.class)*m.getProperties().get("tileheight",Integer.class);
		return newY;
	}
	
	public void move(float pX, float pY, TiledMap m) {
		timer++;
		if(timer%60==0)
		{
			float newX = x;
			float newY = y;
			Random rand = new Random();
			newX += (rand.nextInt(3)-1)*16;
			newY += (rand.nextInt(3)-1)*16;
        	if(checkValid(newX, newY, m))
        	{
    			if(aggro)
    			{
    				if(newX>pX)
    					newX -= 16;
    				else if(newX<pX)
    					newX += 16;
    				if(newY>pY)
    					newY -= 16;
    				else if(newY<pY)
    					newY += 16;
    			}
	        	if(checkValid(newX,newY,m))
	        	{
	        		x = constrainX(newX,m);
	        		y = constrainY(newY,m);
	        	}
        	}
		}
	}
	
}
