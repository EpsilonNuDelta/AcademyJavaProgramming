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
		sprite = "Slasherino.png";
	}

	@Override
	public boolean reachedEnd() {
		return ended||(startX!=x||startY!=y);
	}
	
	@Override
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
