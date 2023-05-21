package com.galing.codecube;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.I18NBundleLoader;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.galing.codecube.enums.BoardType;
import com.galing.codecube.enums.SoundType;

import java.util.Locale;

public class Assets {
    private final AssetManager manager;

    public static TiledMap tileMap;

    public static TextureAtlas atlasTileset;
    public static TextureAtlas atlasUI;
    public static TextureAtlas atlasConfetti;

    public static I18NBundle selectedBundle;
    public static I18NBundle bundleEn;
    public static I18NBundle bundleEsES;

    public static ParticleEffect confettiParticle;

    public static BitmapFont vagaRoundBoldWhite25;
    public static BitmapFont vagaRoundBoldGray25;
    public static BitmapFont vagaRoundBoldWhite30;
    public static BitmapFont vagaRoundBoldGray30;
    public static BitmapFont vagaRoundBoldWhite35;
    public static BitmapFont vagaRoundBoldGray35;

    public static Sound clickSound;
    public static Sound playerTapSound;
    public static Sound playerMovementSound;
    public static Sound playerTurnSound;
    public static Sound playerKillSound;
    public static Sound boxProgramAddSound;
    public static Sound boxFunctionAddSound;
    public static Sound boxKillSound;
    public static Music menuMusic;
    public static Music gameMusic;

    public static AtlasRegion bg;
    public static NinePatchDrawable bgNinePatch;
    public static NinePatchDrawable greyPanelNinePatch;

    public static AtlasRegion smallOutlineBlueButton;
    public static AtlasRegion largeOutlineBlueButton;
    public static AtlasRegion smallOutlineYellowButton;
    public static AtlasRegion largeOutlineYellowButton;

    public static AtlasRegion checkmarkBlueButton;
    public static AtlasRegion crossBlueButton;
    public static AtlasRegion redCircleButton;

    public static AtlasRegion smallBlueButton;
    public static AtlasRegion smallPressedBlueButton;
    public static AtlasRegion smallYellowButton;
    public static AtlasRegion smallPressedYellowButton;
    public static AtlasRegion smallGreenButton;
    public static AtlasRegion smallPressedGreenButton;
    public static AtlasRegion smallRedButton;
    public static AtlasRegion smallPressedRedButton;

    public static AtlasRegion largeBlueButton;
    public static AtlasRegion largePressedBlueButton;
    public static AtlasRegion largeYellowButton;
    public static AtlasRegion largePressedYellowButton;
    public static AtlasRegion largeGreenButton;
    public static AtlasRegion largePressedGreenButton;
    public static AtlasRegion largeRedButton;
    public static AtlasRegion largePressedRedButton;

    public static AtlasRegion greyCheckmarkIcon;
    public static AtlasRegion greyCrossIcon;
    public static AtlasRegion whiteCheckmarkIcon;
    public static AtlasRegion whiteCrossIcon;
    public static AtlasRegion exclamationIcon;
    public static AtlasRegion musicOffIcon;
    public static AtlasRegion musicOnIcon;
    public static AtlasRegion crossIcon;
    public static AtlasRegion questionIcon;
    public static AtlasRegion homeIcon;
    public static AtlasRegion shareIcon;
    public static AtlasRegion audioOnIcon;
    public static AtlasRegion audioOffIcon;
    public static AtlasRegion exitLeftIcon;
    public static AtlasRegion gearIcon;
    public static AtlasRegion menuListIcon;
    public static AtlasRegion returnIcon;
    public static AtlasRegion barsHorizontalIcon;
    public static AtlasRegion informationIcon;
    public static AtlasRegion checkmarkIcon;
    public static AtlasRegion rightIcon;
    public static AtlasRegion zoomIcon;
    public static AtlasRegion rewindIcon;

