package com.galing.codecube.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.galing.codecube.CodeCube;
import com.galing.codecube.board.Board;

public class GameScreen extends Screen {
    private enum GameState {
        RUNNING, PAUSED, GAME_OVER;
    }

    public static final int VIEWPORT_WIDTH = 12;
    public static final int VIEWPORT_HEIGHT = 20;

    public GameState state;
    private final Stage stageGame;

    private final Board board;

    public GameScreen(final CodeCube game) {
        super(game);

        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.update();

        stageGame = new Stage(new ExtendViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera));
        inputMultiplexer.addProcessor(stageGame);

        board = new Board(stageGame);

        stageGame.addActor(board);

        setRunning();
    }

    @Override
    public void draw(float delta) {
        board.render();
        stageGame.draw();
    }

    @Override
    public void update(float delta) {
        if (state != GameState.PAUSED) {
            stageGame.act(delta);

            if (state.equals(GameState.RUNNING) && board.isGameOver()) {
                setGameOver();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        board.resize(width, height);
        stageGame.getViewport().update(width, height, false);
    }

    @Override
    public void dispose() {
        super.dispose();
        board.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (state.equals(GameState.RUNNING)) {
            if (keycode == Input.Keys.ESCAPE)
                setPause();
        }
        if (keycode == Input.Keys.ESCAPE)
            Gdx.app.exit();

        return true;
    }


    private void setGameOver() {
        state = GameState.GAME_OVER;
        Gdx.app.log("GAME_STATE", state.toString());
        Gdx.app.exit();
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
