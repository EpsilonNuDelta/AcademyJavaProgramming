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
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.core.armors.Armour;
import com.core.mechanics.classes.Default;
import com.core.mechanics.classes.Marksman;
import com.core.mechanics.combat.MeleeProjectiles;
import com.core.mechanics.combat.Projectiles;
import com.core.mechanics.player.ChooseClass;
import com.core.mechanics.player.Inventory;
import com.core.mechanics.player.Player;
import com.core.mechanics.player.PlayerHead;
import com.core.mobs.Alien;
import com.core.mobs.Blockbot;
import com.core.mobs.Cadet;
import com.core.mobs.HostileCreation;
import com.core.mobs.Mobs;
import com.core.mobs.Slime;
import com.core.mobs.Spaceman;
import com.core.weapons.Weapons;

public class Main extends ApplicationAdapter implements InputProcessor {
    TiledMap tiledMap;
    OrthographicCamera camera;
    OrthogonalTiledMapRendererWithSprites tiledMapRenderer;
    Player p;
    private PlayerHead pH;
    HostileCreation hostiles;
    private SpriteBatch batch;
    private ShapeRenderer shapes;
    float w;
    float h;
    private ArrayList<Projectiles> projectiles;
	private ArrayList<ChooseClass> choose;
    private boolean invOpen;
    private boolean classOpen;
    private Inventory inv;
    private ItemHandler items;
    private int DOOR_UP = 5;
    private int DOOR_UP_OPEN = 6;
    private int DOOR_DOWN = 11;
    private int DOOR_DOWN_OPEN = 12;
    
