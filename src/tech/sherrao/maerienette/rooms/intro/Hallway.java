package tech.sherrao.maerienette.rooms.intro;

import tech.sherrao.maerienette.GameApp;
import tech.sherrao.maerienette.Room;
import tech.sherrao.maerienette.screens.MainScreen;

public class Hallway extends Room {

	public Hallway(GameApp game, MainScreen screen) {
		super(game, screen, "textures/rooms/intro/hallway.png");
		super.rightRoom = "Intro.LivingRoom";
		
	}

	@Override
	public void load() {
		super.load();
	
	}

	@Override
	public void clear() {
		
	}

}
