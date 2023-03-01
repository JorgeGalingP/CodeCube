package com.galing.codecube.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.galing.codecube.Settings;
import com.galing.codecube.enums.Difficulty;

public class Matrix extends Actor {

    private final ShapeRenderer shapeRenderer;
    private final int x;
    private final int y;
    private final int j;
    private final int k;

    public Matrix() {
        shapeRenderer = new ShapeRenderer();

        if (Settings.selectedDifficulty.equals(Difficulty.EASY)) {
            x = 4;
            y = 8;
            j = 13;
            k = 17;
        } else {
            x = 3;
            y = 9;
            j = 12;
            k = 18;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
        Gdx.gl.glLineWidth(4f);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);

        for (int i = x; i <= y; i++)
            shapeRenderer.line(new Vector2(i, k), new Vector2(i, j));

        for (int i = j; i <= k; i++)
            shapeRenderer.line(new Vector2(x, i), new Vector2(y, i));

        shapeRenderer.end();

        batch.begin();
    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}
