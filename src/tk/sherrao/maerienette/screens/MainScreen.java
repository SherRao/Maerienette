package tk.sherrao.maerienette.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

import tk.sherrao.maerienette.GameApp;
import tk.sherrao.maerienette.GameScreen;
import tk.sherrao.maerienette.entities.Floor;
import tk.sherrao.maerienette.entities.Player;
import tk.sherrao.maerienette.entities.TestBox;
import tk.sherrao.maerienette.world.InteractableEntity;

public class MainScreen extends GameScreen implements ContactListener {

	private Box2DDebugRenderer renderer;
	private Stage worldStage, guiStage;
	private World world;
	
	private Floor floor;
	private Player player;
	private TestBox box;
	
	private Sprite dialogueBackground;
	private TypingLabel dialogue;
	
	private Music backgroundMusic;
	
	public MainScreen(GameApp game) {
		super(game);
	
		renderer = new Box2DDebugRenderer(true, true, true, true, true, true);
		worldStage = new Stage(view, batch);
		guiStage = new Stage(view, batch);
		world = new World( new Vector2(0f, -980f), true);
		world.setWarmStarting(true);
		world.setContinuousPhysics(true);
		world.setContactListener(this);
		
		floor = new Floor(game, this);
		floor.init();
		
		player = new Player(game, this);
		player.init();

		box = new TestBox(game, this);
		box.init();
		
		dialogue = new TypingLabel("{EASE}this is a test label", new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas("uiskin.atlas")) );
		dialogue.setColor(Color.WHITE);
		dialogue.setBounds(400, 400, 200, 50);
		
		backgroundMusic = Gdx.audio.newMusic( Gdx.files.internal("test/musictest.mp3") );
		backgroundMusic.setVolume(.2f);
		backgroundMusic.play();
		
		guiStage.addActor(dialogue);
		
	}
	
	@Override
	public void show() {
		worldStage.addAction(Actions.fadeIn(1f));
		guiStage.addAction(Actions.fadeIn(1f));
		
	}
	
	@Override
	public void update() {
		player.tick();
		floor.tick();
		box.tick();
		
		camera.position.set(player.getPosition().x, camera.position.y, camera.position.z);
		camera.update();
		world.step(1/60f, 6, 2);
		worldStage.act();
		guiStage.act();
		
		worldStage.setDebugAll(game.isDebug());
		guiStage.setDebugAll(game.isDebug());
	}

	@Override
	public void draw() {
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.DARK_GRAY);
		shape.rect(0,0,1920,1080);
		shape.end();
		
		if(game.isDebug()) 
			renderer.render(world, view.getCamera().combined);

		worldStage.draw();
		guiStage.draw();
		
		shape.setProjectionMatrix(camera.combined);
		shape.begin(ShapeType.Filled);
		shape.setColor(Color.RED);
		shape.circle(Gdx.input.getX(), -Gdx.input.getY(), 20);
		shape.end();
	}
	
	@Override
	public void hide() {
		worldStage.addAction(Actions.fadeOut(1f));
		guiStage.addAction(Actions.fadeOut(1f));
		
	}

	@Override
	public void end() {
		renderer.dispose();
		worldStage.dispose();
		guiStage.dispose();
		world.dispose();
		player.destroy();
		floor.destroy();
		box.destroy();
		
	}
	
	@Override
	public void beginContact(Contact contact) {
		Fixture a = contact.getFixtureA();
		Fixture b = contact.getFixtureB();
		if(a.getUserData() instanceof Player && b.getUserData() instanceof InteractableEntity) {
			InteractableEntity entity = (InteractableEntity) b.getUserData();
			if(player.nearestInteractable != null) {
				float oldDistance = player.getPosition().dst( player.nearestInteractable.getPosition() );
				float newDistance = player.getPosition().dst( entity.getPosition() );
				if(newDistance < oldDistance)
					player.nearestInteractable = entity;
				
			} else
				player.nearestInteractable = entity;
			
		}else if(b.getUserData() instanceof Player && a.getUserData() instanceof InteractableEntity) {
			InteractableEntity entity = (InteractableEntity) a.getUserData();
			if(player.nearestInteractable != null) {
				float oldDistance = player.getPosition().dst( player.nearestInteractable.getPosition() );
				float newDistance = player.getPosition().dst( entity.getPosition() );
				if(newDistance < oldDistance)
					player.nearestInteractable = entity;
				
			} else
				player.nearestInteractable = entity;
			
		}
		
	}

	@Override
	public void endContact(Contact contact) {
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}
	
	public Stage getWorldStage() {
		return this.worldStage;
		
	}
	
	public Stage getGuiStage() {
		return this.guiStage;
		
	}
	
	public World getWorld() {
		return this.world;
		
	}
	
	public Player getPlayer() {
		return this.player;
		
	}
	
	public int getScreenWidth() {
		return this.view.getScreenWidth();
		
	}
	
	public int getScreenHeight() {
		return this.view.getScreenHeight();
		
	}

}
