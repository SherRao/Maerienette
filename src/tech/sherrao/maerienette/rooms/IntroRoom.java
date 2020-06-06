package tech.sherrao.maerienette.rooms;

import static com.badlogic.gdx.Gdx.files;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import tech.sherrao.maerienette.GameApp;
import tech.sherrao.maerienette.entities.Entity;
import tech.sherrao.maerienette.entities.LeftWall;
import tech.sherrao.maerienette.entities.RightWall;
import tech.sherrao.maerienette.screens.MainScreen;

public class IntroRoom extends Room {

	private Image background;
	private Room leftRoom;
	private Room rightRoom;

	public IntroRoom(final GameApp game, MainScreen screen) {
		super(game, screen);

	}

	@Override
	public void load() {
		background = new Image(new Texture(files.internal("textures/rooms/introroom/background.jpg")));
		background.setFillParent(true);
		background.setUserObject(this);

		leftRoom = new TestRoom(game, screen);
		rightRoom = new TestRoom2(game, screen);

		screen.getWorldStage().addActor(background);

	}

	@Override
	public void update() {
		super.update();

	}

	@Override
	public void clear() {

	}

}