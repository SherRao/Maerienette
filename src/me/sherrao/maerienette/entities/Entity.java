package me.sherrao.maerienette.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import me.sherrao.maerienette.GameApp;
import me.sherrao.maerienette.GameComponent;
import me.sherrao.maerienette.screens.MainScreen;

public abstract class Entity extends GameComponent {

	protected final MainScreen screen;
	protected final World world;
	protected final SpriteBatch batch;
	
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	
	protected Body body;
	protected Fixture fixture;
	
	public Entity(final GameApp game, MainScreen screen, float x, float y, float width, float height) {
		super(game);
	
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.screen = screen;
		this.world = screen.getWorld();
		this.batch = screen.getGame().getBatch();
		
	}
	
	public void tick() {
		x = (body.getPosition().x * MainScreen.PPM) - (width / 2);
		y = (body.getPosition().y * MainScreen.PPM) - (height / 2);
		
	}
	
	public void draw() {
		
	}
	
	public void destroy() {
		
	}
	
	public final MainScreen getScreen() {
		return this.screen;
		
	}
	
	public final World getWorld() {
		return this.world;
		
	}
	
	public final SpriteBatch getBatch() {
		return this.batch;
		
	}
	
	public final Body getBody() {
		return this.body;
		
	}
	
	public final Fixture getFixture() {
		return this.fixture;
		
	}
	
	public final Vector2 getPhysicsPosition() {
		return this.body.getPosition();
		
	}
	
	public final Vector2 getPhysicsVelocity() {
		return this.body.getLinearVelocity();
		
	}
	
	public final float getX() {
		return this.x;
		
	}
	
	public final float getY() {
		return this.y;
		
	}
	
	public final float getWidth() {
		return this.width;
		
	}
	
	public final float getHeight() {
		return this.height;
		
	}
	
}
