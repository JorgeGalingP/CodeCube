package com.galing.codecube.board;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.galing.codecube.enums.BoxType;
import com.galing.codecube.objects.Box;

public class SpawnManager {

    private final Board board;
    private final Stage stage;
    private final Array<Box> activeBoxes;

    public SpawnManager(Board board, Stage stage) {
        this.board = board;
        this.stage = stage;
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
                coordinate = new Vector2(5.5f, 3);
                break;
            case RIGHT:
                coordinate = new Vector2(1.5f, 3);
                break;
            case LEFT:
                coordinate = new Vector2(3.5f, 3);
                break;
            case NEGATION:
                coordinate = new Vector2(7.5f, 3);
                break;
            case FUNCTION:
                coordinate = new Vector2(9.5f, 3);
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
