package tech.sherrao.maerienette.rooms;

import tech.sherrao.maerienette.GameApp;
import tech.sherrao.maerienette.GameComponent;
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

	private String nextRoom;
	private float nextX;
	
	protected String leftRoom;
	protected String rightRoom;
	protected boolean fadeLeft;
	protected boolean fadeRight;
	
	public Room(final GameApp game, MainScreen screen) {
		super(game);

		this.screen = screen;

	}

	public abstract void load();

	public abstract void clear();

	public void wallCollision(Entity wall) {
		if(wall instanceof LeftWall) {
			float nextX = screen.getWorldWidth() - 250f;
			nextRoom = leftRoom;

		} else if(wall instanceof RightWall) {
			float nextX = 250f;
			nextRoom = rightRoom;
		}

	}

	public void update() {
		if(nextRoom != null)
			screen.changeRoom(nextRoom, nextX);

	}

}
