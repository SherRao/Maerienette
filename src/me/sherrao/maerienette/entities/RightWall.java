package me.sherrao.maerienette.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import me.sherrao.maerienette.GameApp;
import me.sherrao.maerienette.Utilities;
import me.sherrao.maerienette.screens.MainScreen;

public class RightWall extends Entity {

	private ShapeRenderer shape;
	private Color color;
	
	public RightWall(GameApp game, MainScreen screen) {
		super(game, screen, screen.getWorldWidth() - 25f, 50f, 25f, screen.getWorldHeight() - 50f);
		super.fixture = Utilities.createPhysicsEntity(this, BodyType.StaticBody, 10f, 0f, 20f, this);
		fixture.setSensor(false);
		
		super.body = fixture.getBody();
		
		this.shape = game.getShapeBatch();
		this.color = Color.VIOLET;
		color.a = .5f;
		
	}

	@Override
	public void draw() {
		super.draw();
		if(game.isDebug() ) {
			Gdx.gl.glEnable(GL30.GL_BLEND);
			Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
			shape.begin(ShapeType.Filled);
			shape.setProjectionMatrix(screen.getView().getCamera().combined);
			shape.setColor(color);
			shape.rect(x, y, width, height);
			shape.end();
			Gdx.gl.glDisable(GL30.GL_BLEND);

		}
	}

	@Override
	public void destroy() {

	}
}