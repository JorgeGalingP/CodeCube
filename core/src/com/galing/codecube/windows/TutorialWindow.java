package com.galing.codecube.windows;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.galing.codecube.Assets;
import com.galing.codecube.CodeCube;
import com.galing.codecube.enums.SoundType;
import com.galing.codecube.screens.Screen;
import com.galing.codecube.screens.TutorialScreen;
import com.galing.codecube.widgets.GreyLabel;

public class TutorialWindow extends CloseableWindow {

    private final String title;

    public TutorialWindow(final CodeCube game, Screen screen, String title) {
        super(game, screen);

        this.title = title;

        initialize();
    }

    @Override
    public void initialize() {
        // create buttons
        Label selectedTitle = new GreyLabel(title, false);
        selectedTitle.setAlignment(Align.center);

        ImageButton acceptButton = new ImageButton(Assets.checkmarkButtonStyle);
        acceptButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playSound(SoundType.ClickSound);

                // close
                close();
            }
        });

        // create content table
        contentTable.setBackground(Assets.greyPanelNinePatch);
        contentTable.center();
        contentTable.add(selectedTitle).colspan(2).center().padBottom(25f);
        contentTable.row();
        contentTable.add(acceptButton).center().pad(25f).width(150).height(75);
    }

    @Override
    public void close() {
        super.close();

        ((TutorialScreen) screen).nextPhase();


    }
}
