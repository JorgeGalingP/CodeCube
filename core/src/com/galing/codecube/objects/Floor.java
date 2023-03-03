package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.Assets;

public class Floor extends Tile {

    public Floor(Vector2 coordinate, String subType) {
        super(coordinate);

        switch (subType) {
            case "barrels":
                setAtlasRegion(Assets.barrelsFloor);
                break;
            case "campfire":
                setAtlasRegion(Assets.campfireFloor);
                break;
            case "cracked":
                setAtlasRegion(Assets.crackedFloor);
                break;
            case "decorative":
                setAtlasRegion(Assets.decorativeFloor);
                break;
            case "empty":
                setAtlasRegion(Assets.emptyFloor);
                break;
            case "grass":
                setAtlasRegion(Assets.grassFloor);
                break;
            case "ocean":
                setAtlasRegion(Assets.oceanFloor);
                break;
            case "plants":
                setAtlasRegion(Assets.plantsFloor);
                break;
            case "puddle":
                setAtlasRegion(Assets.puddleFloor);
                break;
            case "tile":
                setAtlasRegion(Assets.tileFloor);
                break;
            case "wood":
                setAtlasRegion(Assets.woodFloor);
                break;
        }
    }
}