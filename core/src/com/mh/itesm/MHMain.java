package com.mh.itesm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MHMain extends Game {
	// Hay un SOLO assetManager para el juego
	private final AssetManager assetManager=new AssetManager();


	/*public MHMain() {

		assetManager = new AssetManager();
	}*/

	// Para que las otras pantallas usen el assetManager
	public AssetManager getAssetManager() {
		return assetManager;
	}
	@Override
	public void create() {

		// Lo preparamos para que cargue mapas
		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		// Pone la pantalla inicial (Splash)
		setScreen(new loadingScreen(this));
	}
	@Override
	public void dispose() {
		super.dispose();
		assetManager.clear();
	}
}
