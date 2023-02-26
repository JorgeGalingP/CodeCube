package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.Assets;

public class Wall extends Tile {

    public Wall(Vector2 coordinate, String subtype) {
        super(coordinate);

        switch (subtype) {
            case "boardCornerTopLeft":
                setAtlasRegion(Assets.boardCornerTopLeft);
                break;
            case "boardCornerTopRight":
                setAtlasRegion(Assets.boardCornerTopRight);
                break;
            case "boardCornerBottomLeft":
                setAtlasRegion(Assets.boardCornerBottomLeft);
                break;
            case "boardCornerBottomRight":
                setAtlasRegion(Assets.boardCornerBottomRight);
                break;
            case "boardLaneLeft":
                setAtlasRegion(Assets.boardLaneLeft);
                break;
            case "boardLaneRight":
                setAtlasRegion(Assets.boardLaneRight);
                break;
            case "boardLaneBottom":
                setAtlasRegion(Assets.boardLaneBottom);
                break;
            case "boardLaneTop":
                setAtlasRegion(Assets.boardLaneTop);
                break;
            case "rock":
                setAtlasRegion(Assets.rockWall);
                break;
        }

    }
}