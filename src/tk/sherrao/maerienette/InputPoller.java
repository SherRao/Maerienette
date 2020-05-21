package tk.sherrao.maerienette;

import static com.badlogic.gdx.Gdx.input;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

public class InputPoller extends GameAppComponent {

	private final InputMultiplexer inputs;
	private Map<String, Integer> keyMap;
	
	public InputPoller(final GameApp game) {
		super(game);
		
		this.inputs = new InputMultiplexer();
		this.keyMap = new HashMap<String, Integer>(5);
	
		keyMap.put("left", Keys.A);
		keyMap.put("left-alt", Keys.LEFT);
		keyMap.put("right", Keys.D);
		keyMap.put("right-alt", Keys.RIGHT);
		keyMap.put("jump", Keys.W);
		keyMap.put("jump-alt", Keys.SPACE);
		keyMap.put("debug", Keys.H);
		keyMap.put("interact", Keys.E);
		keyMap.put("exit", Keys.ESCAPE);
		keyMap.put("pause", Keys.P);

		Gdx.input.setInputProcessor(inputs);
		
	}
	
	public void addInputSource(InputProcessor processor) {
		inputs.addProcessor(processor);
		
	}
	
	public void removeInputSource(InputProcessor processor) {
		inputs.removeProcessor(processor);
		
	}
	
	public boolean isLeftKey(int keycode) {
		return keycode == getLeftKey() || keycode == getLeftAltKey();
		
	}
	
	public boolean isRightKey(int keycode) {
		return keycode == getRightKey() || keycode == getRightAltKey();
		
	}
	
	public boolean isJumpKey(int keycode) {
		return keycode == getJumpKey() || keycode == getJumpAltKey();
		
	}
	
	public int getLeftKey() {
		return keyMap.get("left");
		
	}
	
	public int getLeftAltKey() {
		return keyMap.get("left-alt");
		
	}
	
	public int getRightKey() {
		return keyMap.get("right");

	}
	
	public int getRightAltKey() {
		return keyMap.get("right-alt");

	}
	
	public int getJumpKey() {
		return keyMap.get("jump");

	}
	
	public int getJumpAltKey() {
		return keyMap.get("jump-alt");

	}
	
	public int getInteractKey() {
		return keyMap.get("interact");

	}
	
	public int getDebugKey() {
		return keyMap.get("debug");
		
	}
	
	public int getExitKey() {
		return keyMap.get("exit");
		
	}
	
	public int getPauseKey() {
		return keyMap.get("pause");
		
	}
	
	public boolean keyPress(String key) {
		return input.isKeyPressed( keyMap.get(key) ) || input.isKeyPressed( keyMap.get(key + "-alt") );
		
	}
	
	public boolean keyTap(String key) {
		return input.isKeyJustPressed( keyMap.get(key) ) || input.isKeyJustPressed( keyMap.get(key + "-alt") );
		
	}
	
	public boolean leftMouseDown() {
		return input.isButtonPressed(Buttons.LEFT);
		
	}

	public boolean leftMouseClick() {
		return input.isButtonJustPressed(Buttons.LEFT);
		
	}
	
	public boolean rightMouseDown() {
		return input.isButtonPressed(Buttons.RIGHT);
		
	}
	
	public boolean rightMouseClick() {
		return input.isButtonJustPressed(Buttons.LEFT);
		
	}
	
}