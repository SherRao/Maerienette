package tk.sherrao.maerienette.entities;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import tk.sherrao.maerienette.GameApp;
import tk.sherrao.maerienette.screens.MainScreen;
import tk.sherrao.maerienette.world.BaseEntity;

public class Floor extends BaseEntity<PolygonShape> {

	public Floor(GameApp game, MainScreen screen) {
		super(game, screen);

		shape = new PolygonShape();
		shape.setAsBox(2000, 25);

		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(0, 25);
		body = world.createBody(bodyDef);
		body.setUserData("floor");

		fixtureDef.shape = shape;
		fixtureDef.density = 100f;
		fixtureDef.friction = 0f;
		fixtureDef.restitution = 0f;
		fixture = body.createFixture(fixtureDef);
		fixture.setUserData("floor");
		shape.dispose();

	}

	@Override
	public void tick() {

	}

	@Override
	public void draw() {

	}

	@Override
	public void destroy() {

	}

}
