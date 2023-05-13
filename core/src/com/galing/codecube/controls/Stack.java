package com.galing.codecube.controls;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Array;
import com.galing.codecube.board.SpawnManager;
import com.galing.codecube.enums.BoxType;
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
    public void addToProgram(Box box) {
        pushToProgram(box);
        getProgram().push(box);

        Vector2 newPosition = getProgramControls().get(getProgramSize() - 1).getCoordinate();
        addBounceAction(box, newPosition);
    }

    @Override
    public void addToFunction(Box box) {
        pushToFunction(box);

        //getFunction().forEach(f -> f.push(box));

        Vector2 newPosition = getFunctionControls().get(getFunctionSize() - 1).getCoordinate();
        addBounceAction(box, newPosition);
    }

    @Override
    public void remove(Box box) {
        if (box.getControlType().equals(ContainerType.PROGRAM)) {
            getProgram().pop();

            handleProgramTouchable();
            //  removeFunctionMethod(box);
        } else if (box.getControlType().equals(ContainerType.FUNCTION)) {
            //getFunction().forEach(java.util.Stack::pop);

            handleFunctionTouchable();
        }

        // kill box
        box.setAlive(false);
    }

    @Override
    public Box removeFromProgram() {
        Box box = getProgram().pop();

        //removeFunctionMethod(box);
        handleProgramTouchable();

        return box;
    }

    @Override
    public Box removeFromFunction() {
        int count =
                (int) this.getProgram().stream().filter(box -> box.getType().equals(BoxType.FUNCTION)).count();

        Box box = null;//getFunction().get(count - 1).pop();
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
        //if (getFunctionSize() > 0)
        //getFunction().get(0).get(getFunctionSize() - 1).setIsTouchable(true);
    }

    @Override
    public void generateHolder() {

    }

    @Override
    public void resetFunction() {

    }

    private void addBounceAction(Box box, Vector2 position) {
        MoveToAction action = Actions.action(MoveToAction.class);
        action.setPosition(position.x, position.y);
        action.setDuration(.75f);
        action.setInterpolation(Interpolation.bounceOut);

        box.addAction(action);
    }
}
