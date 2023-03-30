package com.galing.codecube.enums;

import com.galing.codecube.Assets;

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
                type = Assets.selectString("BoardType_List");
                break;
            case STACK:
                type = Assets.selectString("BoardType_Stack");
                break;
            case QUEUE:
                type = Assets.selectString("BoardType_Queue");
                break;
        }

        return type;
    }
}

