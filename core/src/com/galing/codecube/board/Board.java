package com.galing.codecube.board;

import static com.badlogic.gdx.math.MathUtils.random;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.galing.codecube.assets.AssetManager;
import com.galing.codecube.controls.GameStack;
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

    public static final float TILE_SIZE = 64f;
    public static final float UNIT_SCALE = 1 / TILE_SIZE;
    public static final int NUM_FLOOR_TILES_WIDTH = 6;
    public static final int NUM_FLOOR_TILES_HEIGHT = 6;
    public static final int NUM_TILES_WIDTH = 12;
    public static final int NUM_TILES_HEIGHT = 20;

    public final static HashMap<Integer, Vector2> positionHashMap = new LinkedHashMap<>();

    static {
        int tilePosition = 0; // positions go left to right and down to up

        for (int y = 0; y < Board.NUM_TILES_HEIGHT; y++) {
            for (int x = 0; x < Board.NUM_TILES_WIDTH; x++) {
                positionHashMap.put(tilePosition,
                        new Vector2(x, y));
                tilePosition++;
            }
        }
    }

    private final OrthogonalTiledMapRenderer tiledRender;
    private final OrthographicCamera camera;
    private final Viewport viewport;

    public BoardState state;
    private boolean inverse;
    private final Array<Integer> floorPositions;

    private final GameStack gameStack;

    private final Array<Tile> tiles;
    private final Array<Vector2> playerMoves;

    private Player player;
    private final Matrix matrix;

    public Board(Stage stage, GameStack gameStack) {
        // initialize main variables
        camera = (OrthographicCamera) stage.getCamera();
        viewport = stage.getViewport();
        tiledRender = new OrthogonalTiledMapRenderer(AssetManager.tileMap, UNIT_SCALE);

        // initialize Board variables
        state = BoardState.RUNNING;
        inverse = false;
        floorPositions = new Array<>(NUM_FLOOR_TILES_WIDTH * NUM_FLOOR_TILES_HEIGHT);

        // initialize ControlGame
        this.gameStack = gameStack;

        // initialize Tile variables
        tiles = new Array<>(NUM_TILES_WIDTH * NUM_TILES_HEIGHT);
        playerMoves = new Array<>(NUM_FLOOR_TILES_WIDTH * NUM_FLOOR_TILES_HEIGHT);

        // initialize board and controls layers
        initializeLayer("board");
        initializeLayer("controls");


        // initialize matrix on top of board
        matrix = new Matrix();
        addActor(matrix);

        // initialize objects layer on top
        initializeLayer("objects");
        initializeLayer("boxes");
        setSize(Screen.WIDTH, Screen.HEIGHT);
    }

    public boolean isGameOver() {
        return state.equals(BoardState.GAME_OVER);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (player.canMove())
            movePlayer();
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

    private void setBoardStateGameOver() {
        this.state = BoardState.GAME_OVER;
    }

    private void initializeLayer(String layerName) {
        TiledMapTileLayer mapLayer = (TiledMapTileLayer) AssetManager.tileMap.getLayers().get(layerName);

        if (mapLayer != null) {
            int tilePosition = 0;

            for (int y = 0; y < NUM_TILES_HEIGHT; y++) {
                for (int x = 0; x < NUM_TILES_WIDTH; x++) {
                    Cell cell = mapLayer.getCell(x, y);

                    if (cell != null) {
                        TiledMapTile mapTile = cell.getTile();

                        if (mapTile.getProperties() != null
                                && mapTile.getProperties().containsKey("type")) {
                            String type = mapTile.getProperties().get("type").toString();

                            if (type != null) {
                                Tile tile = null;

                                switch (type) {
                                    case "floor":
                                        floorPositions.add(tilePosition);

                                        tile = new Floor(tilePosition,
                                                mapTile.getProperties().get("subtype").toString());
                                        break;
                                    case "wall":
                                        tile = new Wall(tilePosition,
                                                mapTile.getProperties().get("subtype").toString());
                                        break;
                                    case "player":
                                        int randomPlayerPosition =
                                                floorPositions.get(random.nextInt((floorPositions.size)));

                                        tile = new Player(randomPlayerPosition);
                                        this.player = (Player) tile;
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
                                        gameStack.attachDragListener((Box) tile);
                                        break;
                                    case "target":
                                        tile = new Target(tilePosition,
                                                mapTile.getProperties().get("color").toString());
                                        break;
                                }

                                // add tile as actor to board group
                                addActor(tile);

                                // add tile to the tiles array
                                tiles.add(tile);
                            }
                        }
                    }

                    tilePosition++;
                }
            }
        }
    }

    private void movePlayer() {
        if (state == BoardState.RUNNING) {
            if (!gameStack.isProgramEmpty()) {
                Box box;

                switch (gameStack.getProgramPeek().getMovement()) {
                    case Box.UP:
                    case Box.RIGHT:
                    case Box.LEFT:
                        box = gameStack.popOutProgram();
                        handleMovement(box);
                        break;
                    case Box.FUNCTION:
                        if (!gameStack.isFunctionEmpty()) {
                            box = gameStack.popOutFunction();
                            handleMovement(box);
                        } else {
                            box = gameStack.popOutProgram();
                            box.addResetPositionAction();
                        }
                        break;
                    case Box.NEGATION:
                        box = gameStack.popOutProgram();
                        box.addResetPositionAction();

                        inverse = !inverse;
                        break;
                }

                player.resetStateTime();

                // movement is finished
                if (gameStack.isProgramEmpty()
                        && gameStack.isFunctionEmpty()) {
                    // check if current target is achieved
                    if (isPlayerInTarget()) {
                        addAction(Actions.sequence(Actions.delay(.5f), Actions.run(this::resetTarget)));
                    } else {
                        addAction(Actions.sequence(Actions.delay(.5f), Actions.run(this::setBoardStateGameOver)));
                    }
                }
            } else
                player.setPressed(false);
        }
    }

    private void handleMovement(Box box) {
        switch (box.getMovement()) {
            case Box.UP:
                int mov = inverse
                        ? -player.getMovement(box)
                        : player.getMovement(box);
                int nextPos = player.getPosition() + mov;

                if (isTileEmpty(nextPos)) {
                    // add new move
                    playerMoves.add(new Vector2(player.getPosition(), nextPos));

                    // perform sequence of actions
                    box.addResetPositionAction();
                    player.addMovePositionAction(nextPos);
                } else {
                    addAction(Actions.sequence(Actions.delay(.5f), Actions.run(this::setBoardStateGameOver)));
                }

                inverse = false;
            case Box.RIGHT:
            case Box.LEFT:
                box.addResetPositionAction();
                player.addRotationAction(box, inverse);

                inverse = false;
                break;
            case Box.NEGATION:
                box.addResetPositionAction();
                inverse = !inverse;
                break;
        }
    }

    private void resetTarget() {
        for (Tile tile : tiles)
            if (tile.getClass().equals(Target.class))
                tile.addInOutPositionAction(getRandomPositionNoPlayer());
    }

    private int getRandomPositionNoPlayer() {
        int playerPositionIndex = floorPositions.indexOf(player.getPosition(), false);
        Array<Integer> positions = new Array<>(floorPositions);
        positions.removeIndex(playerPositionIndex);

        return positions.get(random.nextInt((positions.size)));
    }

    private boolean isPlayerInTarget() {
        for (Tile tile : tiles) {
            if (tile.getPosition() == this.player.getPosition()
                    && tile.getClass().equals(Target.class))
                return true;
        }
        return false;
    }

    private boolean isTileEmpty(int position) {
        for (Tile tile : tiles) {
            if (tile.getPosition() == position
                    && tile.getClass().equals(Wall.class))
                return false;
        }
        return true;
    }
}
