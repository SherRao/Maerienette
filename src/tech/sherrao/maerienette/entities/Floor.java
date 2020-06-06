package tech.sherrao.maerienette.entities;

import static com.badlogic.gdx.Gdx.gl;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import tech.sherrao.maerienette.GameApp;
import tech.sherrao.maerienette.screens.MainScreen;
import tech.sherrao.maerienette.utils.Utilities;

public class Floor extends Entity {

	private ShapeRenderer shape;
	private Color color;

	public Floor(GameApp game, MainScreen screen) {
		super(game, screen, 0f, 0f, screen.getView().getWorldWidth(), 50f);
		super.fixture = Utilities.createPhysicsEntity(this, BodyType.StaticBody, 10f, 0f, 20f, this);
		super.body = fixture.getBody();

		this.shape = game.getShapeBatch();
		this.color = Color.FIREBRICK;
		color.a = .5f;

	}

	@Override
	public void draw() {
		super.draw();
		if(game.isDebug()) {
			gl.glEnable(GL30.GL_BLEND);
			gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
			shape.begin(ShapeType.Filled);
			shape.setProjectionMatrix(screen.getView().getCamera().combined);
			shape.setColor(color);
			shape.rect(x, y, width, height);
			shape.end();
			gl.glDisable(GL30.GL_BLEND);

		}
	}

	@Override
	public void destroy() {

	}

}
