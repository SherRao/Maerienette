package tech.sherrao.maerienette.entities;

import tech.sherrao.maerienette.GameApp;
import tech.sherrao.maerienette.screens.MainScreen;

public abstract class InteractableEntity extends Entity {

	protected int detectionWidth;

	protected boolean enabled;
	protected boolean alreadyInteractedWith;
	protected boolean playerInRadius;

	public InteractableEntity(final GameApp game, MainScreen screen, float x, float y, float width, float height) {
		super(game, screen, x, y, width, height);

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
