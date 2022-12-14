package com.galing.codecube.enums;

public enum BoardType {
    SEQUENCE("sequence"),
    STACK("stack"),
    QUEUE("queue");

    private final String type;

    BoardType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

