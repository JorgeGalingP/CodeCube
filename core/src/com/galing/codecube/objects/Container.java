package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.Assets;
import com.galing.codecube.enums.ContainerType;

public class Container extends Tile {
    public ContainerType type;

    public Container(Vector2 coordinate, String container) {
        super(coordinate);

        switch (container) {
            case "program":
                this.type = ContainerType.PROGRAM;
                setAtlasRegion(Assets.control);
                break;
            case "function":
                this.type = ContainerType.FUNCTION;
                setAtlasRegion(Assets.controlFunction);
                break;
        }
    }

    public ContainerType getType() {
        return type;
    }
}