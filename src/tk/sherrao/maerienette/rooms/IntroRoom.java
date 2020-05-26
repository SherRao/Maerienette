package tk.sherrao.maerienette.rooms;

import static com.badlogic.gdx.Gdx.files;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import tk.sherrao.maerienette.GameApp;
import tk.sherrao.maerienette.screens.MainScreen;

public class IntroRoom extends AbstractRoom {

	private Image background;
	
	public IntroRoom(final GameApp game, MainScreen screen) {
		super(game, screen);
	
	}

	@Override
	public void load() {
		background = new Image( new Texture( files.internal("textures/rooms/introroom/background.jpg")) );
		background.setFillParent(true);
		
		screen.getWorldStage().addActor(background);
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void clear() {
		
	}
	
	
}
