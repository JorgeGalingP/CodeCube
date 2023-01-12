package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.galing.codecube.AssetManager;
import com.galing.codecube.enums.BoxType;

public class Player extends Tile {
    public float stateTime;
    public boolean pressed;

    public Player(Vector2 coordinate) {
        super(coordinate);
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

    public Vector2 getMovement(Box box) {
        Vector2 movementVector = Vector2.Zero;
        int rotation = (int) getRotation();

        if (box.getType().equals(BoxType.UP)) {
            switch (rotation) {
                case 0:
                case 360:
                    movementVector = new Vector2(0, 1);
                    break;
                case 180:
                case -180:
                    movementVector = new Vector2(0, -1);
                    break;
                case 90:
                case -270:
                    movementVector = new Vector2(-1, 0);
                    break;
                case -90:
                case 270:
                    movementVector = new Vector2(1, 0);
                    break;
                default:
                    break;
            }
        }

        return movementVector;
    }

    public void addRotationAction(Box box, boolean inverse) {
        RotateToAction action;
        switch (box.getType()) {
            case LEFT:
                action = Actions.rotateTo((getRotation() + (inverse ? -90f : 90f)) % 360,
                        .25f);
                action.setUseShortestDirection(true);
                addAction(action);
                break;
            case RIGHT:
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
