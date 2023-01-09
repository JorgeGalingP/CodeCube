package com.galing.codecube;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.galing.codecube.enums.Difficulty;

public class Settings {

    public static Difficulty selectedDifficulty;

    private final static Preferences preferences = Gdx.app.getPreferences("com.galing.codecube");

    public static void load() {
        selectedDifficulty = Difficulty.EASY;
        //selectedDifficulty = pref.getBoolean("animationWalkIsON", false);
    }

    public static void save() {
        preferences.putString("selectedDifficulty", String.valueOf(selectedDifficulty));
        preferences.flush();

    }
}
