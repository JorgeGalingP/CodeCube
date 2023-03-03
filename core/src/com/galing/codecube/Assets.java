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

    public static AtlasRegion barrelsFloor;
    public static AtlasRegion campfireFloor;
    public static AtlasRegion crackedFloor;
    public static AtlasRegion decorativeFloor;
    public static AtlasRegion emptyFloor;
    public static AtlasRegion grassFloor;
    public static AtlasRegion oceanFloor;
    public static AtlasRegion plantsFloor;
    public static AtlasRegion puddleFloor;
    public static AtlasRegion tileFloor;
    public static AtlasRegion woodFloor;

    public static AtlasRegion boardCornerTopLeft;
    public static AtlasRegion boardCornerTopRight;
    public static AtlasRegion boardCornerBottomLeft;
    public static AtlasRegion boardCornerBottomRight;
    public static AtlasRegion boardLaneLeft;
    public static AtlasRegion boardLaneRight;
    public static AtlasRegion boardLaneBottom;
    public static AtlasRegion boardLaneTop;
    public static AtlasRegion rockWall;

    public static AtlasRegion cornerTopLeft;
    public static AtlasRegion cornerTopRight;
    public static AtlasRegion cornerBottomLeft;
    public static AtlasRegion cornerBottomRight;
    public static AtlasRegion laneLeft;
    public static AtlasRegion laneRight;
    public static AtlasRegion laneBottom;
    public static AtlasRegion laneTop;

    public static AtlasRegion greenBox;
    public static AtlasRegion blueBox;
    public static AtlasRegion redBox;
    public static AtlasRegion yellowBox;
    public static AtlasRegion greyBox;
    public static AtlasRegion brownBox;

    public static AtlasRegion greenTarget;
    public static AtlasRegion redTarget;
    public static AtlasRegion whiteTarget;

    public static AtlasRegion control;
    public static AtlasRegion controlFunction;

    public static AtlasRegion button;
    public static AtlasRegion buttonVertical;
    public static AtlasRegion buttonFunction;
    public static AtlasRegion buttonFunctionVertical;

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
        oceanFloor = atlasTileset.findRegion("oceanFloor");
        barrelsFloor = atlasTileset.findRegion("barrelsFloor");
        campfireFloor = atlasTileset.findRegion("campfireFloor");
        crackedFloor = atlasTileset.findRegion("crackedFloor");
        decorativeFloor = atlasTileset.findRegion("decorativeFloor");
        emptyFloor = atlasTileset.findRegion("emptyFloor");
        grassFloor = atlasTileset.findRegion("grassFloor");
        oceanFloor = atlasTileset.findRegion("oceanFloor");
        plantsFloor = atlasTileset.findRegion("plantsFloor");
        puddleFloor = atlasTileset.findRegion("puddleFloor");
        tileFloor = atlasTileset.findRegion("tileFloor");
        woodFloor = atlasTileset.findRegion("woodFloor");

        boardCornerTopLeft = atlasTileset.findRegion("boardCornerTopLeft");
        boardCornerTopRight = atlasTileset.findRegion("boardCornerTopRight");
        boardCornerBottomLeft = atlasTileset.findRegion("boardCornerBottomLeft");
        boardCornerBottomRight = atlasTileset.findRegion("boardCornerBottomRight");
        boardLaneLeft = atlasTileset.findRegion("boardLaneLeft");
        boardLaneTop = atlasTileset.findRegion("boardLaneTop");
        boardLaneRight = atlasTileset.findRegion("boardLaneRight");
        boardLaneBottom = atlasTileset.findRegion("boardLaneBottom");

        cornerTopLeft = atlasTileset.findRegion("cornerTopLeft");
        cornerTopRight = atlasTileset.findRegion("cornerTopRight");
        cornerBottomLeft = atlasTileset.findRegion("cornerBottomLeft");
        cornerBottomRight = atlasTileset.findRegion("cornerBottomRight");
        laneLeft = atlasTileset.findRegion("laneLeft");
        laneTop = atlasTileset.findRegion("laneTop");
        laneRight = atlasTileset.findRegion("laneRight");
        laneBottom = atlasTileset.findRegion("laneBottom");
        rockWall = atlasTileset.findRegion("rockWall");

        blueBox = atlasTileset.findRegion("blueBox");
        yellowBox = atlasTileset.findRegion("yellowBox");
        greenBox = atlasTileset.findRegion("greenBox");
        redBox = atlasTileset.findRegion("redBox");
        greyBox = atlasTileset.findRegion("greyBox");
        brownBox = atlasTileset.findRegion("brownBox");

        greenTarget = atlasTileset.findRegion("greenTarget");
        redTarget = atlasTileset.findRegion("redTarget");
        whiteTarget = atlasTileset.findRegion("whiteTarget");

        control = atlasTileset.findRegion("control");
        controlFunction = atlasTileset.findRegion("controlFunction");

        button = atlasTileset.findRegion("button");
        buttonVertical = atlasTileset.findRegion("buttonVertical");
        buttonFunction = atlasTileset.findRegion("buttonFunction");
        buttonFunctionVertical = atlasTileset.findRegion("buttonFunctionVertical");

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
