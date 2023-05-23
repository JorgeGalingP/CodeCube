package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.Assets;
import com.galing.codecube.enums.ContainerType;

public class Button extends Tile {
    public ContainerType type;

    public Button(Vector2 coordinate, String container, String direction) {
        super(coordinate);

        switch (container) {
            case "program":
                this.type = ContainerType.PROGRAM;

                switch (direction) {
                    case "horizontal":
                        setAtlasRegion(Assets.button);
                        break;
                    case "vertical":
                        setAtlasRegion(Assets.buttonVertical);
                        break;
                }
                break;
            case "function":
                this.type = ContainerType.FUNCTION;

                switch (direction) {
                    case "horizontal":
                        setAtlasRegion(Assets.buttonFunction);
                        break;
                    case "vertical":
                        setAtlasRegion(Assets.buttonFunctionVertical);
                        break;
                }
                break;
        }

        // make button more big
        scaleBy(0.25f);
    }

    public ContainerType getType() {
        return type;
    }
}