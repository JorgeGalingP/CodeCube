package com.galing.codecube;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.galing.codecube.enums.BoardType;

public class AssetManager {
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

    public static void load() {
        // Atlas
        atlasTileset = new TextureAtlas(Gdx.files.internal("atlas/tileset.atlas"));
        atlasUI = new TextureAtlas(Gdx.files.internal("atlas/UI.atlas"));

        // Font
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter basicParameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        basicParameters.minFilter = Texture.TextureFilter.Linear;
        basicParameters.magFilter = Texture.TextureFilter.Linear;
        basicParameters.size = 50;
        basicParameters.borderColor = Color.BLACK;
        basicParameters.borderWidth = 1.5f;
        basicParameters.shadowColor = Color.BLACK;
        basicParameters.shadowOffsetX = 2;
        basicFont = fontGenerator.generateFont(basicParameters);

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

        // Buttons
        TextureRegionDrawable bluePressed = new TextureRegionDrawable(AssetManager.bluePressed);
        TextureRegionDrawable blueNoPressed = new TextureRegionDrawable(AssetManager.blueNoPressed);
        TextureRegionDrawable greenPressed = new TextureRegionDrawable(AssetManager.greenPressed);
        TextureRegionDrawable greenNoPressed = new TextureRegionDrawable(AssetManager.greenNoPressed);
        TextureRegionDrawable playIcon = new TextureRegionDrawable(AssetManager.playIcon);
        TextureRegionDrawable homeIcon = new TextureRegionDrawable(AssetManager.homeIcon);
        TextureRegionDrawable pauseIcon = new TextureRegionDrawable(AssetManager.pauseIcon);
        TextureRegionDrawable backIcon = new TextureRegionDrawable(AssetManager.backIcon);
        TextureRegionDrawable debugIcon = new TextureRegionDrawable(AssetManager.debugIcon);

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

        // Tileset
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

    public static void loadMap(BoardType type) {
        if (tileMap != null) {
            tileMap.dispose();
            tileMap = null;
        }

        tileMap = new TmxMapLoader().load("stages/" + type.getType() + "_" +
                Settings.selectedDifficulty.toString().toLowerCase() + ".tmx");
    }
}
