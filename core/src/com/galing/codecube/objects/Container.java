package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.AssetManager;
import com.galing.codecube.enums.ContainerType;

public class Container extends Tile {
    public ContainerType type;

    public Container(Vector2 coordinate, String color) {
        super(coordinate);

        switch (color) {
            case "green":
                setAtlasRegion(AssetManager.greenControl);
                break;
            case "red":
                setAtlasRegion(AssetManager.redControl);
                break;
            case "blue":
                setAtlasRegion(AssetManager.blueControl);
                type = ContainerType.PROGRAM;
                break;
            case "yellow":
                setAtlasRegion(AssetManager.yellowControl);
                type = ContainerType.FUNCTION;
                break;
        }
    }

    public ContainerType getType() {
        return type;
    }
}