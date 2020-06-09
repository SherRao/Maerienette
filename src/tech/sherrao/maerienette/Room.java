package tech.sherrao.maerienette;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Logger;

import tech.sherrao.maerienette.entities.Entity;
import tech.sherrao.maerienette.entities.LeftWall;
import tech.sherrao.maerienette.entities.RightWall;
import tech.sherrao.maerienette.screens.MainScreen;

/**
 * Represents a room that the player can go into, housing entities and
 * controlling interactions. Controlled by {@link MainScreen}
 * 
 * @author Nausher Rao
 *
 */
public abstract class Room extends GameComponent {

	protected final MainScreen screen;
	protected final Logger logger;
	
	private String background;
	private String nextRoom;
	private float nextX;
	private boolean fadeToNext;
	
	protected String leftRoom;
	protected String rightRoom;
	protected boolean fadeLeft;
	protected boolean fadeRight;
	
	public Room(GameApp game, final MainScreen screen, String background) {
		super(game);

		this.logger = new Logger("Entity/" + this.getClass().getSimpleName(), Logger.DEBUG);
		this.screen = screen;
		this.background = background;

	}

	public void load() {
		screen.setBackground(new Texture(Gdx.files.internal(background)));
		
	}

	public abstract void clear();

	public void wallCollision(Entity wall) {
		if(wall instanceof LeftWall && leftRoom != null) {
			this.nextX = screen.getWorldWidth() - 250f;
			nextRoom = leftRoom;
			fadeToNext = fadeLeft;

		} else if(wall instanceof RightWall && rightRoom != null) {
			this.nextX = 250f;
			nextRoom = rightRoom;
			fadeToNext = fadeRight;
			
		} else
			return;

	}

	public void update() {
		if(nextRoom != null)
			screen.changeRoom(nextRoom, nextX, fadeToNext);

	}

}
