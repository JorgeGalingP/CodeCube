package com.galing.codecube.objects;

import com.galing.codecube.assets.AssetManager;

public class Button extends Tile {

    public Button(int position, String color) {
        super(position);

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