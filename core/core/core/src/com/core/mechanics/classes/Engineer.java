package com.core.mechanics.classes;

import com.core.mechanics.player.Player;

public class Engineer extends Player{

	protected Engineer( int h, int a, int am, int e, int xp) {
		super("Engineer", 125, 15, 15, 100, 0);
	}
	
	@Override
	public void setSprite(String newSprite) {
		if(newSprite == "left")
		{
			if(sprite == "EngineerLeft.png")
				sprite = "Engineerwalkleft.png";
			else
				sprite = "EngineerLeft.png";
		}
		if(newSprite == "right")
		{
			if(sprite == "EngineerRight.png")
				sprite = "Engineerwalkright.png";
			else
				sprite = "EngineerRight.png";
		}
	}

}
