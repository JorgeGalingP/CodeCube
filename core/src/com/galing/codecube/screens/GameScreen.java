package com.galing.codecube.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.galing.codecube.AssetManager;
import com.galing.codecube.CodeCube;
import com.galing.codecube.board.Board;
import com.galing.codecube.enums.BoardType;

public class GameScreen extends Screen {

    private enum GameState {
        RUNNING, PAUSED, GAME_OVER;
    }

    public static final int VIEWPORT_WIDTH = 12;
    public static final int VIEWPORT_HEIGHT = 20;

    public GameState state;
    private final Stage stageGame;

    private final Button pauseButton;

    private final Board board;

    public GameScreen(final CodeCube game, BoardType type) {
        super(game);

        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.update();

        // need to create a new stage
        stageGame = new Stage(new ExtendViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera));

        // add stage to the Input Multiplexer
        inputMultiplexer.addProcessor(stageGame);

        // load board
        AssetManager.loadMap(type);
        board = new Board(stageGame, type);

        // initialize buttons
        TextureRegionDrawable blueNoPressed = new TextureRegionDrawable(AssetManager.blueNoPressed);
        TextureRegionDrawable greenPressed = new TextureRegionDrawable(AssetManager.greenPressed);
        TextureRegionDrawable pauseIcon = new TextureRegionDrawable(AssetManager.pauseIcon);
        ImageButton.ImageButtonStyle imageButtonStyle =
                new ImageButton.ImageButtonStyle(blueNoPressed, greenPressed, greenPressed, pauseIcon, pauseIcon,
                        pauseIcon);
        imageButtonStyle.imageDown.setMinHeight(50f);
        imageButtonStyle.imageUp.setMinWidth(50f);
        imageButtonStyle.imageChecked.setMinWidth(50f);
        pauseButton = new ImageButton(imageButtonStyle);
        pauseButton.setSize(stage.getViewport().getWorldWidth() * .1f,
                stage.getViewport().getWorldWidth() * .1f);
        pauseButton.setPosition(stage.getViewport().getWorldWidth() - pauseButton.getWidth() - 25,
                stage.getViewport().getWorldHeight() - pauseButton.getHeight() - 25);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                setPause();
            }
        });

        // add actors
        stageGame.addActor(board);
        stage.addActor(pauseButton);

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

        pauseButton.setPosition(stage.getViewport().getWorldWidth() - pauseButton.getWidth() - 25,
                stage.getViewport().getWorldHeight() - pauseButton.getHeight() - 25);
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
        if (state.equals(GameState.PAUSED)) {
            if (keycode == Input.Keys.ESCAPE)
                Gdx.app.exit();
        }

        if (state.equals(GameState.RUNNING)) {
            if (keycode == Input.Keys.ESCAPE)
                setPause();
            if (keycode == Input.Keys.TAB)
                board.resetTarget();
        }

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
