package com.galing.codecube.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Matrix extends Actor {

    private final ShapeRenderer shapeRenderer;

    public Matrix() {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
        Gdx.gl.glLineWidth(4);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        for (int i = 3; i <= 9; i++) {
            shapeRenderer.line(new Vector2(i, 18), new Vector2(i, 12));
        }
        for (int i = 12; i <= 18; i++) {
            shapeRenderer.line(new Vector2(3, i), new Vector2(9, i));
        }
        shapeRenderer.end();

        batch.begin();
    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}
