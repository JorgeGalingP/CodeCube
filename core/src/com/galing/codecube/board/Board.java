package com.galing.codecube.board;

import static com.badlogic.gdx.math.MathUtils.random;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
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

    private final OrthogonalTiledMapRenderer tiledRender;
    private final OrthographicCamera camera;
    private final Viewport viewport;

    public BoardState state;
    private boolean inverse;
    private final Array<Vector2> floorPositions;

    private final GameStack gameStack;

    private final Array<Vector2> playerMoves;

    private Player player;
    private Target target;
    private final Matrix matrix;

    public Board(Stage stage, GameStack gameStack) {
        // initialize main variables
        this.camera = (OrthographicCamera) stage.getCamera();
        this.viewport = stage.getViewport();
        this.tiledRender = new OrthogonalTiledMapRenderer(AssetManager.tileMap, UNIT_SCALE);

        // initialize Board variables
        this.state = BoardState.RUNNING;
        this.inverse = false;
        this.floorPositions = new Array<>(NUM_FLOOR_TILES_WIDTH * NUM_FLOOR_TILES_HEIGHT);

        // initialize ControlGame
        this.gameStack = gameStack;

        // initialize Tile variables
        this.playerMoves = new Array<>(NUM_FLOOR_TILES_WIDTH * NUM_FLOOR_TILES_HEIGHT);

        // initialize board and controls layers
        initializeLayer("board");
        initializeLayer("controls");

        // initialize matrix on top of board
        this.matrix = new Matrix();
        addActor(this.matrix);

        // initialize objects layer on top
        initializeLayer("objects");
        initializeLayer("player");
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
        viewport.update(width, height, true);

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
                                Vector2 tilePosition = new Vector2(x, y);

                                switch (type) {
                                    case "floor":
                                        floorPositions.add(new Vector2(x, y));

                                        tile = new Floor(tilePosition,
                                                mapTile.getProperties().get("subtype").toString());
                                        break;
                                    case "wall":
                                        tile = new Wall(tilePosition,
                                                mapTile.getProperties().get("subtype").toString());
                                        break;
                                    case "player":
                                        tile = new Player(getRandomPosition(true, Target.class));
                                        player = (Player) tile;
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
                                        tile = new Target(getRandomPosition(),
                                                mapTile.getProperties().get("color").toString());
                                        target = (Target) tile;
                                        break;
                                }

                                // add tile as actor to board group
                                addActor(tile);
                            }
                        }
                    }
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

                    // check if player is in target's position
                    if (player.equalCoordinate(target.getCoordinate()))
                        addAction(Actions.sequence(Actions.delay(.5f), Actions.run(this::resetTarget)));
                    else
                        addAction(Actions.sequence(Actions.delay(.5f), Actions.run(this::setBoardStateGameOver)));
                }
            } else
                player.setPressed(false);
        }
    }

    private void handleMovement(Box box) {
        switch (box.getMovement()) {
            case Box.UP:
                Vector2 movement = player.getMovement(box);
                if (inverse) {
                    movement.x = -movement.x;
                    movement.y = -movement.y;
                }
                Vector2 playerPosition = player.getCoordinate();
                Vector2 newPosition = new Vector2(playerPosition.x + movement.x, playerPosition.y + movement.y);

                if (isTileEmpty(newPosition)) {
                    // add new move
                    playerMoves.add(newPosition);

                    // perform sequence of actions
                    box.addResetPositionAction();
                    player.addMovePositionAction(newPosition);
                } else
                    addAction(Actions.sequence(Actions.delay(.5f), Actions.run(this::setBoardStateGameOver)));

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
        target.addInOutPositionAction(getRandomPosition(true, Player.class));
    }

    private Vector2 getRandomPosition() {
        return getRandomPosition(false, null);
    }

    private Vector2 getRandomPosition(boolean exclude, Class<?> type) {
        Array<Vector2> positions = new Array<>(floorPositions);

        if (exclude) {
            for (Actor tile : getChildren())
                if (tile.getClass().equals(type)) {
                    int index = floorPositions.indexOf(((Tile) tile).getCoordinate(), false);
                    positions.removeIndex(index);
                }
        }

        return positions.get(random.nextInt((positions.size)));
    }

    private boolean isTileEmpty(Vector2 position) {
        for (Actor tile : getChildren()) {
            if (tile.getClass().equals(Wall.class)
                    && ((Tile) tile).equalCoordinate(position))
                return false;
        }
        return true;
    }
}
