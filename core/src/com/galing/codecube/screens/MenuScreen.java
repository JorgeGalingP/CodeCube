package com.galing.codecube.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.galing.codecube.AssetManager;
import com.galing.codecube.CodeCube;

public class MenuScreen extends Screen {

    public MenuScreen(CodeCube game) {
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

        // set textures
        TextureRegionDrawable squareCircleWindow = new TextureRegionDrawable(AssetManager.squareCircleWindow);
        TextureRegionDrawable blueNoPressed = new TextureRegionDrawable(AssetManager.blueNoPressed);
        TextureRegionDrawable greenPressed = new TextureRegionDrawable(AssetManager.greenPressed);
        TextureRegionDrawable playIcon = new TextureRegionDrawable(AssetManager.playIcon);

        // create buttons
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle(blueNoPressed,
                greenPressed, blueNoPressed, new BitmapFont());
        ImageButton.ImageButtonStyle imageButtonStyle =
                new ImageButton.ImageButtonStyle(blueNoPressed, greenPressed, greenPressed, playIcon, playIcon,
                        playIcon);
        imageButtonStyle.imageUp.setMinHeight(125f);
        imageButtonStyle.imageUp.setMinWidth(125f);
        TextButton.TextButtonStyle squareStyle = new TextButton.TextButtonStyle(squareCircleWindow, squareCircleWindow,
                squareCircleWindow, new BitmapFont());

        TextButton title = new TextButton("Code Cube", squareStyle);
        ImageButton playButton = new ImageButton(imageButtonStyle);
        TextButton difficultyButton = new TextButton("Difficulty", buttonStyle);

        // add listeners to buttons
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ModeScreen(game));
            }
        });
        difficultyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new DifficultyScreen(game));
            }
        });

        // add background to table
        table.setBackground(new TextureRegionDrawable(AssetManager.bg));

        // add buttons and padding to table
        table.add(title).padBottom(150);
        table.row();
        table.add(playButton).width(350).height(200).pad(50);
        table.row();
        table.add(difficultyButton).width(350).height(200).pad(50);
        table.row();

        // add table to stage
        stage.addActor(table);
    }
}
