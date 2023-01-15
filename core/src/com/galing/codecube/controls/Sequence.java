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
        setFunction1(new ArrayList<>());
        setFunction2(new ArrayList<>());
    }

    @Override
    public Box getNextBox() {
        return this.getProgram().get(0);
    }

    @Override
    public void addToProgram(Box box) {
        if (!isProgramEmpty())
            for (Box programBox : this.getProgram())
                programBox.setIsTouchable(false);

        box.setIsTouchable(true);
        getProgram().add(box);

        box.setControlType(ContainerType.PROGRAM);
        box.setPushedIdle();

        Vector2 newPosition = getProgramControls().get(getProgramSize() - 1).getCoordinate();
        box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));
    }

    @Override
    public void addToFunction(Box box) {
        if (!isFunctionEmpty())
            this.getFunction().forEach(b -> box.setIsTouchable(false));

        box.setIsTouchable(true);
        getFunction1().add(box);
        getFunction2().add(box);

        box.setControlType(ContainerType.FUNCTION);
        box.setPushedIdle();

        Vector2 newPosition = getFunctionControls().get(getFunctionSize() - 1).getCoordinate();
        box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));
    }


    @Override
    public void remove(Box box) {
        if (box.getControlType().equals(ContainerType.PROGRAM)) {
            getProgram().remove(box);
            if (getProgramSize() > 0)
                getProgram().get(getProgramSize() - 1).setIsTouchable(true);
        } else if (box.getControlType().equals(ContainerType.FUNCTION)) {
            getFunction1().remove(box);
            getFunction2().remove(box);

            if (getFunctionSize() > 0)
                getFunction().get(getFunctionSize() - 1).setIsTouchable(true);
        }

        // back to start
        box.clearControl();
        box.addResetPositionAction();
    }

    @Override
    public Box removeFromProgram() {
        return getProgram().remove(0);
    }

    @Override
    public Box removeFromFunction() {
        int c = (int) this.getProgram().stream().filter(box -> box.getType().equals(BoxType.FUNCTION)).count();
        if (c == 2)
            return getFunction2().remove(0);
        else {
            return getFunction1().remove(0);
        }
    }

    @Override
    public boolean isFunctionEmpty() {
        int c = (int) this.getProgram().stream().filter(box -> box.getType().equals(BoxType.FUNCTION)).count();
        if (c == 2)
            return getFunction2().isEmpty();
        else {
            return getFunction1().isEmpty();
        }
    }
}
