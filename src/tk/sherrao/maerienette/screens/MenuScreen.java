package tk.sherrao.maerienette.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

import tk.sherrao.maerienette.GameApp;

public class MenuScreen extends AbstractScreen {

	private Stage stage;
	private Skin skin;
	
	private ImageButton play;
	private ImageButton credits;
	private ImageButton quit;
	private TypingLabel copyright;
	
	private Slider soundVolume;
	private Slider musicVolume;
	
	public MenuScreen(final GameApp game) {
		super(game);
	}
	
	@Override
	public void load() {
		stage = new Stage(view, batch);
		skin = new Skin( Gdx.files.internal("skins/menu/skin.json") );
		
		play = new ImageButton(skin.getDrawable(null), skin.getDrawable(null), skin.getDrawable(null));
		credits = new ImageButton(skin.getDrawable(null), skin.getDrawable(null), skin.getDrawable(null));
		quit = new ImageButton(skin.getDrawable(null), skin.getDrawable(null), skin.getDrawable(null));
		copyright = new TypingLabel("AEARENUS 2020", skin);
		
		soundVolume = new Slider(0f, 100f, 1f, false, skin);
		musicVolume = new Slider(0f, 100f, 1f, false, skin);
		
		stage.addActor(play);
		stage.addActor(credits);
		stage.addActor(quit);
		stage.addActor(copyright);
		stage.addActor(soundVolume);
		stage.addActor(musicVolume);
		input.addInputSource(stage);
		
		addButtonListeners();
		
	}


	@Override
	public void update() {
		stage.setDebugAll( game.isDebug() );
		stage.act();
		
	}

	@Override
	public void draw() {
		stage.draw();
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		
	}
	
	private void addButtonListeners() {
		play.addListener( new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				game.setScreen( game.findScreen("game") );
				
			}
		} );

		credits.addListener( new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				game.setScreen( game.findScreen("credits") );
				
			}
		} );
		
		quit.addListener( new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.stop();
				
			}
		} );
		
	}

}
