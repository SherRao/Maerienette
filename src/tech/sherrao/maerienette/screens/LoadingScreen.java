package tech.sherrao.maerienette.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

import tech.sherrao.maerienette.Assets;
import tech.sherrao.maerienette.GameApp;

public class LoadingScreen extends AbstractScreen {

	private Assets assets;
	private Stage stage;
	private Skin skin;
	private Label status;
	private Label currentlyLoading;

	public LoadingScreen(final GameApp game) {
		super(game);

	}

	@Override
	public void load() {
		assets = game.getAssetManager();
		stage = new Stage(view, batch);
		skin = new Skin(Gdx.files.internal("uiskin.json"), new TextureAtlas("uiskin.atlas"));
		status = new Label("", skin);
		status.setAlignment(Align.center);
		currentlyLoading = new Label("", skin);
		currentlyLoading.setAlignment(Align.center);

		stage.addActor(status);
		stage.addActor(currentlyLoading);

	}

	@Override
	public void update() {
		if(assets.update() < 100) {
			status.setText(assets.getRemaining() + "/" + assets.getTotal());
			currentlyLoading.setText(assets.getCurrent());
			stage.act();

		} else
			game.changeScreen(new MainScreen(game));

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

}