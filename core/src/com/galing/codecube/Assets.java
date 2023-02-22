package com.galing.codecube;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.galing.codecube.enums.BoardType;

public class Assets {
    private final AssetManager manager;

    public static TiledMap tileMap;

    public static TextureAtlas atlasTileset;
    public static TextureAtlas atlasUI;

    public static BitmapFont basicFont;

    public static AtlasRegion bg;
    public static AtlasRegion blueWindow;
    public static AtlasRegion whiteWindow;
    public static AtlasRegion roundWindow;
    public static AtlasRegion rectWindow;
    public static AtlasRegion squareWindow;
    public static AtlasRegion squareCircleWindow;

    public static AtlasRegion blueNoPressed;
    public static AtlasRegion bluePressed;
    public static AtlasRegion yellowNoPressed;
    public static AtlasRegion yellowPressed;
    public static AtlasRegion greenNoPressed;
    public static AtlasRegion greenPressed;
    public static AtlasRegion blueNoPressedRect;
    public static AtlasRegion bluePressedRect;
    public static AtlasRegion yellowNoPressedRect;
    public static AtlasRegion yellowPressedRect;
    public static AtlasRegion greenNoPressedRect;
    public static AtlasRegion greenPressedRect;

    public static AtlasRegion closeIcon;
    public static AtlasRegion tickIcon;
    public static AtlasRegion settingsIcon;
    public static AtlasRegion pauseIcon;
    public static AtlasRegion trophyIcon;
    public static AtlasRegion shareIcon;
    public static AtlasRegion playIcon;
    public static AtlasRegion homeIcon;
    public static AtlasRegion backIcon;
    public static AtlasRegion debugIcon;
    public static AtlasRegion soundOnIcon;
    public static AtlasRegion soundOffIcon;
    public static AtlasRegion toggleOnIcon;
    public static AtlasRegion toggleOffIcon;

    public static TextButton.TextButtonStyle fontButtonStyle;
    public static ImageButton.ImageButtonStyle playButtonStyle;
    public static ImageButton.ImageButtonStyle homeButtonStyle;
    public static ImageButton.ImageButtonStyle backButtonStyle;
    public static ImageButton.ImageButtonStyle debugButtonStyle;

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

    public Assets(AssetManager manager) {
        this.manager = manager;
    }

    public void queueAssets() {
        // load atlas
        manager.load("atlas/tileset.atlas", TextureAtlas.class);
        manager.load("atlas/UI.atlas", TextureAtlas.class);

        // set handle resolver for TMX files as TileMap
        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

        // load stages
        manager.load("stages/sequence_easy.tmx", TiledMap.class);
        manager.load("stages/sequence_normal.tmx", TiledMap.class);
        manager.load("stages/sequence_hard.tmx", TiledMap.class);
        manager.load("stages/stack_easy.tmx", TiledMap.class);
        manager.load("stages/stack_normal.tmx", TiledMap.class);
        manager.load("stages/stack_hard.tmx", TiledMap.class);
        manager.load("stages/queue_easy.tmx", TiledMap.class);
        manager.load("stages/queue_normal.tmx", TiledMap.class);
        manager.load("stages/queue_hard.tmx", TiledMap.class);

        // set handle resolver for TTF files as BitmapFont
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        // generate basic font
        FreetypeFontLoader.FreeTypeFontLoaderParameter basicParameters =
                new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        basicParameters.fontFileName = "fonts/font.ttf";
        basicParameters.fontParameters.minFilter = Texture.TextureFilter.Linear;
        basicParameters.fontParameters.magFilter = Texture.TextureFilter.Linear;
        basicParameters.fontParameters.size = 50;
        basicParameters.fontParameters.borderColor = Color.BLACK;
        basicParameters.fontParameters.borderWidth = 1.5f;
        basicParameters.fontParameters.shadowColor = Color.BLACK;
        basicParameters.fontParameters.shadowOffsetX = 2;

        // load font
        manager.load("fonts/font.ttf", BitmapFont.class, basicParameters);
    }

    public void loadAssets() {
        // atlas
        atlasTileset = manager.get("atlas/tileset.atlas", TextureAtlas.class);
        atlasUI = manager.get("atlas/UI.atlas", TextureAtlas.class);

        // font
        basicFont = manager.get("fonts/font.ttf", BitmapFont.class);

        // UI
        bg = atlasUI.findRegion("BG");
        squareCircleWindow = atlasUI.findRegion("rect2");
        bluePressed = atlasUI.findRegion("blueBtnTapped");
        blueNoPressed = atlasUI.findRegion("blueBtnNormal");
        playIcon = atlasUI.findRegion("playBtn");
        homeIcon = atlasUI.findRegion("homeBtn");
        greenPressed = atlasUI.findRegion("greenBtnTapped");
        greenNoPressed = atlasUI.findRegion("greenBtnNormal");
        playIcon = atlasUI.findRegion("playBtn");
        backIcon = atlasUI.findRegion("backBtn");
        pauseIcon = atlasUI.findRegion("pauseBtn");
        debugIcon = atlasUI.findRegion("debugIcon");

        // buttons
        TextureRegionDrawable bluePressed = new TextureRegionDrawable(Assets.bluePressed);
        TextureRegionDrawable blueNoPressed = new TextureRegionDrawable(Assets.blueNoPressed);
        TextureRegionDrawable greenPressed = new TextureRegionDrawable(Assets.greenPressed);
        TextureRegionDrawable greenNoPressed = new TextureRegionDrawable(Assets.greenNoPressed);
        TextureRegionDrawable playIcon = new TextureRegionDrawable(Assets.playIcon);
        TextureRegionDrawable homeIcon = new TextureRegionDrawable(Assets.homeIcon);
        TextureRegionDrawable pauseIcon = new TextureRegionDrawable(Assets.pauseIcon);
        TextureRegionDrawable backIcon = new TextureRegionDrawable(Assets.backIcon);
        TextureRegionDrawable debugIcon = new TextureRegionDrawable(Assets.debugIcon);

        fontButtonStyle = new TextButton.TextButtonStyle(blueNoPressed,
                greenPressed, blueNoPressed, basicFont);
        playButtonStyle = new ImageButton.ImageButtonStyle(blueNoPressed, greenPressed, greenPressed, playIcon,
                playIcon, playIcon);
        homeButtonStyle = new ImageButton.ImageButtonStyle(bluePressed, greenNoPressed, bluePressed, homeIcon,
                homeIcon, homeIcon);
        backButtonStyle = new ImageButton.ImageButtonStyle(blueNoPressed, greenPressed, greenPressed, backIcon,
                backIcon, backIcon);
        debugButtonStyle = new ImageButton.ImageButtonStyle(bluePressed, greenNoPressed, greenNoPressed, debugIcon,
                debugIcon, debugIcon);

        // tileset
        dirtFloor = atlasTileset.findRegion("dirtFloor");
        rockFloor = atlasTileset.findRegion("rockFloor");
        oceanFloor = atlasTileset.findRegion("oceanFloor");
        dessertFloor = atlasTileset.findRegion("dessertFloor");

        rockWall = atlasTileset.findRegion("rockWall");
        woodWall = atlasTileset.findRegion("woodWall");

        blueBox = atlasTileset.findRegion("blueBox");
        yellowBox = atlasTileset.findRegion("yellowBox");
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

    public void selectMap(BoardType type) {
        if (tileMap != null) {
            tileMap.dispose();
            tileMap = null;
        }

        tileMap = manager.get("stages/" + type.getType() + "_" +
                Settings.selectedDifficulty.toString().toLowerCase() + ".tmx", TiledMap.class);
    }
}
