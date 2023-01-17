package com.galing.codecube.objects;

import static com.badlogic.gdx.math.MathUtils.random;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.AssetManager;
import com.galing.codecube.enums.BoxType;
import com.galing.codecube.enums.ContainerType;

public class Box extends Tile {

    public ContainerType containerType;
    public Boolean touchable;
    public BoxType type;

    public Box(Vector2 coordinate, String variable) {
        super(coordinate);

        setIsTouchable(null);
        setControlType(null);

        switch (variable) {
            case "up":
                type = BoxType.UP;
                setAtlasRegion(AssetManager.greenBox);
                break;
            case "right":
                type = BoxType.RIGHT;
                setAtlasRegion(AssetManager.blueBox);
                break;
            case "left":
                type = BoxType.LEFT;
                setAtlasRegion(AssetManager.redBox);
                break;
            case "negation":
                type = BoxType.NEGATION;
                setAtlasRegion(AssetManager.greyBox);
                break;
            case "function":
                type = BoxType.FUNCTION;
                setAtlasRegion(AssetManager.yellowBox);
                break;
        }

        setRandomIdle();
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

    public void clearControl() {
        setControlType(null);
        setIsTouchable(null);
    }

    public void setRandomIdle() {
        setRotation(random.nextInt(3 + 3 + 1) - 3);
        setScale(.93f);
    }

    public void setPushedIdle() {
        setRotation(0);
        setScale(1f);
    }

    @Override
    public void addResetPositionAction() {
        setRandomIdle();
        clearControl();
        super.addResetPositionAction();
    }

    @Override
    public String toString() {
        return "Box{" +
                "containerType=" + containerType +
                ", touchable=" + touchable +
                ", type=" + type +
                '}';
    }
}