    public static TextButton.TextButtonStyle vagaRoundBoldFontLargeButtonStyle;
    public static TextButton.TextButtonStyle vagaRoundBoldFontLargeSelectedButtonStyle;
    public static ImageButton.ImageButtonStyle playButtonStyle;
    public static ImageButton.ImageButtonStyle homeButtonStyle;
    public static ImageButton.ImageButtonStyle pauseButtonStyle;
    public static ImageButton.ImageButtonStyle returnButtonStyle;
    public static ImageButton.ImageButtonStyle rewindButtonStyle;
    public static ImageButton.ImageButtonStyle debugButtonStyle;
    public static ImageButton.ImageButtonStyle musicOnButtonStyle;
    public static ImageButton.ImageButtonStyle musicOffButtonStyle;
    public static ImageButton.ImageButtonStyle audioOnButtonStyle;
    public static ImageButton.ImageButtonStyle audioOffButtonStyle;
    public static ImageButton.ImageButtonStyle exitLeftButtonStyle;
    public static ImageButton.ImageButtonStyle exitLeftLargeButtonStyle;
    public static ImageButton.ImageButtonStyle windowCloseButtonStyle;
    public static ImageButton.ImageButtonStyle crossButtonStyle;
    public static ImageButton.ImageButtonStyle checkmarkButtonStyle;

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

    public static AtlasRegion greenBox;
    public static AtlasRegion blueBox;
    public static AtlasRegion redBox;
    public static AtlasRegion yellowBox;
    public static AtlasRegion greyBox;
    public static AtlasRegion brownBox;

    public static AtlasRegion greenTarget;
    public static AtlasRegion redTarget;
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
        // load sounds
        manager.load("sounds/click.ogg", Sound.class);
        manager.load("sounds/player_tap.wav", Sound.class);
        manager.load("sounds/player_movement.wav", Sound.class);
        manager.load("sounds/player_turn.mp3", Sound.class);
        manager.load("sounds/player_kill.mp3", Sound.class);
        manager.load("sounds/box_program_add.mp3", Sound.class);
        manager.load("sounds/box_function_add.mp3", Sound.class);
        manager.load("sounds/box_kill.mp3", Sound.class);

        // load music
        manager.load("music/menu.mp3", Music.class);
        manager.load("music/play.mp3", Music.class);

        // load atlas
        manager.load("atlas/tileset.atlas", TextureAtlas.class);
        manager.load("atlas/UI.atlas", TextureAtlas.class);
        manager.load("atlas/confetti.atlas", TextureAtlas.class);

        // load particles
        ParticleEffectLoader.ParticleEffectParameter particleEffectParameter =
                new ParticleEffectLoader.ParticleEffectParameter();
        particleEffectParameter.atlasFile = "atlas/confetti.atlas";
        manager.load("particles/confetti.p", ParticleEffect.class, particleEffectParameter);

        // load translations
        I18NBundleLoader.I18NBundleParameter localeParams = new I18NBundleLoader.I18NBundleParameter(Locale.ENGLISH,
                "UTF-8");
        I18NBundleLoader.I18NBundleParameter localeParamsEs = new I18NBundleLoader.I18NBundleParameter(
                new Locale("es", "ES"), "ISO-8859-1");
        manager.load("i18n/bundle_en", I18NBundle.class, localeParams);
        manager.load("i18n/bundle_es_ES", I18NBundle.class, localeParamsEs);

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

        // generate vaga round bold font
        FreetypeFontLoader.FreeTypeFontLoaderParameter vagaRoundBoldWhite25Parameters =
                new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        vagaRoundBoldWhite25Parameters.fontFileName = "fonts/vagaRoundBold.ttf";
        vagaRoundBoldWhite25Parameters.fontParameters.minFilter = Texture.TextureFilter.Linear;
        vagaRoundBoldWhite25Parameters.fontParameters.magFilter = Texture.TextureFilter.Linear;
        vagaRoundBoldWhite25Parameters.fontParameters.size = 25;
        vagaRoundBoldWhite25Parameters.fontParameters.color = Color.WHITE;

        FreetypeFontLoader.FreeTypeFontLoaderParameter vagaRoundBoldGray25Parameters =
                new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        vagaRoundBoldGray25Parameters.fontFileName = "fonts/vagaRoundBold.ttf";
        vagaRoundBoldGray25Parameters.fontParameters.minFilter = Texture.TextureFilter.Linear;
        vagaRoundBoldGray25Parameters.fontParameters.magFilter = Texture.TextureFilter.Linear;
        vagaRoundBoldGray25Parameters.fontParameters.size = 25;
        vagaRoundBoldGray25Parameters.fontParameters.color = Color.GRAY;

