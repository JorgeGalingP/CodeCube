package com.galing.codecube.objects;

import com.galing.codecube.assets.AssetManager;

public class Control extends Tile {

    public Control(int position, String color) {
        super(position);

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