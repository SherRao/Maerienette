package tk.sherrao.maerienette.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import tk.sherrao.maerienette.GameApp;
import tk.sherrao.maerienette.screens.MainScreen;
import tk.sherrao.maerienette.world.BaseEntity;

public class TestBox extends BaseEntity<PolygonShape>{

	private Image image;
	
	public TestBox(final GameApp game, MainScreen screen) {
		super(game, screen);

		image = new Image( new Texture( Gdx.files.internal("test/box.png") ) );
		image.setSize(100, 100); //730 x 1330  / 4
		
		shape = new PolygonShape();
		shape.setAsBox(120, 100);
		
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(500, 216);
		body = world.createBody(bodyDef);
		body.setUserData("testbox");
		
		fixtureDef.isSensor = true;
		fixtureDef.shape = shape;
		fixtureDef.density = 100f;
		fixtureDef.friction = 0f;
		fixtureDef.restitution = 0f;
		fixture = body.createFixture(fixtureDef);
		fixture.setUserData("testbox");
		shape.dispose();

		screen.getWorldStage().addActor(image);
		
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
