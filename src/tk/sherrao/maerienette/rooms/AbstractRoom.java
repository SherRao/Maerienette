package tk.sherrao.maerienette.rooms;

import tk.sherrao.maerienette.GameApp;
import tk.sherrao.maerienette.GameComponent;
import tk.sherrao.maerienette.screens.MainScreen;

public abstract class AbstractRoom extends GameComponent {

	protected final MainScreen screen;
	
	public AbstractRoom(final GameApp game, MainScreen screen) {
		super(game);
		
		this.screen = screen;
		
	}
	
	public abstract void load();
	
	public abstract void update();
	
	public abstract void clear();
	
}
