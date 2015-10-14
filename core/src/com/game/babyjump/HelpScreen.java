package com.game.babyjump;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class HelpScreen extends ScreenAdapter {
    BJGame game;
    OrthographicCamera guiCam;
    Rectangle nextBounds;
    Vector3 touchPoint;
    String files[] = new String[]{"data/help1.png","data/help2.png","data/help3.png","data/help4.png","data/help5.png"};
    Texture[] helpImages = new Texture[5];
    TextureRegion[] helpRegions = new TextureRegion[5];
    int current;

    public HelpScreen(BJGame game) {
        // TODO Auto-generated constructor stub
        this.game = game;

        guiCam = new OrthographicCamera();
        guiCam.setToOrtho(false, 320, 480);
        nextBounds = new Rectangle(320 - 64, 0, 64, 64);
        touchPoint = new Vector3();
        for(int i = 0; i < 5; i++){
            helpImages[i] = Assets.loadTexture(files[i]);
            helpRegions[i] = new TextureRegion(helpImages[i],0,0,320,480);
        }
        current = 0;
    }

    public void update(){
        if (Gdx.input.justTouched()){
            guiCam.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));

            if (nextBounds.contains(touchPoint.x, touchPoint.y)) {
                Assets.playSound(Assets.clickSound);
                current ++;
                if ( current == 4){
                    current = 0;
                    game.setScreen(new MainMenuScreen(game));
                }
            }
        }
    }

    public void draw(){
        GL20 gl = Gdx.gl;
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        guiCam.update();
        game.batcher.setProjectionMatrix(guiCam.combined);
        game.batcher.disableBlending();
        game.batcher.begin();
        game.batcher.draw(helpRegions[current], 0, 0);
        game.batcher.end();

        game.batcher.enableBlending();
        game.batcher.begin();
        game.batcher.draw(Assets.arrow, 320, 0, -64,64);
        game.batcher.end();
    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        super.render(delta);
        update();
        draw();
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        super.dispose();
        for(int i = 0; i < 5; i ++){
            helpImages[i].dispose();
        }
    }


}
