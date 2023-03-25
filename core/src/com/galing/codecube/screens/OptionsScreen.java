package com.galing.codecube.screens;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.galing.codecube.Assets;
import com.galing.codecube.CodeCube;
import com.galing.codecube.Settings;
import com.galing.codecube.enums.Difficulty;
import com.galing.codecube.enums.Language;

public class OptionsScreen extends Screen {

    private Table difficultyTable;
    private TextButton easyButton;
    private TextButton normalButton;
    private TextButton hardButton;
    private ImageButton musicButton;
    private ImageButton soundButton;
    private TextButton enLanguageButton;
    private TextButton esESLanguageButton;
    private Label difficultyLabel;
    private Label musicLabel;
    private Label audioLabel;
    private Label languageLabel;
    private Label configurationLabel;

    public OptionsScreen(CodeCube game) {
        super(game);
    }

    @Override
    public void update(float delta) {
        // reset styles
        this.easyButton.setStyle(Settings.selectedDifficulty.equals(Difficulty.EASY) ?
                Assets.vagaRoundBoldFontLargeSelectedButtonStyle : Assets.vagaRoundBoldFontLargeButtonStyle);
        this.normalButton.setStyle(Settings.selectedDifficulty.equals(Difficulty.NORMAL) ?
                Assets.vagaRoundBoldFontLargeSelectedButtonStyle : Assets.vagaRoundBoldFontLargeButtonStyle);
        this.hardButton.setStyle(Settings.selectedDifficulty.equals(Difficulty.HARD) ?
                Assets.vagaRoundBoldFontLargeSelectedButtonStyle : Assets.vagaRoundBoldFontLargeButtonStyle);
        this.musicButton.setStyle(Settings.music.equals("ON") ?
                Assets.musicOnButtonStyle : Assets.musicOffButtonStyle);
        this.soundButton.setStyle(Settings.audio.equals("ON") ?
                Assets.audioOnButtonStyle : Assets.audioOffButtonStyle);
        this.enLanguageButton.setStyle(Settings.selectedLanguage.equals(Language.EN) ?
                Assets.vagaRoundBoldFontLargeSelectedButtonStyle : Assets.vagaRoundBoldFontLargeButtonStyle);
        this.esESLanguageButton.setStyle(Settings.selectedLanguage.equals(Language.ES_ES) ?
                Assets.vagaRoundBoldFontLargeSelectedButtonStyle : Assets.vagaRoundBoldFontLargeButtonStyle);
    }

