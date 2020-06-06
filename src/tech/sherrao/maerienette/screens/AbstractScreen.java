package tech.sherrao.maerienette.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import tech.sherrao.maerienette.GameApp;
import tech.sherrao.maerienette.GameComponent;
import tech.sherrao.maerienette.InputPoller;

/**
 * Represents a screen within the game Controlled by {@link GameApp}
 * 
 * @author Nausher Rao
 *
 */
public abstract class AbstractScreen extends GameComponent implements Screen {

	protected final InputPoller input;
	protected final SpriteBatch batch;
	protected final ShapeRenderer shape;
	protected final OrthographicCamera camera;
	protected final Viewport view;

	public AbstractScreen(final GameApp game) {
		super(game);

		this.input = game.getInputPoller();
		this.batch = game.getBatch();
		this.shape = game.getShapeBatch();
		this.camera = game.getCamera();
		this.view = game.getView();

		view.apply(true);

	}

	/**
	 * 
	 * Initialization of all resources required by the Screen.
	 * 
	 */
	public abstract void load();

	/**
	 * 
	 * Called each frame of the game to update the logic of the Screen.
	 * 
	 */
	public abstract void update();

	/**
	 * 
	 * Called each frame of the game to draw any assets of the Screen.
	 * 
	 */
	public abstract void draw();

	/**
	 * 
	 * Called when switching screens to release all resources used by this Screen.
	 * 
	 */
	public abstract void dispose();

	@Override
	public final void render(float delta) {
		update();
		draw();

	}

	@Override
	public void resize(int width, int height) {
		view.update(width, height);
		view.apply();

	}

	/**
	 * 
	 * Unused
	 * 
	 */
	@Override
	public final void show() {
	}

	/**
	 * 
	 * Unused
	 * 
	 */
	@Override
	public final void hide() {
	}

	/**
	 * 
	 * Unused
	 * 
	 */
	@Override
	public final void pause() {
	}

	/**
	 * 
	 * Unused
	 * 
	 */
	@Override
	public final void resume() {
	}

	public final SpriteBatch getBatch() {
		return this.batch;

	}

	public final Camera getCamera() {
		return this.camera;

	}

	public final Viewport getView() {
		return this.view;

	}

}