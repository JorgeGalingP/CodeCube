package com.galing.codecube.controls;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.galing.codecube.board.SpawnManager;
import com.galing.codecube.enums.ContainerType;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Container;

import java.util.ArrayDeque;

public class Queue extends Control<ArrayDeque<Box>> {
    public Queue(SpawnManager spawnManager,
                 Button programButton,
                 Button functionButton,
                 Array<Container> programControls,
                 Array<Container> functionControls) {
        super(spawnManager, programButton, functionButton, programControls, functionControls);
        setProgram(new ArrayDeque<>());
        setFunction(new ArrayDeque<>());
        setHolder(new ArrayDeque<>());
    }

    @Override
    public Box getNextBox() {
        return this.getProgram().getFirst();
    }

    @Override
    public void reset() {
        if (!isProgramEmpty())
            for (Box box : getProgram()) {
                box.setAlive(false);
            }

        if (!isFunctionEmpty())
            for (Box box : getFunction()) {
                box.setAlive(false);
            }

        setProgram(new ArrayDeque<>());
        setFunction(new ArrayDeque<>());
        setHolder(new ArrayDeque<>());
    }

    @Override
    public void addToProgram(Box box) {
        // add box to program logically
        pushToProgram(box);
        getProgram().addLast(box);

        // add box to program container
        Vector2 newPosition =
                getProgramControls().get(getProgramControls().size - getProgramSize()).getCoordinate();
        box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));
    }

    @Override
    public void addToFunction(Box box) {
        // add box to function logically
        pushToFunction(box);
        getFunction().add(box);

        // add box to function container
        Vector2 newPosition =
                getFunctionControls().get(getFunctionControls().size - getFunctionSize()).getCoordinate();
        box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));
    }

    @Override
    public void kill(Box box) {
        if (box.getControlType().equals(ContainerType.PROGRAM)) {
            getProgram().remove(box);

            handleProgramTouchable();
        } else if (box.getControlType().equals(ContainerType.FUNCTION)) {
            getFunction().remove(box);

            handleFunctionTouchable();
        }

        // kill box
        box.setAlive(false);
    }

    @Override
    public Box removeFromProgram() {
        Box box = getProgram().removeFirst();
        if (getProgramSize() > 0)
            getProgram().forEach(this::addSlideAction);

        handleProgramTouchable();

        return box;
    }

    @Override
    public Box removeFromFunction() {
        Box box = getHolder().removeFirst();

        if (getFunctionSize() > 0 && countFunction() == 1)
            getFunction().forEach(this::addSlideAction);

        handleFunctionTouchable();

        return box;
    }

    @Override
    public void handleProgramTouchable() {
        if (getProgramSize() > 0)
            getProgram().getLast().setIsTouchable(true);
    }

    @Override
    public void handleFunctionTouchable() {
        if (getFunctionSize() > 0)
            getFunction().getLast().setIsTouchable(true);
    }

    @Override
    public void copyFunction() {
        setHolder(new ArrayDeque<>(getFunction()));
    }

    @Override
    public void resetFunction() {
        setFunction(new ArrayDeque<>());
    }

    private void addSlideAction(Box box) {
        box.addAction(moveTo(box.getX() + 1, box.getY(), .3f));
    }
}