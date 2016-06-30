package com.core.mobs;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.core.OrthogonalTiledMapRendererWithSprites;


public class HostileCreation {
private String sprite;
TiledMap tiledMap;
	private ArrayList<Mobs> hostiles;
	
	public HostileCreation() {
		hostiles = new ArrayList<Mobs>();
		hostiles.add(new Cadet());
    	hostiles.add(new Cadet());
    	hostiles.add(new Cadet());
    	hostiles.add(new Cadet());
    	hostiles.add(new Cadet());
    	hostiles.add(new Spaceman());
    	hostiles.add(new Spaceman());
    	hostiles.add(new Slime());
    	hostiles.add(new Blockbot());
    	hostiles.add(new Alien());	
	
	}
	
	public Mobs getH(int i) {
		return hostiles.get(i);
	}
	
	public int getHSize() {
		return hostiles.size();
	}

	Sound splaterino = Gdx.audio.newSound(Gdx.files.internal("237928__foolboymedia__messy-splat-3a.wav"));

	public void remH(int i)
	
	{
		splaterino.play(0.2f);
		hostiles.remove(i);

	}
}
