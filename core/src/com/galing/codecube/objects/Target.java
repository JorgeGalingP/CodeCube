package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.assets.AssetManager;

public class Target extends Tile {
    public Target(Vector2 coordinate, String color) {
        super(coordinate);

        switch (color) {
            case "green":
                setAtlasRegion(AssetManager.greenTarget);
                break;
            case "red":
                setAtlasRegion(AssetManager.redTarget);
                break;
            case "white":
                setAtlasRegion(AssetManager.whiteTarget);
                break;
        }
    }
}
