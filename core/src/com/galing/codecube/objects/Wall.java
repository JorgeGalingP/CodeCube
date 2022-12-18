package com.galing.codecube.objects;

import com.galing.codecube.assets.AssetManager;

public class Wall extends Tile {

    public Wall(int position, String subType) {
        super(position);

        switch (subType) {
            case "wood":
                setAtlasRegion(AssetManager.woodWall);
                break;
            case "rock":
                setAtlasRegion(AssetManager.rockWall);
                break;
        }

    }
}