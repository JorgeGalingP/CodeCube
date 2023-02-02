package com.galing.codecube.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.galing.codecube.AssetManager;
import com.galing.codecube.CodeCube;
import com.galing.codecube.Settings;
import com.galing.codecube.enums.Difficulty;

public class DifficultyScreen extends Screen {

    public DifficultyScreen(CodeCube game) {
        super(game);
    }

    @Override
    public void draw(float delta) {
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void show() {
        // create table
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        // create buttons
        TextButton easyButton = new TextButton("Fácil", AssetManager.fontButtonStyle);
        TextButton normalButton = new TextButton("Normal", AssetManager.fontButtonStyle);
        TextButton hardButton = new TextButton("Difícil", AssetManager.fontButtonStyle);

        easyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Settings.selectedDifficulty = Difficulty.EASY;
                saveSettings();
            }
        });
        normalButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Settings.selectedDifficulty = Difficulty.NORMAL;
                saveSettings();
            }
        });
        hardButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Settings.selectedDifficulty = Difficulty.HARD;
                saveSettings();
            }
        });

        // add background to table
        table.setBackground(new TextureRegionDrawable(AssetManager.bg));

        // add buttons and padding to table
        table.add(easyButton).width(350).height(200).pad(50);
        table.row();
        table.add(normalButton).width(350).height(200).pad(50);
        table.row();
        table.add(hardButton).width(350).height(200).pad(50);
        table.row();

        // add table to stage
        stage.addActor(table);
    }

    private void saveSettings() {
        Settings.save();
        game.setScreen(new MenuScreen(game));
    }
}
