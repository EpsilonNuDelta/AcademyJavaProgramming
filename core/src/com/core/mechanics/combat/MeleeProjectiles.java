package com.core.mechanics.combat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.core.mechanics.player.Player;
import com.core.mobs.Mobs;

public class MeleeProjectiles extends Projectiles {
	private float startX;
	private float startY;
	public MeleeProjectiles(float newX, float newY, int d, int dam, int spd){
		super(newX,newY,d,dam,spd);
		startX = newX;
		startY = newY;
		sprite = "slasherino.png";
	}

	@Override
	public boolean reachedEnd() {
		return ended||(startX!=x||startY!=y);
	}
	
	@Override
	public boolean dealDamage(Mobs m, Player p) //if the coordinates are the same as the end ones the deal damage
	{
		if(x == m.getX() && y == m.getY()){
			int dam = (int)Math.round(damage*((50+p.getMeleeAttack())/(50+m.getArmor())));
			m.setHealth(m.getHealth()-dam);
			return true;
		}
		return false;
	}
}
