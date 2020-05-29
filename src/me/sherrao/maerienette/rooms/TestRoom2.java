package me.sherrao.maerienette.rooms;

import me.sherrao.maerienette.GameApp;
import me.sherrao.maerienette.entities.Entity;
import me.sherrao.maerienette.entities.LeftWall;
import me.sherrao.maerienette.screens.MainScreen;

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
		if (wall instanceof LeftWall) {
			nextX = screen.getWorldWidth() - 250f;
			nextRoom = new IntroRoom(game, screen);
		}
	}

}
