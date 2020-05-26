package tk.sherrao.maerienette.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import tk.sherrao.maerienette.GameApp;
import tk.sherrao.maerienette.GameComponent;
import tk.sherrao.maerienette.InputPoller;

public abstract class AbstractScreen extends GameComponent implements Screen {

	/** General */
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
	
	public abstract void load();

	public abstract void update();
	
	public abstract void draw();
	
	public abstract void dispose();
	
	@Override
	public final void show() {}
	
	@Override
	public final void hide() {}
	
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

	@Override
	public void pause() {}

	@Override
	public void resume() {}
	
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