        FreetypeFontLoader.FreeTypeFontLoaderParameter vagaRoundBoldWhite30Parameters =
                new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        vagaRoundBoldWhite30Parameters.fontFileName = "fonts/vagaRoundBold.ttf";
        vagaRoundBoldWhite30Parameters.fontParameters.minFilter = Texture.TextureFilter.Linear;
        vagaRoundBoldWhite30Parameters.fontParameters.magFilter = Texture.TextureFilter.Linear;
        vagaRoundBoldWhite30Parameters.fontParameters.size = 30;
        vagaRoundBoldWhite30Parameters.fontParameters.color = Color.WHITE;

        FreetypeFontLoader.FreeTypeFontLoaderParameter vagaRoundBoldGray30Parameters =
                new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        vagaRoundBoldGray30Parameters.fontFileName = "fonts/vagaRoundBold.ttf";
        vagaRoundBoldGray30Parameters.fontParameters.minFilter = Texture.TextureFilter.Linear;
        vagaRoundBoldGray30Parameters.fontParameters.magFilter = Texture.TextureFilter.Linear;
        vagaRoundBoldGray30Parameters.fontParameters.size = 30;
        vagaRoundBoldGray30Parameters.fontParameters.color = Color.GRAY;

        FreetypeFontLoader.FreeTypeFontLoaderParameter vagaRoundBoldWhite35Parameters =
                new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        vagaRoundBoldWhite35Parameters.fontFileName = "fonts/vagaRoundBold.ttf";
        vagaRoundBoldWhite35Parameters.fontParameters.minFilter = Texture.TextureFilter.Linear;
        vagaRoundBoldWhite35Parameters.fontParameters.magFilter = Texture.TextureFilter.Linear;
        vagaRoundBoldWhite35Parameters.fontParameters.size = 35;
        vagaRoundBoldWhite35Parameters.fontParameters.color = Color.WHITE;

        FreetypeFontLoader.FreeTypeFontLoaderParameter vagaRoundBoldGray35Parameters =
                new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        vagaRoundBoldGray35Parameters.fontFileName = "fonts/vagaRoundBold.ttf";
        vagaRoundBoldGray35Parameters.fontParameters.minFilter = Texture.TextureFilter.Linear;
        vagaRoundBoldGray35Parameters.fontParameters.magFilter = Texture.TextureFilter.Linear;
        vagaRoundBoldGray35Parameters.fontParameters.size = 35;
        vagaRoundBoldGray35Parameters.fontParameters.color = Color.GRAY;

