package com.galing.codecube.controls;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.galing.codecube.board.SpawnManager;
import com.galing.codecube.enums.ContainerType;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Container;

import java.util.ArrayList;
import java.util.List;

public class Sequence extends Control<List<Box>> {
    public Sequence(SpawnManager spawnManager,
                    Button programButton,
                    Button functionButton,
                    Array<Container> programControls,
                    Array<Container> functionControls) {
        super(spawnManager, programButton, functionButton, programControls, functionControls);
        setProgram(new ArrayList<>());
        setFunction(new ArrayList<>());
        setHolder(new ArrayList<>());
    }

    @Override
    public Box getNextBox() {
        return this.getProgram().get(0);
    }

    @Override
    public void addToProgram(Box box) {
        // add box to program logically
        pushToProgram(box);
        getProgram().add(box);

        // add box to program container
        Vector2 newPosition = getProgramControls().get(getProgramSize() - 1).getCoordinate();
        box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));
    }

    @Override
    public void addToFunction(Box box) {
        // add box to function logically
        pushToFunction(box);
        getFunction().add(box);

        // add box to function container
        Vector2 newPosition = getFunctionControls().get(getFunctionSize() - 1).getCoordinate();
        box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));
    }

    @Override
    public void remove(Box box) {
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
        Box box = getProgram().remove(0);
        handleProgramTouchable();

        return box;
    }

    @Override
    public Box removeFromFunction() {
        Box box = getHolder().remove(0);
        handleFunctionTouchable();

        return box;
    }

    @Override
    public void handleProgramTouchable() {
        if (getProgramSize() > 0)
            getProgram().get(getProgramSize() - 1).setIsTouchable(true);
    }

    @Override
    public void handleFunctionTouchable() {
        if (getFunctionSize() > 0)
            getFunction().get(getFunctionSize() - 1).setIsTouchable(true);
    }

    @Override
    public void copyFunction() {
        setHolder(new ArrayList<>(getFunction()));
    }

    @Override
    public void resetFunction() {
        setFunction(new ArrayList<>());
    }
}
