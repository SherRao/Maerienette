package me.sherrao.maerienette.rooms;

import me.sherrao.maerienette.GameApp;
import me.sherrao.maerienette.entities.Entity;
import me.sherrao.maerienette.entities.LeftWall;
import me.sherrao.maerienette.entities.RightWall;
import me.sherrao.maerienette.screens.MainScreen;

public class TestRoom extends Room {

	public TestRoom(GameApp game, MainScreen screen) {
		super(game, screen);
	}

	@Override
	public void load() {

	}

	@Override
	public void update() {
		super.update();
		
	}

	@Override
	public void clear() {

	}

	@Override
	public void wallCollision(Entity wall) {
		if (wall instanceof RightWall) {
			nextX = 250f;
			nextRoom = new IntroRoom(game, screen);
		}
	}

}
