package com.core;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.core.armors.Armour;
import com.core.mechanics.classes.Default;
import com.core.decals.BloodSplatter;
import com.core.decals.Decal;
import com.core.items.Ammo;
import com.core.items.Item;
import com.core.mechanics.combat.MeleeProjectiles;
import com.core.mechanics.combat.Projectiles;
import com.core.mechanics.player.ChooseClass;
import com.core.mechanics.player.Inventory;
import com.core.mechanics.player.Player;
import com.core.mechanics.player.PlayerHead;
import com.core.mobs.HostileCreation;
import com.core.weapons.MeleeWeapons;
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
    private ArrayList<Decal> decals;
    private boolean invOpen;
    private boolean classOpen;
    private Inventory inv;
    private ItemHandler items;
    public static final int DOOR_UP = 5;
    public static final int DOOR_UP_OPEN = 6;
    public static final int DOOR_DOWN = 11;
    public static final int DOOR_DOWN_OPEN = 12;
    
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
        decals = new ArrayList<Decal>();
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
        if(p.getHealth()>0)
        {
	        for(int i = decals.size()-1; i>=0; i--)
	        	tiledMapRenderer.addSprite(decals.get(i).sprite());
	        for(int i = projectiles.size()-1; i >= 0; i-- )
	        {
	        	projectiles.get(i).move();
	            for(int m = 0; m<hostiles.getHSize(); m++)
	            {
	            	if(projectiles.get(i).dealDamage(hostiles.getH(m),p))
	            		projectiles.get(i).end();
	            }
	        	TiledMapTileLayer layer = (TiledMapTileLayer)tiledMap.getLayers().get(1);
	        	TiledMapTileLayer dLayer = (TiledMapTileLayer)tiledMap.getLayers().get(2);
	        	
	        	Cell cell = layer.getCell((int)((projectiles.get(i).sprite().getX()/16)), (int)((projectiles.get(i).sprite().getY()/16)));
	        	Cell doorCell = dLayer.getCell((int)((projectiles.get(i).sprite().getX()/16)), (int)((projectiles.get(i).sprite().getY()/16)));
	        	boolean solid = ((cell!=null)||(doorCell!=null&&(doorCell.getTile().getId()==DOOR_UP||doorCell.getTile().getId()==DOOR_DOWN)));
	        	if(solid)
	        		projectiles.get(i).end();
	
	    		tiledMapRenderer.addSprite(projectiles.get(i).sprite());
	        	if(projectiles.get(i).reachedEnd()||((projectiles.get(i).sprite().getX()<0)||(projectiles.get(i).sprite().getY()<0))
	        			||(projectiles.get(i).sprite().getX()>tiledMap.getProperties().get("width", Integer.class)*tiledMap.getProperties().get("tilewidth", Integer.class))
	        			||(projectiles.get(i).sprite().getY()>tiledMap.getProperties().get("height", Integer.class)*tiledMap.getProperties().get("tileheight", Integer.class)))
	        		projectiles.remove(i);
	        }
	        if(inv.getGun()!=null)
	        	inv.getGun().reload();
	        for(int i = hostiles.getHSize()-1; i>0; i--)
	        {
	        	hostiles.getH(i).move(p.getX(),p.getY(),tiledMap,p, hostiles);
	        	if(hostiles.getH(i).getHealth()>0){
	        		tiledMapRenderer.addSprite(hostiles.getH(i).sprite());
	
	        	}
	        	else
	        	{
	        		items.addW(hostiles.getH(i).getDrop(tiledMap));
	        		p.setXP(p.getXP()+hostiles.getH(i).getExp());
	        		decals.add(new BloodSplatter(hostiles.getH(i).getX(),hostiles.getH(i).getY()));
	        		hostiles.remH(i);
	        	}
	    		
	        }
	        for(int i = 0; i<items.getWSize(); i++)
	        	tiledMapRenderer.addSprite(items.getW(i).sprite());
	        for(int i = 0; i<items.getASize(); i++)
	        	tiledMapRenderer.addSprite(items.getA(i).sprite());
	        for(int i = 0; i<items.getISize(); i++)
	        	tiledMapRenderer.addSprite(items.getI(i).sprite());
	        for(int i = 0; i<items.getPSize(); i++)
	        	tiledMapRenderer.addSprite(items.getP(i).sprite());
	        tiledMapRenderer.addSprite(p.sprite());
	        tiledMapRenderer.render();
	    	shapes.begin(ShapeType.Filled);
	    	if(invOpen)
	    	{
	    		shapes.setColor(new Color(105/255f,100/255f,100/255f,1));
	    		shapes.rect(48, 200, 160, 20);
	    		shapes.rect(48, 152, 160, 20);
	    		shapes.rect(48, 240, 160, 20);
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
		        	wS.setPosition(73,320);
		        	wS.draw(batch);
		        }
		        if(inv.getItem()!=null)
		        {
		        	Sprite wS = inv.getItem().sprite();
		        	wS.setScale(4.0f);
		        	wS.setPosition(63,420);
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
        else
        {
        	//you lose
        }
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
        if(keycode == Input.Keys.U)
        {
        	if(inv.getItem()!=null)
        	{
        		inv.getItem().use(p);
        		inv.setItem(null);
        	}
        }
        if(keycode == Input.Keys.Q)
        {
    		Sound pickup = Gdx.audio.newSound(Gdx.files.internal("Gear Shift Into Drive-SoundBible.com-2101462767.mp3"));
            for(int i = items.getWSize()-1; i>=0; i--)
            {
            	if(items.getW(i).getX() == p.getX() && items.getW(i).getY() == p.getY())
            	{
            		if(inv.getGun()!=null)
            		{
            			Weapons gun = inv.getGun();
            			gun.setX(p.getX());
            			gun.setY(p.getY());
            			items.addW(gun);
            		}
            		inv.setGun(items.getW(i));
            		items.remW(i);
            		pickup.play(2f);
            	}
            }
            for(int i = items.getASize()-1; i>=0; i--)
            {
            	if(items.getA(i).getX() == p.getX() && items.getA(i).getY() == p.getY())
            	{
            		if(inv.getArmor()!=null)
            		{
            			Armour arm = inv.getArmor();
            			arm.sprite().setX(p.getX());
            			arm.sprite().setY(p.getY());
            			items.addA(arm);
            		}
            		inv.setArmor(items.getA(i));
            		items.remA(i);
            		pickup.play(2f);

            	}
            }
            for(int i = items.getPSize()-1; i>=0; i--)
            {
            	if(items.getP(i).getX() == p.getX() && items.getP(i).getY() == p.getY())
            	{
            		if(items.getP(i) instanceof Ammo)
            			((Ammo)items.getP(i)).pickUp(inv);
            		else
            			items.getP(i).pickUp(p);
            		items.remP(i);
            		pickup.play(2f);

            	}
            }
            for(int i = items.getISize()-1; i>=0; i--)
            {
            	if(items.getI(i).getX() == p.getX() && items.getI(i).getY() == p.getY())
            	{
            		if(inv.getItem()!=null)
            		{
            			Item it = inv.getItem();
            			it.sprite().setX(p.getX());
            			it.sprite().setY(p.getY());
            			items.addI(it);
            		}
            		inv.setItem(items.getI(i));
            		items.remI(i);
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
	    		if(!chosen&&(inv.getGun()==null||(!inv.getGun().isJammed()&&inv.getGun().fire(p))))
	    		{
		    		float x = ((float)Math.ceil(((screenX/4)+camera.position.x-(camera.viewportWidth/2))/16)*16)-16;
		    		float y = ((float)Math.ceil((((h-screenY)/4)+camera.position.y-(camera.viewportHeight/2))/16)*16)-16;
		    		float xDiff = Math.abs(x - p.getX());
		    		float yDiff = Math.abs(y - p.getY());
		    		Sound shot = (inv.getGun()!=null?inv.getGun().sound():Gdx.audio.newSound(Gdx.files.internal(ItemHandler.MELEE_SOUND)));
		    		shot.play();
		    		int dir = 0;
		    		if(xDiff > yDiff)
		    			dir = 0;
		    		else
		    			dir = 2;
		    		if(((x-p.getX())<0&&dir==0)||((y-p.getY())<0&&dir==2))
		    			dir++;
		    		Projectiles proj = new MeleeProjectiles(p.getX(),p.getY(), dir, (inv.getGun()!=null?inv.getGun().getDamage():5), 1);
		    		if(inv.getGun()!=null&&!(inv.getGun() instanceof MeleeWeapons))
		    			proj = new Projectiles(p.getX(),p.getY(), dir, inv.getGun().getDamage(), 1);
		    		for(int i = 0; i<3; i++)
		    			proj.move();
		    		projectiles.add(proj);
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