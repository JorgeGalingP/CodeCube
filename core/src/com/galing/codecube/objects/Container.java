package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.Assets;
import com.galing.codecube.enums.ContainerType;

public class Container extends Tile {
    public ContainerType type;

    public Container(Vector2 coordinate, String container, String direction, boolean isFinal) {
        super(coordinate);

        switch (container) {
            case "program":
                this.type = ContainerType.PROGRAM;

                if (isFinal)
                    switch (direction) {
                        case "horizontal":
                            setAtlasRegion(Assets.controlFinal);
                            break;
                        case "vertical":
                            setAtlasRegion(Assets.controlFinalVertical);
                            break;
                    }
                else
                    switch (direction) {
                        case "horizontal":
                            setAtlasRegion(Assets.control);
                            break;
                        case "vertical":
                            setAtlasRegion(Assets.controlVertical);
                            break;
                    }
                break;
            case "function":
                this.type = ContainerType.FUNCTION;

                if (isFinal)
                    switch (direction) {
                        case "horizontal":
                            setAtlasRegion(Assets.controlFunctionFinal);
                            break;
                        case "vertical":
                            setAtlasRegion(Assets.controlFunctionFinalVertical);
                            break;
                    }
                else
                    switch (direction) {
                        case "horizontal":
                            setAtlasRegion(Assets.controlFunction);
                            break;
                        case "vertical":
                            setAtlasRegion(Assets.controlFunctionVertical);
                            break;
                    }
                break;
        }


    }

    public ContainerType getType() {
        return type;
    }
}