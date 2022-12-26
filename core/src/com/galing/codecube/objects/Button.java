package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.assets.AssetManager;
import com.galing.codecube.enums.ControlType;

public class Button extends Tile {
    public ControlType type;

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
                type = ControlType.PROGRAM;
                break;
            case "yellow":
                setAtlasRegion(AssetManager.yellowButton);
                type = ControlType.FUNCTION;
                break;
        }
    }

    public ControlType getType() {
        return type;
    }
}