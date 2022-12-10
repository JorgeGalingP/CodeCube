package com.galing.codecube.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.galing.codecube.CodeCube;

public class GameScreen extends Screen {
    private enum GameState {
        RUNNING, PAUSED, GAME_OVER;
    }

    public static final int VIEWPORT_WIDTH = 12;
    public static final int VIEWPORT_HEIGHT = 20;

    public GameState state;
    private final OrthographicCamera camera;
    private final Stage stageGame;

    public GameScreen(final CodeCube game) {
        super(game);

        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.update();

        stageGame = new Stage(new ExtendViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera));
        inputMultiplexer.addProcessor(stageGame);

        setRunning();
    }

    @Override
    public void draw(float delta) {
        stageGame.draw();
    }

    @Override
    public void update(float delta) {
        if (state != GameState.PAUSED) {
            stageGame.act(delta);
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stageGame.getViewport().update(width, height, false);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (state.equals(GameState.RUNNING)) {
            if (keycode == Input.Keys.ESCAPE)
                setPause();
        } else if (keycode == Input.Keys.ESCAPE)
            Gdx.app.exit();

        return true;
    }


    private void setGameOver() {
        state = GameState.GAME_OVER;
        Gdx.app.log("GAME_STATE", state.toString());
    }

    public void setRunning() {
        if (state != GameState.GAME_OVER) {
            state = GameState.RUNNING;
            Gdx.app.log("GAME_STATE", state.toString());
        }
    }

    public void setPause() {
        if (state == GameState.RUNNING) {
            state = GameState.PAUSED;
            Gdx.app.log("GAME_STATE", state.toString());
        }
    }
}
