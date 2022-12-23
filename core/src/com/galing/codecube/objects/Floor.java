package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.assets.AssetManager;

public class Floor extends Tile {

    public Floor(Vector2 coordinate, String subType) {
        super(coordinate);

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