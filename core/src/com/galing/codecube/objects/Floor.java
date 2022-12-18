package com.galing.codecube.objects;

import com.galing.codecube.assets.AssetManager;

public class Floor extends Tile {

    public Floor(int position, String subType) {
        super(position);

        switch (subType) {
            case "dirt":
                setAtlasRegion(AssetManager.dirtFloor);
                break;
            case "rock":
                setAtlasRegion(AssetManager.rockFloor);
                break;
            case "ocean":
                setAtlasRegion(AssetManager.oceanFloor);
                break;
            case "dessert":
                setAtlasRegion(AssetManager.dessertFloor);
                break;
        }
    }
}