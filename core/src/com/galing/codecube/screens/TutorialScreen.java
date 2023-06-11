package com.galing.codecube.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.galing.codecube.Assets;
import com.galing.codecube.CodeCube;
import com.galing.codecube.board.Board;
import com.galing.codecube.enums.BoardType;
import com.galing.codecube.windows.TutorialWindow;

public class TutorialScreen extends Screen {

    private enum GameState {
        INIT,
        WELCOME,
        PHASE_1, PHASE_2, PHASE_3, PHASE_4, PHASE_5, PHASE_6, PHASE_7,
        PHASE_1_WINDOW, PHASE_2_WINDOW, PHASE_3_WINDOW, PHASE_4_WINDOW,
        PHASE_5_WINDOW, PHASE_6_WINDOW, PHASE_7_WINDOW,
        WIN,
        END,
    }

    public static final int VIEWPORT_WIDTH = 12;
    public static final int VIEWPORT_HEIGHT = 20;

    public GameState state;
    private final Stage stageGame;

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
        Assets.getInstance().selectMap(boardType);
        board = new Board(stageGame, boardType);
        board.getWinTarget().setVisible(false);

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
                || state.equals(GameState.PHASE_3)
                || state.equals(GameState.PHASE_4)
                || state.equals(GameState.PHASE_5)
                || state.equals(GameState.PHASE_6)
                || state.equals(GameState.PHASE_7)
                || state.equals(GameState.PHASE_3_WINDOW)
                || state.equals(GameState.PHASE_5_WINDOW)) {
            stageGame.act(delta);

            if (board.isGameOver())
                setEnd();
        }

        // if state is paused, do nothing
        switch (state) {
            case INIT:
                // add actors
                stageGame.addActor(board);

                setWelcome();

                stage.addActor(new TutorialWindow(game, this, "Welcome to Code Cube!"));
                break;
            case PHASE_1:
                setWindowPhase(1);
                stage.addActor(new TutorialWindow(game, this, "The goal is to reach the green target"));
                break;
            case PHASE_2:
                setWindowPhase(2);// generate green target
                stage.addActor(new TutorialWindow(game, this, "Go generate a green target!"));
                break;
            case PHASE_3:
                setWindowPhase(3);
                stage.addAction(Actions.sequence(
                        Actions.run(board::regenerateTarget),
                        Actions.delay(2f),
                        Actions.run(() -> stage.addActor(
                                new TutorialWindow(game, this, "Here is the green target!")))
                ));
                break;
            case PHASE_4:
                setWindowPhase(4);
                stage.addActor(new TutorialWindow(game, this, "Now, drag a box to the main program"));

                break;
            case PHASE_5:
                if (!board.getGameControl().isProgramEmpty()) {
                    setWindowPhase(5);
                    stage.addAction(Actions.sequence(
                            Actions.delay(2f),
                            Actions.run(() -> stage.addActor(new TutorialWindow(game, this, "Nice!")))
                    ));
                }
                break;
            case PHASE_6:
                setWindowPhase(6);
                stage.addActor(new TutorialWindow(game, this, "Try to reach the target"));
                break;
            case PHASE_7:
                if (board.isWin()) {
                    setWindowPhase(7);
                    stage.addActor(new TutorialWindow(game, this, "Good job! You win!"));
                }
                break;
            case WIN:
                stage.addActor(new TutorialWindow(game, this, "Tutorial finished!"));
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
            case WIN:
                setPhase(-1); // finish tutorial
                break;
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
            case PHASE_7_WINDOW:
                setPhase(0);
                break;
        }
    }

    public void setPhase(int n) {
        if (state != GameState.END) {
            switch (n) {
                case -1:
                    state = GameState.END;
                    break;
                case 0:
                    state = GameState.WIN;
                    break;
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
                case 7:
                    state = GameState.PHASE_7_WINDOW;
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
