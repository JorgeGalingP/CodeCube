package com.galing.codecube.board;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.galing.codecube.assets.AssetManager;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Control;
import com.galing.codecube.objects.Floor;
import com.galing.codecube.objects.Matrix;
import com.galing.codecube.objects.Player;
import com.galing.codecube.objects.Target;
import com.galing.codecube.objects.Tile;
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

    private final Array<Tile> tiles;
    private final Matrix matrix;

    public Board(Stage stage) {
        camera = (OrthographicCamera) stage.getCamera();
        viewport = stage.getViewport();
        tiledRender = new OrthogonalTiledMapRenderer(AssetManager.tileMap, UNIT_SCALE);

        tiles = new Array<>(NUM_TILES_WIDTH * NUM_TILES_HEIGHT);

        // initialize actors
        initializeLayersInMap();
        matrix = new Matrix();

        // add actors to board
        addToBoard(Floor.class);
        addToBoard(Wall.class);
        addToBoard(Button.class);
        addToBoard(Control.class);
        addToBoard(Box.class);

        addActor(matrix);

        addToBoard(Target.class);
        addToBoard(Player.class);
    }

    public void render() {
        camera.update();
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
        matrix.dispose();
    }

    private void addToBoard(Class<?> type) {
        for (Tile tile : tiles) {
            if (tile.getClass().equals(type))
                addActor(tile);
        }
    }

    private void initializeLayersInMap() {
        MapLayers mapLayers = AssetManager.tileMap.getLayers();

        for (MapLayer layerMap : mapLayers) {
            TiledMapTileLayer layer = (TiledMapTileLayer) mapLayers.get(layerMap.getName());

            int tilePosition = 0;

            for (int y = 0; y < NUM_TILES_HEIGHT; y++) {
                for (int x = 0; x < NUM_TILES_WIDTH; x++) {
                    Cell cell = layer.getCell(x, y);

                    if (cell != null) {
                        TiledMapTile mapTile = cell.getTile();

                        if (mapTile.getProperties() != null
                                && mapTile.getProperties().containsKey("type")) {
                            String type = mapTile.getProperties().get("type").toString();

                            if (type != null) {
                                Tile tile = null;

                                switch (type) {
                                    case "floor":
                                        tile = new Floor(tilePosition,
                                                mapTile.getProperties().get("subtype").toString());
                                        break;
                                    case "wall":
                                        tile = new Wall(tilePosition,
                                                mapTile.getProperties().get("subtype").toString());
                                        break;
                                    case "player":
                                        tile = new Player(tilePosition);
                                        break;
                                    case "control":
                                        tile = new Control(tilePosition,
                                                mapTile.getProperties().get("color").toString());
                                        break;
                                    case "button":
                                        tile = new Button(tilePosition,
                                                mapTile.getProperties().get("color").toString());
                                        break;
                                    case "box":
                                        tile = new Box(tilePosition,
                                                mapTile.getProperties().get("variable").toString());
                                        break;
                                    case "target":
                                        tile = new Target(tilePosition,
                                                mapTile.getProperties().get("color").toString());
                                        break;
                                }

                                tiles.add(tile);
                            }
                        }
                    }

                    tilePosition++;
                }
            }
        }
    }
}
