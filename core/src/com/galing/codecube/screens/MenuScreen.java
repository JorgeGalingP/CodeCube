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
    public void update(float delta) {
        Assets.playMenuMusic();
    }

    @Override
    public void show() {
        // create table
        Table table = new Table();
        table.setFillParent(true);
        table.center();

        // create buttons
        String optionsButtonTitle = Assets.selectString("MenuScreen_OptionsButton");
        String helpButtonTitle = Assets.selectString("MenuScreen_HowToPlayButton");
        ImageButton playButton = new ImageButton(Assets.playButtonStyle);
        TextButton optionsButton = new TextButton(optionsButtonTitle, Assets.vagaRoundBoldFontLargeButtonStyle);
        TextButton helpButton = new TextButton(helpButtonTitle, Assets.vagaRoundBoldFontLargeButtonStyle);

        // add listeners to buttons
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();
                game.setScreen(new ModeScreen(game));
            }
        });
        optionsButton.addListener(new ClickListener() {
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

        // add buttons and padding to table
        table.add(new Image(new TextureRegionDrawable(new Texture("images/titleLogo.png")))).padBottom(100).height(500f).width(500f);
        table.row();
        table.add(playButton).width(450).height(100).pad(25);
        table.row();
        table.add(optionsButton).width(450).height(100).pad(25);
        table.row();
        table.add(helpButton).width(450).height(100).pad(25);
        table.row();

        // add table to stage
        stage.addActor(table);
    }
}
