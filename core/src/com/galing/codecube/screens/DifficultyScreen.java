package com.galing.codecube.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.galing.codecube.AssetManager;
import com.galing.codecube.CodeCube;

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
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle(AssetManager.blueNoPressed,
                AssetManager.blueNoPressed,
                AssetManager.blueNoPressed, new BitmapFont());
        TextButton.TextButtonStyle squareStyle = new TextButton.TextButtonStyle(AssetManager.squareCircleWindow,
                AssetManager.squareCircleWindow,
                AssetManager.squareCircleWindow, new BitmapFont());

        TextButton title = new TextButton("Code Cube", squareStyle);
        TextButton easyButton = new TextButton("EASY", buttonStyle);
        TextButton normalButton = new TextButton("NORMAL", buttonStyle);
        TextButton hardButton = new TextButton("HARD", buttonStyle);
        TextButton backButton = new TextButton("Back", buttonStyle);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });

        // add background to table
        table.setBackground(new TextureRegionDrawable(AssetManager.bg));

        // add buttons and padding to table
        table.add(title).padBottom(75);
        table.row();
        table.add(easyButton).width(350).height(200).pad(15);
        table.row();
        table.add(normalButton).width(350).height(200).pad(15);
        table.row();
        table.add(hardButton).width(350).height(200).pad(15);
        table.row();
        table.add(backButton).width(350).height(200).pad(50);
        table.row();

        // add table to stage
        stage.addActor(table);
    }
}
