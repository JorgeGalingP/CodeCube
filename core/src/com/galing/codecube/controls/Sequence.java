package com.galing.codecube.controls;

import com.badlogic.gdx.utils.Array;
import com.galing.codecube.enums.ContainerType;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Container;

import java.util.List;
import java.util.Stack;

public class Sequence extends Control<List<Box>> {
    public Sequence(Button programButton, Button functionButton, Array<Container> programControls,
                    Array<Container> functionControls) {
        super(programButton, functionButton, programControls, functionControls);
        setProgram(new Stack<>());
        setFunction(new Stack<>());
    }

    @Override
    public Box getNextBox() {
        return this.getProgram().get(0);
    }

    @Override
    public void addToProgram(Box box) {
        if (!isProgramEmpty())
            for (Box programBox : this.getProgram())
                programBox.setNext(false);

        box.setNext(true);
        getProgram().add(box);
    }

    @Override
    public void addToFunction(Box box) {
        if (!isFunctionEmpty())
            for (Box programBox : this.getFunction())
                programBox.setNext(false);

        box.setNext(true);
        getFunction().add(box);
    }


    @Override
    public void remove(Box box) {
        if (box.getControlType().equals(ContainerType.PROGRAM)) {
            getProgram().remove(box);
            if (getProgramSize() > 0)
                getProgram().get(getProgramSize() - 1).setNext(true);
        } else if (box.getControlType().equals(ContainerType.FUNCTION)) {
            getFunction().remove(box);
            if (getFunctionSize() > 0)
                getFunction().get(getFunctionSize() - 1).setNext(true);
        }
    }

    @Override
    public Box removeFromProgram() {
        return getProgram().remove(0);
    }

    @Override
    public Box removeFromFunction() {
        return getFunction().remove(0);
    }
}