    @Override
    public void create () {
    	Gdx.graphics.setWindowedMode(1024, 768);
    	w = Gdx.graphics.getWidth();
    	h = Gdx.graphics.getHeight();
        tiledMap = new TmxMapLoader().load("testington.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRendererWithSprites(tiledMap);
       	p = new Default();
       	pH = new PlayerHead();
    	hostiles = new HostileCreation();
        batch = new SpriteBatch();
        shapes = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.viewportHeight = h/4;
        camera.viewportWidth = w/4;
        camera.update();
        projectiles = new ArrayList<Projectiles>();
        choose = new ArrayList<ChooseClass>();
        inv = new Inventory();
        invOpen = false;
        classOpen = false;
        items = new ItemHandler(tiledMap);
        Gdx.input.setInputProcessor(this);
        //Sound music = Gdx.audio.newSound(Gdx.files.internal(""));
        //music.play();
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
        	camera.position.y = camera.viewportHeight/2;
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
            for(int m = 0; m<hostiles.getHSize(); m++)
            {
            	if(projectiles.get(i).dealDamage(hostiles.getH(m)))
            		projectiles.get(i).end();
            }
        	TiledMapTileLayer layer = (TiledMapTileLayer)tiledMap.getLayers().get(1);
        	TiledMapTileLayer dLayer = (TiledMapTileLayer)tiledMap.getLayers().get(2);
        	
        	Cell cell = layer.getCell((int)((projectiles.get(i).sprite().getX()/16)), (int)((projectiles.get(i).sprite().getY()/16)));
        	Cell doorCell = dLayer.getCell((int)((projectiles.get(i).sprite().getX()/16)), (int)((projectiles.get(i).sprite().getY()/16)));
        	boolean solid = ((cell!=null)||(doorCell!=null&&(doorCell.getTile().getId()==DOOR_UP||doorCell.getTile().getId()==DOOR_DOWN)));
        	if(solid)
        		projectiles.get(i).end();

        	if(projectiles.get(i).reachedEnd()||((projectiles.get(i).sprite().getX()<0)||(projectiles.get(i).sprite().getY()<0))
        			||(projectiles.get(i).sprite().getX()>tiledMap.getProperties().get("width", Integer.class)*tiledMap.getProperties().get("tilewidth", Integer.class))
        			||(projectiles.get(i).sprite().getY()>tiledMap.getProperties().get("height", Integer.class)*tiledMap.getProperties().get("tileheight", Integer.class)))
        		projectiles.remove(i);
        	else
        		tiledMapRenderer.addSprite(projectiles.get(i).sprite());
        }
        for(int i = hostiles.getHSize()-1; i>0; i--)
        {
        	hostiles.getH(i).move(p.getX(),p.getY(),tiledMap);
        	if(hostiles.getH(i).getHealth()>0)
        		tiledMapRenderer.addSprite(hostiles.getH(i).sprite());
        	else
        	{
        		items.addW(hostiles.getH(i).getDrop(tiledMap));
        		p.setXP(p.getXP()+hostiles.getH(i).getExp());
        		hostiles.remH(i);
        	}
    		
        }
        for(int i = 0; i<items.getWSize(); i++)
        	tiledMapRenderer.addSprite(items.getW(i).sprite());
        for(int i = 0; i<items.getASize(); i++)
        	tiledMapRenderer.addSprite(items.getA(i).sprite());
        tiledMapRenderer.addSprite(p.sprite());
        tiledMapRenderer.render();
    	shapes.begin(ShapeType.Filled);
    	if(invOpen)
    	{
    		shapes.setColor(Color.RED);
    		float width = 160.0f*(p.getHealth()/p.getMaxHealth());
    		shapes.rect(48, 200, width, 20);
    		shapes.setColor(Color.GREEN);
    		float width2 = 160.0f*(p.getXP()/(100.0f*p.getLevel()));
    		shapes.rect(48, 152, width2, 20);
    		shapes.setColor(Color.BLUE);
    		float width3 = 160.0f*(p.getEnergy()/p.getMaxEnergy());
    		shapes.rect(48, 240, width3, 20);
    	}
    	shapes.end();
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
	        if(inv.getArmor()!=null)
	        {
	        	Sprite wS = inv.getArmor().sprite();
	        	wS.setScale(4.0f);
	        	wS.setPosition(123,320);
	        	wS.draw(batch);
	        }
	        pH.sprite().draw(batch);
	        BitmapFont f = new BitmapFont();
	        f.getData().setScale(2.0f);
	        f.draw(batch, p.getLevel()+"", 176, 607);
        	choose.clear();
	        if(classOpen)
	        {
	        	choose.add(new ChooseClass("Marksman",25,658));
	        	choose.add(new ChooseClass("Juggernaut",85,655));
	        	choose.add(new ChooseClass("Engineer",150,655));
	        	choose.add(new ChooseClass("SecretAgent",215,655));
	        	for(ChooseClass ch : choose)
	        	{
	        		ch.sprite().draw(batch);
	        	}
	        }
        }
        batch.end();
        
    }

    @Override
	public void dispose() {
		super.dispose();
		tiledMapRenderer.dispose();
		batch.dispose();
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
    	Sound spur = Gdx.audio.newSound(Gdx.files.internal("348355__natty23__footstep.wav"));
    	TiledMapTileLayer layer = (TiledMapTileLayer)tiledMap.getLayers().get(1);
    	TiledMapTileLayer dLayer = (TiledMapTileLayer)tiledMap.getLayers().get(2);
    	Sound door = Gdx.audio.newSound(Gdx.files.internal("Doorwind_converted.wav"));
        if(keycode == Input.Keys.A)
        {
        	Cell cell = layer.getCell((int)((p.getX()/16)-1), (int)((p.getY()/16)));
        	Cell doorCell = dLayer.getCell((int)((p.getX()/16)-1), (int)((p.getY()/16)));
        	boolean solid = ((cell!=null)||(doorCell!=null&&(doorCell.getTile().getId()==DOOR_UP||doorCell.getTile().getId()==DOOR_DOWN)));
        	if(!solid)
        	{
        		p.setX(p.getX()-16);
        		p.setSprite("left");
        		if(p.getX()<0)
        			p.setX(0);
        		spur.play(0.2f);
        	}
        	else
        	{
        		if(doorCell!=null)
        		{
        			if(doorCell.getTile().getId()==DOOR_UP)
        				doorCell.setTile(tiledMap.getTileSets().getTile(DOOR_UP_OPEN));
        			if(doorCell.getTile().getId()==DOOR_DOWN)
        				doorCell.setTile(tiledMap.getTileSets().getTile(DOOR_DOWN_OPEN));
        		}
        	}
        }
        if(keycode == Input.Keys.D)
        {
        	Cell cell = layer.getCell((int)((p.getX()/16)+1), (int)((p.getY()/16)));
        	Cell doorCell = dLayer.getCell((int)((p.getX()/16)+1), (int)((p.getY()/16)));
        	boolean solid = ((cell!=null)||(doorCell!=null&&(doorCell.getTile().getId()==DOOR_UP||doorCell.getTile().getId()==DOOR_DOWN)));
        	if(!solid)
        	{
	        	p.setX(p.getX()+16);
	        	p.setSprite("right");
	        	if(p.getX()>(mapWidth-1)*tilePixelWidth)
	        		p.setX((mapWidth-1)*tilePixelWidth);
	        	spur.play(0.2f);
        	}
        	else 
        	{
        		if(doorCell!=null)
        		{
        			if(doorCell.getTile().getId()==DOOR_UP)
        				doorCell.setTile(tiledMap.getTileSets().getTile(DOOR_UP_OPEN));
        			if(doorCell.getTile().getId()==DOOR_DOWN)
        				doorCell.setTile(tiledMap.getTileSets().getTile(DOOR_DOWN_OPEN));
        			door.play();
        		}
        	}
        }
        if(keycode == Input.Keys.W)
        {
        	Cell cell = layer.getCell((int)((p.getX()/16)), (int)((p.getY()/16)+1));
        	Cell doorCell = dLayer.getCell((int)((p.getX()/16)), (int)((p.getY()/16)+1));
        	boolean solid = ((cell!=null)||(doorCell!=null&&(doorCell.getTile().getId()==DOOR_UP||doorCell.getTile().getId()==DOOR_DOWN)));
        	if(!solid)
        	{
	        	p.setY(p.getY()+16);
	        	p.setSprite("up");
	        	if(p.getY()>(mapHeight-1)*tilePixelHeight)
	        		p.setY((mapHeight-1)*tilePixelHeight);
	        	spur.play(0.2f);
        	}
        	else
        	{
        		if(doorCell!=null)
        		{
        			if(doorCell.getTile().getId()==DOOR_UP)
        				doorCell.setTile(tiledMap.getTileSets().getTile(DOOR_UP_OPEN));
        			if(doorCell.getTile().getId()==DOOR_DOWN)
        				doorCell.setTile(tiledMap.getTileSets().getTile(DOOR_DOWN_OPEN));
        			door.play();
        		}
        	}
        }
        if(keycode == Input.Keys.S)
        {
        	Cell cell = layer.getCell((int)((p.getX()/16)), (int)((p.getY()/16)-1));
        	Cell doorCell = dLayer.getCell((int)((p.getX()/16)), (int)((p.getY()/16)-1));
        	boolean solid = ((cell!=null)||(doorCell!=null&&(doorCell.getTile().getId()==DOOR_UP||doorCell.getTile().getId()==DOOR_DOWN)));
        	if(!solid)
        	{
	        	p.setY(p.getY()-16);
	        	p.setSprite("down");
	        	if(p.getY()<0)
	        		p.setY(0);
	        	spur.play(0.2f);
        	}
        	else
        	{
        		if(doorCell!=null)
        		{
        			if(doorCell.getTile().getId()==DOOR_UP)
        				doorCell.setTile(tiledMap.getTileSets().getTile(DOOR_UP_OPEN));
        			if(doorCell.getTile().getId()==DOOR_DOWN)
        				doorCell.setTile(tiledMap.getTileSets().getTile(DOOR_DOWN_OPEN));
        			door.play();
        		}
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
    		Sound pickup = Gdx.audio.newSound(Gdx.files.internal("Gear Shift Into Drive-SoundBible.com-2101462767.mp3"));
            for(int i = items.getWSize()-1; i>=0; i--)
            {
            	if(items.getW(i).getX() == p.getX() && items.getW(i).getY() == p.getY())
            	{
            		inv.setGun(items.getW(i));
            		items.remW(i);
            		pickup.play(2f);
            	}
            }
            for(int i = items.getASize()-1; i>=0; i--)
            {
            	if(items.getA(i).getX() == p.getX() && items.getA(i).getY() == p.getY())
            	{
            		inv.setArmor(items.getA(i));
            		items.remA(i);
            		pickup.play(2f);

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
			if((pH.getSprite().equals("defaulthead.png"))
					&&(pH.getX()<=(screenX))&&((pH.getX()+pH.sprite().getWidth())>=(screenX))
					&&(pH.getY()<=(h-screenY))&&((pH.getY()+pH.sprite().getHeight())>=(h-screenY)))
			{
				classOpen = true;
			}
			else
			{
	    		boolean chosen = false;
	    		for(ChooseClass ch : choose)
	    		{
	    			if((ch.getX()<=(screenX))&&((ch.getX()+ch.sprite().getWidth())>=(screenX))&&(ch.getY()<=(h-screenY))&&((ch.getY()+ch.sprite().getHeight())>=(h-screenY)))
	    			{
	    				chosen = true;
	    				classOpen = false;
	    				float oldX = p.getX();
	    				float oldY = p.getY();
	    				pH.setSprite(ch.getName()+"head.png");
	    				p = ch.pick();
	    				p.setX(oldX);
	    				p.setY(oldY);
	    			}
	    		}
	    		if(!chosen)
	    		{
		    		float x = ((float)Math.ceil(((screenX/4)+camera.position.x-(camera.viewportWidth/2))/16)*16)-16;
		    		float y = ((float)Math.ceil((((h-screenY)/4)+camera.position.y-(camera.viewportHeight/2))/16)*16)-16;
		    		float xDiff = Math.abs(x - p.getX());
		    		float yDiff = Math.abs(y - p.getY());
		    		Sound shot = Gdx.audio.newSound(Gdx.files.internal("Gear Shift Into Drive-SoundBible.com-2101462767.mp3"));
		    		shot.play();
		    		int dir = 0;
		    		if(xDiff > yDiff)
		    			dir = 0;
		    		else
		    			dir = 2;
		    		if(((x-p.getX())<0&&dir==0)||((y-p.getY())<0&&dir==2))
		    			dir++;
			    	projectiles.add(new Projectiles(p.getX(),p.getY(), dir, (inv.getGun()!=null?inv.getGun().getDamage():5), 1));
	    		}
			}
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