package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.Assets;
import com.galing.codecube.enums.ContainerType;

public class Button extends Tile {
    public ContainerType type;

    public Button(Vector2 coordinate, String color) {
        super(coordinate);

        switch (color) {
            case "green":
                setAtlasRegion(Assets.greenButton);
                break;
            case "red":
                setAtlasRegion(Assets.redButton);
                break;
            case "blue":
                setAtlasRegion(Assets.blueButton);
                type = ContainerType.PROGRAM;
                break;
            case "yellow":
                setAtlasRegion(Assets.yellowButton);
                type = ContainerType.FUNCTION;
                break;
        }
    }

    public ContainerType getType() {
        return type;
    }
}