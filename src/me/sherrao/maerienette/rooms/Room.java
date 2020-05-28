package me.sherrao.maerienette.rooms;

import me.sherrao.maerienette.GameApp;
import me.sherrao.maerienette.GameComponent;
import me.sherrao.maerienette.entities.Entity;
import me.sherrao.maerienette.screens.MainScreen;

/**
 * Represents a room that the player can go into, housing entities and controlling interactions.
 * Controlled by {@link MainScreen}
 * 
 * @author Nausher Rao
 *
 */
public abstract class Room extends GameComponent {

	protected final MainScreen screen;
	
	public Room(final GameApp game, MainScreen screen) {
		super(game);
		
		this.screen = screen;
		
	}
	
	public abstract void load();
	
	public abstract void update();
	
	public abstract void clear();
	
	public abstract void wallCollision(Entity wall);
	
}
