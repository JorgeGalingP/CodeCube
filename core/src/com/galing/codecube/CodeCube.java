package com.galing.codecube;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.galing.codecube.screens.LoadingScreen;
import com.galing.codecube.screens.Screen;

public final class CodeCube extends Game {

    private static CodeCube instance;
    private Screen currentScreen;
    private Stage stage;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    private CodeCube() {
        instance = null;
    }

    @Override
    public void create() {
        camera = new OrthographicCamera(Screen.WIDTH, Screen.HEIGHT);
        camera.setToOrtho(false, Screen.WIDTH, Screen.HEIGHT);
        camera.update();

        // initialize stage with ExtendViewport
        stage = new Stage(new ExtendViewport(Screen.WIDTH, Screen.HEIGHT, camera));

        // initialize spritebatch
        batch = new SpriteBatch();

        // load settings
        Settings.load();

        // set screen to main game
        setCurrentScreen(new LoadingScreen());
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        currentScreen.dispose();
        Assets.getInstance().dispose();
    }

    public static synchronized CodeCube getInstance() {
        if (instance == null)
            instance = new CodeCube();

        return instance;
    }

    public void setCurrentScreen(Screen screen) {
        this.currentScreen = screen;
        instance.setScreen(screen);
    }

    public Screen getScreen() {
        return currentScreen;
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
