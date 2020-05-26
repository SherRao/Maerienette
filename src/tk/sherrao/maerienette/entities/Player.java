package tk.sherrao.maerienette.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;
import tk.sherrao.maerienette.GameApp;
import tk.sherrao.maerienette.InputPoller;
import tk.sherrao.maerienette.screens.MainScreen;
import tk.sherrao.maerienette.world.AbstractEntity;
import tk.sherrao.maerienette.world.InteractableEntity;

public class Player extends AbstractEntity<PolygonShape> implements InputProcessor {

	enum PlayerState { WALKING, TALKING, IDLE }
	
	private InputPoller input;
	
	private Image image;
	private AnimatedSprite sprite;
	private Animation runningAnimation;
	private Animation idleAnimation;
	
	public PlayerState state;
	public InteractableEntity nearestInteractable;
	
	public Player(final GameApp game, MainScreen screen) {
		super(game, screen);
		
		input = game.getInputPoller();
		image = new Image( new Texture(Gdx.files.internal("textures/global/player/player.png")));
		image.setScale(.35f); //269.85 x 473.2
		
		super.setSize(269.85f, 473.2f);
		
		shape = new PolygonShape();
		shape.setAsBox(super.getWidth()/2, super.getHeight()/2);

		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(300, 700);
		body = world.createBody(bodyDef);
		body.setLinearDamping(0f);
		body.setUserData("player");
		
		fixtureDef.shape = shape;
		fixtureDef.density = 20f;
		fixtureDef.friction = .02f;
		fixtureDef.restitution = 0f; // Bouncyness
		fixture = body.createFixture(fixtureDef);
		fixture.setUserData("player");
		shape.dispose();
		
		screen.getWorldStage().addActor(image);
		input.addInputSource(this);
		
	}

	
	@Override
	public void tick() {
		if(Gdx.input.isKeyPressed(Keys.A))
			body.setLinearVelocity(-10000f, body.getLinearVelocity().y);
		
		else if(Gdx.input.isKeyPressed(Keys.D))
			body.setLinearVelocity(10000f, body.getLinearVelocity().y);
		
		else
			body.setLinearVelocity(0, body.getLinearVelocity().y);
			
		image.setPosition(body.getPosition().x - (super.getWidth() / 2), body.getPosition().y - (super.getHeight() / 2));

	}


	@Override
	public void destroy() {
		
	}

	@Override
	public boolean keyDown(int keycode) {
		return true;
			
	}

	@Override
	public boolean keyUp(int keycode) {
		return true;
		
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
