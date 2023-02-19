package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.Assets;
import com.galing.codecube.enums.ContainerType;

public class Container extends Tile {
    public ContainerType type;

    public Container(Vector2 coordinate, String color) {
        super(coordinate);

        switch (color) {
            case "green":
                setAtlasRegion(Assets.greenControl);
                break;
            case "red":
                setAtlasRegion(Assets.redControl);
                break;
            case "blue":
                setAtlasRegion(Assets.blueControl);
                type = ContainerType.PROGRAM;
                break;
            case "yellow":
                setAtlasRegion(Assets.yellowControl);
                type = ContainerType.FUNCTION;
                break;
        }
    }

    public ContainerType getType() {
        return type;
    }
}