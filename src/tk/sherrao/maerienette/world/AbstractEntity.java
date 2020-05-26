package tk.sherrao.maerienette.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import tk.sherrao.maerienette.GameApp;
import tk.sherrao.maerienette.GameComponent;
import tk.sherrao.maerienette.screens.MainScreen;

public abstract class AbstractEntity<T extends Shape> extends GameComponent {

	protected final MainScreen screen;
	protected final World world;
	protected final SpriteBatch batch;
	
	protected final BodyDef bodyDef;
	protected final FixtureDef fixtureDef;

	protected float width;
	protected float height;
	
	protected T shape;
	protected Body body;
	protected Fixture fixture;
	
	public AbstractEntity(final GameApp game, MainScreen screen) {
		super(game);
	
		this.screen = screen;
		this.world = screen.getWorld();
		this.batch = screen.getGame().getBatch();
		
		bodyDef = new BodyDef();
		fixtureDef = new FixtureDef();
		
	}
	
	public abstract void tick();
	
	public abstract void destroy();
	
	protected final void setSize(float width, float height) {
		this.width = width;
		this.height = height;
		
	}
	
	public final Body getBody() {
		return this.body;
		
	}
	
	public final Fixture getFixture() {
		return this.fixture;
		
	}
	
	public final Vector2 getPosition() {
		return this.body.getPosition();
		
	}
	
	public final Vector2 getVelocity() {
		return this.body.getLinearVelocity();
		
	}
	
	public final float getWidth() {
		return this.width;
		
	}
	
	public final float getHeight() {
		return this.height;
		
	}
	
}