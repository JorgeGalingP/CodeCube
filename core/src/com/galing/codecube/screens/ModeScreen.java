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
import com.galing.codecube.enums.BoardType;

public class ModeScreen extends Screen {

    public ModeScreen(CodeCube game) {
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
        // create table
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

        // create buttons
        TextButton sequenceButton = new TextButton(BoardType.toString(BoardType.SEQUENCE),
                Assets.vagaRoundBoldFontLargeButtonStyle);
        TextButton stackButton = new TextButton(BoardType.toString(BoardType.STACK),
                Assets.vagaRoundBoldFontLargeButtonStyle);
        TextButton queueButton = new TextButton(BoardType.toString(BoardType.QUEUE),
                Assets.vagaRoundBoldFontLargeButtonStyle);

        // add listeners to buttons
        sequenceButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.clickSound.play();
                game.setScreen(new GameScreen(game, BoardType.SEQUENCE));
            }
        });
        stackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.clickSound.play();
                game.setScreen(new GameScreen(game, BoardType.STACK));
            }
        });
        queueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.clickSound.play();
                game.setScreen(new GameScreen(game, BoardType.QUEUE));
            }
        });


        // add buttons and padding to table
        backTable.add(backButton).width(450).height(100).pad(125);
        backTable.row();

        table.add(sequenceButton).width(350).height(150).pad(25);
        table.row();
        table.add(stackButton).width(350).height(150).pad(25);
        table.row();
        table.add(queueButton).width(350).height(150).pad(25);
        table.row();

        // add table to stage
        stage.addActor(table);
        stage.addActor(backTable);
    }
}
