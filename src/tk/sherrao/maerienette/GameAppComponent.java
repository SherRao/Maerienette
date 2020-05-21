package tk.sherrao.maerienette;

public abstract class GameAppComponent {

	protected final GameApp game;
	
	public GameAppComponent(final GameApp game) {
		this.game = game;
		
	}
	
	public final GameApp getGame() {
		return this.game;
		
	}
	
}
