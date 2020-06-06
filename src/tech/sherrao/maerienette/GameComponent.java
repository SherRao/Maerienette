package tech.sherrao.maerienette;

public abstract class GameComponent {

	protected final GameApp game;

	public GameComponent(final GameApp game) {
		this.game = game;

	}

	public final GameApp getGame() {
		return this.game;

	}

}
