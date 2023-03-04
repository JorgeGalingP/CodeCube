package com.galing.codecube.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.galing.codecube.Assets;
import com.galing.codecube.CodeCube;

public class HelpScreen extends Screen {
    private ScrollPane scrollPane;

    public HelpScreen(CodeCube game) {
        super(game);
    }

    @Override
    public void draw(float delta) {
        //Clear screen
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
        // create tables
        Table helpTable = new Table();
        helpTable.pad(50).defaults().space(75);
        helpTable.top();

        Table backTable = new Table();
        backTable.setFillParent(true);
        backTable.bottom();

        // create back button
        ImageButton backButton = new ImageButton(Assets.backButtonStyle);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });

        backTable.add(backButton).width(350).height(200).pad(125);
        backTable.row();

        // set textures
        TextButton one = new TextButton("Lorem ipsum dolor sit amet,\n consectetur adipiscing elit,\n sed do eiusmod " +
                "tempor incididunt ut labore et dolore magna aliqua.", Assets.greyPanelStyle);
        TextButton two = new TextButton("Ut enim ad minim veniam,\n quis nostrud exercitation ullamco laboris nisi ut" +
                " " +
                "aliquip ex ea commodo consequat.", Assets.greyPanelStyle);
        TextButton three = new TextButton("Duis aute irure dolor in reprehenderit in voluptate velit esse cillum " +
                "dolore eu fugiat nulla pariatur.", Assets.greyPanelStyle);
        TextButton four = new TextButton("Excepteur sint occaecat cupidatat non proident,\n sunt in culpa qui officia" +
                " " +
                "deserunt mollit anim id est laborum.", Assets.greyPanelStyle);

        // add buttons and padding to table
        helpTable.add(one);
        helpTable.add(two);
        helpTable.add(three);
        helpTable.add(four);

        // create scrollPane
        scrollPane = new ScrollPane(helpTable);
        scrollPane.setFlickScroll(true);
        scrollPane.setSmoothScrolling(true);

        // add table to stage
        stage.addActor(scrollPane);
        stage.addActor(backTable);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        scrollPane.setBounds(0, 0, stage.getWidth(), stage.getHeight());
    }
}
