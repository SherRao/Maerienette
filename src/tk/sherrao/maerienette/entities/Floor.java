package tk.sherrao.maerienette.entities;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import tk.sherrao.maerienette.GameApp;
import tk.sherrao.maerienette.screens.MainScreen;
import tk.sherrao.maerienette.world.AbstractEntity;

public class Floor extends AbstractEntity<PolygonShape> {

	public Floor(GameApp game, MainScreen screen) {
		super(game, screen);

		shape = new PolygonShape();
		shape.setAsBox(2000, 50);

		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(25, 0);
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
	public void destroy() {

	}

}
