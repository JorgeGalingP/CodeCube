package com.galing.codecube;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.galing.codecube.screens.LoadingScreen;
import com.galing.codecube.screens.Screen;

public class CodeCube extends Game {

    private Stage stage;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Assets assets;
    public AssetManager assetManager;

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
        assetManager = new AssetManager();
        assets = new Assets(assetManager);

        // set screen to main game
        setScreen(new LoadingScreen(this));
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        assetManager.dispose();
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

    public Assets getAssets() {
        return assets;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
