package com.galing.codecube.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.galing.codecube.CodeCube;

public class LoadingScreen extends Screen {

    private final ShapeRenderer shapeRenderer;

    public LoadingScreen(CodeCube game) {
        super(game);

        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void draw(float delta) {
    }

    @Override
    public void update(float delta) {
        if (game.getAssetManager().update()) {
            game.getAssets().loadAssets();
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
        shapeRenderer.rect(32, camera.viewportHeight / 2 - 8,
                game.getAssetManager().getProgress() * (camera.viewportWidth - 64), 16);
        shapeRenderer.end();
    }

    @Override
    public void show() {
        game.getAssets().queueAssets();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
