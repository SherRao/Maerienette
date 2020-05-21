package tk.sherrao.maerienette.world;

import com.badlogic.gdx.physics.box2d.PolygonShape;

import tk.sherrao.maerienette.GameApp;
import tk.sherrao.maerienette.screens.MainScreen;

public abstract class InteractableEntity extends BaseEntity<PolygonShape> {

	protected int detectionWidth;
	
	protected boolean enabled;
	protected boolean alreadyInteractedWith;
	protected boolean playerInRadius;
	
	public InteractableEntity(final GameApp game, MainScreen screen) {
		super(game, screen);
	
	}
	
	public final void setEnabled(boolean enabled) {
		this.enabled = enabled;
		
	}
	
	public abstract void onPlayerInteract();

	public final int getDetectionWidth() {
		return this.detectionWidth;
		
	}
	
	public final boolean isEnabled() {
		return this.enabled;
		
	}
	
	public final boolean hasBeenInteractedWith() {
		return this.alreadyInteractedWith;
		
	}
	
	

}
