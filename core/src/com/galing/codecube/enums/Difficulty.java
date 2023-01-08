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
}
