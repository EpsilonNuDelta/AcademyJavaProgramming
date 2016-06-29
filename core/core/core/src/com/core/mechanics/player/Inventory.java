package com.core.mechanics.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Inventory {

	private String sprite;
	
	public Inventory()
	{
		sprite = "Inventory.png";
	}
	
	public Sprite sprite()
	{
		Sprite is = new Sprite(new Texture(sprite));
		is.setScale(4.0f);
		return is;
	}
}
