package com.game.babyjump;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class CreditsScreen extends ScreenAdapter {
    BJGame game;

    OrthographicCamera guiCam;
    Rectangle nextBounds;
    Vector3 touchPoint;
    Texture texture;
    TextureRegion creditsRegion;

    public CreditsScreen (BJGame game) {
        this.game = game;
        guiCam = new OrthographicCamera();
        guiCam.setToOrtho(false, 320, 480);
        nextBounds = new Rectangle(320 - 64, 0, 64, 64);
        touchPoint = new Vector3();
        texture = Assets.loadTexture("data/items2.png");
        creditsRegion = new TextureRegion(texture, 322, 422, 79, 12);
    }

    public void update () {
        if (Gdx.input.justTouched()) {
            guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (nextBounds.contains(touchPoint.x, touchPoint.y)) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new MainMenuScreen(game));
            }
        }
    }

    public void draw () {
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        guiCam.update();
        game.batcher.setProjectionMatrix(guiCam.combined);
        game.batcher.disableBlending();
        game.batcher.begin();
        game.batcher.draw(creditsRegion, 0, 0);
        game.batcher.end();

        game.batcher.enableBlending();
        game.batcher.begin();
        game.batcher.draw(Assets.arrow, 320, 0, -64, 64);
        game.batcher.end();
    }

    @Override
    public void render (float delta) {
        draw();
        update();
    }

    @Override
    public void hide () {
        texture.dispose();
    }
}