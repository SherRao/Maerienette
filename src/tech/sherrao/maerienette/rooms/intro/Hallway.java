package tech.sherrao.maerienette.rooms.intro;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import tech.sherrao.maerienette.GameApp;
import tech.sherrao.maerienette.Room;
import tech.sherrao.maerienette.screens.MainScreen;

public class Hallway extends Room {

	public Hallway(GameApp game, MainScreen screen) {
		super(game, screen, "temp/rooms/scene1/intro/hallway.png");
		super.rightRoom = "Intro.LivingRoom";
		
		new Image().setDrawable( new TextureAtlas().g );
		
	}

	@Override
	public void load() {
		super.load();
	
	}

	@Override
	public void clear() {
		
	}

}