    @Override
    public void show() {
        // create tables
        difficultyTable = new Table();
        setDifficultyTableBounds();
        difficultyTable.top().padTop(50);
        difficultyTable.background(new NinePatchDrawable(new NinePatch(Assets.greyPanel,
                12, 12, 12, 12)));

        Table backTable = new Table();
        backTable.setFillParent(true);
        backTable.bottom();

        // create labels
        difficultyLabel = new Label(Assets.selectString("OptionsScreen_DifficultyLabel"),
                new Label.LabelStyle(Assets.vagaRoundBoldGray25, null));
        musicLabel = new Label(Assets.selectString("OptionsScreen_MusicLabel"),
                new Label.LabelStyle(Assets.vagaRoundBoldGray25, null));
        audioLabel = new Label(Assets.selectString("OptionsScreen_SoundLabel"),
                new Label.LabelStyle(Assets.vagaRoundBoldGray25, null));
        languageLabel = new Label(Assets.selectString("OptionsScreen_LanguageLabel"),
                new Label.LabelStyle(Assets.vagaRoundBoldGray25, null));
        configurationLabel = new Label(Assets.selectString("OptionsScreen_OptionsLabel"),
                new Label.LabelStyle(Assets.vagaRoundBoldGray35, null));

        // create back button
        ImageButton backButton = new ImageButton(Assets.exitLeftLargeButtonStyle);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();
                game.setScreen(new MenuScreen(game));
            }
        });

        // create buttons
        easyButton = new TextButton(Assets.selectString("OptionsScreen_EasyButton"),
                Assets.vagaRoundBoldFontLargeButtonStyle);
        normalButton = new TextButton(Assets.selectString("OptionsScreen_NormalButton"),
                Assets.vagaRoundBoldFontLargeButtonStyle);
        hardButton = new TextButton(Assets.selectString("OptionsScreen_HardButton"),
                Assets.vagaRoundBoldFontLargeButtonStyle);

        enLanguageButton = new TextButton(Language.toString(Language.EN),
                Assets.vagaRoundBoldFontLargeButtonStyle);
        esESLanguageButton = new TextButton(Language.toString(Language.ES_ES),
                Assets.vagaRoundBoldFontLargeButtonStyle);

        easyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();
                Settings.modifyDifficulty(Difficulty.EASY);
            }
        });
        normalButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();
                Settings.modifyDifficulty(Difficulty.NORMAL);
            }
        });
        hardButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();
                Settings.modifyDifficulty(Difficulty.HARD);
            }
        });

        enLanguageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();
                Settings.modifyLanguage(Language.EN);
                updateLanguage();
            }
        });
        esESLanguageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();
                Settings.modifyLanguage(Language.ES_ES);
                updateLanguage();
            }
        });

        musicButton = new ImageButton(Assets.musicOnButtonStyle);
        musicButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();
                Settings.switchMusic();
            }
        });

        soundButton = new ImageButton(Assets.audioOnButtonStyle);
        soundButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playClickSound();
                Settings.switchAudio();
            }
        });

        // add buttons and padding to tables
        difficultyTable.add(configurationLabel).colspan(4).center().padBottom(75);
        difficultyTable.row();
        difficultyTable.add(difficultyLabel).center().pad(10).padBottom(75);
        difficultyTable.add(easyButton).width(100).height(50).pad(10).padBottom(75);
        difficultyTable.add(normalButton).width(100).height(50).pad(10).padBottom(75);
        difficultyTable.add(hardButton).width(100).height(50).pad(10).padBottom(75);
        difficultyTable.row();
        difficultyTable.add(musicLabel).colspan(2).center().pad(10).padBottom(75);
        difficultyTable.add(musicButton).colspan(2).width(100).height(50).pad(10).padBottom(75);
        difficultyTable.row();
        difficultyTable.add(audioLabel).colspan(2).center().pad(10).padBottom(75);
        difficultyTable.add(soundButton).colspan(2).width(100).height(50).pad(10).padBottom(75);
        difficultyTable.row();
        difficultyTable.add(languageLabel).colspan(2).center().pad(10).padBottom(75);
        difficultyTable.add(enLanguageButton).colspan(1).width(100).height(50).pad(10).padBottom(75);
        difficultyTable.add(esESLanguageButton).colspan(1).width(100).height(50).pad(10).padBottom(75);
        difficultyTable.row();

        backTable.add(backButton).width(450).height(100).pad(125);
        backTable.row();

        // add difficultyTable to stage
        stage.addActor(difficultyTable);
        stage.addActor(backTable);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        setDifficultyTableBounds();
    }

    private void setDifficultyTableBounds() {
        difficultyTable.setPosition(stage.getWidth() / 2 - (stage.getWidth() * 0.8f) / 2,
                stage.getHeight() / 1.65f - (stage.getHeight() * 0.7f) / 2);
        difficultyTable.setSize(stage.getWidth() * 0.8f,
                stage.getHeight() * 0.7f);
    }

    private void updateLanguage() {
        difficultyLabel.setText(Assets.selectString("OptionsScreen_DifficultyLabel"));
        musicLabel.setText(Assets.selectString("OptionsScreen_MusicLabel"));
        audioLabel.setText(Assets.selectString("OptionsScreen_SoundLabel"));
        languageLabel.setText(Assets.selectString("OptionsScreen_LanguageLabel"));
        configurationLabel.setText(Assets.selectString("OptionsScreen_OptionsLabel"));

        easyButton.setText(Assets.selectString("OptionsScreen_EasyButton"));
        normalButton.setText(Assets.selectString("OptionsScreen_NormalButton"));
        hardButton.setText(Assets.selectString("OptionsScreen_HardButton"));
    }
}
