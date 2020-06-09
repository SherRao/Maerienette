package tech.sherrao.maerienette.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

public class BackgroundLabel extends Label {

	private Color backgroundColor;

	public BackgroundLabel(CharSequence text, Skin skin) {
		super(text, skin);

	}

	public BackgroundLabel(CharSequence text, Skin skin, String styleName) {
		super(text, skin, styleName);

	}

	public BackgroundLabel(CharSequence text, Skin skin, String fontName, Color color) {
		super(text, skin, fontName, color);

	}

	public BackgroundLabel(CharSequence text, Skin skin, String fontName, String colorName) {
		super(text, skin, fontName, colorName);

	}

	public BackgroundLabel(CharSequence text, LabelStyle style) {
		super(text, style);

	}

	public void setBackground(Color color) {
		this.backgroundColor = color;

		Pixmap labelColor = new Pixmap((int) super.getWidth(), (int) super.getHeight(), Pixmap.Format.RGB888);
		labelColor.setColor(color);
		labelColor.fill();
		super.getStyle().background = new Image(new Texture(labelColor)).getDrawable();

	}

	public Color getColor() {
		return this.backgroundColor;

	}

}
