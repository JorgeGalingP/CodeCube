package com.galing.codecube.objects;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.galing.codecube.Assets;
import com.galing.codecube.enums.BoxType;
import com.galing.codecube.enums.ContainerType;

public class Box extends Tile {

    public BoxType type;
    public ContainerType containerType;
    public Boolean touchable;
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

        setAlive(true);
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
        setScale(1.5f);
    }

    public void setDraggedIdle() {
        setScale(2f);
    }

    public void setPushedIdle() {
        setScale(.8f);
        setRotation(0);
    }

    public void showAction() {
        addAction(Actions.parallel(
                Actions.scaleTo(0, 0),
                Actions.scaleTo(1.5f, 1.5f, .15f),
                Actions.alpha(1f, .15f)));
    }

    public void resetAction() {
        addAction(Actions.parallel(
                moveTo(getCoordinate().x, getCoordinate().y, .3f),
                Actions.run(this::setRandomIdle)
        ));
    }

    @Override
    public void pinchAction() {
        addAction(Actions.sequence(
                Actions.scaleTo(1.25f, 1.25f, .15f),
                Actions.scaleTo(.8f, .8f, .15f)));
    }
}