package com.galing.codecube.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class AssetManager {
    public static TiledMap tileMap;
    public static TextureAtlas atlasTileset;

    public static AtlasRegion greenBox;
    public static AtlasRegion blueBox;
    public static AtlasRegion redBox;
    public static AtlasRegion yellowBox;
    public static AtlasRegion greyBox;

    public static AtlasRegion greenTarget;

    public static AtlasRegion player;

    public static void load() {
        atlasTileset = new TextureAtlas(Gdx.files.internal("atlas/atlasTileset.txt"));

        blueBox = atlasTileset.findRegion("blueBox");
        yellowBox = atlasTileset.findRegion("yellowBox");
        greenBox = atlasTileset.findRegion("greenBox");
        redBox = atlasTileset.findRegion("redBox");
        greyBox = atlasTileset.findRegion("greyBox");
        greenTarget = atlasTileset.findRegion("greenTarget");

        player = atlasTileset.findRegion("player");
    }

    public static void loadTiledMap() {
        if (tileMap != null) {
            tileMap.dispose();
            tileMap = null;
        }

        tileMap = new TmxMapLoader().load("stages/stack.tmx");
    }
}
