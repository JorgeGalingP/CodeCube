package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.AssetManager;
import com.galing.codecube.enums.ContainerType;

public class Button extends Tile {
    public ContainerType type;

    public Button(Vector2 coordinate, String color) {
        super(coordinate);

        switch (color) {
            case "green":
                setAtlasRegion(AssetManager.greenButton);
                break;
            case "red":
                setAtlasRegion(AssetManager.redButton);
                break;
            case "blue":
                setAtlasRegion(AssetManager.blueButton);
                type = ContainerType.PROGRAM;
                break;
            case "yellow":
                setAtlasRegion(AssetManager.yellowButton);
                type = ContainerType.FUNCTION;
                break;
        }
    }

    public ContainerType getType() {
        return type;
    }
}