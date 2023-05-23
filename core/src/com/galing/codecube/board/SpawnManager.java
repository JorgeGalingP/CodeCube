package com.galing.codecube.board;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.galing.codecube.enums.BoardType;
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

        box.showAction();
    }

    public Box create(BoxType boxType) {
        Vector2 coordinate = Vector2.Zero;

        // calculate coordinate of each box
        switch (boxType) {
            case UP:
                if (board.getType().equals(BoardType.STACK))
                    coordinate = new Vector2(Board.NUM_TILES_WIDTH / 3.5f - Tile.SIZE / 2f,
                            Board.NUM_TILES_HEIGHT / 3.5f);
                else
                    coordinate = new Vector2(Board.NUM_TILES_WIDTH / 2f - Tile.SIZE / 2f,
                            Board.NUM_TILES_HEIGHT / 6f);
                break;
            case RIGHT:
                if (board.getType().equals(BoardType.STACK))
                    coordinate = new Vector2(Board.NUM_TILES_WIDTH / 3.5f - Tile.SIZE / 2f,
                            Board.NUM_TILES_HEIGHT / 3.5f + 2 * Tile.SIZE);
                else
                    coordinate = new Vector2((Board.NUM_TILES_WIDTH / 2f * (1 / 3f)) - Tile.SIZE / 2f,
                            Board.NUM_TILES_HEIGHT / 6f);
                break;
            case LEFT:
                if (board.getType().equals(BoardType.STACK))
                    coordinate = new Vector2(Board.NUM_TILES_WIDTH / 3.5f - Tile.SIZE / 2f,
                            Board.NUM_TILES_HEIGHT / 3.5f + 4 * Tile.SIZE);
                else
                    coordinate = new Vector2((Board.NUM_TILES_WIDTH / 2f * (2 / 3f)) - Tile.SIZE / 2f,
                            Board.NUM_TILES_HEIGHT / 6f);
                break;
            case NEGATION:
                if (board.getType().equals(BoardType.STACK))
                    coordinate = new Vector2(Board.NUM_TILES_WIDTH / 3.5f - Tile.SIZE / 2f,
                            Board.NUM_TILES_HEIGHT / 3.5f - 4 * Tile.SIZE);
                else
                    coordinate = new Vector2((Board.NUM_TILES_WIDTH / 2f) + ((Board.NUM_TILES_WIDTH / 2f) * (1 / 3f)) -
                            Tile.SIZE / 2f, Board.NUM_TILES_HEIGHT / 6f);
                break;
            case FUNCTION:
                if (board.getType().equals(BoardType.STACK))
                    coordinate = new Vector2(Board.NUM_TILES_WIDTH / 3.5f - Tile.SIZE / 2f,
                            Board.NUM_TILES_HEIGHT / 3.5f - 2 * Tile.SIZE);
                else
                    coordinate = new Vector2((Board.NUM_TILES_WIDTH / 2f) + ((Board.NUM_TILES_WIDTH / 2f) * (2 / 3f)) -
                            Tile.SIZE / 2f, Board.NUM_TILES_HEIGHT / 6f);
                break;
        }

        // create box with new coordinate
        Box box = new Box(coordinate, boxType);

        activeBoxes.add(box);

        board.addActor(box);
        board.getGameControl().attachDragListener(box);

        return box;
    }

    public void free() {
        Box box;
        int size = activeBoxes.size;

        // iterate through active boxes
        for (int i = size; --i >= 0; ) {
            box = activeBoxes.get(i);

            if (!box.isAlive()) {
                activeBoxes.removeIndex(i);
                box.removeAction();
            }
        }
    }
}
