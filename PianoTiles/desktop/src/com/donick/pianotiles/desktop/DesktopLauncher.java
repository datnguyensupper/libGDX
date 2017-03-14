package com.donick.pianotiles.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.donick.pianotiles.PianoTiles;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 900/2;
		config.height = 1600/2;
		new LwjglApplication(new PianoTiles(), config);
	}
}
