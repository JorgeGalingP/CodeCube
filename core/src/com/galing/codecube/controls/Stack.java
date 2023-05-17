package com.galing.codecube.controls;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Array;
import com.galing.codecube.board.SpawnManager;
import com.galing.codecube.enums.ContainerType;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Container;

public class Stack extends Control<java.util.Stack<Box>> {

    public Stack(SpawnManager spawnManager,
                 Button programButton,
                 Button functionButton,
                 Array<Container> programControls,
                 Array<Container> functionControls) {
        super(spawnManager, programButton, functionButton, programControls, functionControls);
        setProgram(new java.util.Stack<>());
        setFunction(new java.util.Stack<>());
        setHolder(new java.util.Stack<>());
    }

    @Override
    public Box getNextBox() {
        return this.getProgram().peek();
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

        setProgram(new java.util.Stack<>());
        setFunction(new java.util.Stack<>());
        setHolder(new java.util.Stack<>());
    }

    @Override
    public void addToProgram(Box box) {
        // add box to program logically
        pushToProgram(box);
        getProgram().push(box);

        // add box to function container
        Vector2 newPosition = getProgramControls().get(getProgramSize() - 1).getCoordinate();
        addBounceAction(box, newPosition);
    }

    @Override
    public void addToFunction(Box box) {
        // add box to function logically
        pushToFunction(box);
        getFunction().add(box);

        // add box to function container
        Vector2 newPosition = getFunctionControls().get(getFunctionSize() - 1).getCoordinate();
        addBounceAction(box, newPosition);
    }

    @Override
    public void kill(Box box) {
        if (box.getControlType().equals(ContainerType.PROGRAM)) {
            getProgram().pop();

            handleProgramTouchable();
        } else if (box.getControlType().equals(ContainerType.FUNCTION)) {
            getFunction().pop();

            handleFunctionTouchable();
        }

        // kill box
        box.setAlive(false);
    }

    @Override
    public Box removeFromProgram() {
        Box box = getProgram().pop();
        handleProgramTouchable();

        return box;
    }

    @Override
    public Box removeFromFunction() {
        Box box = getHolder().pop();
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
        java.util.Stack<Box> newStack = new java.util.Stack<>();
        newStack.addAll(getFunction());

        setHolder(newStack);
    }

    @Override
    public void resetFunction() {
        setFunction(new java.util.Stack<>());
    }

    private void addBounceAction(Box box, Vector2 position) {
        MoveToAction action = Actions.action(MoveToAction.class);
        action.setPosition(position.x, position.y);
        action.setDuration(.75f);
        action.setInterpolation(Interpolation.bounceOut);

        box.addAction(action);
    }
}
