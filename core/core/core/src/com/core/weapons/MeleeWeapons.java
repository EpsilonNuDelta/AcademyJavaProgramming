package com.core.weapons;

public class MeleeWeapons extends Weapons {
	protected int wait;
	
	public MeleeWeapons(String n, int d, int w)
	{
		super(n,d,0);
		wait = w;
	}
	
	public int getWait () {
		switch(quality){
		case "Awful":
			return (int)Math.round(wait-0);
		case "Stoddy":
			return (int)Math.round(wait-0);
		case "Stock":
			return (int)Math.round(wait-0);
		case "Great":
			return (int)Math.round(wait-1);
		case "Epic":
			return (int)Math.round(wait-1);
		default:
			return wait;
		}
	}

}
