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

    public static String toString(BoardType boardType) {
        String type = "";
        switch (boardType) {
            case SEQUENCE:
                type = "Lista";
                break;
            case STACK:
                type = "Pila";
                break;
            case QUEUE:
                type = "Cola";
                break;
        }

        return type;
    }
}

