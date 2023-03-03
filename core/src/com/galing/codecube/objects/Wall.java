package com.galing.codecube.objects;

import com.badlogic.gdx.math.Vector2;
import com.galing.codecube.Assets;

public class Wall extends Tile {

    public Wall(Vector2 coordinate, String subtype) {
        super(coordinate);

        switch (subtype) {
            case "cornerTopLeft":
                setAtlasRegion(Assets.boardCornerTopLeft);
                break;
            case "cornerTopRight":
                setAtlasRegion(Assets.boardCornerTopRight);
                break;
            case "cornerBottomLeft":
                setAtlasRegion(Assets.boardCornerBottomLeft);
                break;
            case "cornerBottomRight":
                setAtlasRegion(Assets.boardCornerBottomRight);
                break;
            case "laneLeft":
                setAtlasRegion(Assets.boardLaneLeft);
                break;
            case "laneRight":
                setAtlasRegion(Assets.boardLaneRight);
                break;
            case "laneBottom":
                setAtlasRegion(Assets.boardLaneBottom);
                break;
            case "laneTop":
                setAtlasRegion(Assets.boardLaneTop);
                break;
        }

    }
}