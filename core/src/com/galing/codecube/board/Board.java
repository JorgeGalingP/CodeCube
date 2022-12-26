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
import com.galing.codecube.controls.StackControl;
import com.galing.codecube.enums.ControlType;
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

    private Array<Tile> floor;
    private Array<Tile> walls;
    private Button programButton;
    private Button functionButton;
    private Array<Control> programControls;
    private Array<Control> functionControls;

    private StackControl gameControl;

    private final Array<Vector2> playerMoves;

    private Player player;
    private Target target;
    private final Matrix matrix;

    public Board(Stage stage) {
        // initialize main variables
        this.camera = (OrthographicCamera) stage.getCamera();
        this.viewport = stage.getViewport();
        this.tiledRender = new OrthogonalTiledMapRenderer(AssetManager.tileMap, UNIT_SCALE);

        // initialize Board variables
        this.state = BoardState.RUNNING;
        this.inverse = false;

        // initialize Tile variables
        this.playerMoves = new Array<>(NUM_FLOOR_TILES_WIDTH * NUM_FLOOR_TILES_HEIGHT);
        this.programButton = null;
        this.functionButton = null;
        this.programControls = new Array<>(NUM_FLOOR_TILES_WIDTH * NUM_FLOOR_TILES_HEIGHT);
        this.functionControls = new Array<>(NUM_FLOOR_TILES_WIDTH * NUM_FLOOR_TILES_HEIGHT);
        this.walls = new Array<>(NUM_FLOOR_TILES_WIDTH * NUM_FLOOR_TILES_HEIGHT);
        this.floor = new Array<>(NUM_FLOOR_TILES_WIDTH * NUM_FLOOR_TILES_HEIGHT);

        // initialize board and controls layers
        initializeLayer("walls");
        initializeLayer("floor");
        initializeLayer("controls");

        // initialize Game Control
        this.gameControl = new StackControl(programButton, functionButton, programControls, functionControls);

        // initialize matrix on top of board
        this.matrix = new Matrix();
        addActor(this.matrix);

        // initialize objects layer on top
        initializeLayer("objects");
        initializeLayer("player");
        initializeLayer("boxes");
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

    private Array<Tile> initializeLayer(String layerName) {
        Array<Tile> tiles = new Array<>(NUM_FLOOR_TILES_WIDTH * NUM_FLOOR_TILES_HEIGHT);
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
                                        tile = new Floor(tilePosition,
                                                mapTile.getProperties().get("subtype").toString());

                                        floor.add((Floor) tile);
                                        break;
                                    case "wall":
                                        tile = new Wall(tilePosition,
                                                mapTile.getProperties().get("subtype").toString());

                                        walls.add((Wall) tile);
                                        break;
                                    case "player":
                                        tile = new Player(getRandomPosition(true, Target.class));
                                        player = (Player) tile;
                                        break;
                                    case "control":
                                        tile = new Control(tilePosition,
                                                mapTile.getProperties().get("color").toString());

                                        if (((Control) tile).getType() == ControlType.PROGRAM)
                                            this.programControls.add((Control) tile);

                                        if (((Control) tile).getType() == ControlType.FUNCTION)
                                            this.functionControls.add((Control) tile);
                                        break;
                                    case "button":
                                        tile = new Button(tilePosition,
                                                mapTile.getProperties().get("color").toString());

                                        if (((Button) tile).getType() == ControlType.PROGRAM)
                                            this.programButton = (Button) tile;

                                        if (((Button) tile).getType() == ControlType.FUNCTION)
                                            this.functionButton = (Button) tile;
                                        break;
                                    case "box":
                                        tile = new Box(tilePosition,
                                                mapTile.getProperties().get("variable").toString());

                                        gameControl.attachDragListener((Box) tile);
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

        return tiles;
    }

    private void movePlayer() {
        if (state == BoardState.RUNNING) {
            if (!gameControl.isProgramEmpty()) {
                Box box;

                switch (gameControl.getNextBox().getMovement()) {
                    case Box.UP:
                    case Box.RIGHT:
                    case Box.LEFT:
                        box = gameControl.popOutProgram();
                        handleMovement(box);
                        break;
                    case Box.FUNCTION:
                        if (!gameControl.isFunctionEmpty()) {
                            box = gameControl.popOutFunction();
                            handleMovement(box);
                        } else {
                            box = gameControl.popOutProgram();
                            box.addResetPositionAction();
                        }
                        break;
                    case Box.NEGATION:
                        box = gameControl.popOutProgram();
                        box.addResetPositionAction();

                        inverse = !inverse;
                        break;
                }

                player.resetStateTime();

                // movement is finished
                if (gameControl.isProgramEmpty()
                        && gameControl.isFunctionEmpty()) {

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
        Array<Vector2> positions = new Array<>(this.floor.size);
        for (Tile floor : this.floor) {
            positions.add(floor.getCoordinate());
        }

        if (exclude) {
            for (Actor tile : getChildren())
                if (tile.getClass().equals(type)) {
                    int index = positions.indexOf(((Tile) tile).getCoordinate(), false);
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
