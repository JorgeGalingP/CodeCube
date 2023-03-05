package com.galing.codecube.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.galing.codecube.Assets;
import com.galing.codecube.CodeCube;
import com.galing.codecube.Settings;
import com.galing.codecube.enums.Difficulty;

public class DifficultyScreen extends Screen {

    private Table difficultyTable;
    private TextButton easyButton;
    private TextButton normalButton;
    private TextButton hardButton;

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
        // reset styles
        this.easyButton.setStyle(Settings.selectedDifficulty.equals(Difficulty.EASY) ?
                Assets.vagaRoundBoldFontLargeSelectedButtonStyle : Assets.vagaRoundBoldFontLargeButtonStyle);
        this.normalButton.setStyle(Settings.selectedDifficulty.equals(Difficulty.NORMAL) ?
                Assets.vagaRoundBoldFontLargeSelectedButtonStyle : Assets.vagaRoundBoldFontLargeButtonStyle);
        this.hardButton.setStyle(Settings.selectedDifficulty.equals(Difficulty.HARD) ?
                Assets.vagaRoundBoldFontLargeSelectedButtonStyle : Assets.vagaRoundBoldFontLargeButtonStyle);
    }

    @Override
    public void show() {
        // create tables
        difficultyTable = new Table();
        setDifficultyTableBounds();
        difficultyTable.top().padTop(50);
        difficultyTable.background(new NinePatchDrawable(new NinePatch(Assets.greyPanel,
                12, 12, 12, 12)));

        Table backTable = new Table();
        backTable.setFillParent(true);
        backTable.bottom();

        // create labels
        Label difficultyLabel = new Label("Dificultad", new Label.LabelStyle(Assets.vagaRoundBoldGray25, null));
        Label configurationLabel = new Label("Configuración", new Label.LabelStyle(Assets.vagaRoundBoldGray35, null));

        // create back button
        ImageButton backButton = new ImageButton(Assets.backButtonStyle);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.clickSound.play();
                game.setScreen(new MenuScreen(game));
            }
        });

        // create buttons
        easyButton = new TextButton(Difficulty.toString(Difficulty.EASY),
                Assets.vagaRoundBoldFontLargeButtonStyle);
        normalButton = new TextButton(Difficulty.toString(Difficulty.NORMAL),
                Assets.vagaRoundBoldFontLargeButtonStyle);
        hardButton = new TextButton(Difficulty.toString(Difficulty.HARD),
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

        difficultyTable.add(configurationLabel).colspan(4).center().padBottom(75);
        difficultyTable.row();
        difficultyTable.add(difficultyLabel).pad(25);
        difficultyTable.add(easyButton).width(100).height(50).pad(10);
        difficultyTable.add(normalButton).width(100).height(50).pad(10);
        difficultyTable.add(hardButton).width(100).height(50).pad(10);
        difficultyTable.row();

        backTable.add(backButton).width(450).height(100).pad(125);
        backTable.row();

        // add difficultyTable to stage
        stage.addActor(difficultyTable);
        stage.addActor(backTable);
    }

    private void saveSettings() {
        Settings.save();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        setDifficultyTableBounds();
    }

    private void setDifficultyTableBounds() {
        difficultyTable.setPosition(stage.getWidth() / 2 - (stage.getWidth() * 0.8f) / 2,
                stage.getHeight() / 1.65f - (stage.getHeight() * 0.7f) / 2);
        difficultyTable.setSize(stage.getWidth() * 0.8f,
                stage.getHeight() * 0.7f);
    }
}
