package com.galing.codecube.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.galing.codecube.Assets;
import com.galing.codecube.CodeCube;
import com.galing.codecube.Settings;
import com.galing.codecube.enums.Difficulty;

public class DifficultyScreen extends Screen {

    public DifficultyScreen(CodeCube game) {
        super(game);
    }

    @Override
    public void draw(float delta) {
        // clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(Assets.bg, 0, 0, stage.getWidth(), stage.getHeight());
        stage.getBatch().end();

        stage.draw();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void show() {
        // create tables
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        Table backTable = new Table();
        backTable.setFillParent(true);
        backTable.bottom();

        // create back button
        ImageButton backButton = new ImageButton(Assets.backButtonStyle);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.clickSound.play();
                game.setScreen(new MenuScreen(game));
            }
        });

        // create label
        TextButton selectedTitle =
                new TextButton("Dificultad seleccionada: " + Difficulty.toString(Settings.selectedDifficulty),
                        Assets.greyPanelStyle);

        // create buttons
        TextButton easyButton = new TextButton(Difficulty.toString(Difficulty.EASY),
                Assets.vagaRoundBoldFontLargeButtonStyle);
        TextButton normalButton = new TextButton(Difficulty.toString(Difficulty.NORMAL),
                Assets.vagaRoundBoldFontLargeButtonStyle);
        TextButton hardButton = new TextButton(Difficulty.toString(Difficulty.HARD),
                Assets.vagaRoundBoldFontLargeButtonStyle);

        easyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.clickSound.play();
                Settings.selectedDifficulty = Difficulty.EASY;
                saveSettings();
            }
        });
        normalButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.clickSound.play();
                Settings.selectedDifficulty = Difficulty.NORMAL;
                saveSettings();
            }
        });
        hardButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.clickSound.play();
                Settings.selectedDifficulty = Difficulty.HARD;
                saveSettings();
            }
        });

        // add buttons and padding to tables
        backTable.add(backButton).width(450).height(100).pad(125);
        backTable.row();

        table.add(selectedTitle);
        table.row();
        table.add(easyButton).width(350).height(150).pad(25);
        table.row();
        table.add(normalButton).width(350).height(150).pad(25);
        table.row();
        table.add(hardButton).width(350).height(150).pad(25);
        table.row();

        // add table to stage
        stage.addActor(table);
        stage.addActor(backTable);
    }

    private void saveSettings() {
        Settings.save();
        game.setScreen(new MenuScreen(game));
    }
}
