package com.galing.codecube.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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

        // create buttons
        ImageButton playButton = new ImageButton(Assets.playButtonStyle);
        TextButton difficultyButton = new TextButton("OPCIONES", Assets.vagaRoundBoldFontLargeButtonStyle);
        TextButton helpButton = new TextButton("¿CÓMO JUGAR?", Assets.vagaRoundBoldFontLargeButtonStyle);

        // add listeners to buttons
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();
                game.setScreen(new ModeScreen(game));
            }
        });
        difficultyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();
                game.setScreen(new OptionsScreen(game));
            }
        });
        helpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();
                game.setScreen(new HelpScreen(game));
            }
        });

        // add background to table
        table.setBackground(new TextureRegionDrawable(Assets.bg));

        // add buttons and padding to table
        table.add(new Image(new TextureRegionDrawable(new Texture("images/titleLogo.png")))).padBottom(100).height(500f).width(500f);
        table.row();
        table.add(playButton).width(450).height(100).pad(25);
        table.row();
        table.add(difficultyButton).width(450).height(100).pad(25);
        table.row();
        table.add(helpButton).width(450).height(100).pad(25);
        table.row();

        // add table to stage
        stage.addActor(table);
    }
}
