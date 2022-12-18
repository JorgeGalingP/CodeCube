package com.galing.codecube.board;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.galing.codecube.assets.AssetManager;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Control;
import com.galing.codecube.objects.Floor;
import com.galing.codecube.objects.Player;
import com.galing.codecube.objects.Target;
import com.galing.codecube.objects.Wall;
import com.galing.codecube.screens.Screen;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Board extends Group {

    private enum BoardState {
        RUNNING, GAME_OVER;
    }

    public BoardState state;
    public static final String BOARD_LAYER = "board";
    public static final String CONTROLS_LAYER = "controls";
    public static final String OBJECTS_LAYER = "objects";

    public static final float TILE_SIZE = 64f;
    public static final float UNIT_SCALE = 1 / TILE_SIZE;
    public static final int NUM_FLOOR_TILES_WIDTH = 6;
    public static final int NUM_FLOOR_TILES_HEIGHT = 6;
    public static final int NUM_TILES_WIDTH = 12;
    public static final int NUM_TILES_HEIGHT = 20;

    public final static HashMap<Integer, Vector2> positionHashMap = new LinkedHashMap<Integer,
            Vector2>();

    static {
        int tilePosition = 0; // positions go left to right and down to up

        for (int y = 0; y < Board.NUM_TILES_HEIGHT; y++) {
            for (int x = 0; x < Board.NUM_TILES_WIDTH; x++) {
                positionHashMap.put(tilePosition,
                        new Vector2(x * Board.TILE_SIZE * Board.UNIT_SCALE, y * Board.TILE_SIZE * Board.UNIT_SCALE));
                tilePosition++;
            }
        }
    }

    private final OrthogonalTiledMapRenderer tiledRender;
    private final OrthographicCamera camera;
    private final Viewport viewport;
    private final ShapeRenderer shapeRenderer;

    public Board(Stage stage) {
        camera = (OrthographicCamera) stage.getCamera();
        viewport = stage.getViewport();
        shapeRenderer = new ShapeRenderer();
        tiledRender = new OrthogonalTiledMapRenderer(AssetManager.tileMap, UNIT_SCALE);

        initializeLayerMap(BOARD_LAYER);
        initializeLayerMap(OBJECTS_LAYER);
        initializeLayerMap(CONTROLS_LAYER);
    }

    public void render() {
        camera.update();
/*
        // render a matrix in top of the board
        shapeRenderer.setProjectionMatrix(camera.combined);
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
*/
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
        shapeRenderer.dispose();
    }

    private void initializeLayerMap(String layerName) {
        TiledMapTileLayer layer = (TiledMapTileLayer) AssetManager.tileMap.getLayers().get(layerName);

        if (layer != null) {
            int tilePosition = 0;

            for (int y = 0; y < NUM_TILES_HEIGHT; y++) {
                for (int x = 0; x < NUM_TILES_WIDTH; x++) {
                    TiledMapTileLayer.Cell cell = layer.getCell(x, y);

                    if (cell != null) {
                        TiledMapTile tile = cell.getTile();

                        if (tile.getProperties() != null
                                && tile.getProperties().containsKey("type")) {
                            String type = tile.getProperties().get("type").toString();

                            switch (type) {
                                case "floor":
                                    addActor(new Floor(tilePosition, tile.getProperties().get("subtype").toString()));
                                    break;
                                case "wall":
                                    addActor(new Wall(tilePosition, tile.getProperties().get("subtype").toString()));
                                    break;
                                case "player":
                                    addActor(new Player(tilePosition));
                                    break;
                                case "control":
                                    addActor(new Control(tilePosition, tile.getProperties().get("color").toString()));
                                    break;
                                case "button":
                                    addActor(new Button(tilePosition, tile.getProperties().get("color").toString()));
                                    break;
                                case "box":
                                    addActor(new Box(tilePosition, tile.getProperties().get("variable").toString()));
                                    break;
                                case "target":
                                    addActor(new Target(tilePosition, tile.getProperties().get("color").toString()));
                                    break;
                            }
                        }
                    }

                    tilePosition++;
                }
            }
        }
    }
}
