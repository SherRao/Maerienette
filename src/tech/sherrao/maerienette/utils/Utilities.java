package tech.sherrao.maerienette.utils;

import java.io.File;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;

import tech.sherrao.maerienette.entities.Entity;
import tech.sherrao.maerienette.screens.MainScreen;

public abstract class Utilities {

	private static final RandomXS128 r;
	private static final File SCREENSHOTS_FOLDER;

	static {
		r = new RandomXS128();
		SCREENSHOTS_FOLDER = new File("screenshots/");

	}

	public static long randLong(long a) {
		return Utilities.randLong(0, a);

	}

	public static long randLong(long a, long b) { // -2, 5
		return r.nextLong(Math.abs(a) + b) - Math.abs(a);

	}

	public static Fixture createPhysicsEntity(Entity entity, BodyType type, boolean isEssential, float density,
			float restitution, float friction) {
		float physicsHWidth = (entity.getWidth() / 2) / MainScreen.PPM;
		float physicsHHeight = (entity.getHeight() / 2) / MainScreen.PPM;

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = type;
		bodyDef.position.set((entity.getX() / MainScreen.PPM) + physicsHWidth,
				(entity.getY() / MainScreen.PPM) + physicsHHeight);
		bodyDef.linearDamping = 0f;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(physicsHWidth, physicsHHeight);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = density / MainScreen.PPM;
		fixtureDef.restitution = restitution / MainScreen.PPM;
		fixtureDef.friction = friction / MainScreen.PPM;
		fixtureDef.shape = shape;

		Body body = entity.getWorld().createBody(bodyDef);
		Fixture fixture = body.createFixture(fixtureDef);

		body.setUserData(isEssential);
		fixture.setUserData(isEssential);

		shape.dispose();
		return fixture;

	}

	public static Fixture createPhysicSensor(World world, float x, float y, boolean isEssential,
			float detectionRadius) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(x / MainScreen.PPM, y / MainScreen.PPM);
		bodyDef.linearDamping = 0f;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(detectionRadius / MainScreen.PPM, 10 / MainScreen.PPM);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = 0f;
		fixtureDef.restitution = 0f;
		fixtureDef.friction = 0f;
		fixtureDef.shape = shape;
		fixtureDef.isSensor = true;

		Body body = world.createBody(bodyDef);
		Fixture fixture = body.createFixture(fixtureDef);

		body.setUserData(isEssential);
		fixture.setUserData(isEssential);

		shape.dispose();
		return fixture;

	}

	public static void screenshot() {
		byte[] pixels = ScreenUtils.getFrameBufferPixels(0, 0, Gdx.graphics.getBackBufferWidth(),
				Gdx.graphics.getBackBufferHeight(), true);
		for (int i = 4; i < pixels.length; i += 4) {
			pixels[i - 1] = (byte) 255;
		}

		File folder = new File("screenshots/");
		if (!folder.exists())
			folder.mkdir();

		File file = new File(folder, System.currentTimeMillis() + ".png");
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

		System.out.println(file.getAbsolutePath());

		Pixmap pixmap = new Pixmap(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(),
				Pixmap.Format.RGBA8888);
		BufferUtils.copy(pixels, 0, pixmap.getPixels(), pixels.length);
		PixmapIO.writePNG(new FileHandle(file), pixmap);
		pixmap.dispose();

	}

	public static void applyLinearImpulse(Entity entity, float dx, float dy) {
		entity.getBody().applyLinearImpulse(dx / MainScreen.PPM, dy / MainScreen.PPM, entity.getPhysicsPosition().x,
				entity.getPhysicsPosition().y, true);

	}

}
