package com.galing.codecube.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.galing.codecube.Assets;
import com.galing.codecube.CodeCube;
import com.galing.codecube.board.Board;
import com.galing.codecube.enums.BoardType;
import com.galing.codecube.windows.PauseWindow;

public class GameScreen extends Screen {

    private enum GameState {
        INIT, RUNNING, PAUSED, GAME_OVER;
    }

    public static final int VIEWPORT_WIDTH = 12;
    public static final int VIEWPORT_HEIGHT = 20;

    public GameState state;
    private final Stage stageGame;

    private final Button homeButton;
    private final Button debugButton;

    private final Board board;

    public GameScreen(final CodeCube game, BoardType boardType) {
        super(game);
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.update();

        // need to create a new stage
        stageGame = new Stage(new ExtendViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera));

        // add stage to the Input Multiplexer
        inputMultiplexer.addProcessor(stageGame);

        // load board
        game.getAssets().selectMap(boardType);
        board = new Board(stageGame, boardType);

        // initialize buttons
        ImageButton.ImageButtonStyle homeButtonStyle =
                Assets.pauseButtonStyle;
        homeButtonStyle.imageDown.setMinHeight(50f);
        homeButtonStyle.imageUp.setMinWidth(50f);
        homeButtonStyle.imageChecked.setMinWidth(50f);

        ImageButton.ImageButtonStyle debugButtonStyle =
                Assets.debugButtonStyle;
        debugButtonStyle.imageDown.setMinHeight(50f);
        debugButtonStyle.imageUp.setMinWidth(50f);
        debugButtonStyle.imageChecked.setMinWidth(50f);

        homeButton = new ImageButton(homeButtonStyle);
        homeButton.setSize(stage.getViewport().getWorldWidth() * .1f,
                stage.getViewport().getWorldWidth() * .1f);
        homeButton.setPosition(stage.getViewport().getWorldWidth() - homeButton.getWidth() - 25,
                stage.getViewport().getWorldHeight() - homeButton.getHeight() - 25);
        homeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();

                // add pause window
                createPauseMenu();
            }
        });

        debugButton = new ImageButton(debugButtonStyle);
        debugButton.setSize(stage.getViewport().getWorldWidth() * .1f,
                stage.getViewport().getWorldWidth() * .1f);
        debugButton.setPosition(stage.getViewport().getWorldWidth() - debugButton.getWidth() * 2 - 45,
                stage.getViewport().getWorldHeight() - debugButton.getHeight() - 25);
        debugButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();
                board.setDebugMode();
            }
        });

        setInit();
    }

    @Override
    public void draw(float delta) {
        super.draw(delta);
        board.render();
        stageGame.draw();
    }

    @Override
    public void update(float delta) {
        Assets.playGameMusic();

        board.handleBoxes();

        if (state == GameState.INIT) {
            // add actors
            stageGame.addActor(board);
            stage.addActor(homeButton);
            stage.addActor(debugButton);

            // running
            setRunning();
        }

        if (state.equals(GameState.RUNNING)) {
            stageGame.act(delta);

            // set disabled depending board's running state
            debugButton.setDisabled(board.isRunning());

            if (board.isGameOver())
                setGameOver();
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        board.resize(width, height);
        stageGame.getViewport().update(width, height, false);

        // update buttons position
        homeButton.setPosition(stage.getViewport().getWorldWidth() - homeButton.getWidth() - 25,
                stage.getViewport().getWorldHeight() - homeButton.getHeight() - 25);
        debugButton.setPosition(stage.getViewport().getWorldWidth() - debugButton.getWidth() * 2 - 45,
                stage.getViewport().getWorldHeight() - debugButton.getHeight() - 25);
    }

    @Override
    public void dispose() {
        super.dispose();
        board.dispose();
        stageGame.dispose();
        stage.dispose();
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
                resetTarget();
        }

        return true;
    }

    private void createPauseMenu() {
        setPause();
        stage.addActor(new PauseWindow(game, this));
    }

    public void resetTarget() {
        board.resetTarget();
    }

    public void setInit() {
        if (state != GameState.GAME_OVER) {
            state = GameState.INIT;
            Gdx.app.log("GAME_STATE", state.toString());
        }
    }

    public void setPause() {
        if (state == GameState.RUNNING) {
            state = GameState.PAUSED;
            Gdx.app.log("GAME_STATE", state.toString());
        } else if (state == GameState.PAUSED) {
            state = GameState.RUNNING;
            Gdx.app.log("GAME_STATE", state.toString());
        }
    }

    public void setRunning() {
        if (state != GameState.GAME_OVER) {
            state = GameState.RUNNING;
            Gdx.app.log("GAME_STATE", state.toString());
        }
    }

    private void setGameOver() {
        state = GameState.GAME_OVER;
        Gdx.app.log("GAME_STATE", state.toString());
        // Gdx.app.exit();
        game.setScreen(new MenuScreen(game));
    }
}
