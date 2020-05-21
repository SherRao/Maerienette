package tk.sherrao.maerienette;

import java.util.StringJoiner;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import tk.sherrao.maerienette.screens.MainScreen;

public class GameApp extends Game {

	/** General */
	private SpriteBatch batch;
	private ShapeRenderer shape;
	private OrthographicCamera camera;
	private Viewport view;
	private InputPoller input;

	/** Overlay */
	private Skin skin;
	private Label debugOverlay;
	
	/** Data */
	private boolean paused;
	private boolean debug;
	private float delta;
	private float fps;
	private long javaMem;
	private long sysMem;
	
	@Override
	public void create() {
		Box2D.init();
		
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		camera = new OrthographicCamera();
		view = new FitViewport(1920, 1080, camera);//new StretchViewport(1920, 1080, camera);
		input = new InputPoller(this);

		skin = new Skin( Gdx.files.internal("uiskin.json"), new TextureAtlas("uiskin.atlas") );
		debugOverlay = new Label("", skin);
		
		paused = false;
		debug = false;
		
		updateData();
		updateDebugOverlay();
		debugOverlay.setPosition(0f, 125f);
		
		camera.update();
		view.apply();
		
		super.setScreen( new MainScreen(this) );
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		super.render();
		
		updateData();
		updateDebugOverlay();
		
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) 
			super.setScreen(null);
			Gdx.app.exit();
		
	}
	
	@Override
	public void dispose() {
		
	}

	@Override
	public void resize(int width, int height) {
		view.update(width, height);
		view.apply();
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	public void stop() {
		
		
	}
	
	public Screen findScreen(String name ) {
		return null;
		
	}
		
	private void updateData() {
		delta = Gdx.graphics.getDeltaTime();
		fps = Gdx.graphics.getFramesPerSecond();
		javaMem = Gdx.app.getJavaHeap() / 1000;
		sysMem = Gdx.app.getNativeHeap() / 1000;
		
	}

	private void updateDebugOverlay() {
		if(Gdx.input.isKeyJustPressed(Keys.G)) {
			debug = !debug;
			debugOverlay.setVisible(debug);
			
		}
		
		if(debug) {
			debugOverlay.setText( new StringJoiner("\n")
					.add("FPS: " + fps)
					.add("Delta (ms): " + delta)
					.add("Java Memory (kB): " + javaMem)
					.add("System Memory (kB): " + sysMem)
					.add("")
					.add("Paused: " + paused)
					.add("")
					.add("Render Calls/Frame: " + batch.renderCalls)
					.add("Total Render Calls: " + batch.totalRenderCalls)
					.add("")
					.add( String.format("Mouse (x,y): (%d,%d)", Gdx.input.getX(), Gdx.input.getY()) )
					.toString() );
			
			batch.begin();
			debugOverlay.draw(batch, 1f);
			batch.end();
			
		}
	}

	public SpriteBatch getBatch() {
		return this.batch;
	
	}
	
	public ShapeRenderer getShapeBatch() {
		return this.shape;
		
	}
	
	public OrthographicCamera getCamera() {
		return this.camera;
		
	}
	
	public Viewport getView() {
		return this.view;
		
	}
	
	public InputPoller getInputPoller() {
		return this.input;
		
	}
	
	public boolean isDebug() {
		return this.debug;
		
	}	
	
	public float getDelta() {
		return this.delta;
		
	}
	
	public float getFPS() {
		return this.fps;
		
	}
	
	public long getJavaMemory() {
		return this.javaMem;
		
	}
	
	public long getSystemMemory() {
		return this.sysMem;
		
	}
	
}