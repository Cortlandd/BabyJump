package com.game.babyjump.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.babyjump.BJGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Baby Jump";
		config.width = 420;
		config.height = 800;
		new LwjglApplication(new BJGame(), config);
	}
}
