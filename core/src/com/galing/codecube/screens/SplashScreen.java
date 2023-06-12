package com.galing.codecube.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.galing.codecube.CodeCube;

public class SplashScreen extends Screen {

    private final Image splashImage;
    private final Action splashAction;

    public SplashScreen() {
        super();
        splashImage = new Image(new TextureRegionDrawable(new Texture("images/logo.png")));

        splashAction = Actions.sequence(
                Actions.alpha(0),
                Actions.scaleTo(.1f, .1f),
                Actions.moveTo(stage.getWidth() / 2 - splashImage.getWidth() / 2,
                        stage.getHeight() / 2 - splashImage.getHeight() / 2),
                Actions.parallel(
                        Actions.fadeIn(2f),
                        Actions.scaleTo(1f, 1f, 1f, Interpolation.pow5Out),
                        Actions.rotateBy(360, 1f, Interpolation.exp5Out)
                ),
                Actions.delay(.25f),
                Actions.scaleTo(1.5f, 1.5f, 1.25f, Interpolation.elasticOut),
                Actions.delay(.5f),
                run(() -> CodeCube.getInstance().setCurrentScreen(new MenuScreen())));
    }

    @Override
    public void draw(float delta) {
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void show() {
        splashImage.setOrigin(splashImage.getWidth() / 2, splashImage.getHeight() / 2);
        splashImage.setPosition(stage.getWidth() / 2 - splashImage.getWidth() / 2,
                stage.getHeight() + splashImage.getHeight());
        splashImage.addAction(splashAction);

        stage.addActor(splashImage);
    }
}
