package tech.sherrao.maerienette.rooms.intro;

import tech.sherrao.maerienette.GameApp;
import tech.sherrao.maerienette.Room;
import tech.sherrao.maerienette.screens.MainScreen;

public class Outside extends Room {

	public Outside(GameApp game, MainScreen screen) {
		super(game, screen, "textures/rooms/intro/outside.jpg");
		super.rightRoom = "Intro.Hallway";
		
	}

	@Override
	public void load() {
		super.load();
		
	}

	@Override
	public void update() {
		super.update();

	}

	@Override
	public void clear() {

	}

}