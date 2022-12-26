package com.galing.codecube.objects;

import static com.badlogic.gdx.math.MathUtils.random;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.assets.AssetManager;

public class Box extends Tile {
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int LEFT = 2;
    public static final int NEGATION = 3;
    public static final int FUNCTION = 4;

    public Integer stackType;
    public Integer stackPosition;

    int movement;

    public Box(Vector2 coordinate, String variable) {
        super(coordinate);

        this.stackType = null;
        this.stackPosition = null;

        switch (variable) {
            case "up":
                movement = UP;
                setAtlasRegion(AssetManager.greenBox);
                break;
            case "right":
                movement = RIGHT;
                setAtlasRegion(AssetManager.blueBox);
                break;
            case "left":
                movement = LEFT;
                setAtlasRegion(AssetManager.redBox);
                break;
            case "negation":
                movement = NEGATION;
                setAtlasRegion(AssetManager.greyBox);
                break;
            case "function":
                movement = FUNCTION;
                setAtlasRegion(AssetManager.yellowBox);
                break;
        }

        setRandomIdle();
    }

    public int getMovement() {
        return movement;
    }

    public void setRandomIdle() {
        setRotation(random.nextInt(3 + 3 + 1) - 3);
        setScale(.93f);
    }

    public void setPushedIdle() {
        setRotation(0);
        setScale(1f);
    }
}