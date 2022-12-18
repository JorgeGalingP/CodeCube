package com.galing.codecube.objects;

import com.galing.codecube.assets.AssetManager;

public class Target extends Tile {
    public Target(int position, String color) {
        super(position);

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
