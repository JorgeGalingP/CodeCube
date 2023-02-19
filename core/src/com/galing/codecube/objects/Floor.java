package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.Assets;

public class Floor extends Tile {

    public Floor(Vector2 coordinate, String subType) {
        super(coordinate);

        switch (subType) {
            case "dirt":
                setAtlasRegion(Assets.dirtFloor);
                break;
            case "rock":
                setAtlasRegion(Assets.rockFloor);
                break;
            case "ocean":
                setAtlasRegion(Assets.oceanFloor);
                break;
            case "dessert":
                setAtlasRegion(Assets.dessertFloor);
                break;
        }
    }
}