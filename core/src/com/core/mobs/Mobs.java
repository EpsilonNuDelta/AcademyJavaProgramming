package com.core.mobs;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.core.ItemHandler;
import com.core.Main;
import com.core.mechanics.player.Player;
import com.core.weapons.MeleeWeapons;
import com.core.weapons.RangedWeapon;
import com.core.weapons.Weapons;

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
		attack = mobAttack[type];
		armor = mobArmor[type];
		experience = mobBaseExp[type]+rand.nextInt(mobExpRange[type])+1;
		health = mobHealth[type];
		aggro = true;
		x = 96;
		y = 96;
	}
	
	private int experience;
	private float health;
	private float attack;
	private float armor;
	private boolean aggro;
	protected float x;
	protected float y;
	
	public float getHealth() {
		return health;
	}
	
	public void setHealth(float h){
		health = h;
	}
	
	public int getExp() {
		return experience;
	}
	
	public void setExp(int exp){
		experience = exp;
	}

	public float getAttack() {
		return attack;
	}
	
	public void setAttack(float a){
		attack = a;
	}
	
	public float getArmor() {
		return armor;
	}
	
	public void setArmor(float ar){
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
	
	private boolean checkValid(float x1, float y1, TiledMap m, HostileCreation h, float pX, float pY)
	{
    	TiledMapTileLayer layer = (TiledMapTileLayer)m.getLayers().get(1);
    	TiledMapTileLayer dLayer = (TiledMapTileLayer)m.getLayers().get(2);
    	
    	Cell cell = layer.getCell((int)((x1/16)), (int)((y1/16)));
    	Cell doorCell = dLayer.getCell((int)((x1/16)), (int)((y1/16)));
    	boolean solid = ((cell!=null)||(doorCell!=null&&(doorCell.getTile().getId()==Main.DOOR_UP||doorCell.getTile().getId()==Main.DOOR_DOWN||doorCell.getTile().getId()==Main.DOOR_LEFT||doorCell.getTile().getId()==Main.DOOR_RIGHT)));
		boolean existingMob = false;
		for(int i = 0; i < h.getHSize(); i++)
		{
			if(h.getH(i).getX()==x1&&h.getH(i).getY()==y1)
			{
				existingMob = true;
				break;
			}
		}
    	return (!solid&&pX!=x1&&pY!=y1&&!existingMob);
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
	
	public void move(float pX, float pY, TiledMap m, Player p, HostileCreation h) {
		timer++;
		float newX = x;
		float newY = y;
		if(timer%45==0)
		{
			Random rand = new Random();
			newX += (rand.nextInt(3)-1)*16;
			newY += (rand.nextInt(3)-1)*16;
        	if(checkValid(newX, newY, m, h, pX, pY))
        	{
        		x = constrainX(newX,m);
        		y = constrainY(newY,m);
        	}
		}
		if(timer%60==0)
		{
			newX = x;
			newY = y;
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
    			dealPDamage(p,newX,newY);
			}
        	if(checkValid(newX,newY,m,h, pX, pY))
        	{
        		x = constrainX(newX,m);
        		y = constrainY(newY,m);
        	}
		}
	}

	public Weapons getDrop(TiledMap map) {
		if(rand.nextInt(11) < 3);
		{
			return new RangedWeapon("Grey AR", ItemHandler.LASER_SOUND,15, 10, 5, x, y);
		}

	
	}
	private boolean dealPDamage(Player p, float x1, float y1) //if the coordinates are the same as the end ones the deal damage
	{
		if(p.getX() == x1 && p.getY() == y1){
			p.setHealth(p.getHealth()-(attack*(50/(50+p.getArmor()))));
			return true;
		}
		return false;
	}
	
}
