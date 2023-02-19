package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.Assets;
import com.galing.codecube.enums.TargetType;

public class Target extends Tile {

    private TargetType type;

    public Target(Vector2 coordinate, String color) {
        super(coordinate);

        switch (color) {
            case "green":
                setType(TargetType.WIN);
                setAtlasRegion(Assets.greenTarget);
                break;
            case "red":
                setType(TargetType.FAIL);
                setAtlasRegion(Assets.redTarget);
                break;
            case "white":
                setAtlasRegion(Assets.whiteTarget);
                break;
        }
    }

    public TargetType getType() {
        return type;
    }

    public void setType(TargetType type) {
        this.type = type;
    }
}
