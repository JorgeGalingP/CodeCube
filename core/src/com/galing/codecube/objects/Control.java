package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.assets.AssetManager;

public class Control extends Tile {

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
                break;
            case "yellow":
                setAtlasRegion(AssetManager.yellowControl);
                break;
        }
    }
}