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
            box.stackPosition = getProgramInitial();
        else
            box.stackPosition = this.getProgram().peek().stackPosition + 1;

        box.stackType = 1;
        this.getProgram().push(box);

        box.setPushedIdle();
    }

    @Override
    public void addToFunction(Box box) {
        if (isFunctionEmpty())
            box.stackPosition = getProgramInitial();
        else
            box.stackPosition = this.getFunction().peek().stackPosition + 1;

        box.stackType = 2;
        this.getFunction().push(box);

        box.setPushedIdle();
    }

    @Override
    public Box removeFromProgram() {
        Box box = this.getProgram().pop();
        box.stackPosition = null;
        box.stackType = null;

        box.setRandomIdle();

        return box;
    }

    @Override
    public Box removeFromFunction() {
        Box box = this.getFunction().pop();
        box.stackPosition = null;
        box.stackType = null;

        box.setRandomIdle();

        return box;
    }
}
