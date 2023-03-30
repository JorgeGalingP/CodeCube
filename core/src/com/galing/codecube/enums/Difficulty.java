package com.galing.codecube.enums;

import com.galing.codecube.Assets;

public enum Difficulty {
    EASY("easy"),
    NORMAL("normal"),
    HARD("hard");

    private final String type;

    Difficulty(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static String toString(Difficulty difficulty) {
        String type = "";
        switch (difficulty) {
            case EASY:
                type = Assets.selectString("Difficulty_Easy");
                break;
            case NORMAL:
                type = Assets.selectString("Difficulty_Normal");
                break;
            case HARD:
                type = Assets.selectString("Difficulty_Hard");
                break;
        }

        return type;
    }
}
