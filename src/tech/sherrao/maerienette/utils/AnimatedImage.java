package tech.sherrao.maerienette.utils;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AnimatedImage extends Image {

	private Animation<TextureRegionDrawable> animation;
	private float stateTime;
	
	public AnimatedImage(Animation<TextureRegionDrawable> animation) {
		super(animation.getKeyFrame(0));

		this.animation = animation;
		
	}
	
	@Override
	public void act(float delta) {
		stateTime += delta;
		super.setDrawable(animation.getKeyFrame(stateTime));
		
	}

	public void reset() {
		this.stateTime = 0;
		
	}
	
	public Animation<TextureRegionDrawable> getAnimation() { return this.animation; }
	
	public float getStateTime() { return this.stateTime; }
	
}
