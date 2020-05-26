package tk.sherrao.maerienette.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

import tk.sherrao.maerienette.GameApp;
import tk.sherrao.maerienette.entities.Floor;
import tk.sherrao.maerienette.entities.Player;
import tk.sherrao.maerienette.rooms.AbstractRoom;
import tk.sherrao.maerienette.rooms.IntroRoom;
import tk.sherrao.maerienette.world.InteractableEntity;

public class MainScreen extends AbstractScreen implements ContactListener {

	private Box2DDebugRenderer renderer;
	private Stage worldStage;
	private Stage guiStage;
	private World world;
	
	private AbstractRoom room;
	private Player player;
	private Floor floor;
	
	private Image dialogueBackground;
	private TypingLabel dialogue;
	private Music backgroundMusic;
	
	public MainScreen(GameApp game) {
		super(game);
	
	}
	
	@Override
	public void load() {
		renderer = new Box2DDebugRenderer(true, true, true, true, true, true);
		renderer.AABB_COLOR.set(Color.MAGENTA);
		renderer.VELOCITY_COLOR.set(Color.BLUE);
		
		worldStage = new Stage(view, batch);
		guiStage = new Stage(view, batch);
		world = new World( new Vector2(0f, -980f), true);
		world.setWarmStarting(true);
		world.setContinuousPhysics(true);
		world.setContactListener(this);
		
		changeRoom(new IntroRoom(game, this));
		player = new Player(game, this);
		floor = new Floor(game, this);
		
		dialogue = new TypingLabel("{EASE}this is a test label", new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas("uiskin.atlas")) );
		dialogue.setColor(Color.WHITE);
		dialogue.setPosition(50, 500);
		
		dialogueBackground = new Image();
		//dialogue = new TypingLabel(text, style);
		backgroundMusic = Gdx.audio.newMusic( Gdx.files.internal("test/musictest.mp3") );
		backgroundMusic.setVolume(.2f);
		backgroundMusic.play();
		
		Gdx.graphics.setSystemCursor(SystemCursor.Crosshair);
		
	}
	
	@Override
	public void update() {
		if(room != null)
			room.update();
		player.tick();
		floor.tick();
		
		camera.update();
		world.step(1/60f, 6, 2);
		worldStage.act();
		guiStage.act();
		
		worldStage.setDebugAll(game.isDebug());
		guiStage.setDebugAll(game.isDebug());
		
	}

	@Override
	public void draw() {	
		worldStage.draw();
		guiStage.draw();

		if(game.isDebug()) 
			renderer.render(world, view.getCamera().combined);
		
	}
	
	@Override
	public void dispose() {
		renderer.dispose();
		worldStage.dispose();
		guiStage.dispose();
		world.dispose();
		player.destroy();
		floor.destroy();
		
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
	
	public void changeRoom(AbstractRoom newRoom) {
		if(room != null) {
			worldStage.addAction( Actions.fadeOut(.5f) );
			room.clear();
		
		}
		
		this.room = newRoom;
		room.load();
		worldStage.addAction( Actions.fadeIn(.5f) );
		
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
