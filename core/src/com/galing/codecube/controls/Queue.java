package com.galing.codecube.controls;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.galing.codecube.enums.BoxType;
import com.galing.codecube.enums.ContainerType;
import com.galing.codecube.objects.Box;
import com.galing.codecube.objects.Button;
import com.galing.codecube.objects.Container;

import java.util.ArrayDeque;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Queue extends Control<ArrayDeque<Box>> {
    public Queue(Button programButton, Button functionButton, Array<Container> programControls,
                 Array<Container> functionControls, int numberOfFunctionTiles) {
        super(programButton, functionButton, programControls, functionControls, numberOfFunctionTiles);
        setProgram(new ArrayDeque<>());
        setFunction(IntStream.range(0, numberOfFunctionTiles)
                .mapToObj(i -> new ArrayDeque<Box>())
                .collect(Collectors.toList()));
    }

    @Override
    public Box getNextBox() {
        return this.getProgram().getFirst();
    }

    @Override
    public void addToProgram(Box box) {
        if (!isProgramEmpty())
            for (Box programBox : this.getProgram())
                programBox.setIsTouchable(false);

        box.setIsTouchable(true);
        getProgram().addLast(box);

        box.setControlType(ContainerType.PROGRAM);
        box.setPushedIdle();

        Vector2 newPosition =
                getProgramControls().get(getProgramControls().size - getProgramSize()).getCoordinate();
        box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));
    }

    @Override
    public void addToFunction(Box box) {
        if (!isFunctionEmpty())
            getFunction().forEach(f -> f.forEach(b -> b.setIsTouchable(false)));

        box.setIsTouchable(true);
        getFunction().forEach(f -> f.addLast(box));

        box.setControlType(ContainerType.FUNCTION);
        box.setPushedIdle();

        Vector2 newPosition =
                getFunctionControls().get(getFunctionControls().size - getFunctionSize()).getCoordinate();
        box.addAction(Actions.sequence(Actions.moveTo(newPosition.x, newPosition.y, 0.15f)));
    }

    @Override
    public void remove(Box box) {
        if (box.getControlType().equals(ContainerType.PROGRAM)) {
            getProgram().remove(box);
            if (getProgramSize() > 0)
                getProgram().getLast().setIsTouchable(true);
        } else if (box.getControlType().equals(ContainerType.FUNCTION)) {
            getFunction().forEach(function -> function.remove(box));

            if (getFunctionSize() > 0)
                getFunction().get(0).getLast().setIsTouchable(true);
        }

        // back to start
        box.clearControl();
        box.addResetPositionAction();
    }

    @Override
    public Box removeFromProgram() {
        Box box = getProgram().removeFirst();
        if (getProgramSize() > 0)
            getProgram().forEach(x -> x.addAction(moveTo(x.getX() + 1, x.getY(), .3f)));

        return box;
    }

    @Override
    public Box removeFromFunction() {
        int count =
                (int) this.getProgram().stream().filter(box -> box.getType().equals(BoxType.FUNCTION)).count();
        Box box = getFunction().get(count - 1).removeFirst();

        if (getFunctionSize() > 0 && !hasSeveralFunctions())
            getFunction().forEach(f -> f.forEach(b -> b.addAction(moveTo(b.getX() + 1, b.getY(), .3f))));

        return box;
    }
}