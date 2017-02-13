package com.donick.helloworld.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.donick.helloworld.HelloWorld;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "hello-world";
		config.useGL30 = false;
		config.width = 480;
		config.height = 320;

		new LwjglApplication(new HelloWorld(), config);
	}
}
