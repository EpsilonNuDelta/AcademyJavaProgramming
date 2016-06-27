package com.core.mechanics.classes;

import com.core.mechanics.player.Player;

public class Marksman extends Player{

	public Marksman() {
		super("Marksman", 100, 10, 10, 100, 0);
		sprite = "MarksmanRight.png";
	}
	
	@Override
	public void setSprite(String newSprite) {
		if(newSprite == "left")
		{
			if(sprite == "MarksmanLeft.png")
				sprite = "Marksmanwalkleft.png";
			else
				sprite = "MarksmanLeft.png";
		}
		if(newSprite == "right")
		{
			if(sprite == "MarksmanRight.png")
				sprite = "Marksmanwalkright.png";
			else
				sprite = "MarksmanRight.png";
		}
	}
}
