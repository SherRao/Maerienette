package me.sherrao.maerienette.rooms;

import static com.badlogic.gdx.Gdx.files;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import me.sherrao.maerienette.GameApp;
import me.sherrao.maerienette.entities.Entity;
import me.sherrao.maerienette.entities.LeftWall;
import me.sherrao.maerienette.entities.RightWall;
import me.sherrao.maerienette.screens.MainScreen;

public class IntroRoom extends Room {

	private Image background;
	private Room leftRoom;
	private Room rightRoom;
	
	public IntroRoom(final GameApp game, MainScreen screen) {
		super(game, screen);
	
	}

	@Override
	public void load() {
		background = new Image( new Texture( files.internal("textures/rooms/introroom/background.jpg")) );
		background.setFillParent(true);
		background.setUserObject(this);
		background.setZIndex(2);
		
		screen.getWorldStage().addActor(background);
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void clear() {
		
	}
	
	@Override
	public void wallCollision(Entity wall) {
		if(wall instanceof LeftWall)
			screen.changeRoom( new TestRoom(game, screen));
			
		else if (wall instanceof RightWall) 
			screen.changeRoom( new TestRoom2(game, screen));

	}
	
	
}
