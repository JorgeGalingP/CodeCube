package com.galing.codecube.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.galing.codecube.CodeCube;
import com.galing.codecube.assets.AssetManager;
import com.galing.codecube.enums.BoardType;
import com.galing.codecube.enums.Difficulty;

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

        // create buttons
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle(AssetManager.blueNoPressed,
                AssetManager.blueNoPressed,
                AssetManager.blueNoPressed, new BitmapFont());
        TextButton.TextButtonStyle squareStyle = new TextButton.TextButtonStyle(AssetManager.squareCircleWindow,
                AssetManager.squareCircleWindow,
                AssetManager.squareCircleWindow, new BitmapFont());

        TextButton title = new TextButton("Code Cube", squareStyle);
        TextButton sequenceButton = new TextButton("Sequence", buttonStyle);
        TextButton queueButton = new TextButton("Queue", buttonStyle);
        TextButton stackButton = new TextButton("Stack", buttonStyle);

        // add listeners to buttons
        sequenceButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, Difficulty.EASY, BoardType.SEQUENCE));
            }
        });
        queueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, Difficulty.EASY, BoardType.QUEUE));
            }
        });
        stackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, Difficulty.EASY, BoardType.STACK));
            }
        });

        // add background to table
        table.setBackground(new TextureRegionDrawable(AssetManager.bg));

        // add buttons and padding to table
        table.add(title).padBottom(150);
        table.row();
        table.add(sequenceButton).width(350).height(200).pad(15);
        table.row();
        table.add(queueButton).width(350).height(200).pad(15);
        table.row();
        table.add(stackButton).width(350).height(200).pad(15);
        table.row();

        // add table to stage
        stage.addActor(table);
    }
}
