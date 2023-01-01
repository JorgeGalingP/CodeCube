package com.galing.codecube.controls;

import com.badlogic.gdx.utils.Array;
import com.galing.codecube.enums.ContainerType;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Container;

public class Stack extends Control<java.util.Stack<Box>> {

    public Stack(Button programButton, Button functionButton, Array<Container> programControls,
                 Array<Container> functionControls) {
        super(programButton, functionButton, programControls, functionControls);
        setProgram(new java.util.Stack<>());
        setFunction(new java.util.Stack<>());
    }

    @Override
    public Box getNextBox() {
        return this.getProgram().peek();
    }

    @Override
    public void addToProgram(Box box) {
        if (!isProgramEmpty())
            for (Box programBox : this.getProgram())
                programBox.setNext(false);

        box.setNext(true);
        getProgram().push(box);
    }

    @Override
    public void addToFunction(Box box) {
        if (!isFunctionEmpty())
            for (Box programBox : this.getFunction())
                programBox.setNext(false);

        box.setNext(true);
        getFunction().push(box);
    }

    @Override
    public void remove(Box box) {
        if (box.getControlType().equals(ContainerType.PROGRAM)) {
            getProgram().pop();
            if (getProgramSize() > 0)
                getProgram().get(getProgramSize() - 1).setNext(true);
        } else if (box.getControlType().equals(ContainerType.FUNCTION)) {
            getFunction().pop();
            if (getFunctionSize() > 0)
                getFunction().get(getFunctionSize() - 1).setNext(true);
        }
    }

    @Override
    public Box removeFromProgram() {
        return getProgram().pop();
    }

    @Override
    public Box removeFromFunction() {
        return getFunction().pop();
    }
}
