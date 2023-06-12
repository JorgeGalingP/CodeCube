package com.galing.codecube.windows;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.galing.codecube.Assets;
import com.galing.codecube.enums.SoundType;

public abstract class CloseableWindow extends Window {
    Table titleTable;
    Table contentTable;

    public CloseableWindow() {
        super("", new WindowStyle(Assets.vagaRoundBoldGray25, Assets.vagaRoundBoldGray25.getColor(),
                Assets.bgNinePatch));

        setVisible(true);
        setFillParent(true);

        titleTable = new Table();
        contentTable = new Table();

        add(titleTable);
        row();
        add(contentTable);
    }

    public abstract void initialize();

    public void close() {
        Assets.playSound(SoundType.ClickSound);

        addAction(Actions.sequence(
                Actions.fadeOut(.5f, Interpolation.fade),
                Actions.run(this::remove))
        );
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // maintain in batch current parent's alpha
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        super.draw(batch, parentAlpha);

        // reset the original batch's color
        batch.setColor(Color.WHITE);
    }

    @Override
    protected void drawBackground(Batch batch, float parentAlpha, float x, float y) {
        super.drawBackground(batch, 0.5f, x, y);
    }
}
