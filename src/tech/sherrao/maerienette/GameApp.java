package tech.sherrao.maerienette;

import java.util.StringJoiner;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import tech.sherrao.maerienette.screens.MainScreen;
import tech.sherrao.maerienette.utils.Utilities;

public class GameApp implements ApplicationListener {

	/** General */
	private Logger logger;
	private SpriteBatch batch;
	private ShapeRenderer shape;
	private OrthographicCamera camera;
	private Viewport view;
	private InputPoller input;
	private Assets assets;
	
	private ObjectMap<String, Screen> screens;
	private Screen screen;

	/** Overlay */
	private Skin skin;
	private Label debugOverlay;

	/** Data */
	private float mx;
	private float my;
	private boolean paused;
	private boolean debug;
	private float delta;
	private float fps;
	private long javaMem;
	private long sysMem;

	@Override
	public void create() {
		Box2D.init();
		
		logger = new Logger("Maerienette/GameApp", Logger.DEBUG);
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		camera = new OrthographicCamera();
		view = new FitViewport(1920, 1080, camera);// new StretchViewport(1920, 1080, camera);
		input = new InputPoller(this);
		assets = new Assets(this);
		skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas("uiskin.atlas"));
		debugOverlay = new Label("", skin);

		paused = false;
		debug = false;

		updateData();
		updateDebugOverlay();
		debugOverlay.setPosition(0f, 125f);

		camera.update();
		view.apply();

		changeScreen(new MainScreen(this));

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		screen.update();
		screen.draw();

		updateData();
		updateDebugOverlay();

		if(Gdx.input.isKeyJustPressed(Keys.P))
			Utilities.screenshot();

		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE))
			stop();

	}

	@Override
	public void dispose() {
		logger.info("Dispose() called. Disposing loaded screen and all other resources.");

		changeScreen(null);
		batch.dispose();
		shape.dispose();
		skin.dispose();

	}

	@Override
	public void resize(int width, int height) {
		logger.info("Resize() called. Updating viewport and screen resolutions.");

		view.update(width, height);
		view.apply();
		screen.resize(width, height);

	}

	@Override
	public void pause() {
		logger.info("Pause() called");

	}

	@Override
	public void resume() {
		logger.info("Resume() called");

	}

	public void changeScreen(Screen newScreen) {
		logger.info( (newScreen == null ? "Removing screen" : "Changing screen to " + newScreen.getClass().getSimpleName()) );
		if(screen != null)
			screen.dispose();

		screen = newScreen;
		if(screen != null) {
			screen.load();
			screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		}
	}

	public void stop() {
		logger.info("Stopping game");
		Gdx.app.exit();

	}

	public Screen findScreen(String name) {
		return null;

	}

	private void updateData() {
		Vector3 mp = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		mx = mp.x;
		my = mp.y;
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
			debugOverlay.setText(new StringJoiner("\n").add("FPS: " + fps).add("Delta (ms): " + delta)
					.add("Java Memory (kB): " + javaMem).add("System Memory (kB): " + sysMem).add("")
					.add("Paused: " + paused).add("").add("Render Calls/Frame: " + batch.renderCalls)
					.add("Total Render Calls: " + batch.totalRenderCalls).add("")
					.add(String.format("Mouse (x,y): (%.2f,%.2f)", mx, my)).toString());

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

	public Assets getAssetManager() {
		return this.assets;

	}

	public float getMouseX() {
		return this.mx;

	}

	public float getMouseY() {
		return this.my;

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