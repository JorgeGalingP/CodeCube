package com.galing.codecube.board;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.galing.codecube.enums.BoxType;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Tile;

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
        Vector2 coordinate = Vector2.Zero;

        switch (boxType) {
            case UP:
                coordinate = new Vector2(stage.getWidth() / 2 - Tile.SIZE / 2,
                        stage.getWidth() - stage.getWidth() * 0.75f);
                break;
            case RIGHT:
                coordinate = new Vector2(((stage.getWidth() / 2) * (1 / 3f)) - Tile.SIZE / 2,
                        stage.getWidth() - stage.getWidth() * 0.75f);
                break;
            case LEFT:
                coordinate = new Vector2(((stage.getWidth() / 2) * (2 / 3f)) - Tile.SIZE / 2,
                        stage.getWidth() - stage.getWidth() * 0.75f);
                break;
            case NEGATION:
                coordinate = new Vector2((stage.getWidth() / 2) + ((stage.getWidth() / 2) * (1 / 3f)) - Tile.SIZE / 2,
                        stage.getWidth() - stage.getWidth() * 0.75f);
                break;
            case FUNCTION:
                coordinate = new Vector2((stage.getWidth() / 2) + ((stage.getWidth() / 2) * (2 / 3f)) - Tile.SIZE / 2,
                        stage.getWidth() - stage.getWidth() * 0.75f);
                break;
        }

        Box box = new Box(coordinate, boxType);
        activeBoxes.add(box);

        board.addActor(box);
        board.getGameControl().attachDragListener(box);

        // TODO
        box.addAction(Actions.sequence(
                Actions.scaleTo(1.25f, 1.25f, .3f),
                Actions.alpha(1f, .15f)));
    }

    public void free() {
        Box box;
        int len = activeBoxes.size;

        for (int i = len; --i >= 0; ) {
            box = activeBoxes.get(i);
            if (!box.isAlive()) {
                activeBoxes.removeIndex(i);
                box.addRemoveAction();
            }
        }
    }
}
