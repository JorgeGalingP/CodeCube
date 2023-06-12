package com.galing.codecube.windows;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.galing.codecube.Assets;
import com.galing.codecube.CodeCube;
import com.galing.codecube.enums.SoundType;
import com.galing.codecube.screens.GameScreen;
import com.galing.codecube.screens.ModeScreen;
import com.galing.codecube.widgets.GreyLabel;

public class PauseWindow extends CloseableWindow {

    public PauseWindow() {
        super();

        initialize();
    }

    @Override
    public void initialize() {
        // create close button
        Button closeButton = new ImageButton(Assets.windowCloseButtonStyle);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                close();
            }
        });

        // create title table
        titleTable.center();
        titleTable.add(closeButton).width(40).height(40).padLeft(400f).right();

        // create buttons
        Label selectedTitle = new GreyLabel(Assets.selectString("PauseWindow_Title"));
        selectedTitle.setAlignment(Align.center);

        ImageButton homeButton = new ImageButton(Assets.exitLeftButtonStyle);
        homeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playSound(SoundType.ClickSound);

                // back to main menu
                CodeCube.getInstance().setCurrentScreen(new ModeScreen());
            }
        });

        ImageButton resetButton = new ImageButton(Assets.returnButtonStyle);
        resetButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // close pause window (and play parent's Assets.playClickSound())
                close();

                // reset target
                ((GameScreen) CodeCube.getInstance().getScreen()).resetTarget();
            }
        });

        // create content table
        contentTable.setBackground(Assets.greyPanelNinePatch);
        contentTable.center();
        contentTable.add(selectedTitle).colspan(2).center().padBottom(25f);
        contentTable.row();
        contentTable.add(homeButton).center().width(125).height(75).pad(25f);
        contentTable.add(resetButton).center().width(125).height(75).pad(25f);
    }

    @Override
    public void close() {
        super.close();
        ((GameScreen) CodeCube.getInstance().getScreen()).setRunning();
    }
}
