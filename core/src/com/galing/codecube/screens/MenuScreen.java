package com.galing.codecube.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.galing.codecube.CodeCube;
import com.galing.codecube.assets.AssetManager;
import com.galing.codecube.enums.BoardType;

public class MenuScreen extends Screen {

    public MenuScreen(CodeCube game) {
        super(game);
    }

    @Override
    public void draw(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game, BoardType.SEQUENCE));
            dispose();
        }
    }

    @Override
    public void show() {
        super.show();

        //Stage should control input:
        Gdx.input.setInputProcessor(stage);

        // create Table
        Table table = new Table();
        table.setFillParent(true);
        table.bottom();
        table.padBottom(75);

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
                game.setScreen(new GameScreen(game, BoardType.SEQUENCE));
            }
        });
        queueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, BoardType.QUEUE));
            }
        });
        stackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, BoardType.STACK));
            }
        });

        //Add buttons to table
        table.add(title).padBottom(175);
        table.row();
        table.add(sequenceButton).width(350).height(200).pad(15);
        table.row();
        table.add(queueButton).width(350).height(200).pad(15);
        table.row();
        table.add(stackButton).width(350).height(200).pad(15);
        table.row();

        //Add table to stage
        stage.addActor(table);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(float delta) {
        stage.act();
        batch.begin();
        batch.draw(AssetManager.bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.draw();
    }
}
