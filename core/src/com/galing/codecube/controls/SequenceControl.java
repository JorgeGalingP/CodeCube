package com.galing.codecube.controls;

import com.badlogic.gdx.utils.Array;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Control;

import java.util.List;
import java.util.Stack;

public class SequenceControl extends GameControl<List<Box>> {
    public SequenceControl(Button programButton, Button functionButton, Array<Control> programControls,
                           Array<Control> functionControls) {
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
        if (isProgramEmpty())
            box.setControlPosition(0);
        else
            box.setControlPosition(getProgram().get(getProgram().size() - 1).getControlPosition() + 1);

        getProgram().add(box);
    }

    @Override
    public void addToFunction(Box box) {
        if (isFunctionEmpty())
            box.setControlPosition(0);
        else
            box.setControlPosition(getFunction().get(getFunction().size() - 1).getControlPosition() + 1);

        getFunction().add(box);
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
