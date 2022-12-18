package com.galing.codecube.objects;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.galing.codecube.assets.AssetManager;
import com.galing.codecube.board.Board;

public class Player extends Tile {
    public float stateTime;
    public boolean pressed;

    public Player(int position) {
        super(position);
        stateTime = 1f;
        pressed = false;

        setAtlasRegion(AssetManager.player);

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                addAction(Actions.sequence(Actions.run(() -> addInOutAction()),
                        Actions.delay(.5f),
                        Actions.run(() -> pressed = true)));
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
    }

    public int getMovement(Box box) {
        int mov = 0;
        int rotation = (int) getRotation();

        if (box.getMovement() == Box.UP) {
            switch (rotation) {
                case 0:
                case 360:
                    mov = Board.NUM_TILES_WIDTH;
                    break;
                case 180:
                case -180:
                    mov = -Board.NUM_TILES_WIDTH;
                    break;
                case 90:
                case -270:
                    mov = -1;
                    break;
                case -90:
                case 270:
                    mov = 1;
                    break;
                default:
                    break;
            }
        }

        return mov;
    }

    public void addRotationAction(Box box, boolean inverse) {
        RotateToAction action;
        switch (box.getMovement()) {
            case Box.LEFT:
                action = Actions.rotateTo((getRotation() + (inverse ? -90f : 90f)) % 360,
                        .25f);
                action.setUseShortestDirection(true);
                addAction(action);
                break;
            case Box.RIGHT:
                action = Actions.rotateTo((getRotation() + (inverse ? 90f : -90f)) % 360,
                        .25f);
                action.setUseShortestDirection(true);
                addAction(action);
                break;
        }
    }

    public void resetStateTime() {
        stateTime = 0;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public boolean canMove() {
        return pressed && stateTime >= 1f;
    }
}
