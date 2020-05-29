package me.sherrao.maerienette;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;

public class Assets extends GameComponent {

	private AssetManager assets;
	private ObjectMap<String, Skin> skins;
	
	public Assets(final GameApp game) {
		super(game);
		
		this.assets = new AssetManager();
		this.skins = new ObjectMap<>();
		
	}
	
	public void queueAssets() {
		if(assets.isFinished())
			throw new GdxRuntimeException("Can't queue assets once they've finished loading!");
		
		// Loads textures from the "textures' folder
		for(FileHandle file : Gdx.files.internal("textures/").list()) 
			assets.load( new AssetDescriptor<Sprite>(file, Sprite.class) );
		
		// Loads atlases from the "atlases' folder
		for(FileHandle file : Gdx.files.internal("skins/").list()) 
			assets.load( new AssetDescriptor<TextureAtlas>(file, TextureAtlas.class) );
		
		// Loads sounds from the "sounds" folder
		for(FileHandle file : Gdx.files.internal("sounds/").list())
			assets.load( new AssetDescriptor<Sound>(file, Sound.class) );
			
		// Loads music from the "music" folder
		for(FileHandle file : Gdx.files.internal("music/").list())
			assets.load( new AssetDescriptor<Music>(file, Music.class) );
	
		
	}

	public int update() {
		if(assets.isFinished())
			throw new GdxRuntimeException("Can't loading assets if they've already been loaded!");
		
		if(!assets.isFinished()) {
			assets.update();
			return (int) (assets.getProgress() * 100);
			
		} else {
			skins.put("default", new Skin( new FileHandle("skins/default.atlas") ));
			//skins.put("menu", new Skin( new FileHandle("skins/menu.atlas") ));
			return -1;
			
		}
	}
	
	public Sprite getSprite(String name) {
		if(!assets.isFinished())
			throw new GdxRuntimeException("Textures have to be loaded before retrieval!");
			
		return assets.get(name, Sprite.class);
		
	}
	
	public Skin getSkin(String name) {
		if(!assets.isFinished())
			throw new GdxRuntimeException("Skins have to be loaded before retrieval!");
		
		return skins.get(name, skins.get("default"));
		
	}
	
	public TextureAtlas getAtlas(String name) {
		if(!assets.isFinished())
			throw new GdxRuntimeException("Atlases have to be loaded before retrieval!");
			
		return assets.get(name, TextureAtlas.class);
		
	}
	
	public Sound getSound(String name) {
		if(!assets.isFinished())
			throw new GdxRuntimeException("Sounds have to be loaded before retrieval!");
			
		return assets.get(name, Sound.class);
		
	}
	
	public Music getMusic(String name) {
		if(!assets.isFinished())
			throw new GdxRuntimeException("Music has to be loaded before retrieval!");
			
		return assets.get(name, Music.class);
		
	}
	
	public String getCurrent() {
		return this.assets.getAssetNames().peek();
		
	}
	
	public int getTotal() {
		return this.assets.getQueuedAssets() + this.assets.getLoadedAssets();
		
	}
	
	public int getRemaining() {
		return this.assets.getQueuedAssets();
		
	}
	
	public int getFinished() {
		return this.assets.getLoadedAssets();
		
	}
	
}
