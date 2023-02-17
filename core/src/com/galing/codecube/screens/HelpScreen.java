package com.galing.codecube.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.galing.codecube.AssetManager;
import com.galing.codecube.CodeCube;

public class HelpScreen extends Screen {
    private ScrollPane scrollPane;

    public HelpScreen(CodeCube game) {
        super(game);
    }

    @Override
    public void draw(float delta) {
        batch.begin();
        batch.draw(AssetManager.bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void show() {
        // create tables
        Table helpTable = new Table();
        helpTable.center();
        helpTable.pad(100).defaults().space(75);

        Table backTable = new Table();
        backTable.setFillParent(true);
        backTable.bottom();

        // create back button
        ImageButton.ImageButtonStyle imageButtonStyle = AssetManager.backButtonStyle;
        imageButtonStyle.imageUp.setMinHeight(125f);
        imageButtonStyle.imageUp.setMinWidth(125f);

        ImageButton backButton = new ImageButton(imageButtonStyle);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });

        backTable.add(backButton).width(350).height(200).pad(125);
        backTable.row();

        // set textures
        TextureRegionDrawable squareCircleWindow = new TextureRegionDrawable(AssetManager.squareCircleWindow);
        TextButton.TextButtonStyle squareStyle = new TextButton.TextButtonStyle(squareCircleWindow, squareCircleWindow,
                squareCircleWindow, AssetManager.basicFont);

        TextButton one = new TextButton("Lorem ipsum dolor sit amet,\n consectetur adipiscing elit,\n sed do eiusmod " +
                "tempor incididunt ut labore et dolore magna aliqua.", squareStyle);
        TextButton two = new TextButton("Ut enim ad minim veniam,\n quis nostrud exercitation ullamco laboris nisi ut" +
                " " +
                "aliquip ex ea commodo consequat.", squareStyle);
        TextButton three = new TextButton("Duis aute irure dolor in reprehenderit in voluptate velit esse cillum " +
                "dolore eu fugiat nulla pariatur.", squareStyle);
        TextButton four = new TextButton("Excepteur sint occaecat cupidatat non proident,\n sunt in culpa qui officia" +
                " " +
                "deserunt mollit anim id est laborum.", squareStyle);

        // add buttons and padding to table
        helpTable.add(one);
        helpTable.add(two);
        helpTable.add(three);
        helpTable.add(four);

        // create scrollPane
        scrollPane = new ScrollPane(helpTable);
        scrollPane.setFlickScroll(true);
        scrollPane.setBounds(0, 0, stage.getWidth(), stage.getHeight());

        // add table to stage
        stage.addActor(scrollPane);
        stage.addActor(backTable);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        scrollPane.setBounds(0, 0, stage.getWidth(), stage.getHeight());
        /*batch.begin();
        batch.draw(AssetManager.bg, 0, 0, stage.getWidth(), stage.getHeight());
        batch.end();*/
    }
}
