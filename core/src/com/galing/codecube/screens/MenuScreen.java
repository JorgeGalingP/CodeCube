package com.galing.codecube.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;
import com.galing.codecube.CodeCube;
import com.galing.codecube.enums.BoardType;

public class MenuScreen extends Screen {

    public MenuScreen(CodeCube game) {
        super(game);
    }

    @Override
    public void draw(float delta) {
        ScreenUtils.clear(0, 0, 0f, 1);

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game, BoardType.SEQUENCE));
            dispose();
        }
    }

    @Override
    public void update(float delta) {

    }
}
