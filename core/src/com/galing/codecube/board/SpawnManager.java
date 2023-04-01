package com.galing.codecube.board;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.galing.codecube.enums.BoxType;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Tile;

public class SpawnManager {

    private final Board board;
    private final Array<Box> activeBoxes;

    public SpawnManager(Board board) {
        this.board = board;
        this.activeBoxes = new Array<>();
    }

    public void spawn(BoxType boxType) {
        Box box = create(boxType);

        box.addShowAction();
    }

    public Box create(BoxType boxType) {
        Vector2 coordinate = Vector2.Zero;

        switch (boxType) {
            case UP:
                coordinate = new Vector2(Board.NUM_TILES_WIDTH / 2f - Tile.SIZE / 2f, Board.NUM_TILES_HEIGHT / 7f);
                break;
            case RIGHT:
                coordinate = new Vector2((Board.NUM_TILES_WIDTH / 2f * (1 / 3f)) - Tile.SIZE / 2f,
                        Board.NUM_TILES_HEIGHT / 7f);
                break;
            case LEFT:
                coordinate = new Vector2((Board.NUM_TILES_WIDTH / 2f * (2 / 3f)) - Tile.SIZE / 2f,
                        Board.NUM_TILES_HEIGHT / 7f);
                break;
            case NEGATION:
                coordinate = new Vector2((Board.NUM_TILES_WIDTH / 2f) + ((Board.NUM_TILES_WIDTH / 2f) * (1 / 3f)) -
                        Tile.SIZE / 2f, Board.NUM_TILES_HEIGHT / 7f);
                break;
            case FUNCTION:
                coordinate = new Vector2((Board.NUM_TILES_WIDTH / 2f) + ((Board.NUM_TILES_WIDTH / 2f) * (2 / 3f)) -
                        Tile.SIZE / 2f, Board.NUM_TILES_HEIGHT / 7f);
                break;
        }

        Box box = new Box(coordinate, boxType);

        activeBoxes.add(box);

        board.addActor(box);
        board.getGameControl().attachDragListener(box);

        return box;
    }

    public void free() {
        Box box;
        int len = activeBoxes.size;

        for (int i = len; --i >= 0; ) {
            box = activeBoxes.get(i);
            if (!box.isAlive()) {
                activeBoxes.removeIndex(i);
                box.addRemoveAction();
                //box.addHandleControlAction();
            }
        }
    }
}
