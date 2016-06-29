package com.core;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.core.armors.Armour;
import com.core.mechanics.classes.Marksman;
import com.core.mechanics.combat.Projectiles;
import com.core.mechanics.player.Inventory;
import com.core.mechanics.player.Player;
import com.core.mobs.Cadet;
import com.core.mobs.Mobs;
import com.core.mobs.Slime;
import com.core.mobs.Spaceman;
import com.core.weapons.Weapons;

public class Main extends ApplicationAdapter implements InputProcessor {
    Texture img;
    TiledMap tiledMap;
    OrthographicCamera camera;
    OrthogonalTiledMapRendererWithSprites tiledMapRenderer;
    Player p;
    Projectiles proj;
    Mobs m;
    Weapons wp;
    Weapons wp2;
    Armour ar;
    Spaceman s;
    Slime sl;
    Cadet c;
    Weapons cr8;
    ArrayList<Mobs> hostiles;
    private SpriteBatch batch;
    float w;
    float h;
    private ArrayList<Projectiles> projectiles;
    private boolean invOpen;
    Inventory inv;
    private ArrayList<Inventory> inventory;
    private ArrayList<Weapons> weapons;
    private ArrayList<Armour> armour;
    
    @Override
    public void create () {
    	Gdx.graphics.setWindowedMode(1024, 768);
    	w = Gdx.graphics.getWidth();
    	h = Gdx.graphics.getHeight();
       	p = new Marksman();
        c = new Cadet();
        s = new Spaceman();
        sl = new Slime();
        
        
        
    	hostiles = new ArrayList<Mobs>();
    	hostiles.add(c);
    	hostiles.add(new Cadet());
    	hostiles.add(new Cadet());
    	hostiles.add(new Cadet());
    	hostiles.add(new Cadet());
    	hostiles.add(s);
    	hostiles.add(new Spaceman());
    	hostiles.add(sl);
<<<<<<< HEAD
    	hostiles.add(new Blockbot());
    	hostiles.add(new Alien());
    	armour = new ArrayList<Armour>();
    	weapons = new ArrayList<Weapons>();
        weapons.add(new Weapons("grey ar", 10, 20));
        weapons.add(new Weapons("grey pistol", 10, 20));
        armour.add(new Armour("mediumarmordivertram16by16",15));
        weapons.add(new Weapons("crate", 10, 20));
=======
 
        wp = new Weapons("grey ar", 10, 20);
        wp2 = new Weapons("grey pistol", 10, 20);
        
        cr8 = new Weapons("crate", 10, 20);
>>>>>>> master
        		
        weapons.get(0).setX(160);
        weapons.get(1).setY(160);
        weapons.get(2).setY(176);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.viewportHeight = h/4;
        camera.viewportWidth = w/4;
        camera.update();
        tiledMap = new TmxMapLoader().load("testington.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRendererWithSprites(tiledMap);
        Gdx.input.setInputProcessor(this);
        projectiles = new ArrayList<Projectiles>();
        inv = new Inventory();
        invOpen = false;
        Sound music = Gdx.audio.newSound(Gdx.files.internal("Theyre-Here_Looping.mp3"));
        music.play();
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camera.position.set(p.getX(),p.getY(), 0);
        if((camera.position.x-(camera.viewportWidth/2))<0)
        	camera.position.x = camera.viewportWidth/2;
        if((camera.position.y-(camera.viewportHeight/2))<0)
        if((camera.position.x+(camera.viewportWidth/2))>tiledMap.getProperties().get("width", Integer.class)*tiledMap.getProperties().get("tilewidth", Integer.class))
        		camera.position.x = tiledMap.getProperties().get("width", Integer.class)*tiledMap.getProperties().get("tilewidth", Integer.class)-(camera.viewportWidth/2);
        if((camera.position.y+(camera.viewportHeight/2))>tiledMap.getProperties().get("height", Integer.class)*tiledMap.getProperties().get("tileheight", Integer.class))
    		camera.position.y = tiledMap.getProperties().get("height", Integer.class)*tiledMap.getProperties().get("tileheight", Integer.class)-(camera.viewportHeight/2);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.clearSprites();
        // for loop for the projectiles here
        for(int i = projectiles.size()-1; i >= 0; i-- )
        {
        	projectiles.get(i).move();
        	tiledMapRenderer.addSprite(projectiles.get(i).sprite());
        	if(projectiles.get(i).reachedEnd())
        		projectiles.remove(i);
        }
        for(int i = 0; i<hostiles.size(); i++)
        {
        	hostiles.get(i).setAggro(true);
        	hostiles.get(i).move(p.getX(),p.getY());
        	tiledMapRenderer.addSprite(hostiles.get(i).sprite());
        }
        for(int i = 0; i<weapons.size(); i++)
        	tiledMapRenderer.addSprite(weapons.get(i).sprite());
        for(int i = 0; i<armour.size(); i++)
        	tiledMapRenderer.addSprite(armour.get(i).sprite());
        tiledMapRenderer.addSprite(p.sprite());
        tiledMapRenderer.render();
        batch.begin();
        if(invOpen)
        {
	        Sprite i = inv.sprite();
	        i.setPosition(0+(i.getWidth()*1.5f), h-(i.getHeight()*3.5f));
	        i.draw(batch);
	        if(inv.getGun()!=null)
	        {
	        	Sprite wS = inv.getGun().sprite();
	        	wS.setScale(4.0f);
	        	wS.setPosition(54,510);
	        	wS.draw(batch);
	        }
	        if(inv.getArmour()!=null)
	        {
	        	Sprite wS = inv.getArmour().sprite();
	        	wS.setScale(4.0f);
	        	wS.setPosition(123,320);
	        	wS.draw(batch);
	        }
        }
        batch.end();
        
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
    	MapProperties prop = tiledMap.getProperties();
    	int mapWidth = prop.get("width", Integer.class);
    	int mapHeight = prop.get("height", Integer.class);
    	int tilePixelWidth = prop.get("tilewidth", Integer.class);
    	int tilePixelHeight = prop.get("tileheight", Integer.class);
    	Sound spur = Gdx.audio.newSound(Gdx.files.internal("Cowboy_with_spurs-G-rant-1371954508.wav"));
    	TiledMapTileLayer layer = (TiledMapTileLayer)tiledMap.getLayers().get(1);
        if(keycode == Input.Keys.A)
        {
        	Cell cell = layer.getCell((int)((p.getX()/16)-1), (int)((p.getY()/16)));
        	boolean solid = (cell!=null);
        	if(!solid)
        	{
        		p.setX(p.getX()-16);
        		p.setSprite("left");
        		if(p.getX()<0)
        			p.setX(0);
        		spur.play(0.2f);
        	}
        }
        if(keycode == Input.Keys.D)
        {
        	Cell cell = layer.getCell((int)((p.getX()/16)+1), (int)((p.getY()/16)));
        	boolean solid = (cell!=null);
        	if(!solid)
        	{
	        	p.setX(p.getX()+16);
	        	p.setSprite("right");
	        	if(p.getX()>(mapWidth-1)*tilePixelWidth)
	        		p.setX((mapWidth-1)*tilePixelWidth);
	        	spur.play(0.2f);
        	}
        }
        if(keycode == Input.Keys.W)
        {
        	Cell cell = layer.getCell((int)((p.getX()/16)), (int)((p.getY()/16)+1));
        	boolean solid = (cell!=null);
        	if(!solid)
        	{
	        	p.setY(p.getY()+16);
	        	p.setSprite("up");
	        	if(p.getY()>(mapHeight-1)*tilePixelHeight)
	        		p.setY((mapHeight-1)*tilePixelHeight);
	        	spur.play(0.2f);
        	}
        }
        if(keycode == Input.Keys.S)
        {
        	Cell cell = layer.getCell((int)((p.getX()/16)), (int)((p.getY()/16)-1));
        	boolean solid = (cell!=null);
        	if(!solid)
        	{
	        	p.setY(p.getY()-16);
	        	p.setSprite("down");
	        	if(p.getY()<0)
	        		p.setY(0);
	        	spur.play(0.2f);
        	}
        }
        if(keycode == Input.Keys.I)
        {
        	if(invOpen)
        		invOpen = false;
        	else
        		invOpen = true;
        }
        if(keycode == Input.Keys.Q)
        {
            for(int i = weapons.size()-1; i>=0; i--)
            {
            	if(weapons.get(i).getX() == p.getX() && weapons.get(i).getY() == p.getY())
            	{
            		inv.setGun(weapons.get(i));
            		weapons.remove(i);
            	}
            }
            for(int i = armour.size()-1; i>=0; i--)
            {
            	if(armour.get(i).getX() == p.getX() && armour.get(i).getY() == p.getY())
            	{
            		inv.setArmour(armour.get(i));
            		armour.remove(i);
            	}
            }
        }
           
        return false;
    }

    @Override
    public boolean keyTyped(char character) {

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	//add a projectile to the arraylist then go to render to put them on map
    	if(button == Buttons.LEFT){
    		float x = ((float)Math.ceil(((screenX/4)+camera.position.x-(camera.viewportWidth/2))/16)*16)-16;
    		float y = ((float)Math.ceil((((h-screenY)/4)+camera.position.y-(camera.viewportHeight/2))/16)*16)-16;
    		float xDiff = Math.abs(x - p.getX());
    		float yDiff = Math.abs(y - p.getY());
    		if(xDiff > yDiff)
    			y = p.getY();
    		else
    			x = p.getX();
    		int dir = 0;
	    	projectiles.add(new Projectiles(p.getX(),p.getY(), x, y));
    	}
    	return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    			
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}