        // load font
        manager.load("vagaRoundBoldWhite25.ttf", BitmapFont.class, vagaRoundBoldWhite25Parameters);
        manager.load("vagaRoundBoldGray25.ttf", BitmapFont.class, vagaRoundBoldGray25Parameters);
        manager.load("vagaRoundBoldWhite30.ttf", BitmapFont.class, vagaRoundBoldWhite30Parameters);
        manager.load("vagaRoundBoldGray30.ttf", BitmapFont.class, vagaRoundBoldGray30Parameters);
        manager.load("vagaRoundBoldWhite35.ttf", BitmapFont.class, vagaRoundBoldWhite35Parameters);
        manager.load("vagaRoundBoldGray35.ttf", BitmapFont.class, vagaRoundBoldGray35Parameters);
    }

    public void loadAssets() {
        // sounds
        clickSound = manager.get("sounds/click.ogg", Sound.class);
        playerTapSound = manager.get("sounds/player_tap.wav", Sound.class);
        playerMovementSound = manager.get("sounds/player_movement.wav", Sound.class);
        playerTurnSound = manager.get("sounds/player_turn.mp3", Sound.class);
        playerKillSound = manager.get("sounds/player_kill.mp3", Sound.class);
        boxProgramAddSound = manager.get("sounds/box_program_add.mp3", Sound.class);
        boxFunctionAddSound = manager.get("sounds/box_function_add.mp3", Sound.class);
        boxKillSound = manager.get("sounds/box_kill.mp3", Sound.class);

        // music
        menuMusic = manager.get("music/menu.mp3", Music.class);
        menuMusic.setLooping(true);
        menuMusic.setVolume(.25f);
        gameMusic = manager.get("music/play.mp3", Music.class);
        gameMusic.setLooping(true);
        gameMusic.setVolume(.25f);

        // atlas
        atlasTileset = manager.get("atlas/tileset.atlas", TextureAtlas.class);
        atlasUI = manager.get("atlas/UI.atlas", TextureAtlas.class);
        atlasConfetti = manager.get("atlas/confetti.atlas", TextureAtlas.class);

        // translations
        bundleEn = manager.get("i18n/bundle_en", I18NBundle.class);
        bundleEsES = manager.get("i18n/bundle_es_ES", I18NBundle.class);
        selectedBundle = bundleEn;

        // particles
        confettiParticle = manager.get("particles/confetti.p", ParticleEffect.class);

        // fonts
        vagaRoundBoldWhite25 = manager.get("vagaRoundBoldWhite25.ttf", BitmapFont.class);
        vagaRoundBoldGray25 = manager.get("vagaRoundBoldGray25.ttf", BitmapFont.class);
        vagaRoundBoldWhite30 = manager.get("vagaRoundBoldWhite30.ttf", BitmapFont.class);
        vagaRoundBoldGray30 = manager.get("vagaRoundBoldGray30.ttf", BitmapFont.class);
        vagaRoundBoldWhite35 = manager.get("vagaRoundBoldWhite35.ttf", BitmapFont.class);
        vagaRoundBoldGray35 = manager.get("vagaRoundBoldGray35.ttf", BitmapFont.class);

        // UI
        bg = atlasUI.findRegion("background");
        bgNinePatch = new NinePatchDrawable(new NinePatch(Assets.bg,
                1, 1, 1, 1));
        greyPanelNinePatch = new NinePatchDrawable(new NinePatch(atlasUI.findRegion("greyPanel"),
                12, 12, 12, 12));

        // buttons
        smallOutlineBlueButton = atlasUI.findRegion("smallOutlineBlueButton");
        largeOutlineBlueButton = atlasUI.findRegion("largeOutlineBlueButton");
        smallOutlineYellowButton = atlasUI.findRegion("smallOutlineYellowButton");
        largeOutlineYellowButton = atlasUI.findRegion("largeOutlineYellowButton");

        checkmarkBlueButton = atlasUI.findRegion("blueBoxCheckmark");
        crossBlueButton = atlasUI.findRegion("blueBoxCross");
        redCircleButton = atlasUI.findRegion("redCircle");

        smallBlueButton = atlasUI.findRegion("smallBlueButton");
        smallPressedBlueButton = atlasUI.findRegion("smallPressedBlueButton");
        smallYellowButton = atlasUI.findRegion("smallYellowButton");
        smallPressedYellowButton = atlasUI.findRegion("smallPressedYellowButton");
        smallGreenButton = atlasUI.findRegion("smallGreenButton");
        smallPressedGreenButton = atlasUI.findRegion("smallPressedGreenButton");
        smallRedButton = atlasUI.findRegion("smallRedButton");
        smallPressedRedButton = atlasUI.findRegion("smallPressedRedButton");

        largeBlueButton = atlasUI.findRegion("largeBlueButton");
        largePressedBlueButton = atlasUI.findRegion("largePressedBlueButton");
        largeYellowButton = atlasUI.findRegion("largeYellowButton");
        largePressedYellowButton = atlasUI.findRegion("largePressedYellowButton");
        largeGreenButton = atlasUI.findRegion("largeGreenButton");
        largePressedGreenButton = atlasUI.findRegion("largePressedGreenButton");
        largeRedButton = atlasUI.findRegion("largeRedButton");
        largePressedRedButton = atlasUI.findRegion("largePressedRedButton");

        // icons
        greyCheckmarkIcon = atlasUI.findRegion("greyCheckmarkGrey");
        greyCrossIcon = atlasUI.findRegion("greyCrossGrey");
        whiteCheckmarkIcon = atlasUI.findRegion("greyCheckmarkWhite");
        whiteCrossIcon = atlasUI.findRegion("greyCrossWhite");

        exclamationIcon = atlasUI.findRegion("exclamation");
        musicOffIcon = atlasUI.findRegion("musicOff");
        musicOnIcon = atlasUI.findRegion("musicOn");
        crossIcon = atlasUI.findRegion("cross");
        questionIcon = atlasUI.findRegion("question");
        homeIcon = atlasUI.findRegion("home");
        shareIcon = atlasUI.findRegion("share");
        audioOnIcon = atlasUI.findRegion("audioOn");
        audioOffIcon = atlasUI.findRegion("audioOff");
        exitLeftIcon = atlasUI.findRegion("exitLeft");
        gearIcon = atlasUI.findRegion("gear");
        menuListIcon = atlasUI.findRegion("menuList");
        returnIcon = atlasUI.findRegion("return");
        barsHorizontalIcon = atlasUI.findRegion("barsHorizontal");
        informationIcon = atlasUI.findRegion("information");
        checkmarkIcon = atlasUI.findRegion("checkmark");
        rightIcon = atlasUI.findRegion("right");
        zoomIcon = atlasUI.findRegion("zoom");
        rewindIcon = atlasUI.findRegion("rewind");

        // buttons
        TextureRegionDrawable smallBlueButton = new TextureRegionDrawable(Assets.smallBlueButton);
        TextureRegionDrawable smallPressedBlueButton = new TextureRegionDrawable(Assets.smallPressedBlueButton);
        TextureRegionDrawable smallGreenButton = new TextureRegionDrawable(Assets.smallGreenButton);
        TextureRegionDrawable smallPressedGreenButton = new TextureRegionDrawable(Assets.smallPressedGreenButton);
        TextureRegionDrawable smallYellowButton = new TextureRegionDrawable(Assets.smallYellowButton);
        TextureRegionDrawable smallPressedYellowButton = new TextureRegionDrawable(Assets.smallPressedYellowButton);
        TextureRegionDrawable smallRedButton = new TextureRegionDrawable(Assets.smallRedButton);
        TextureRegionDrawable smallPressedRedButton = new TextureRegionDrawable(Assets.smallPressedRedButton);

        TextureRegionDrawable largeBlueButton = new TextureRegionDrawable(Assets.largeBlueButton);
        TextureRegionDrawable largePressedBlueButton = new TextureRegionDrawable(Assets.largePressedBlueButton);
        TextureRegionDrawable largeGreenButton = new TextureRegionDrawable(Assets.largeGreenButton);
        TextureRegionDrawable largePressedGreenButton = new TextureRegionDrawable(Assets.largePressedGreenButton);
        TextureRegionDrawable largeYellowButton = new TextureRegionDrawable(Assets.largeYellowButton);
        TextureRegionDrawable largePressedYellowButton = new TextureRegionDrawable(Assets.largePressedYellowButton);
        TextureRegionDrawable largeRedButton = new TextureRegionDrawable(Assets.largeRedButton);
        TextureRegionDrawable largePressedRedButton = new TextureRegionDrawable(Assets.largePressedRedButton);

        // icons
        TextureRegionDrawable greyCheckmarkIcon = new TextureRegionDrawable(Assets.greyCheckmarkIcon);
        TextureRegionDrawable greyCrossIcon = new TextureRegionDrawable(Assets.greyCrossIcon);
        TextureRegionDrawable whiteCheckmarkIcon = new TextureRegionDrawable(Assets.whiteCheckmarkIcon);
        TextureRegionDrawable whiteCrossIcon = new TextureRegionDrawable(Assets.whiteCrossIcon);
        TextureRegionDrawable exclamationIcon = new TextureRegionDrawable(Assets.exclamationIcon);
        TextureRegionDrawable musicOffIcon = new TextureRegionDrawable(Assets.musicOffIcon);
        TextureRegionDrawable musicOnIcon = new TextureRegionDrawable(Assets.musicOnIcon);
        TextureRegionDrawable crossIcon = new TextureRegionDrawable(Assets.crossIcon);
        TextureRegionDrawable questionIcon = new TextureRegionDrawable(Assets.questionIcon);
        TextureRegionDrawable homeIcon = new TextureRegionDrawable(Assets.homeIcon);
        TextureRegionDrawable shareIcon = new TextureRegionDrawable(Assets.shareIcon);
        TextureRegionDrawable audioOnIcon = new TextureRegionDrawable(Assets.audioOnIcon);
        TextureRegionDrawable audioOffIcon = new TextureRegionDrawable(Assets.audioOffIcon);
        TextureRegionDrawable exitLeftIcon = new TextureRegionDrawable(Assets.exitLeftIcon);
        TextureRegionDrawable gearIcon = new TextureRegionDrawable(Assets.gearIcon);
        TextureRegionDrawable menuListIcon = new TextureRegionDrawable(Assets.menuListIcon);
        TextureRegionDrawable returnIcon = new TextureRegionDrawable(Assets.returnIcon);
        TextureRegionDrawable barsHorizontalIcon = new TextureRegionDrawable(Assets.barsHorizontalIcon);
        TextureRegionDrawable informationIcon = new TextureRegionDrawable(Assets.informationIcon);
        TextureRegionDrawable checkmarkIcon = new TextureRegionDrawable(Assets.checkmarkIcon);
        TextureRegionDrawable rightIcon = new TextureRegionDrawable(Assets.rightIcon);
        TextureRegionDrawable zoomIcon = new TextureRegionDrawable(Assets.zoomIcon);
        TextureRegionDrawable rewindIcon = new TextureRegionDrawable(Assets.rewindIcon);

        // button styles
        NinePatchDrawable redCircleButtonNinePatch = new NinePatchDrawable(new NinePatch(Assets.redCircleButton,
                8, 8, 8, 8));
        NinePatchDrawable smallBlueButtonNinePatch = new NinePatchDrawable(new NinePatch(Assets.smallBlueButton,
                12, 12, 12, 12));
        NinePatchDrawable smallPressedBlueButtonNinePatch =
                new NinePatchDrawable(new NinePatch(Assets.smallPressedBlueButton, 12, 12, 12, 12));
        NinePatchDrawable largeBlueButtonNinePatch = new NinePatchDrawable(new NinePatch(Assets.largeBlueButton,
                12, 12, 12, 12));
        NinePatchDrawable largePressedBlueButtonNinePatch =
                new NinePatchDrawable(new NinePatch(Assets.largePressedBlueButton, 12, 12, 12, 12));

        NinePatchDrawable smallGreenButtonNinePatch = new NinePatchDrawable(new NinePatch(Assets.smallGreenButton,
                12, 12, 12, 12));
        NinePatchDrawable smallPressedGreenButtonNinePatch =
                new NinePatchDrawable(new NinePatch(Assets.smallPressedGreenButton, 12, 12, 12, 12));
        NinePatchDrawable largeGreenButtonNinePatch = new NinePatchDrawable(new NinePatch(Assets.largeGreenButton,
                12, 12, 12, 12));
        NinePatchDrawable largePressedGreenButtonNinePatch =
                new NinePatchDrawable(new NinePatch(Assets.largePressedGreenButton, 12, 12, 12, 12));

        NinePatchDrawable smallYellowButtonNinePatch = new NinePatchDrawable(new NinePatch(Assets.smallYellowButton,
                12, 12, 12, 12));
        NinePatchDrawable smallPressedYellowButtonNinePatch =
                new NinePatchDrawable(new NinePatch(Assets.smallPressedYellowButton, 12, 12, 12, 12));
        NinePatchDrawable largeYellowButtonNinePatch = new NinePatchDrawable(new NinePatch(Assets.largeYellowButton,
                12, 12, 12, 12));
        NinePatchDrawable largePressedYellowButtonNinePatch =
                new NinePatchDrawable(new NinePatch(Assets.largePressedYellowButton, 12, 12, 12, 12));

        NinePatchDrawable smallRedButtonNinePatch = new NinePatchDrawable(new NinePatch(Assets.smallRedButton,
                12, 12, 12, 12));
        NinePatchDrawable smallPressedRedButtonNinePatch =
                new NinePatchDrawable(new NinePatch(Assets.smallPressedRedButton, 12, 12, 12, 12));
        NinePatchDrawable largeRedButtonNinePatch = new NinePatchDrawable(new NinePatch(Assets.largeRedButton,
                12, 12, 12, 12));
        NinePatchDrawable largePressedRedButtonNinePatch =
                new NinePatchDrawable(new NinePatch(Assets.largePressedRedButton, 12, 12, 12, 12));

        vagaRoundBoldFontLargeButtonStyle = new TextButton.TextButtonStyle(largeBlueButtonNinePatch,
                largePressedBlueButtonNinePatch, largeBlueButtonNinePatch, vagaRoundBoldWhite25);
        vagaRoundBoldFontLargeSelectedButtonStyle = new TextButton.TextButtonStyle(largeGreenButtonNinePatch,
                largePressedGreenButtonNinePatch, largeGreenButtonNinePatch, vagaRoundBoldWhite25);

        playButtonStyle = new ImageButton.ImageButtonStyle(largeYellowButtonNinePatch,
                largePressedYellowButtonNinePatch,
                largePressedYellowButtonNinePatch, rightIcon, rightIcon, rightIcon);
        homeButtonStyle = new ImageButton.ImageButtonStyle(smallBlueButtonNinePatch, smallPressedBlueButtonNinePatch,
                smallPressedBlueButtonNinePatch, homeIcon, homeIcon, homeIcon);
        pauseButtonStyle = new ImageButton.ImageButtonStyle(smallBlueButtonNinePatch, smallPressedBlueButtonNinePatch,
                smallBlueButtonNinePatch, barsHorizontalIcon, barsHorizontalIcon, barsHorizontalIcon);
        returnButtonStyle = new ImageButton.ImageButtonStyle(smallBlueButtonNinePatch, smallPressedBlueButtonNinePatch,
                smallBlueButtonNinePatch, returnIcon, returnIcon, returnIcon);
        rewindButtonStyle = new ImageButton.ImageButtonStyle(smallBlueButtonNinePatch, smallPressedBlueButtonNinePatch,
                smallBlueButtonNinePatch, rewindIcon, rewindIcon, rewindIcon);
        debugButtonStyle = new ImageButton.ImageButtonStyle(smallBlueButtonNinePatch, smallPressedGreenButtonNinePatch,
                smallPressedGreenButtonNinePatch, zoomIcon, zoomIcon, zoomIcon);
        musicOnButtonStyle = new ImageButton.ImageButtonStyle(smallBlueButtonNinePatch,
                smallPressedBlueButtonNinePatch,
                smallBlueButtonNinePatch, musicOnIcon, musicOnIcon, musicOnIcon);
        musicOffButtonStyle = new ImageButton.ImageButtonStyle(smallBlueButtonNinePatch,
                smallPressedBlueButtonNinePatch,
                smallBlueButtonNinePatch, musicOffIcon, musicOffIcon, musicOffIcon);
        audioOnButtonStyle = new ImageButton.ImageButtonStyle(smallBlueButtonNinePatch,
                smallPressedBlueButtonNinePatch,
                smallBlueButtonNinePatch, audioOnIcon, audioOnIcon, audioOnIcon);
        audioOffButtonStyle = new ImageButton.ImageButtonStyle(smallBlueButtonNinePatch,
                smallPressedBlueButtonNinePatch,
                smallBlueButtonNinePatch, audioOffIcon, audioOffIcon, audioOffIcon);
        exitLeftButtonStyle = new ImageButton.ImageButtonStyle(smallBlueButtonNinePatch,
                smallPressedBlueButtonNinePatch,
                smallBlueButtonNinePatch, exitLeftIcon, exitLeftIcon, exitLeftIcon);
        exitLeftLargeButtonStyle = new ImageButton.ImageButtonStyle(largeBlueButtonNinePatch,
                largePressedBlueButtonNinePatch,
                largePressedBlueButtonNinePatch, exitLeftIcon, exitLeftIcon, exitLeftIcon);
        windowCloseButtonStyle = new ImageButton.ImageButtonStyle(redCircleButtonNinePatch,
                redCircleButtonNinePatch,
                redCircleButtonNinePatch, crossIcon, crossIcon, crossIcon);
        crossButtonStyle = new ImageButton.ImageButtonStyle(smallRedButtonNinePatch, smallPressedBlueButtonNinePatch,
                smallBlueButtonNinePatch, crossIcon, crossIcon, crossIcon);
        checkmarkButtonStyle = new ImageButton.ImageButtonStyle(smallGreenButtonNinePatch,
                smallPressedBlueButtonNinePatch,
                smallBlueButtonNinePatch, checkmarkIcon, checkmarkIcon, checkmarkIcon);

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

        blueBox = atlasTileset.findRegion("blueBox");
        yellowBox = atlasTileset.findRegion("yellowBox");
        greenBox = atlasTileset.findRegion("greenBox");
        redBox = atlasTileset.findRegion("redBox");
        greyBox = atlasTileset.findRegion("greyBox");
        brownBox = atlasTileset.findRegion("brownBox");

        greenTarget = atlasTileset.findRegion("greenTarget");
        redTarget = atlasTileset.findRegion("redTarget");
        control = atlasTileset.findRegion("control");
        controlFunction = atlasTileset.findRegion("controlFunction");

        button = atlasTileset.findRegion("button");
        buttonVertical = atlasTileset.findRegion("buttonVertical");
        buttonFunction = atlasTileset.findRegion("buttonFunction");
        buttonFunctionVertical = atlasTileset.findRegion("buttonFunctionVertical");

        player = atlasTileset.findRegion("player");
    }

    public static void playSound(SoundType type) {
        if (Settings.audio.equals("ON")) {
            switch (type) {
                case ClickSound:
                    clickSound.play();
                    break;
                case PlayerTapSound:
                    playerTapSound.play(.5f);
                    break;
                case PlayerMovementSound:
                    playerMovementSound.play(.25f);
                    break;
                case PlayerTurnSound:
                    playerTurnSound.play(.25f);
                    break;
                case PlayerKillSound:
                    playerKillSound.play(.5f);
                    break;
                case BoxProgramAddSound:
                    boxProgramAddSound.play(.25f);
                    break;
                case BoxFunctionAddSound:
                    boxFunctionAddSound.play(.25f);
                    break;
                case BoxKillSound:
                    boxKillSound.play(.25f);
                    break;
            }
        }
    }

    public static void playMenuMusic() {
        if (Settings.music.equals("OFF"))
            menuMusic.pause();
        else if (Settings.music.equals("ON")
                && !menuMusic.isPlaying()) {
            if (gameMusic.isPlaying())
                gameMusic.pause();

            menuMusic.play();
        }
    }

    public static void playGameMusic() {
        if (Settings.music.equals("OFF"))
            gameMusic.pause();
        else if (Settings.music.equals("ON")
                && !gameMusic.isPlaying()) {
            if (menuMusic.isPlaying())
                menuMusic.pause();

            gameMusic.play();
        }
    }

    public void selectMap(BoardType type) {
        if (tileMap != null) {
            tileMap.dispose();
            tileMap = null;
        }

        tileMap = manager.get("stages/" + type.getType() + "_" +
                Settings.selectedDifficulty.toString().toLowerCase() + ".tmx", TiledMap.class);
    }

    private static void selectBundle() {
        switch (Settings.selectedLanguage) {
            case EN:
                selectedBundle = bundleEn;
                break;
            case ES_ES:
                selectedBundle = bundleEsES;
                break;
        }
    }

    public static String selectString(String key) {
        selectBundle();

        return selectedBundle.get(key);
    }

    public static String formatString(String key, Object... args) {
        selectBundle();

        return selectedBundle.format(key, args);
    }
}
