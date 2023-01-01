package com.galing.codecube.controls;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.galing.codecube.enums.ContainerType;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Container;

import java.util.ArrayDeque;

public class Queue extends Control<ArrayDeque<Box>> {
    public Queue(Button programButton, Button functionButton, Array<Container> programControls,
                 Array<Container> functionControls) {
        super(programButton, functionButton, programControls, functionControls);
        setProgram(new ArrayDeque<>());
        setFunction(new ArrayDeque<>());
    }

    @Override
    public Box getNextBox() {
        return this.getProgram().getLast();
    }

    @Override
    public void addToProgram(Box box) {
        if (!isProgramEmpty())
            for (Box programBox : this.getProgram())
                programBox.setNext(false);

        box.setNext(true);
        getProgram().addLast(box);

        box.setControlType(ContainerType.PROGRAM);
        box.setPushedIdle();

        // TODO
        Vector2 newPosition = getProgramControls().get(getProgramSize() - 1).getCoordinate();
        box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));
    }

    @Override
    public void addToFunction(Box box) {
        if (!isFunctionEmpty())
            for (Box programBox : this.getFunction())
                programBox.setNext(false);

        box.setNext(true);
        getFunction().addLast(box);

        box.setControlType(ContainerType.FUNCTION);
        box.setPushedIdle();

        // TODO
        Vector2 newPosition = getFunctionControls().get(getFunctionSize() - 1).getCoordinate();
        box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));
    }

    @Override
    public void remove(Box box) {
        if (box.getControlType().equals(ContainerType.PROGRAM)) {
            getProgram().remove(box);
            if (getProgramSize() > 0)
                getProgram().getLast().setNext(true);
        } else if (box.getControlType().equals(ContainerType.FUNCTION)) {
            getFunction().remove(box);
            if (getFunctionSize() > 0)
                getFunction().getLast().setNext(true);
        }

        // back to start
        box.clearControl();
        box.addResetPositionAction();
    }

    @Override
    public Box removeFromProgram() {
        return getProgram().removeLast();
    }

    @Override
    public Box removeFromFunction() {
        return getFunction().removeLast();
    }
}
