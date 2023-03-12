package com.galing.codecube.objects;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.galing.codecube.Assets;
import com.galing.codecube.enums.BoxType;
import com.galing.codecube.enums.ContainerType;

public class Box extends Tile {

    public ContainerType containerType;
    public Boolean touchable;
    public BoxType type;
    public boolean alive;

    public Box(Vector2 coordinate, BoxType type) {
        super(coordinate);

        this.type = type;

        switch (type) {
            case UP:
                setAtlasRegion(Assets.greenBox);
                break;
            case RIGHT:
                setAtlasRegion(Assets.blueBox);
                break;
            case LEFT:
                setAtlasRegion(Assets.redBox);
                break;
            case NEGATION:
                setAtlasRegion(Assets.greyBox);
                break;
            case FUNCTION:
                setAtlasRegion(Assets.yellowBox);
                break;
        }

        clearControl();
        setRandomIdle();

        alive = true;

        setScale(0); // TODO
    }

    public ContainerType getControlType() {
        return containerType;
    }

    public void setControlType(ContainerType containerType) {
        this.containerType = containerType;
    }

    public Boolean getIsTouchable() {
        return touchable;
    }

    public void setIsTouchable(Boolean touchable) {
        this.touchable = touchable;
    }

    public BoxType getType() {
        return type;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void clearControl() {
        setControlType(null);
        setIsTouchable(null);
    }

    public void setRandomIdle() {
        setRotation(random.nextInt(3 + 3 + 1) - 3);
        setScale(1.25f);
    }

    public void setDraggedIdle() {
        setScale(1.75f);
    }

    public void setPushedIdle() {
        setScale(.8f);
        setRotation(0);
    }

    @Override
    public void addInOutAction() {
        addAction(Actions.sequence(
                Actions.scaleTo(1.25f, 1.25f, .15f),
                Actions.scaleTo(.8f, .8f, .2f)));
    }

    @Override
    public void addResetPositionAction() {
        setRandomIdle();
        clearControl();
        super.addResetPositionAction();
    }

    @Override
    public void addRemoveAction() {
        setRandomIdle();
        clearControl();
        super.addRemoveAction();
        setAlive(false);
    }
}