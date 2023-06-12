package com.galing.codecube.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.galing.codecube.Assets;
import com.galing.codecube.CodeCube;

public class LoadingScreen extends Screen {

    private final ShapeRenderer shapeRenderer;
    private final Assets assets;

    public LoadingScreen() {
        super();

        this.shapeRenderer = new ShapeRenderer();
        this.assets = Assets.getInstance();
    }

    @Override
    public void draw(float delta) {
    }

    @Override
    public void update(float delta) {
        if (assets.getAssetManager().update()) {
            assets.loadAssets();
            CodeCube.getInstance().setCurrentScreen(new SplashScreen());
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(32, stage.getHeight() / 2 - 8, stage.getWidth() - 64, 16);

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(32, stage.getHeight() / 2 - 8,
                assets.getAssetManager().getProgress() * (stage.getWidth() - 64), 16);
        shapeRenderer.end();
    }

    @Override
    public void show() {
        shapeRenderer.setProjectionMatrix(camera.combined);
        assets.queueAssets();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
