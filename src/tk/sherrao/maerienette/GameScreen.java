package tk.sherrao.maerienette;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class GameScreen extends GameAppComponent implements Screen {

	/** General */
	protected final InputPoller input;
	protected final SpriteBatch batch;
	protected final ShapeRenderer shape;
	protected final OrthographicCamera camera;
	protected final Viewport view;

	public GameScreen(final GameApp game) {
		super(game);
		
		this.input = game.getInputPoller();
		this.batch = game.getBatch();
		this.shape = game.getShapeBatch();
		this.camera = game.getCamera();
		this.view = game.getView();
		
		view.apply(true);
		
	}
	
	public abstract void show();

	public abstract void update();
	
	public abstract void draw();
	
	public abstract void hide();
	
	public abstract void end();
	

	@Override
	public final void render(float delta) {
		update();
		draw();
		
	}

	@Override
	public void dispose() {
		this.end();
		
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