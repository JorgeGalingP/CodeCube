package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.assets.AssetManager;

public class Button extends Tile {

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
                break;
            case "yellow":
                setAtlasRegion(AssetManager.yellowButton);
                break;
        }
    }
}