package tk.sherrao.maerienette.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import tk.sherrao.maerienette.GameApp;
import tk.sherrao.maerienette.InputPoller;
import tk.sherrao.maerienette.screens.MainScreen;
import tk.sherrao.maerienette.world.BaseEntity;
import tk.sherrao.maerienette.world.InteractableEntity;

public class Player extends BaseEntity<PolygonShape> implements InputProcessor {

	private InputPoller input;
	
	private Image image;
	
	private float frameTime;
	private TextureAtlas runningAtlas;
	private TextureAtlas idleAtlas;
	private TextureAtlas jumpingAtlas;
	private Animation<Sprite> runningAnimation;
	private Animation<Sprite> idleAnimation;
	private Animation<Sprite> jumpingAnimation;
	
	public InteractableEntity nearestInteractable;
	
	public Player(final GameApp game, MainScreen screen) {
		super(game, screen);

	}

	@Override
	public void init() {
		input = game.getInputPoller();
		
		image = new Image( new Texture( Gdx.files.internal("test/MC.png") ));
		image.setSize(183, 333); //730 x 1330  / 4
		/**
		frameTime = 1/60f;
		runningAtlas = new TextureAtlas();
		idleAtlas = new TextureAtlas();
		jumpingAtlas = new TextureAtlas();
		runningAnimation = new Animation<Sprite>(frameTime, runningAtlas.createSprites());
		idleAnimation = new Animation<Sprite>(frameTime, idleAtlas.createSprites());
		jumpingAnimation = new Animation<Sprite>(frameTime, jumpingAtlas.createSprites());
		*/
		
		shape = new PolygonShape();
		shape.setAsBox(183/2, 333/2);

		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(300, 216);
		body = world.createBody(bodyDef);
		body.setUserData("player");
		
		fixtureDef.shape = shape;
		fixtureDef.density = 20f;
		fixtureDef.friction = .02f;
		fixtureDef.restitution = 0f; // Bouncyness
		fixture = body.createFixture(fixtureDef);
		fixture.setUserData("player");
		shape.dispose();
		
		screen.getWorldStage().addActor(image);
		
	}
	
	@Override
	public void tick() {
		body.setLinearVelocity(0f, 0f);
		if(input.keyPress("left"))
			body.setLinearVelocity(-500f, 0f);
		
		if(input.keyPress("right"))
			body.setLinearVelocity(500f, 0f);
		image.setBounds(body.getPosition().x - (183/2), body.getPosition().y - (333/2), 183, 333);
		
	}

	@Override
	public void draw() {
			
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public boolean keyDown(int keycode) {
		if(input.isLeftKey(keycode)) {
			body.setLinearVelocity(-30, 0);
			
		} else if(input.isRightKey(keycode)) {
			body.setLinearVelocity(30, 0);
	
	 	} else {
			body.setLinearVelocity(0, 0);
	 	}
	 	
		return true;
			
	}

	@Override
	public boolean keyUp(int keycode) {
		//if(input.isLeftKey(keycode) || input.isRightKey(keycode)) {
		//	body.setLinearVelocity(0, 0);
		//	return true;
		//
		//}
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
