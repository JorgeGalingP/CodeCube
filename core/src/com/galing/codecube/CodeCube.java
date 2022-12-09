package com.galing.codecube;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class CodeCube extends Game {

    public Stage stage;
    public SpriteBatch batch;

    @Override
    public void create() {
        // initialize stage with ScreenViewport
        stage = new Stage(new ScreenViewport());

        // initialize spritebatch
        batch = new SpriteBatch();

        // TODO load assets
        // TODO create assets class
        // Assets.load();

        // TODO set screen to main game
        // this.setScreen();
    }
}
