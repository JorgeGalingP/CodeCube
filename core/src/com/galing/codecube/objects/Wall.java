package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.AssetManager;

public class Wall extends Tile {

    public Wall(Vector2 coordinate, String subType) {
        super(coordinate);

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