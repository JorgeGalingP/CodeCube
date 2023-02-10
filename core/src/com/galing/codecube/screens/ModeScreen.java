package com.galing.codecube.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.galing.codecube.AssetManager;
import com.galing.codecube.CodeCube;
import com.galing.codecube.enums.BoardType;

public class ModeScreen extends Screen {

    public ModeScreen(CodeCube game) {
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
        ImageButton.ImageButtonStyle imageButtonStyle = AssetManager.backButtonStyle;
        imageButtonStyle.imageUp.setMinHeight(125f);
        imageButtonStyle.imageUp.setMinWidth(125f);

        TextButton sequenceButton = new TextButton(BoardType.toString(BoardType.SEQUENCE),
                AssetManager.fontButtonStyle);
        TextButton stackButton = new TextButton(BoardType.toString(BoardType.STACK), AssetManager.fontButtonStyle);
        TextButton queueButton = new TextButton(BoardType.toString(BoardType.QUEUE), AssetManager.fontButtonStyle);
        ImageButton backButton = new ImageButton(imageButtonStyle);

        // add listeners to buttons
        sequenceButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, BoardType.SEQUENCE));
            }
        });
        stackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, BoardType.STACK));
            }
        });
        queueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, BoardType.QUEUE));
            }
        });
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });

        // add background to table
        table.setBackground(new TextureRegionDrawable(AssetManager.bg));

        // add buttons and padding to table
        table.add(sequenceButton).width(350).height(200).pad(25);
        table.row();
        table.add(stackButton).width(350).height(200).pad(25);
        table.row();
        table.add(queueButton).width(350).height(200).pad(25);
        table.row();
        table.add(backButton).width(350).height(200).pad(125);
        table.row();

        // add table to stage
        stage.addActor(table);
    }
}
