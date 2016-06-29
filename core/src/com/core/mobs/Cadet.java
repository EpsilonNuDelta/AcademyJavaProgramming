package com.core.mobs;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class Cadet extends Mobs{
	public Cadet(){
		super(2);
		sprite = "cadet.png";
	}

}
