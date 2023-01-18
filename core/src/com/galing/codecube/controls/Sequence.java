package com.galing.codecube.controls;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.galing.codecube.enums.BoxType;
import com.galing.codecube.enums.ContainerType;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Container;

import java.util.ArrayList;
import java.util.List;

public class Sequence extends Control<List<Box>> {
    public Sequence(Button programButton, Button functionButton, Array<Container> programControls,
                    Array<Container> functionControls) {
        super(programButton, functionButton, programControls, functionControls);
        setProgram(new ArrayList<>());
        setFunction(new ArrayList<>());
        getFunction().add(new ArrayList<>());
    }

    @Override
    public Box getNextBox() {
        return this.getProgram().get(0);
    }

    @Override
    public void addToProgram(Box box) {
        pushToProgram(box);

        getProgram().add(box);

        Vector2 newPosition = getProgramControls().get(getProgramSize() - 1).getCoordinate();
        box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));

        if (box.getType().equals(BoxType.FUNCTION)
                && numberOfFunctions() != getFunction().size()) {
            getFunction().add(new ArrayList<>(getFunction().get(0)));
        }
    }

    @Override
    public void addToFunction(Box box) {
        pushToFunction(box);

        getFunction().forEach(f -> f.add(box));

        Vector2 newPosition = getFunctionControls().get(getFunctionSize() - 1).getCoordinate();
        box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));
    }

    @Override
    public void remove(Box box) {
        if (box.getControlType().equals(ContainerType.PROGRAM)) {
            getProgram().remove(box);
            if (getProgramSize() > 0)
                getProgram().get(getProgramSize() - 1).setIsTouchable(true);

            removeFunctionMethod(box);
        } else if (box.getControlType().equals(ContainerType.FUNCTION)) {
            getFunction().forEach(function -> function.remove(box));

            if (getFunctionSize() > 0)
                getFunction().forEach(function -> function.get(getFunctionSize() - 1).setIsTouchable(true));
        }

        // back to start
        box.addResetPositionAction();
    }

    @Override
    public Box removeFromProgram() {
        Box box = getProgram().remove(0);
        removeFunctionMethod(box);

        return box;
    }

    @Override
    public Box removeFromFunction() {
        return getFunction().get(numberOfFunctions() - 1).remove(0);
    }
}
