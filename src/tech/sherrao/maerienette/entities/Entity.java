package tech.sherrao.maerienette.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import tech.sherrao.maerienette.GameApp;
import tech.sherrao.maerienette.GameComponent;
import tech.sherrao.maerienette.screens.MainScreen;

public abstract class Entity extends GameComponent {

	protected final MainScreen screen;
	protected final World world;
	protected final SpriteBatch batch;

	protected float x;
	protected float y;
	protected float width;
	protected float height;
	protected boolean isEssential;
	protected Image image;

	protected Body body;
	protected Fixture fixture;

	public Entity(GameApp game, final MainScreen screen, float x, float y, float width, float height, boolean isEssential) {
		this(game, screen, x, y, width, height, isEssential, "textures/global/notexture.png");
		
	}
	
	public Entity(GameApp game, final MainScreen screen, float x, float y, float width, float height, boolean isEssential, String texture) {
		this(game, screen, x, y, width, height, isEssential, new Texture( Gdx.files.internal(texture)));
		
	}
	
	public Entity(GameApp game, final MainScreen screen, float x, float y, float width, float height, boolean isEssential, Texture texture) {
		super(game);

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isEssential = isEssential;
		this.image = new Image(texture);
		image.setBounds(x, y, width, height);
		image.setUserObject(isEssential);
		
		this.screen = screen;
		this.world = screen.getWorld();
		this.batch = screen.getGame().getBatch();
		
		screen.getWorldStage().addActor(image);
		
	} 
	
	/**
	 * 
	 * Called every game tick to update this entities logic for when it's loaded in the game.
	 * 
	 */
	public void tick() {
		x = (body.getPosition().x * MainScreen.PPM) - (width / 2);
		y = (body.getPosition().y * MainScreen.PPM) - (height / 2);
		image.setBounds(x, y, width, height);

	}

	/**
	 * 
	 * Called every game tick to perform any graphical operations on this entity for when it's loaded in the game.
	 * 
	 */
	public void draw() {

	}
	
	/**
	 * 
	 * Called when this entity should be unloaded.
	 * 
	 */
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
