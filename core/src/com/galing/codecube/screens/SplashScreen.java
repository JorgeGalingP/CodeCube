package com.galing.codecube.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.galing.codecube.Assets;
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
        TextureRegionDrawable splashTex = new TextureRegionDrawable(Assets.debugIcon);

        Image splashImage = new Image(splashTex);
        splashImage.setOrigin(splashImage.getWidth() / 2, splashImage.getHeight() / 2);
        splashImage.setPosition(stage.getWidth() / 2 - splashImage.getWidth() / 2,
                stage.getHeight() + splashImage.getHeight());
        splashImage.addAction(sequence(alpha(0), scaleTo(.1f, .1f),
                parallel(fadeIn(2f),
                        scaleTo(1.75f, 1.75f, 2.25f, Interpolation.swing),
                        moveTo(stage.getWidth() / 2 - splashImage.getWidth() / 2,
                                stage.getHeight() / 2 - splashImage.getHeight() / 2, 2f, Interpolation.swing)),
                delay(.5f),
                scaleTo(2.25f, 2.25f, 1.25f, Interpolation.elasticOut),
                run(this::addTapToStartTitle)));

        stage.addActor(splashImage);
    }

    private void addTapToStartTitle() {
        // set textures
        TextureRegionDrawable squareCircleWindow = new TextureRegionDrawable(Assets.squareCircleWindow);

        // create buttons
        ImageButton.ImageButtonStyle playButtonStyle =
                Assets.playButtonStyle;
        playButtonStyle.imageDown.setMinHeight(125f);
        playButtonStyle.imageUp.setMinWidth(125f);
        playButtonStyle.imageChecked.setMinWidth(125f);
        TextButton.TextButtonStyle squareStyle = new TextButton.TextButtonStyle(squareCircleWindow, squareCircleWindow,
                squareCircleWindow, Assets.basicFont);

        TextButton title = new TextButton("¡Toca para comenzar!", squareStyle);
        title.setOrigin(title.getWidth() / 2, title.getHeight() / 2);
        title.setPosition(stage.getWidth() / 2 - title.getWidth() / 2,
                stage.getHeight() * 0.1f);
        title.setColor(title.getColor().r, title.getColor().g, title.getColor().b, 0);
        title.addAction(Actions.parallel(Actions.alpha(1, 1.5f), run(() ->
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                        game.setScreen(new MenuScreen(game));
                        return true;
                    }
                })
        )));

        stage.addActor(title);


    }
}
