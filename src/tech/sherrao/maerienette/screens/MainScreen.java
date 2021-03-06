package tech.sherrao.maerienette.screens;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

import tech.sherrao.maerienette.GameApp;
import tech.sherrao.maerienette.Room;
import tech.sherrao.maerienette.Screen;
import tech.sherrao.maerienette.entities.Entity;
import tech.sherrao.maerienette.entities.Floor;
import tech.sherrao.maerienette.entities.LeftWall;
import tech.sherrao.maerienette.entities.Player;
import tech.sherrao.maerienette.entities.RightWall;
import tech.sherrao.maerienette.rooms.intro.Hallway;
import tech.sherrao.maerienette.rooms.intro.LivingRoom;
import tech.sherrao.maerienette.rooms.intro.Outside;

public class MainScreen extends Screen implements ContactListener {

	// The amount of pixels to draw per meter in the world
	public static final float PPM = 600f;

	private Box2DDebugRenderer renderer;
	private Stage worldStage;
	private Stage guiStage;
	private World world;
	private Image background;

	private Room room;
	private ObjectMap<String, Room> rooms;
	private Array<Entity> entities;
	private Player player;
	private Floor floor;
	private LeftWall leftWall;
	private RightWall rightWall;

	private Image dialogueBackground;
	private TypingLabel dialogue;
	private Music backgroundMusic;

	public MainScreen(GameApp game) {
		super(game);

	}

	@Override
	public void load() {
		renderer = new Box2DDebugRenderer(true, true, true, true, true, true);
		renderer.AABB_COLOR.set(Color.BLUE);
		renderer.JOINT_COLOR.set(Color.BLUE);
		renderer.SHAPE_AWAKE.set(Color.BLUE);
		renderer.SHAPE_KINEMATIC.set(Color.BLUE);
		renderer.SHAPE_NOT_ACTIVE.set(Color.BLUE);
		renderer.SHAPE_NOT_AWAKE.set(Color.BLUE);
		renderer.SHAPE_STATIC.set(Color.BLUE);
		renderer.VELOCITY_COLOR.set(Color.BLUE);

		worldStage = new Stage(view, batch);
		worldStage.getDebugColor().set(Color.GREEN);
		guiStage = new Stage(view, batch);
		guiStage.getDebugColor().set(Color.GOLD);
		world = new World(new Vector2(0f, -9.8f), true);
		world.setContactListener(this);
		background = new Image();
		background.setPosition(0, 0);
		background.setFillParent(true);
		background.setUserObject(true);

		rooms = new ObjectMap<>();
		entities = new Array<>();
		player = new Player(game, this);
		floor = new Floor(game, this);
		leftWall = new LeftWall(game, this);
		rightWall = new RightWall(game, this);

		dialogue = new TypingLabel("{EASE}this is a test label",
				new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas("uiskin.atlas")));
		dialogue.setColor(Color.WHITE);
		dialogue.setPosition(50, 500);

		dialogueBackground = new Image();
		// dialogue = new TypingLabel(text, style);
		// backgroundMusic =
		// Gdx.audio.newMusic(Gdx.files.internal("test/musictest.mp3"));
		// backgroundMusic.setVolume(.2f);
		// backgroundMusic.play();

		populateRooms();
		worldStage.addActor(background);
		changeRoom("Intro.Outside", 500f, false);
		Gdx.graphics.setSystemCursor(SystemCursor.Crosshair);

	}

	@Override
	public void update() {
		world.step(1 / 60f, 6, 2);

		worldStage.act();
		guiStage.act();

		player.tick();
		floor.tick();
		leftWall.tick();
		rightWall.tick();

		if (room != null)
			room.update();

		worldStage.setDebugAll(game.isDebug());
		guiStage.setDebugAll(game.isDebug());
	}

	@Override
	public void draw() {
		worldStage.draw();
		guiStage.draw();

		player.draw();
		floor.draw();
		leftWall.draw();
		rightWall.draw();

		if (game.isDebug())
			renderer.render(world, view.getCamera().combined.cpy().scale(PPM, PPM, 1));
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
	}

	@Override
	public void endContact(Contact contact) {
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		Fixture a = contact.getFixtureA();
		Fixture b = contact.getFixtureB();
		System.out.println(a.getType());
		System.out.println(b.getType());
		if (a.getUserData() instanceof Player && b.getUserData() instanceof LeftWall) {
			room.wallCollision((Entity) b.getUserData());

		} else if (b.getUserData() instanceof Player && a.getUserData() instanceof LeftWall) {
			room.wallCollision((Entity) a.getUserData());

		} else if (a.getUserData() instanceof Player && b.getUserData() instanceof RightWall) {
			room.wallCollision((Entity) b.getUserData());

		} else if (b.getUserData() instanceof Player && a.getUserData() instanceof RightWall) {
			room.wallCollision((Entity) a.getUserData());

		}
	}

	public void interjectDialogue() {
		player.setFocus(false);
		dialogue.setVisible(true);
		dialogueBackground.setVisible(true);

	}

	public void changeRoom(String newRoomName, float nx, boolean fadeTransition) {
		Action action = Actions.run(() -> {
			Room nextRoom = rooms.get(newRoomName);
			System.out.println(nextRoom);
			if (room != null) {
				room.clear();

				// Clears physics bodies from the physics engine. If a body is marked as
				// "essential" by its UserData being true, then the body is not removed.
				Array<Body> bodies = new Array<>();
				world.clearForces();
				world.getBodies(bodies);
				for (Body body : bodies) {
					if (body.getUserData().equals(false))
						world.destroyBody(body);

				}

				// Clears actors from the stage. If an actor is marked as "essential" by its
				// UserObject being true, then the actor is not removed.
				for (Iterator<Actor> it = worldStage.getActors().iterator(); it.hasNext();) {
					Actor next = it.next();
					System.out.println(next.getUserObject());
					if (next.getUserObject().equals(false))
						it.remove();

				}
			}

			this.room = nextRoom;
			if (room != null) {
				room.load();
				player.getBody().setTransform(nx / PPM, player.getPhysicsPosition().y, 0f);

			}
		});

		if (fadeTransition)
			worldStage.addAction(Actions.sequence(Actions.fadeOut(2f), action, Actions.fadeIn(2f)));

		else
			worldStage.addAction(action);

	}

	public void setBackground(Texture texture) {
		this.background.setDrawable(new TextureRegionDrawable(texture));

	}

	private void populateRooms() {
		this.rooms.put("Intro.Outside", new Outside(game, this));
		this.rooms.put("Intro.Hallway", new Hallway(game, this));
		this.rooms.put("Intro.LivingRoom", new LivingRoom(game, this));

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

	public float getWorldWidth() {
		return this.view.getWorldWidth();

	}

	public float getWorldHeight() {
		return this.view.getWorldHeight();

	}

}
