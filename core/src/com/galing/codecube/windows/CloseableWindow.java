package com.galing.codecube.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.galing.codecube.Assets;

public class CloseableWindow extends Window {

    public CloseableWindow(int width, int height) {
        super("", Assets.greyWindowStyle);

        Button closeButton = new ImageButton(Assets.windowCloseButtonStyle);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                close();
            }
        });

        getTitleTable().add(closeButton).size(35, 35);

        setClip(false);
        setTransform(true);
        setSize(width, height);
        setModal(true);
        setVisible(true);
        setMovable(false);
        setPosition(Gdx.graphics.getWidth() / 2f - this.getWidth() / 2,
                Gdx.graphics.getHeight() / 2f - this.getHeight() / 2);
    }

    public void close() {
        addAction(Actions.sequence(
                Actions.fadeOut(.5f, Interpolation.fade),
                Actions.run(() -> setVisible(false)))
        );
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        super.draw(batch, parentAlpha);

        batch.setColor(Color.WHITE); // reset the original batch's color
    }
}
