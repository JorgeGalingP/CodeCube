package com.galing.codecube.enums;

import com.badlogic.gdx.utils.Array;
import com.galing.codecube.Assets;
import com.galing.codecube.board.SpawnManager;
import com.galing.codecube.controls.Control;
import com.galing.codecube.controls.QueueControl;
import com.galing.codecube.controls.SequenceControl;
import com.galing.codecube.controls.StackControl;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Container;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;

/*public enum BoardType {
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
    }*/

public enum BoardType {
    SEQUENCE("sequence") {
        @Override
        public Control<List<Box>> create(SpawnManager spawnManager,
                                         Button programButton,
                                         Button functionButton,
                                         Array<Container> programControls,
                                         Array<Container> functionControls) {
            return new SequenceControl(spawnManager, programButton, functionButton, programControls,
                    functionControls);
        }
    },
    STACK("stack") {
        @Override
        public Control<java.util.Stack<Box>> create(SpawnManager spawnManager,
                                                    Button programButton,
                                                    Button functionButton,
                                                    Array<Container> programControls,
                                                    Array<Container> functionControls) {
            return new StackControl(spawnManager, programButton, functionButton, programControls, functionControls);
        }
    },
    QUEUE("queue") {
        @Override
        public Control<ArrayDeque<Box>> create(SpawnManager spawnManager,
                                               Button programButton,
                                               Button functionButton,
                                               Array<Container> programControls,
                                               Array<Container> functionControls) {
            return new QueueControl(spawnManager, programButton, functionButton, programControls, functionControls);
        }
    };

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

    public abstract Control<? extends Collection<Box>> create(SpawnManager spawnManager,
                                                              Button programButton,
                                                              Button functionButton,
                                                              Array<Container> programControls,
                                                              Array<Container> functionControls);
}

