package com.galing.codecube.controls;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.galing.codecube.board.SpawnManager;
import com.galing.codecube.enums.BoxType;
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
    public void addToProgram(Box box) {
        pushToProgram(box);

        getProgram().addLast(box);

        Vector2 newPosition =
                getProgramControls().get(getProgramControls().size - getProgramSize()).getCoordinate();
        box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));

        /*if (box.getType().equals(BoxType.FUNCTION)
                && numberOfFunctions() != getFunction().size()) {
              getFunction().add(new ArrayDeque<>(getFunction().get(0)));
        }*/
    }

    @Override
    public void addToFunction(Box box) {
        pushToFunction(box);

        //getFunction().forEach(f -> f.addLast(box));

        Vector2 newPosition =
                getFunctionControls().get(getFunctionControls().size - getFunctionSize()).getCoordinate();
        box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));
    }

    @Override
    public void remove(Box box) {
        if (box.getControlType().equals(ContainerType.PROGRAM)) {
            getProgram().remove(box);

            handleProgramTouchable();
            //removeFunctionMethod(box);
        } else if (box.getControlType().equals(ContainerType.FUNCTION)) {
            //getFunction().forEach(function -> function.remove(box));

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

        //removeFunctionMethod(box);
        handleProgramTouchable();

        return box;
    }

    @Override
    public Box removeFromFunction() {
        int count =
                (int) this.getProgram().stream().filter(box -> box.getType().equals(BoxType.FUNCTION)).count();
        Box box = null;// = getFunction().get(count - 1).removeFirst();

        //if (getFunctionSize() > 0 && !hasSeveralFunctions())
        //getFunction().forEach(f -> f.forEach(this::addSlideAction));

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
        //if (getFunctionSize() > 0)
        //getFunction().get(0).getLast().setIsTouchable(true);
    }

    @Override
    public void generateHolder() {

    }

    private void addSlideAction(Box box) {
        box.addAction(moveTo(box.getX() + 1, box.getY(), .3f));
    }
}