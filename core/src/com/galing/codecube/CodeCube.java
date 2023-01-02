package com.galing.codecube;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.galing.codecube.assets.AssetManager;
import com.galing.codecube.enums.BoardType;
import com.galing.codecube.screens.GameScreen;

public class CodeCube extends Game {

    public Stage stage;
    public SpriteBatch batch;

    @Override
    public void create() {
        // initialize stage with ScreenViewport
        stage = new Stage(new ScreenViewport());

        // initialize spritebatch
        batch = new SpriteBatch();

        // load assets
        AssetManager.loadAssets();

        // set screen to main game
        setScreen(new GameScreen(this, BoardType.QUEUE));
    }
}
