package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.assets.AssetManager;
import com.galing.codecube.enums.ControlType;

public class Control extends Tile {
    public ControlType type;

    public Control(Vector2 coordinate, String color) {
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
                type = ControlType.PROGRAM;
                break;
            case "yellow":
                setAtlasRegion(AssetManager.yellowControl);
                type = ControlType.FUNCTION;
                break;
        }
    }

    public ControlType getType() {
        return type;
    }
}