package com.galing.codecube.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class AssetManager {
    public static TiledMap tileMap;
    public static TextureAtlas atlasTileset;

    public static AtlasRegion dirtFloor;
    public static AtlasRegion rockFloor;
    public static AtlasRegion oceanFloor;
    public static AtlasRegion dessertFloor;

    public static AtlasRegion rockWall;
    public static AtlasRegion woodWall;

    public static AtlasRegion greenBox;
    public static AtlasRegion blueBox;
    public static AtlasRegion redBox;
    public static AtlasRegion yellowBox;
    public static AtlasRegion greyBox;
    public static AtlasRegion brownBox;

    public static AtlasRegion greenTarget;
    public static AtlasRegion redTarget;
    public static AtlasRegion whiteTarget;

    public static AtlasRegion greenControl;
    public static AtlasRegion blueControl;
    public static AtlasRegion redControl;
    public static AtlasRegion yellowControl;

    public static AtlasRegion greenButton;
    public static AtlasRegion blueButton;
    public static AtlasRegion redButton;
    public static AtlasRegion yellowButton;

    public static AtlasRegion player;

    public static void loadAssets() {
        atlasTileset = new TextureAtlas(Gdx.files.internal("atlas/tileset.atlas"));

        dirtFloor = atlasTileset.findRegion("dirtFloor");
        rockFloor = atlasTileset.findRegion("rockFloor");
        oceanFloor = atlasTileset.findRegion("oceanFloor");
        dessertFloor = atlasTileset.findRegion("dessertFloor");

        rockWall = atlasTileset.findRegion("rockWall");
        woodWall = atlasTileset.findRegion("woodWall");

        blueBox = atlasTileset.findRegion("blueBox");
        yellowBox = atlasTileset.findRegion("yellowSquareBox");
        greenBox = atlasTileset.findRegion("greenBox");
        redBox = atlasTileset.findRegion("redBox");
        greyBox = atlasTileset.findRegion("greyBox");
        brownBox = atlasTileset.findRegion("brownBox");

        greenTarget = atlasTileset.findRegion("greenTarget");
        redTarget = atlasTileset.findRegion("redTarget");
        whiteTarget = atlasTileset.findRegion("whiteTarget");

        blueControl = atlasTileset.findRegion("blueControl");
        yellowControl = atlasTileset.findRegion("yellowControl");
        greenControl = atlasTileset.findRegion("greenControl");
        redControl = atlasTileset.findRegion("redControl");

        blueButton = atlasTileset.findRegion("blueButton");
        yellowButton = atlasTileset.findRegion("yellowButton");
        greenButton = atlasTileset.findRegion("greenButton");
        redButton = atlasTileset.findRegion("redButton");

        player = atlasTileset.findRegion("player");
    }

    public static void loadMap() {
        if (tileMap != null) {
            tileMap.dispose();
            tileMap = null;
        }

        tileMap = new TmxMapLoader().load("stages/queue.tmx");
    }
}
