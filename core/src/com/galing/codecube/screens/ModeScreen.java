package com.galing.codecube.screens;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.galing.codecube.Assets;
import com.galing.codecube.CodeCube;
import com.galing.codecube.enums.BoardType;

public class ModeScreen extends Screen {

    private Table modeTable;

    public ModeScreen(CodeCube game) {
        super(game);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void show() {
        // create tables
        modeTable = new Table();
        setModeTableBounds();
        modeTable.top().padTop(50);
        modeTable.background(new NinePatchDrawable(new NinePatch(Assets.greyPanel,
                12, 12, 12, 12)));

        Table backTable = new Table();
        backTable.setFillParent(true);
        backTable.bottom();

        // create labels
        Label modeLabel = new Label("Elige una opción", new Label.LabelStyle(Assets.vagaRoundBoldGray35, null));

        // create back button
        ImageButton backButton = new ImageButton(Assets.exitLeftLargeButtonStyle);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();
                game.setScreen(new MenuScreen(game));
            }
        });

        // create buttons
        TextButton sequenceButton = new TextButton(BoardType.toString(BoardType.SEQUENCE),
                Assets.vagaRoundBoldFontLargeButtonStyle);
        TextButton stackButton = new TextButton(BoardType.toString(BoardType.STACK),
                Assets.vagaRoundBoldFontLargeButtonStyle);
        TextButton queueButton = new TextButton(BoardType.toString(BoardType.QUEUE),
                Assets.vagaRoundBoldFontLargeButtonStyle);

        // add listeners to buttons
        sequenceButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();
                game.setScreen(new GameScreen(game, BoardType.SEQUENCE));
            }
        });
        stackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();
                game.setScreen(new GameScreen(game, BoardType.STACK));
            }
        });
        queueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();
                game.setScreen(new GameScreen(game, BoardType.QUEUE));
            }
        });

        // add buttons and padding to tables
        modeTable.add(modeLabel).center().padBottom(150);
        modeTable.row();
        modeTable.add(sequenceButton).width(300).height(100).pad(35);
        modeTable.row();
        modeTable.add(stackButton).width(300).height(100).pad(35);
        modeTable.row();
        modeTable.add(queueButton).width(300).height(100).pad(35);
        modeTable.row();

        backTable.add(backButton).width(450).height(100).pad(125);
        backTable.row();

        // add difficultyTable to stage
        stage.addActor(modeTable);
        stage.addActor(backTable);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        setModeTableBounds();
    }

    private void setModeTableBounds() {
        modeTable.setPosition(stage.getWidth() / 2 - (stage.getWidth() * 0.8f) / 2,
                stage.getHeight() / 1.65f - (stage.getHeight() * 0.7f) / 2);
        modeTable.setSize(stage.getWidth() * 0.8f,
                stage.getHeight() * 0.7f);
    }
}
