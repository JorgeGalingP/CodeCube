package com.galing.codecube.windows;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.galing.codecube.Assets;
import com.galing.codecube.CodeCube;
import com.galing.codecube.screens.GameScreen;
import com.galing.codecube.screens.MenuScreen;
import com.galing.codecube.screens.Screen;

public class PauseWindow extends CloseableWindow {

    public PauseWindow(final CodeCube game, Screen screen) {
        super(game, screen);
    }

    @Override
    public void setTitleTable() {
        // set button
        Button closeButton = new ImageButton(Assets.windowCloseButtonStyle);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                close();
            }
        });

        // create title table
        titleTable = new Table();
        titleTable.center();
        titleTable.add(closeButton).size(40, 40).right().padLeft(300f);
    }

    @Override
    public void setContentTable() {
        // set buttons
        TextButton selectedTitle =
                new TextButton(Assets.selectString("PauseWindow_Title"), Assets.greyPanelStyleLarge);
        ImageButton.ImageButtonStyle homeButtonStyle =
                Assets.exitLeftButtonStyle;
        homeButtonStyle.imageDown.setMinHeight(50f);
        homeButtonStyle.imageUp.setMinWidth(50f);
        homeButtonStyle.imageChecked.setMinWidth(50f);
        ImageButton homeButton = new ImageButton(homeButtonStyle);
        homeButton.setSize(game.getStage().getViewport().getWorldWidth() * .1f,
                game.getStage().getViewport().getWorldWidth() * .1f);
        homeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();

                // back to main menu
                game.setScreen(new MenuScreen(game));
            }
        });

        ImageButton.ImageButtonStyle resetButtonStyle =
                Assets.returnButtonStyle;
        resetButtonStyle.imageDown.setMinHeight(50f);
        resetButtonStyle.imageUp.setMinWidth(50f);
        resetButtonStyle.imageChecked.setMinWidth(50f);
        ImageButton resetButton = new ImageButton(resetButtonStyle);
        resetButton.setSize(game.getStage().getViewport().getWorldWidth() * .1f,
                game.getStage().getViewport().getWorldWidth() * .1f);
        resetButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();

                // reset target
                ((GameScreen) screen).resetTarget();
            }
        });

        // create content table
        contentTable = new Table();
        contentTable.setBackground(new NinePatchDrawable(new NinePatch(Assets.greyPanel,
                12, 12, 12, 12)));

        contentTable.center();
        contentTable.add(selectedTitle).colspan(2).center().padBottom(25f);
        contentTable.row();
        contentTable.add(homeButton).center().pad(25f);
        contentTable.add(resetButton).center().pad(25f);
    }

    @Override
    public void close() {
        super.close();
        ((GameScreen) screen).setRunning();
    }
}
