package com.galing.codecube.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.galing.codecube.Assets;
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
        TextureRegionDrawable squareCircleWindow = new TextureRegionDrawable(Assets.squareCircleWindow);

        // create buttons
        ImageButton.ImageButtonStyle playButtonStyle =
                Assets.playButtonStyle;
        playButtonStyle.imageDown.setMinHeight(125f);
        playButtonStyle.imageUp.setMinWidth(125f);
        playButtonStyle.imageChecked.setMinWidth(125f);
        TextButton.TextButtonStyle squareStyle = new TextButton.TextButtonStyle(squareCircleWindow, squareCircleWindow,
                squareCircleWindow, Assets.basicFont);

        TextButton title = new TextButton("Code Cube", squareStyle);
        ImageButton playButton = new ImageButton(playButtonStyle);
        TextButton difficultyButton = new TextButton("DIFICULTAD", Assets.fontButtonStyle);
        TextButton helpButton = new TextButton("¿CÓMO JUGAR?", Assets.fontButtonStyle);

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
        helpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HelpScreen(game));
            }
        });

        // add background to table
        table.setBackground(new TextureRegionDrawable(Assets.bg));

        // add buttons and padding to table
        table.add(title).padBottom(150);
        table.row();
        table.add(playButton).width(350).height(200).pad(25);
        table.row();
        table.add(difficultyButton).width(350).height(200).pad(25);
        table.row();
        table.add(helpButton).width(350).height(200).pad(25);
        table.row();

        // add table to stage
        stage.addActor(table);
    }
}
