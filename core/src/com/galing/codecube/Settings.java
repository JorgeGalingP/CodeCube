package com.galing.codecube;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.galing.codecube.enums.Difficulty;

public class Settings {

    public static Difficulty selectedDifficulty;
    public static String audio;
    public static String music;

    private final static Preferences preferences = Gdx.app.getPreferences("com.galing.codecube");

    public static void load() {
        selectedDifficulty = Difficulty.valueOf(preferences.getString("selectedDifficulty", "EASY"));
        audio = preferences.getString("activeAudio", "ON");
        music = preferences.getString("activeMusic", "ON");
    }

    public static void save() {
        preferences.putString("selectedDifficulty", String.valueOf(selectedDifficulty));
        preferences.putString("activeSounds", audio);
        preferences.putString("activeMusic", music);
        preferences.flush();
    }

    public static void modifyDifficulty(Difficulty difficulty) {
        Settings.selectedDifficulty = difficulty;
        Settings.save();
    }

    public static void switchAudio() {
        Settings.audio = Settings.audio.equals("ON") ? "OFF" : "ON";
        Settings.save();
    }

    public static void switchMusic() {
        Settings.music = Settings.music.equals("ON") ? "OFF" : "ON";
        Settings.save();
    }
}
