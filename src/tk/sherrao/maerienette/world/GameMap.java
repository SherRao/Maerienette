package tk.sherrao.maerienette.world;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import tk.sherrao.maerienette.GameApp;
import tk.sherrao.maerienette.GameComponent;

public abstract class GameMap extends GameComponent {

	protected final World world;
	protected final List<BaseEntity> activeEntities;
	protected final List<BaseEntity> allEntities;
	
	public GameMap(final GameApp game) {
		super(game);
		
		this.world = new World( new Vector2(0f, -9.8f), true);
		this.activeEntities = new ArrayList<>();
		this.allEntities = new ArrayList<>();
		
	} 
	
	public void update() {
		world.step(1/60f, 6, 2);
		
	}

}