package com.galing.codecube.controls;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Array;
import com.galing.codecube.enums.BoxType;
import com.galing.codecube.enums.ContainerType;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Container;

import java.util.ArrayList;

public class Stack extends Control<java.util.Stack<Box>> {

    public Stack(Button programButton, Button functionButton, Array<Container> programControls,
                 Array<Container> functionControls) {
        super(programButton, functionButton, programControls, functionControls);
        setProgram(new java.util.Stack<>());
        setFunction(new ArrayList<>());
        getFunction().add(new java.util.Stack<>());
    }

    @Override
    public Box getNextBox() {
        return this.getProgram().peek();
    }

    @Override
    public void addToProgram(Box box) {
        if (!isProgramEmpty())
            for (Box programBox : this.getProgram())
                programBox.setIsTouchable(false);

        box.setIsTouchable(true);
        getProgram().push(box);

        box.setControlType(ContainerType.PROGRAM);
        box.setPushedIdle();

        Vector2 newPosition = getProgramControls().get(getProgramSize() - 1).getCoordinate();
        MoveToAction action = Actions.action(MoveToAction.class);
        action.setPosition(newPosition.x, newPosition.y);
        action.setDuration(.75f);
        action.setInterpolation(Interpolation.bounceOut);

        box.addAction(action);
    }

    @Override
    public void addToFunction(Box box) {
        if (!isFunctionEmpty())
            getFunction().forEach(f -> f.forEach(b -> b.setIsTouchable(false)));

        box.setIsTouchable(true);
        getFunction().forEach(f -> f.push(box));

        box.setControlType(ContainerType.FUNCTION);
        box.setPushedIdle();

        Vector2 newPosition = getFunctionControls().get(getFunctionSize() - 1).getCoordinate();

        MoveToAction action = Actions.action(MoveToAction.class);
        action.setPosition(newPosition.x, newPosition.y);
        action.setDuration(.75f);
        action.setInterpolation(Interpolation.bounceOut);

        box.addAction(action);
    }

    @Override
    public void remove(Box box) {
        if (box.getControlType().equals(ContainerType.PROGRAM)) {
            getProgram().pop();
            if (getProgramSize() > 0)
                getProgram().get(getProgramSize() - 1).setIsTouchable(true);
        } else if (box.getControlType().equals(ContainerType.FUNCTION)) {
            getFunction().forEach(java.util.Stack::pop);

            if (getFunctionSize() > 0)
                getFunction().get(0).get(getFunctionSize() - 1).setIsTouchable(true);
        }

        // back to start
        box.addResetPositionAction();
    }

    @Override
    public Box removeFromProgram() {
        return getProgram().pop();
    }

    @Override
    public Box removeFromFunction() {
        int count =
                (int) this.getProgram().stream().filter(box -> box.getType().equals(BoxType.FUNCTION)).count();
        return getFunction().get(count - 1).pop();
    }
}
