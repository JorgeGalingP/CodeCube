package com.galing.codecube.board;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.galing.codecube.assets.AssetManager;
import com.galing.codecube.screens.Screen;

public class BoardRenderer {

    public static final float TILE_SIZE = 64f;
    public static final float UNIT_SCALE = 1 / TILE_SIZE;
    public static final int NUM_FLOOR_TILES_WIDTH = 6;
    public static final int NUM_FLOOR_TILES_HEIGHT = 6;
    public static final int NUM_TILES_WIDTH = 12;
    public static final int NUM_TILES_HEIGHT = 20;

    private OrthogonalTiledMapRenderer tiledRender;
    private TiledMapTileLayer mapStaticLayer;
    private OrthographicCamera camera;
    private Viewport viewport;

    public BoardRenderer(Stage stage) {
        this.camera = (OrthographicCamera) stage.getCamera();
        this.viewport = stage.getViewport();
        tiledRender = new OrthogonalTiledMapRenderer(AssetManager.tileMap, UNIT_SCALE);
        mapStaticLayer = (TiledMapTileLayer) tiledRender.getMap().getLayers().get("static");
    }

    public void render() {
        camera.update();
        tiledRender.setView(camera);

        // render the tilemap
        tiledRender.getBatch().begin();
        tiledRender.renderTileLayer(mapStaticLayer);
        tiledRender.getBatch().end();
    }

    public void resize(int width, int height) {
        this.viewport.update(width, height, true);

        // resize using tile size
        camera.viewportWidth = Screen.WIDTH / TILE_SIZE;
        camera.viewportHeight = Screen.HEIGHT / TILE_SIZE;

        // center the camera
        camera.setToOrtho(false, camera.viewportWidth, camera.viewportHeight);

        camera.update();
    }

    public void dispose() {
        tiledRender.getBatch().dispose();
    }
}
