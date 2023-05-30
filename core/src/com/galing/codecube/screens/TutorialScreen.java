package com.galing.codecube.screens;

import com.badlogic.gdx.Gdx;
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
import com.galing.codecube.enums.SoundType;
import com.galing.codecube.windows.TutorialPauseWindow;
import com.galing.codecube.windows.TutorialWindow;

public class TutorialScreen extends Screen {

    private enum GameState {
        INIT,
        WELCOME,
        PHASE_1, PHASE_2, PHASE_3, PHASE_4, PHASE_5, PHASE_6, PHASE_7, PHASE_8,
        PHASE_1_WINDOW, PHASE_2_WINDOW, PHASE_3_WINDOW, PHASE_4_WINDOW,
        PHASE_5_WINDOW, PHASE_6_WINDOW, PHASE_7_WINDOW, PHASE_8_WINDOW,
        END,
    }

    public static final int VIEWPORT_WIDTH = 12;
    public static final int VIEWPORT_HEIGHT = 20;

    public GameState state;
    private final Stage stageGame;

    private final Button homeButton;
    private final Button debugButton;

    private final Board board;

    public TutorialScreen(final CodeCube game, BoardType boardType) {
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
                Assets.playSound(SoundType.ClickSound);
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
                Assets.playSound(SoundType.ClickSound);
                board.setDebugMode();
            }
        });

        // start particles
        Assets.confettiParticle.start();
        Assets.confettiParticle.setPosition(stage.getViewport().getWorldWidth() / 2f,
                stage.getViewport().getWorldHeight() / 2f);

        setInit();
    }

    @Override
    public void draw(float delta) {
        super.draw(delta);
        board.render();
        stageGame.draw();

        // draw tutorial label
        batch.begin();
        Assets.vagaRoundBoldWhite35.draw(batch, "Tutorial", stage.getViewport().getWorldWidth() / 15,
                stage.getViewport().getWorldHeight() - 50);
        batch.end();

        if (board.isWin()) {
            Assets.confettiParticle.update(delta);

            batch.begin();
            Assets.confettiParticle.draw(batch, delta);
            batch.end();
        } else
            Assets.confettiParticle.reset();
    }

    @Override
    public void update(float delta) {
        Assets.playGameMusic();

        board.handleBoxes();

        if (state.equals(GameState.PHASE_1)
                || state.equals(GameState.PHASE_2)
                || state.equals(GameState.PHASE_3)) {
            stageGame.act(delta);

            // set disabled depending board's running state
            debugButton.setDisabled(board.isRunning());

            if (board.isGameOver())
                setEnd();
        }

        // if state is paused, do nothing
        switch (state) {
            case INIT:
                // add actors
                stageGame.addActor(board);
                stage.addActor(homeButton);
                stage.addActor(debugButton);

                setWelcome();

                stage.addActor(new TutorialWindow(game, this, "Welcome!"));
                break;
            case PHASE_1:
                setWindowPhase(1);
                stage.addActor(new TutorialWindow(game, this, "Introduction"));
                break;
            case PHASE_2:
                setWindowPhase(2);
                stage.addActor(new TutorialWindow(game, this, "Touch pause button"));
                break;
            case PHASE_3:
                if (homeButton.isPressed()) {
                    setWindowPhase(3);
                    stage.addActor(new TutorialPauseWindow(game, this));
                }
                break;
            case PHASE_4:
                setWindowPhase(4);
                stage.addActor(new TutorialWindow(game, this, "Good job!. Now, touch debug button"));
                break;
            case PHASE_5:
                if (debugButton.isPressed()) {
                    setWindowPhase(5);
                    stage.addActor(new TutorialWindow(game, this, "Good!"));
                }
                break;
            case END:
                // back to main menu
                game.setScreen(new ModeScreen(game));
                break;
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

    public void setInit() {
        if (state != GameState.END) {
            state = GameState.INIT;
            Gdx.app.log("GAME_STATE", state.toString());
        }
    }

    public void setWelcome() {
        if (state == GameState.INIT) {
            state = GameState.WELCOME;
            Gdx.app.log("GAME_STATE", state.toString());
        }
    }

    public void nextPhase() {
        switch (state) {
            case WELCOME:
                setPhase(1);
                break;
            case PHASE_1_WINDOW:
                setPhase(2);
                break;
            case PHASE_2_WINDOW:
                setPhase(3);
                break;
            case PHASE_3_WINDOW:
                setPhase(4);
                break;
            case PHASE_4_WINDOW:
                setPhase(5);
                break;
            case PHASE_5_WINDOW:
                setPhase(6);
                break;
            case PHASE_6_WINDOW:
                setPhase(7);
                break;
        }
    }

    public void setPhase(int n) {
        if (state != GameState.END) {
            switch (n) {
                case 1:
                    state = GameState.PHASE_1;
                    break;
                case 2:
                    state = GameState.PHASE_2;
                    break;
                case 3:
                    state = GameState.PHASE_3;
                    break;
                case 4:
                    state = GameState.PHASE_4;
                    break;
                case 5:
                    state = GameState.PHASE_5;
                    break;
                case 6:
                    state = GameState.PHASE_6;
                    break;
                case 7:
                    state = GameState.PHASE_7;
                    break;
            }
            Gdx.app.log("GAME_STATE", state.toString());
        }
    }

    public void setWindowPhase(int n) {
        if (state != GameState.END) {
            switch (n) {
                case 1:
                    state = GameState.PHASE_1_WINDOW;
                    break;
                case 2:
                    state = GameState.PHASE_2_WINDOW;
                    break;
                case 3:
                    state = GameState.PHASE_3_WINDOW;
                    break;
                case 4:
                    state = GameState.PHASE_4_WINDOW;
                    break;
                case 5:
                    state = GameState.PHASE_5_WINDOW;
                    break;
                case 6:
                    state = GameState.PHASE_6_WINDOW;
                    break;
            }
            Gdx.app.log("GAME_STATE", state.toString());
        }
    }

    private void setEnd() {
        state = GameState.END;
        Gdx.app.log("GAME_STATE", state.toString());
    }
}
