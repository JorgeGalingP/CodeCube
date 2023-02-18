package com.galing.codecube.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.galing.codecube.AssetManager;
import com.galing.codecube.CodeCube;

public class SplashScreen extends Screen {

    public SplashScreen(CodeCube game) {
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
        TextureRegionDrawable splashTex = new TextureRegionDrawable(AssetManager.debugIcon);

        Image splashImage = new Image(splashTex);
        splashImage.setOrigin(splashImage.getWidth() / 2, splashImage.getHeight() / 2);
        splashImage.setPosition(stage.getWidth() / 2 - splashImage.getWidth() / 2,
                stage.getHeight() + splashImage.getHeight());
        splashImage.addAction(sequence(alpha(0), scaleTo(.1f, .1f),
                parallel(fadeIn(2f, Interpolation.pow5),
                        scaleTo(2f, 2f, 2.5f, Interpolation.pow5),
                        moveTo(stage.getWidth() / 2 - splashImage.getWidth() / 2,
                                stage.getHeight() / 2 - splashImage.getHeight() / 2, 2f, Interpolation.swing)),
                delay(1f), fadeOut(1.25f), run(() -> game.setScreen(new MenuScreen(game)))));

        stage.addActor(splashImage);
    }
}
