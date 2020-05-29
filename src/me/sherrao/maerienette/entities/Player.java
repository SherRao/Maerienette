package me.sherrao.maerienette.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import me.sherrao.maerienette.GameApp;
import me.sherrao.maerienette.InputPoller;
import me.sherrao.maerienette.Utilities;
import me.sherrao.maerienette.screens.MainScreen;
import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;

public class Player extends Entity implements InputProcessor {

	enum PlayerState {
		WALKING, TALKING, IDLE
	}

	private boolean focus;
	private PlayerState state;
	private long lastMoveTime;
	private long idleTimeWait;
	private long idleTimeWaitThreshold;

	private Image image;
	private AnimatedSprite sprite;
	private Animation runningAnimation;
	private Animation idleAnimation;

	public InteractableEntity nearestInteractable;

	public Player(final GameApp game, MainScreen screen) {
		super(game, screen, 300f, 700f, 269.85f, 473.2f);
		super.fixture = Utilities.createPhysicsEntity(this, BodyType.DynamicBody, 30f, 0f, 20f, this);
		super.body = fixture.getBody();

		focus = true;
		idleTimeWait = 15000;
		idleTimeWait = 5000;

		image = new Image(new Texture(Gdx.files.internal("textures/global/player/player.png")));
		image.setBounds(x - (width / 2), y - (height / 2), width, height);
		image.setUserObject(this);

		screen.getWorldStage().addActor(image);
		game.getInputPoller().addInputSource(this);

	}

	@Override
	public void tick() {
		if (focus) {
			super.tick();
			if (Gdx.input.isKeyPressed(Keys.A)) {
				state = PlayerState.WALKING;
				Utilities.applyLinearImpulse(this, -.2f, 0f);
				lastMoveTime = System.currentTimeMillis();

			} else if (Gdx.input.isKeyPressed(Keys.D)) {
				state = PlayerState.WALKING;
				Utilities.applyLinearImpulse(this, .2f, 0f);
				lastMoveTime = System.currentTimeMillis();

			}

			long idleTime = lastMoveTime + 0;// Utilities.randLong(-idleTimeWaitThreshold, idleTimeWaitThreshold);
			if (idleTime >= idleTimeWait) {
				// TODO: Make idle animation active for a bit

			}

			image.setBounds(x, y, width, height);
			image.setRotation((float) Math.toDegrees(body.getAngle()));

		}
	}

	@Override
	public void destroy() {
		game.getInputPoller().removeInputSource(this);

	}

	@Override
	public boolean keyDown(int keycode) {
		return true;

	}

	@Override
	public boolean keyUp(int keycode) {
		if (game.getInputPoller().isLeftKey(keycode) || game.getInputPoller().isRightKey(keycode)) {
			state = PlayerState.IDLE;
			body.setLinearVelocity(0, 0);

		}

		return true;

	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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

	public void setFocus(boolean focus) {
		this.focus = focus;

	}

	public Image getImage() {
		return this.image;

	}

}
