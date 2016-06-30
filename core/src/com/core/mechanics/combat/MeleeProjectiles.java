package com.core.mechanics.combat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MeleeProjectiles extends Projectiles {
	private float startX;
	private float startY;
	public MeleeProjectiles(float newX, float newY, int d, int dam, int spd){
		super(newX,newY,d,dam,spd);
		startX = newX;
		startY = newY;
		sprite = "Blank.png";
	}

	@Override
	public boolean reachedEnd() {
		return ended||(startX!=x||startY!=y);
	}
}
