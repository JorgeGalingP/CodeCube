package com.galing.codecube.objects;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.galing.codecube.Assets;
import com.galing.codecube.enums.BoxType;

public class Player extends Tile {
    public float stateTime;
    public boolean pressed;
    public boolean debug;
    public EventListener listener;

    public Player(Vector2 coordinate) {
        super(coordinate);
        stateTime = 1f;
        pressed = false;
        debug = false;

        setAtlasRegion(Assets.player);

        this.listener = new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                addAction(Actions.sequence(
                        Actions.parallel(
                                Actions.run(() -> addInOutAction()),
                                Actions.run(Assets::playPlayerTap)),
                        Actions.delay(.5f),
                        Actions.run(() -> setPressed(true))));
            }
        };

        addListener(this.listener);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
    }

    public boolean getDebug() {
        return this.debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
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

    public void setListener() {
        removeListener(this.listener);

        if (debug)
            this.listener = new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    addAction(Actions.sequence(Actions.run(() -> addInOutAction()),
                            Actions.delay(.5f),
                            Actions.run(() -> pressed = true),
                            Actions.delay(.5f),
                            Actions.run(() -> pressed = false)));
                }
            };
        else
            this.listener = new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    addAction(Actions.sequence(Actions.run(() -> addInOutAction()),
                            Actions.delay(.5f),
                            Actions.run(() -> pressed = true)));
                }
            };

        addListener(this.listener);
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

    public void addMoveAction(Vector2 position) {
        setCoordinate(position);

        // perform move action
        addAction(Actions.parallel(moveTo(position.x, position.y, .3f), run(Assets::playPlayerMovement)));
    }

    public void addRotationAction(Box box, boolean inverse) {
        RotateToAction action;
        switch (box.getType()) {
            case LEFT:
                action = Actions.rotateTo((getRotation() + (inverse ? -90f : 90f)) % 360,
                        .25f);
                action.setUseShortestDirection(true);
                addAction(Actions.parallel(action, run(Assets::playPlayerTurn)));
                break;
            case RIGHT:
                action = Actions.rotateTo((getRotation() + (inverse ? 90f : -90f)) % 360,
                        .25f);
                action.setUseShortestDirection(true);
                addAction(Actions.parallel(action, run(Assets::playPlayerTurn)));
                break;
        }
    }

    @Override
    public void addRemoveAction() {
        addAction(Actions.sequence(Actions.parallel(
                        Actions.scaleTo(0, 0, .3f),
                        Actions.alpha(0, .3f)),
                Actions.run(Assets::playPlayerKill),
                Actions.removeActor()
        ));
    }
}
