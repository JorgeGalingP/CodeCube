package com.galing.codecube.controls;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

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
        setFunction1(new ArrayDeque<>());
        setFunction2(new ArrayDeque<>());
    }

    @Override
    public Box getNextBox() {
        return this.getProgram().getFirst();
    }

    @Override
    public void addToProgram(Box box) {
        if (!isProgramEmpty())
            for (Box programBox : this.getProgram())
                programBox.setIsTouchable(false);

        box.setIsTouchable(true);
        getProgram().addLast(box);

        box.setControlType(ContainerType.PROGRAM);
        box.setPushedIdle();

        Vector2 newPosition =
                getProgramControls().get(getProgramControls().size - getProgramSize()).getCoordinate();
        box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));
    }

    @Override
    public void addToFunction(Box box) {
        if (!isFunctionEmpty())
            for (Box programBox : this.getFunction())
                programBox.setIsTouchable(false);

        box.setIsTouchable(true);
        getFunction().addLast(box);

        box.setControlType(ContainerType.FUNCTION);
        box.setPushedIdle();

        Vector2 newPosition =
                getFunctionControls().get(getFunctionControls().size - getFunctionSize()).getCoordinate();
        box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));
    }

    @Override
    public void remove(Box box) {
        if (box.getControlType().equals(ContainerType.PROGRAM)) {
            getProgram().remove(box);
            if (getProgramSize() > 0)
                getProgram().getLast().setIsTouchable(true);
        } else if (box.getControlType().equals(ContainerType.FUNCTION)) {
            getFunction().remove(box);
            if (getFunctionSize() > 0)
                getFunction().getLast().setIsTouchable(true);
        }

        // back to start
        box.clearControl();
        box.addResetPositionAction();
    }

    @Override
    public Box removeFromProgram() {
        Box box = getProgram().removeFirst();
        if (getProgramSize() > 0)
            getProgram().forEach(x -> x.addAction(moveTo(x.getX() + 1, x.getY(), .3f)));

        return box;
    }

    @Override
    public Box removeFromFunction() {
        Box box = getFunction().removeFirst();
        if (getFunctionSize() > 0)
            getFunction().forEach(x -> x.addAction(moveTo(x.getX() + 1, x.getY(), .3f)));

        return box;
    }
}
