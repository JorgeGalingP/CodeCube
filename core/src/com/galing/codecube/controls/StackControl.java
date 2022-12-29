package com.galing.codecube.controls;

import com.badlogic.gdx.utils.Array;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Control;

import java.util.Stack;

public class StackControl extends GameControl<Stack<Box>> {

    public StackControl(Button programButton, Button functionButton, Array<Control> programControls,
                        Array<Control> functionControls) {
        super(programButton, functionButton, programControls, functionControls);
        setProgram(new Stack<>());
        setFunction(new Stack<>());
    }

    @Override
    public Box getNextBox() {
        return this.getProgram().peek();
    }

    @Override
    public void addToProgram(Box box) {
        if (isProgramEmpty())
            box.setControlPosition(0);
        else
            box.setControlPosition(getProgram().peek().getControlPosition() + 1);

        getProgram().push(box);
    }

    @Override
    public void addToFunction(Box box) {
        if (isFunctionEmpty())
            box.setControlPosition(0);
        else
            box.setControlPosition(getFunction().peek().getControlPosition() + 1);

        getFunction().push(box);
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
