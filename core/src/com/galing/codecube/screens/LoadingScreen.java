package com.galing.codecube.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.galing.codecube.CodeCube;

public class LoadingScreen extends Screen {

    private final ShapeRenderer shapeRenderer;
    private float progress;

    public LoadingScreen(CodeCube game) {
        super(game);

        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void draw(float delta) {
    }

    @Override
    public void update(float delta) {
        progress = MathUtils.lerp(progress, game.getAssetManager().getManager().getProgress(), .1f);
        if (game.getAssetManager().getManager().update() && progress >= game.getAssetManager().getManager().getProgress() - .001f) {
            game.setScreen(new SplashScreen(game));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(32, camera.viewportHeight / 2 - 8, camera.viewportWidth - 64, 16);

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(32, camera.viewportHeight / 2 - 8, progress * (camera.viewportWidth - 64), 16);
        shapeRenderer.end();
    }

    @Override
    public void show() {
        shapeRenderer.setProjectionMatrix(camera.combined);
        this.progress = 0f;
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
