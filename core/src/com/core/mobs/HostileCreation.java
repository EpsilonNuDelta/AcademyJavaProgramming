package com.core.mobs;

import java.util.ArrayList;

public class HostileCreation {

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
	
}
