package me.sherrao.maerienette.entities;

import me.sherrao.maerienette.GameApp;
import me.sherrao.maerienette.screens.MainScreen;

public abstract class InteractableEntity extends Entity {

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
