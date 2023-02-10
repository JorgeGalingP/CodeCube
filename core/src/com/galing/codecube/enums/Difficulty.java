package com.galing.codecube.enums;

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
                type = "Fácil";
                break;
            case NORMAL:
                type = "Normal";
                break;
            case HARD:
                type = "Difícil";
                break;
        }

        return type;
    }
}
