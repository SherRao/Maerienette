package tech.sherrao.maerienette.rooms;

import tech.sherrao.maerienette.GameApp;
import tech.sherrao.maerienette.entities.Entity;
import tech.sherrao.maerienette.entities.LeftWall;
import tech.sherrao.maerienette.screens.MainScreen;

public class TestRoom2 extends Room {

	public TestRoom2(GameApp game, MainScreen screen) {
		super(game, screen);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		super.update();

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wallCollision(Entity wall) {
		if(wall instanceof LeftWall) {
			nextX = screen.getWorldWidth() - 250f;
			nextRoom = new IntroRoom(game, screen);
		}
	}

}
