package com.galing.codecube.windows;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.galing.codecube.Assets;
import com.galing.codecube.CodeCube;
import com.galing.codecube.Settings;
import com.galing.codecube.enums.BoardType;
import com.galing.codecube.enums.Difficulty;
import com.galing.codecube.enums.SoundType;
import com.galing.codecube.screens.GameScreen;
import com.galing.codecube.screens.ModeScreen;
import com.galing.codecube.screens.Screen;
import com.galing.codecube.screens.TutorialScreen;
import com.galing.codecube.widgets.GreyLabel;

public class GameInitWindow extends CloseableWindow {

    BoardType boardType;

    public GameInitWindow(final CodeCube game, Screen screen, BoardType boardType) {
        super(game, screen);

        this.boardType = boardType;

        initialize();
    }

    @Override
    public void initialize() {
        // create buttons
        Label selectedTitle =
                new GreyLabel(Assets.formatString("GameScreen_SelectedTitle",
                        BoardType.toString(boardType),
                        Difficulty.toString(Settings.selectedDifficulty)), false);
        selectedTitle.setAlignment(Align.center);

        ImageButton acceptButton = new ImageButton(Assets.checkmarkButtonStyle);
        acceptButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playSound(SoundType.ClickSound);

                // init game
                if (Settings.tutorial.equals("ON"))
                    game.setScreen(new TutorialScreen(game, boardType));
                else
                    game.setScreen(new GameScreen(game, boardType));

            }
        });

        ImageButton crossButton = new ImageButton(Assets.crossButtonStyle);
        crossButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playSound(SoundType.ClickSound);

                // back to main menu
                game.setScreen(new ModeScreen(game));
            }
        });

        // create content table
        contentTable.setBackground(Assets.greyPanelNinePatch);
        contentTable.center();
        contentTable.add(selectedTitle).colspan(2).center().padBottom(25f);
        contentTable.row();
        contentTable.add(acceptButton).center().pad(25f).width(150).height(75);
        contentTable.add(crossButton).center().pad(25f).width(150).height(75);
    }

    @Override
    public void close() {
        super.close();

        // back to main menu
        game.setScreen(new ModeScreen(game));
    }
}
