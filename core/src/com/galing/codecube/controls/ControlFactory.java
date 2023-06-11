package com.galing.codecube.controls;

import com.badlogic.gdx.utils.Array;
import com.galing.codecube.board.SpawnManager;
import com.galing.codecube.enums.BoardType;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Container;

import java.util.Collection;

public class ControlFactory {

    public static Control<? extends Collection<Box>> createControl(BoardType boardType,
                                                                   SpawnManager spawnManager,
                                                                   Button programButton,
                                                                   Button functionButton,
                                                                   Array<Container> programControls,
                                                                   Array<Container> functionControls) {
        switch (boardType) {
            case SEQUENCE:
                return BoardType.SEQUENCE.create(spawnManager, programButton, functionButton, programControls,
                        functionControls);
            case STACK:
                return BoardType.STACK.create(spawnManager, programButton, functionButton, programControls,
                        functionControls);
            case QUEUE:
                return BoardType.QUEUE.create(spawnManager, programButton, functionButton, programControls,
                        functionControls);
        }

        return null;
    }
}
