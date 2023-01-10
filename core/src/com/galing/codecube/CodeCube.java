package com.galing.codecube;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.galing.codecube.screens.MenuScreen;
import com.galing.codecube.screens.Screen;

public class CodeCube extends Game {

    private Stage stage;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    @Override
    public void create() {
        camera = new OrthographicCamera(Screen.WIDTH, Screen.HEIGHT);
        camera.setToOrtho(false, Screen.WIDTH, Screen.HEIGHT);
        camera.update();

        // initialize stage with ScreenViewport
        stage = new Stage(new ExtendViewport(Screen.WIDTH, Screen.HEIGHT, camera));

        // initialize spritebatch
        batch = new SpriteBatch();

        // load settings
        Settings.load();

        // load assets
        AssetManager.load();

        // set screen to main game
        setScreen(new MenuScreen(this));
    }

    public Stage getStage() {
        return stage;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
