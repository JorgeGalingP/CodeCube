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
import com.galing.codecube.AssetManager;
import com.galing.codecube.controls.Controllable;
import com.galing.codecube.controls.Queue;
import com.galing.codecube.controls.Sequence;
import com.galing.codecube.controls.Stack;
import com.galing.codecube.enums.BoardType;
import com.galing.codecube.enums.ContainerType;
import com.galing.codecube.enums.TargetType;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Container;
import com.galing.codecube.objects.Floor;
import com.galing.codecube.objects.Matrix;
import com.galing.codecube.objects.Player;
import com.galing.codecube.objects.Target;
import com.galing.codecube.objects.Tile;
import com.galing.codecube.objects.Wall;
import com.galing.codecube.screens.Screen;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    private BoardState state;
    private boolean inverse;

    private final Array<Tile> floor;
    private final Array<Tile> walls;
    private Button programButton;
    private Button functionButton;
    private final Array<Container> programControls;
    private final Array<Container> functionControls;

    private final Controllable gameControl;

    private final Array<Vector2> playerMoves;

    private Player player;
    private Target winTarget;
    private Array<Target> failTargets;
    private final Matrix matrix;

    public Board(Stage stage, BoardType type) {
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
        this.failTargets = new Array<>(NUM_FLOOR_TILES_WIDTH * NUM_FLOOR_TILES_HEIGHT);

        // initialize board and controls layers
        initializeLayer("walls");
        initializeLayer("floor");
        initializeLayer("controls");

        // initialize matrix on top of board
        this.matrix = new Matrix();
        addActor(this.matrix);

        // initialize objects layer on top
        initializeLayer("objects");
        initializeLayer("player");
        initializeLayer("boxes");

        // initialize Control
        if (type.equals(BoardType.STACK))
            this.gameControl = new Stack(programButton, functionButton, programControls, functionControls);
        else if (type.equals(BoardType.QUEUE))
            this.gameControl = new Queue(programButton, functionButton, programControls, functionControls);
        else
            this.gameControl = new Sequence(programButton, functionButton, programControls, functionControls);

        // add drag listener for each box
        Arrays.stream(this.getChildren().toArray()).filter(a -> a.getClass().equals(Box.class)).forEach(b -> gameControl.attachDragListener((Box) b));
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

    public void resetTarget() {
        winTarget.addInOutPositionAction(getRandomPosition(Player.class));
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

                                        floor.add(tile);
                                        break;
                                    case "wall":
                                        tile = new Wall(tilePosition,
                                                mapTile.getProperties().get("subtype").toString());

                                        walls.add(tile);
                                        break;
                                    case "player":
                                        tile = new Player(getRandomPosition(Target.class));
                                        player = (Player) tile;
                                        break;
                                    case "control":
                                        tile = new Container(tilePosition,
                                                mapTile.getProperties().get("color").toString());

                                        if (((Container) tile).getType() == ContainerType.PROGRAM)
                                            this.programControls.add((Container) tile);

                                        if (((Container) tile).getType() == ContainerType.FUNCTION)
                                            this.functionControls.add((Container) tile);
                                        break;
                                    case "button":
                                        tile = new Button(tilePosition,
                                                mapTile.getProperties().get("color").toString());

                                        if (((Button) tile).getType() == ContainerType.PROGRAM)
                                            this.programButton = (Button) tile;

                                        if (((Button) tile).getType() == ContainerType.FUNCTION)
                                            this.functionButton = (Button) tile;
                                        break;
                                    case "box":
                                        tile = new Box(tilePosition,
                                                mapTile.getProperties().get("variable").toString());
                                        break;
                                    case "target":
                                        tile = new Target(getRandomPosition(Arrays.asList(Player.class, Target.class)),
                                                mapTile.getProperties().get("color").toString());
                                        if (((Target) tile).getType().equals(TargetType.WIN))
                                            winTarget = (Target) tile;
                                        else
                                            failTargets.add((Target) tile);

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

    public void setDebugMode() {
        this.player.debug = !this.player.debug;
        this.player.setListener();
    }

    private void movePlayer() {
        if (state == BoardState.RUNNING) {
            if (!gameControl.isProgramEmpty()) {
                Box box;

                switch (gameControl.getNextBox().getType()) {
                    case UP:
                    case RIGHT:
                    case LEFT:
                        box = gameControl.removeFromProgram();

                        handleMovement(box);
                        break;
                    case FUNCTION:
                        if (!gameControl.isFunctionEmpty()) {
                            box = gameControl.removeFromFunction();
                            handleMovement(box);
                        } else {
                            box = gameControl.removeFromProgram();
                            box.addResetPositionAction();
                        }
                        break;
                    case NEGATION:
                        box = gameControl.removeFromProgram();
                        box.addResetPositionAction();

                        inverse = !inverse;
                        break;
                }

                player.resetStateTime();

                // if player reach fail target
                for (Target target : failTargets)
                    if (player.isEqualCoordinate(target.getCoordinate())) {
                        player.addRemoveAction();
                        addAction(Actions.sequence(Actions.delay(.5f), Actions.run(this::setBoardStateGameOver)));
                    }


                // if movement is finished
                if (gameControl.isProgramEmpty()
                        && gameControl.isFunctionEmpty()) {

                    // check if player is in target's position
                    if (player.isEqualCoordinate(winTarget.getCoordinate()))
                        addAction(Actions.sequence(Actions.delay(.5f), Actions.run(this::resetTarget)));
                    else
                        addAction(Actions.sequence(Actions.delay(.5f), Actions.run(this::setBoardStateGameOver)));
                }
            } else
                player.setPressed(false);
        }
    }

    private void handleMovement(Box box) {
        switch (box.getType()) {
            case UP:
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

                    handleAnimation(box);
                    player.addMovePositionAction(newPosition);
                } else
                    addAction(Actions.sequence(Actions.delay(.5f), Actions.run(this::setBoardStateGameOver)));

                inverse = false;
                break;
            case RIGHT:
            case LEFT:
                handleAnimation(box);
                player.addRotationAction(box, inverse);

                inverse = false;
                break;
            case NEGATION:
                handleAnimation(box);
                inverse = !inverse;
                break;
        }
    }

    private void handleAnimation(Box box) {
        if (box.getControlType().equals(ContainerType.FUNCTION)
                && gameControl.hasSeveralFunctions())
            box.addInOutAction();
        else
            box.addResetPositionAction();
    }

    private Vector2 getRandomPosition(Class<?> type) {
        return getRandomPosition(Collections.singletonList(type));
    }

    private Vector2 getRandomPosition(List<Class<?>> types) {
        Array<Vector2> positions = new Array<>(this.floor.size);
        for (Tile floor : this.floor) {
            positions.add(floor.getCoordinate());
        }

        if (!types.isEmpty()) {
            for (Actor tile : getChildren())
                if (types.contains(tile.getClass())) {
                    int index = positions.indexOf(((Tile) tile).getCoordinate(), false);
                    positions.removeIndex(index);
                }
        }

        return positions.get(random.nextInt((positions.size)));
    }

    private boolean isTileEmpty(Vector2 position) {
        for (Actor tile : getChildren()) {
            if (tile.getClass().equals(Wall.class)
                    && ((Tile) tile).isEqualCoordinate(position))
                return false;
        }
        return true;
    }
}
