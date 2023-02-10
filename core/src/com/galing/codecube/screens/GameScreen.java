package com.galing.codecube.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.galing.codecube.AssetManager;
import com.galing.codecube.CodeCube;
import com.galing.codecube.Settings;
import com.galing.codecube.board.Board;
import com.galing.codecube.enums.BoardType;
import com.galing.codecube.enums.Difficulty;

public class GameScreen extends Screen {

    private enum GameState {
        INIT, RUNNING, PAUSED, GAME_OVER;
    }

    public static final int VIEWPORT_WIDTH = 12;
    public static final int VIEWPORT_HEIGHT = 20;

    public GameState state;
    private final Stage stageGame;
    private final BoardType boardType;

    private final Button homeButton;
    private final Button debugButton;

    private final Board board;

    private Table initTable;

    public GameScreen(final CodeCube game, BoardType boardType) {
        super(game);

        this.boardType = boardType;
        OrthographicCamera camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.update();

        // need to create a new stage
        stageGame = new Stage(new ExtendViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera));

        // add stage to the Input Multiplexer
        inputMultiplexer.addProcessor(stageGame);

        // load board
        AssetManager.loadMap(boardType);
        board = new Board(stageGame, boardType);

        // initialize buttons
        ImageButton.ImageButtonStyle homeButtonStyle =
                AssetManager.homeButtonStyle;
        homeButtonStyle.imageDown.setMinHeight(50f);
        homeButtonStyle.imageUp.setMinWidth(50f);
        homeButtonStyle.imageChecked.setMinWidth(50f);

        ImageButton.ImageButtonStyle debugButtonStyle =
                AssetManager.debugButtonStyle;
        debugButtonStyle.imageDown.setMinHeight(50f);
        debugButtonStyle.imageUp.setMinWidth(50f);
        debugButtonStyle.imageChecked.setMinWidth(50f);

        homeButton = new ImageButton(homeButtonStyle);
        homeButton.setSize(stage.getViewport().getWorldWidth() * .1f,
                stage.getViewport().getWorldWidth() * .1f);
        homeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ModeScreen(game));
            }
        });

        debugButton = new ImageButton(debugButtonStyle);
        debugButton.setSize(stage.getViewport().getWorldWidth() * .1f,
                stage.getViewport().getWorldWidth() * .1f);
        debugButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                board.setDebugMode();
            }
        });
    }

    @Override
    public void show() {
        setInit();

        // create table
        initTable = new Table();
        initTable.setFillParent(true);
        initTable.center();

        // set textures
        TextureRegionDrawable squareCircleWindow = new TextureRegionDrawable(AssetManager.squareCircleWindow);
        TextButton.TextButtonStyle squareStyle = new TextButton.TextButtonStyle(squareCircleWindow, squareCircleWindow,
                squareCircleWindow, AssetManager.basicFont);

        TextButton selectedTitle =
                new TextButton("Has seleccionado " + BoardType.toString(boardType) + "\n con dificultad " + Difficulty.toString(Settings.selectedDifficulty),
                        squareStyle);
        TextButton areYouReadyTitle = new TextButton("¿Estás preparado?", squareStyle);
        TextButton touchTitle = new TextButton("Pulsa para comenzar el juego.", squareStyle);

        // add background to table
        initTable.setBackground(new TextureRegionDrawable(AssetManager.bg));

        // add buttons and padding to table
        initTable.add(selectedTitle).padBottom(25);
        initTable.row();
        initTable.add(areYouReadyTitle).padBottom(150);
        initTable.row();
        initTable.add(touchTitle).pad(25);
        initTable.row();

        // add table to stage
        stage.addActor(initTable);
    }

    @Override
    public void draw(float delta) {
        board.render();
        stageGame.draw();
    }

    @Override
    public void update(float delta) {
        if (state == GameState.INIT) {
            if (Gdx.input.justTouched()) {
                initTable.remove();

                // add actors
                stageGame.addActor(board);
                stage.addActor(homeButton);
                stage.addActor(debugButton);

                setRunning();
            }
        }
        if (state != GameState.INIT && state != GameState.PAUSED) {
            stageGame.act(delta);

            homeButton.setPosition(stage.getViewport().getWorldWidth() - homeButton.getWidth() - 25,
                    stage.getViewport().getWorldHeight() - homeButton.getHeight() - 25);
            debugButton.setPosition(stage.getViewport().getWorldWidth() - debugButton.getWidth() * 2 - 35,
                    stage.getViewport().getWorldHeight() - debugButton.getHeight() - 25);

            if (state.equals(GameState.RUNNING) && board.isGameOver())
                setGameOver();
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
