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

        // set textures
        TextureRegionDrawable squareCircleWindow = new TextureRegionDrawable(AssetManager.squareCircleWindow);
        TextureRegionDrawable blueNoPressed = new TextureRegionDrawable(AssetManager.blueNoPressed);
        TextureRegionDrawable bluePressed = new TextureRegionDrawable(AssetManager.bluePressed);
        TextureRegionDrawable greenNoPressed = new TextureRegionDrawable(AssetManager.greenNoPressed);
        TextureRegionDrawable greenPressed = new TextureRegionDrawable(AssetManager.greenPressed);
        TextureRegionDrawable backIcon = new TextureRegionDrawable(AssetManager.backIcon);

        // create buttons
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle(blueNoPressed, blueNoPressed,
                blueNoPressed, new BitmapFont());
        ImageButton.ImageButtonStyle imageButtonStyle =
                new ImageButton.ImageButtonStyle(blueNoPressed, greenPressed, greenPressed, backIcon, backIcon,
                        backIcon);
        imageButtonStyle.imageUp.setMinHeight(125f);
        imageButtonStyle.imageUp.setMinWidth(125f);

        TextButton sequenceButton = new TextButton("Sequence", buttonStyle);
        TextButton queueButton = new TextButton("Queue", buttonStyle);
        TextButton stackButton = new TextButton("Stack", buttonStyle);
        ImageButton backButton = new ImageButton(imageButtonStyle);

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
        table.add(queueButton).width(350).height(200).pad(25);
        table.row();
        table.add(stackButton).width(350).height(200).pad(25);
        table.row();
        table.add(backButton).width(350).height(200).pad(125);
        table.row();

        // add table to stage
        stage.addActor(table);
    }
}
