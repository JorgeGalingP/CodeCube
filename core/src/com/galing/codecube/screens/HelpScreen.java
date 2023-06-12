package com.galing.codecube.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.galing.codecube.Assets;
import com.galing.codecube.CodeCube;
import com.galing.codecube.enums.SoundType;
import com.galing.codecube.widgets.GreyLabel;

public class HelpScreen extends Screen {
    private ScrollPane scrollPane;

    public HelpScreen() {
        super();
    }

    @Override
    public void update(float delta) {
        Assets.playMenuMusic();
    }

    @Override
    public void show() {
        // create tables
        Table helpTable = new Table();
        helpTable.pad(50).padBottom(150);
        helpTable.center();
        // TODO modify here to center

        Table backTable = new Table();
        backTable.setFillParent(true);
        backTable.bottom();

        Table oneTable = new Table();
        oneTable.center();

        Table twoTable = new Table();
        twoTable.center();

        // create back button
        ImageButton backButton = new ImageButton(Assets.exitLeftLargeButtonStyle);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playSound(SoundType.ClickSound);
                CodeCube.getInstance().setCurrentScreen(new MenuScreen());
            }
        });

        // set textures
        Label one = new GreyLabel("Lorem ipsum dolor sit amet,\n consectetur adipiscing elit,\n sed do eiusmod " +
                "tempor incididunt ut labore et dolore magna aliqua.", true);
        Label two = new GreyLabel("Ut enim ad minim veniam,\n quis nostrud exercitation ullamco laboris nisi ut" +
                " " +
                "aliquip ex ea commodo consequat.", true);
        Label three = new GreyLabel("Duis aute irure dolor in reprehenderit in voluptate velit esse cillum " +
                "dolore eu fugiat nulla pariatur.", true);
        Label four = new GreyLabel("Excepteur sint occaecat cupidatat non proident,\n sunt in culpa qui officia" +
                " " +
                "deserunt mollit anim id est laborum.", true);

        one.setAlignment(Align.center);
        two.setAlignment(Align.center);
        three.setAlignment(Align.center);
        four.setAlignment(Align.center);

        oneTable.add(one);
        oneTable.row();
        oneTable.add(new Image(new TextureRegionDrawable(new Texture("images/titleLogo.png"))));
        oneTable.row();
        oneTable.add(three);

        twoTable.add(two);

        // add buttons and padding to table
        backTable.add(backButton).width(450).height(100).pad(125);
        backTable.row();

        helpTable.add(oneTable);
        helpTable.add(twoTable);
        // helpTable.add(three);
        // helpTable.add(four);

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
