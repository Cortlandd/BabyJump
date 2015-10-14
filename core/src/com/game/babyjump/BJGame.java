package com.game.babyjump;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BJGame extends Game {
	public SpriteBatch batcher;
	
	@Override
	public void create () {
		batcher = new SpriteBatch();
		Settings.load();
		Assets.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
